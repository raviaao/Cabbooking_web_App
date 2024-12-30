package com.ravitiwari.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codewithravi.Request.RideRequest;
import com.codewithravi.doman.RideStatus;
import com.codewithravi.entity.Driver;
import com.codewithravi.entity.Ride;
import com.codewithravi.entity.User;
import com.codewithravi.reposetory.DriverRepository;
import com.codewithravi.reposetory.RideRepository;
import com.raviwithravi.Exceptions.DriverException;
import com.raviwithravi.Exceptions.RideException;



@Service
public class RideServiceImpl implements RideService {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private RideRepository rideRepository;
	
	@Autowired
	private Calculaters calculaters;
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Ride requestRide(RideRequest Riderequest, User user) throws DriverException {
		double picupLatitude=Riderequest.getPickupLatitude();
		double picupLongitude=Riderequest.getPickupLongitude();
		double destinationLatitude=Riderequest.getDestinationLatitude();
		double destinationLongitude=Riderequest.getDestinationLongitute();
		String pickupArea=Riderequest.getPickupArea();
		String destinationArea=Riderequest.getDestinationArea();
		
		Ride existingDriver=new Ride();
		
		List<Driver> avilableDrivers=driverService.getAvailableDriver(picupLatitude, picupLongitude, existingDriver);
		
		Driver nearestDriver=driverService.findNearestDriver(avilableDrivers, picupLatitude, picupLongitude);
		
		if(nearestDriver==null)
		{
			throw new DriverException("Driver not avilable");
			
		}
		
		
		System.out.println("duration-----befor ride");
		
		Ride ride = createRideRequest(user,nearestDriver,
				picupLatitude,picupLongitude,destinationLatitude,destinationLongitude,
				pickupArea, destinationArea);
		
		return ride;
	}

	@Override
	public Ride createRideRequest(User user, Driver nearestDriver, double picupLatitude, double picupLongitude,
			double destinationLatitude, double destinationLongitude, String picupArea, String destinationArea) {
		
		Ride ride=new Ride();
		ride.setDriver(nearestDriver);
		ride.setUser(user);
		ride.setPickupLtitude(picupLatitude);
		ride.setPickupLongitute(picupLongitude);
		ride.setDestinationLatitude(destinationLatitude);
		ride.setDestinationLongitude(destinationLongitude);
		ride.setStatus(RideStatus.REQUESTED);
		ride.setPickupArea(picupArea);
		ride.setDestinationArea(destinationArea);
		
		return rideRepository.save(ride);
		
		
	}

	@Override
	public void acceptRide(Integer rideId) throws RideException {
		Ride ride=findRideById(rideId);
		
		ride.setStatus(RideStatus.ACCEPTED);
		
		Driver driver=ride.getDriver();
		
		driver.setCurrentRide(ride);
		
		Random random=new Random();
		
		int otp = random.nextInt(9000) + 1000;
		
		ride.setOtp(otp);
		
		driverRepository.save(driver);
		
		rideRepository.save(ride);
		
		
	}

	@Override
	public void declineRide(Integer rideId, Integer driverId) throws RideException {
		Ride ride=findRideById(rideId);
		System.out.println(ride.getId());
		
		ride.getDeclinedDrivers().add(driverId);
		
		System.out.println(ride.getId()+"-"+ride.getDeclinedDrivers());
		
		List<Driver> availableDrivers=driverService.getAvailableDriver(ride.getPickupLtitude(),
		
		ride.getPickupLongitute(),ride);
		
		Driver nearestDriver=driverService.findNearestDriver(availableDrivers, ride.getPickupLongitute(),
				ride.getPickupLongitute());
		
		ride.setDriver(nearestDriver);
		
		rideRepository.save(ride);
		
		
	}

	@Override
	public void startRide(Integer rideId, int otp) throws RideException {
		Ride ride=findRideById(rideId);
		
		if(otp!=ride.getOtp())
		{
			throw new RideException("please provide a valid otp");
		}
		
		ride.setStatus(RideStatus.STARTED);
		ride.setStartTime(LocalDateTime.now());
		rideRepository.save(ride);
		
	}

	@Override
	public void completedRide(Integer rideId) throws RideException {
		Ride ride=findRideById(rideId);
		
		ride.setStatus(RideStatus.COMPLETED);
		ride.setEndTime(LocalDateTime.now());
		
		double distance = calculaters.calculateDistance(ride.getDestinationLatitude(),ride.getDestinationLongitude(),ride.getPickupLtitude(), ride.getPickupLongitute());
		
		LocalDateTime start=ride.getStartTime();
		LocalDateTime end=ride.getEndTime();
		Duration duration=Duration.between(start,end);
		long milliSecond = duration.toMillis();
		
		System.out.println("duration --------"+ milliSecond);
		
		double fare=calculaters.calculateFare(distance);
		ride.setDistance(Math.round(distance * 100.0)/100.0);
		ride.setFare((int)Math.round(fare));
		ride.setDuration(milliSecond);
		ride.setEndTime(LocalDateTime.now());
		
		Driver driver=ride.getDriver();
		driver.getRides().add(ride);
		driver.setCurrentRide(null);
		
		Integer driversRevenue=(int)(driver.getTotalRevenue()+Math.round(fare*0.8));
		driver.setTotalRevenue(driversRevenue);
		
		System.out.println("driver revenue--"+driversRevenue);
		
		driverRepository.save(driver);
		rideRepository.save(ride);
		
	}

	@Override
	public void canceleRide(Integer rideId) throws RideException {
		Ride ride=findRideById(rideId);
		ride.setStatus(RideStatus.CANCELLED);
		rideRepository.save(ride);
		
	}

	@Override
	public Ride findRideById(Integer rideId) throws RideException {
		Optional<Ride> ride=rideRepository.findById(rideId);
		
		if(ride.isPresent()) {
			return ride.get();
		}
		
		throw new RideException("ride not exist with Id"+ rideId);
		
	}
	
	

}

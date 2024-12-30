package com.ravitiwari.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithravi.Request.DriverSingnupRequest;
import com.codewithravi.config.JwtTokenprovider;
import com.codewithravi.doman.RideStatus;
import com.codewithravi.doman.UserRole;
import com.codewithravi.entity.Driver;
import com.codewithravi.entity.License;
import com.codewithravi.entity.Ride;
import com.codewithravi.entity.Vahical;
import com.codewithravi.reposetory.DriverRepository;
import com.codewithravi.reposetory.LicenseRepository;
import com.codewithravi.reposetory.RideRepository;
import com.codewithravi.reposetory.VehicalRepository;
import com.raviwithravi.Exceptions.DriverException;


@Service
public class DriverServiceImpl implements DriverService{
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private Calculaters distenceCalculator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenprovider jwtprovider;
	
	@Autowired
	private VehicalRepository vehicalRepository;
	
	@Autowired
	private LicenseRepository licenseRepository;
	
	@Autowired
	private RideRepository RideRepository;

	@Override
	public Driver registerDrivers(DriverSingnupRequest driverSingupRequest) {
		License license=driverSingupRequest.getLicense();
		Vahical Vehical=driverSingupRequest.getVehical();
		
		License  createdLicense=new License();
		
		createdLicense.setLicenseState(license.getLicenseState());
		createdLicense.setLicenseNumber(license.getLicenseNumber());
		createdLicense.setLicenseExpirationDate(license.getLicenseExpirationDate());
		createdLicense.setId(license.getId());
		
		License savedLicense=licenseRepository.save(createdLicense);
		
		Vahical createdVehical=new Vahical();
		
		createdVehical.setCapicity(Vehical.getCapicity());
		createdVehical.setColor(Vehical.getColor());
		createdVehical.setId(Vehical.getId());
		createdVehical.setLicensePlate(Vehical.getLicensePlate());
		createdVehical.setMake(Vehical.getMake());
		createdVehical.setModel(Vehical.getModel());
		createdVehical.setYear(Vehical.getYear());
		
		Vahical savedVehical = vehicalRepository.save(createdVehical);
		
		Driver driver=new Driver();
		
		String encodedpassword= passwordEncoder.encode(driverSingupRequest.getPassword());
		
		driver.setEmail(driverSingupRequest.getEmail());
		driver.setName(driverSingupRequest.getName());
		driver.setMobail(driverSingupRequest.getMobail());
		driver.setPassword(encodedpassword);
		driver.setLicense(savedLicense);
		driver.setRole(UserRole.DRIVER);
		
		driver.setLatitude(driverSingupRequest.getLatitude());
		driver.setLongitude(driverSingupRequest.getLongitude());
		
		Driver createdDriver = driverRepository.save(driver);
		
		savedLicense.setDriver(createdDriver);
		savedVehical.setDriver(createdDriver);
		
		licenseRepository.save(savedLicense);
		vehicalRepository.save(savedVehical);
		
		
		return createdDriver;
	}

	@Override
	public List<Driver> getAvailableDriver(double pickupLatitude, double pickupLongitude, Ride ride) {
		 List<Driver> allDrivers=driverRepository.findAll();
		 
		 List<Driver> avilableDriver=new ArrayList<>();
		 
		 for(Driver driver:allDrivers) {
			 
			 if(driver.getCurrentRide()!=null && driver.getCurrentRide().getStatus()!=RideStatus.COMPLETED) {
				 
				 continue; 
			 }
			 
			 if(ride.getDeclinedDrivers().contains(driver.getId())) {
				 System.out.println("its Continue");
				 continue;
			 }
			 
			 double driverLatitude=driver.getLatitude();
			 double driverLongitude=driver.getLongitude();
			 
			 double distance=distenceCalculator.calculateDistance(driverLatitude, driverLongitude, pickupLatitude, pickupLongitude);
			 
			 avilableDriver.add(driver);
		 }
		return null;
	}

	@Override
	public Driver findNearestDriver(List<Driver> availableDrivers, double picupLatitude, double picupLongitude) {
		double min=Double.MAX_VALUE;
		Driver nearestDriver = null;
		
		
		for(Driver driver:availableDrivers)
		{
			double driverLatitude=driver.getLatitude();
			double driverLongitude=driver.getLongitude();
			
			double distance=distenceCalculator.calculateDistance(picupLatitude, picupLongitude, driverLatitude, driverLongitude);
			
			if(min>distance) {
				min=distance;
				nearestDriver=driver;
			}
		}
		
		
		return nearestDriver;
	}

	@Override
	public Driver getReqDriverProfile(String jwt) throws DriverException {
		String email=jwtprovider.getEmailFormToken(jwt);
		Driver driver=driverRepository.findByEmail(email);
		
		if(driver==null)
		{
			throw new DriverException("driver not exist with email" + email);
		}
		return driver;
	}

	@Override
	public Ride getDriversCurrentRide(Integer driverId) throws DriverException {
		Driver driver=findDriverById(driverId);
		return driver.getCurrentRide();
	}

	@Override
	public List<Ride> getAllocatedRides(Integer driverId) throws DriverException {
		List<Ride> allocatedRides=driverRepository.getAllocatedRides(driverId);
		return allocatedRides;
	}

	@Override
	public Driver findDriverById(Integer driverId) throws DriverException {
		Optional<Driver> opt=driverRepository.findById(driverId);
		if(opt.isPresent()) {
		
			return opt.get();

	}
		throw new DriverException("driver not exist with Id " +driverId);

	}	
	@Override
	public List<Ride> completedRides(Integer driverId) throws DriverException {
		List<Ride> Rides=driverRepository.getCompletedRides(driverId);
		return Rides;
	}


	
}

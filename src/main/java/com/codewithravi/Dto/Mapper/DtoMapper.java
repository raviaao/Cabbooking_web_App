package com.codewithravi.Dto.Mapper;

import com.codewithravi.Dto.DriverDTO;
import com.codewithravi.Dto.RideDto;
import com.codewithravi.Dto.UserDTO;
import com.codewithravi.entity.Driver;
import com.codewithravi.entity.Ride;
import com.codewithravi.entity.User;

public class DtoMapper {
	
	public static DriverDTO toDriverDto(Driver driver) {
		
		DriverDTO driverDto=new DriverDTO();
		
		driverDto.setEmail(driver.getEmail());
		driverDto.setId(driver.getId());
		driverDto.setLatitude(driver.getLatitude());
		driverDto.setLongitude(driver.getLongitude());
		driverDto.setMobail(driver.getMobail());
		driverDto.setName(driver.getName());
		driverDto.setRating(driver.getRating());
		driverDto.setRole(driver.getRole());
		driverDto.setVehical(driver.getVahical());
		
		return driverDto;
	}
	
	public static UserDTO toUserDto(User user) {
		
		UserDTO userDto=new UserDTO();
		
		userDto.setId(user.getId());
		userDto.setName(user.getFirstName());
		userDto.setEmail(user.getEmail());
		userDto.setMobail(user.getMobail());
		
		return userDto;
		
		
	}
	
	public static RideDto toRideDto(Ride ride) {
		
		DriverDTO driverDTO=toDriverDto(ride.getDriver());
		UserDTO userDto=toUserDto(ride.getUser());
		
		RideDto RideDto=new RideDto();
		RideDto.setId(ride.getId());
		RideDto.setDriver(driverDTO);
		RideDto.setDestinationArea(ride.getDestinationArea());
		RideDto.setDestinationLatitude(ride.getDestinationLatitude());
		RideDto.setDestinationLongitude(ride.getDestinationLongitude());
		RideDto.setDistance(ride.getDistance());
		RideDto.setDuration(ride.getDuration());
		RideDto.setStartTime(ride.getStartTime());
		RideDto.setEndTime(ride.getEndTime());
		RideDto.setFare(ride.getFare());
		RideDto.setUser(userDto);
		RideDto.setOtp(ride.getOtp());
		RideDto.setPamentDetail(ride.getPaymentDetail());
		RideDto.setPickupArea(ride.getPickupArea());
		RideDto.setPickupLatitude(ride.getPickupLtitude());
		RideDto.setPickupLongitude(ride.getPickupLongitute());
		RideDto.setStatus(ride.getStatus());
		
		return RideDto;
			
	}
	
	
	
	

}

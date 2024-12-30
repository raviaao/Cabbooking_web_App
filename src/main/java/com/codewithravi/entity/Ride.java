package com.codewithravi.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.codewithravi.doman.RideStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Driver driver;
	
	@JsonIgnore
	private List<Integer> declinedDrivers = new ArrayList<>();
	
	private double pickupLtitude;
	
	private double pickupLongitute;
	
	private double destinationLatitude;
	
	private double destinationLongitude;
	
	private String PickupArea;
	
	private String destinationArea;
	
	private double distance;
	
	private long duration;
	
	private RideStatus status;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private double fare;
	
	private int otp;
	
	@Embedded
	private PaymentDetail paymentDetail = new PaymentDetail();

	public Ride() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public List<Integer> getDeclinedDrivers() {
		return declinedDrivers;
	}

	public void setDeclinedDrivers(List<Integer> declinedDrivers) {
		this.declinedDrivers = declinedDrivers;
	}

	public double getPickupLtitude() {
		return pickupLtitude;
	}

	public void setPickupLtitude(double pickupLtitude) {
		this.pickupLtitude = pickupLtitude;
	}

	public double getPickupLongitute() {
		return pickupLongitute;
	}

	public void setPickupLongitute(double pickupLongitute) {
		this.pickupLongitute = pickupLongitute;
	}

	public double getDestinationLatitude() {
		return destinationLatitude;
	}

	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public double getDestinationLongitude() {
		return destinationLongitude;
	}

	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public String getPickupArea() {
		return PickupArea;
	}

	public void setPickupArea(String pickupArea) {
		PickupArea = pickupArea;
	}

	public String getDestinationArea() {
		return destinationArea;
	}

	public void setDestinationArea(String destinationArea) {
		this.destinationArea = destinationArea;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus status) {
		this.status = status;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public PaymentDetail getPaymentDetail() {
		return paymentDetail;
	}

	public void setPaymentDetail(PaymentDetail paymentDetail) {
		this.paymentDetail = paymentDetail;
	}
	
	
	
	
	
	
}

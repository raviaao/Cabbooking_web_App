package com.codewithravi.entity;

import com.codewithravi.doman.PaymentStatus;

public class PaymentDetail {
	
	private PaymentStatus paymentStatus;
	
	private String paymentId;
	
	private String razorpayPaymentLinkId;
	
	private String razorpayPaymentLinkReferenceId;
	
	private String razorpayPaymentLinkStatus;
	
	private String razorpayPayment;

	public PaymentDetail() {
		// TODO Auto-generated constructor stub
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}

	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}

	public String getRazorpayPaymentLinkReferenceId() {
		return razorpayPaymentLinkReferenceId;
	}

	public void setRazorpayPaymentLinkReferenceId(String razorpayPaymentLinkReferenceId) {
		this.razorpayPaymentLinkReferenceId = razorpayPaymentLinkReferenceId;
	}

	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}

	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}

	public String getRazorpayPayment() {
		return razorpayPayment;
	}

	public void setRazorpayPayment(String razorpayPayment) {
		this.razorpayPayment = razorpayPayment;
	}
	
	
	
	

}

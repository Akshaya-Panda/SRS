package com.example.demo.bean;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "srs_tbl_reservation")
public class ReservationBean {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(generator = "system-uuid")
//	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private int reservationId;

//	@OneToOne
//	@JoinColumn(name = "scheduleId")
	@ManyToOne
	@JoinColumn(name="shipId",referencedColumnName = "shipId",insertable =
			false
			,updatable =
			false)
	private ScheduleBean scheduleBean;
	@Column
	private String scheduleId;
//	@OneToOne
//	@JoinColumn(name = "userId")
//	private CredentialsBean credentialsBean;
	@Column
	private String userId;
	@Column
	private String bookingStatus;
	@Column
	private String bookingDate;
	@Column
	private String journeyDate;
	@Column
	private int noOfSeats;
	@Column
	private double totalFare;

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public ScheduleBean getScheduleBean() {
		return scheduleBean;
	}

	public void setScheduleBean(ScheduleBean scheduleBean) {
		this.scheduleBean = scheduleBean;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

//	public CredentialsBean getCredentialsBean() {
//		return credentialsBean;
//	}
//
//	public void setCredentialsBean(CredentialsBean credentialsBean) {
//		this.credentialsBean = credentialsBean;
//	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public double getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}

	@Override
	public String toString() {
		return "ReservationBean [reservationId=" + reservationId + ", scheduleBean=" + scheduleBean + ", scheduleId="
				+ scheduleId + ", userId=" + userId + ", bookingStatus=" + bookingStatus + ", bookingDate="
				+ bookingDate + ", journeyDate=" + journeyDate + ", noOfSeats=" + noOfSeats + ", totalFare=" + totalFare
				+ "]";
	}

}

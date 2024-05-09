package com.example.demo.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="srs_tbl_user_passenger")
public class PassengerBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pId;
	
	@ManyToOne
	@JoinColumn(name="reservationId")
	private ReservationBean reservationBean;
	
	@ManyToOne
	@JoinColumn(name="scheduleId")
	private ScheduleBean scheduleBean;
	@Transient
	private String scheduleId;
	@Column
	private String name;
	@Column
	private String gender;
	@Column
	private int age;
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public ReservationBean getReservationBean() {
		return reservationBean;
	}
	public void setReservationBean(ReservationBean reservationBean) {
		this.reservationBean = reservationBean;
	}
	public ScheduleBean getScheduleBean() {
		return scheduleBean;
	}
	public void setScheduleBean(ScheduleBean scheduleBean) {
		this.scheduleBean = scheduleBean;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	@Override
	public String toString() {
		return "PassengerBean [pId=" + pId + ", reservationBean=" + reservationBean + ", scheduleBean=" + scheduleBean
				+ ", scheduleId=" + scheduleId + ", name=" + name + ", gender=" + gender + ", age=" + age + ", seatNo="
				+  "]";
	}
		
}

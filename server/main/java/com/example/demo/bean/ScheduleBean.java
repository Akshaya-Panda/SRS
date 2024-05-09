package com.example.demo.bean;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

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
@Table(name="srs_tbl_schedule")
public class ScheduleBean {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String scheduleId;
	
	@ManyToOne
	@JoinColumn(name="shipId",referencedColumnName = "shipId",insertable =
			false
			,updatable =
			false)
	private ShipBean shipBean;
//	@OneToOne
//	@JoinColumn(name="shipId")
//	private ShipBean shipBean;
	@Column
	private String shipId;
	
//	@OneToOne
//	@JoinColumn(name="routeId")
	@ManyToOne
	@JoinColumn(name="routeId",referencedColumnName = "routeId",insertable =
			false
			,updatable =
			false)
	private RouteBean routeBean;
	@Column
	private String routeId;
	
	@Column
	private String startDate;
	@Column
	private String availableDays;
	@Column
	private String departureTime;

	public String getScheduleId() {
		return scheduleId;
	}


	public String getAvailableDays() {
		return availableDays;
	}

	public void setAvailableDays(String availableDays) {
		this.availableDays = availableDays;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public ShipBean getShipBean() {
		return shipBean;
	}

	public void setShipBean(ShipBean shipBean) {
		this.shipBean = shipBean;
	}

	public RouteBean getRouteBean() {
		return routeBean;
	}

	public void setRouteBean(RouteBean routeBean) {
		this.routeBean = routeBean;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	

	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	@Override
	public String toString() {
		return "ScheduleBean [scheduleId=" + scheduleId + ", shipBean=" + shipBean + ", routeBean=" + routeBean
				+ ", startDate=" + startDate + "]";
	}
	
}

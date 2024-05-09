package com.example.demo.bean;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="srs_tbl_route")
public class RouteBean {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String routeId;
	@Column
	private String source;
	@Column
	private String destination;
	@Column
	private String travelDuration;
	@Column
	private double fare;
	@Transient
	private String searchDate;
	
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getTravelDuration() {
		return travelDuration;
	}
	public void setTravelDuration(String travelDuration) {
		this.travelDuration = travelDuration;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	public String getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(String searchDate) {
		this.searchDate = searchDate;
	}
	@Override
	public String toString() {
		return "RouteBean [routeId=" + routeId + ", source=" + source + ", destination=" + destination
				+ ", travelDuration=" + travelDuration + ", fare=" + fare + "]";
	}
	
	
}

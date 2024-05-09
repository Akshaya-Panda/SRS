package com.example.demo.bean;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*;

@Entity
@Table(name="srs_tbl_ship")
public class ShipBean {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String shipid;
	@Column(name="shipname",length=20)
	private String shipname;
	@Column
	private int reservationcapacity;
	@Column
	private int seatingcapacity;
	
	public String getShipid() {
		return shipid;
	}
	public void setShipid(String shipid) {
		this.shipid = shipid;
	}
	public String getShipname() {
		return shipname;
	}
	public void setShipname(String shipname) {
		this.shipname = shipname;
	}
	public int getReservationcapacity() {
		return reservationcapacity;
	}
	public void setReservationcapacity(int reservationcapacity) {
		this.reservationcapacity = reservationcapacity;
	}
	public int getSeatingcapacity() {
		return seatingcapacity;
	}
	public void setSeatingcapacity(int seatingcapacity) {
		this.seatingcapacity = seatingcapacity;
	}
	@Override
	public String toString() {
		return "ShipBean [shipid=" + shipid + ", shipname=" + shipname + ", reservationcapacity=" + reservationcapacity
				+ ", seatingcapacity=" + seatingcapacity + "]";
	}
	
	
	
	
}

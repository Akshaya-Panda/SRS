package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.RouteBean;
import com.example.demo.bean.ScheduleBean;
import com.example.demo.bean.ShipBean;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	@Autowired
	private AdminService aserv;

	public AdminService getAserv() {
		return aserv;
	}

	public void setAserv(AdminService aserv) {
		this.aserv = aserv;
	}

	@PostMapping("/addschedule")
	public String addSchedule(@RequestBody ScheduleBean sb) {
		// get route by routeId
		RouteBean rb = aserv.getRouteById(sb.getRouteId());
		// get ship by shipId
		ShipBean shipBean = aserv.getShipById(sb.getShipId());
		// set the ship and route
		sb.setRouteBean(rb);
		sb.setShipBean(shipBean);
		System.out.println("Val of sb is:" + sb);
		return "<h1>" + aserv.addSchedule(sb) + " Record inserted successfuly</h1>";
	}

	@PutMapping("/updateschedule")
	public int updateSchedule(@RequestBody ScheduleBean sb) {
		// get route by routeId
		RouteBean rb = aserv.getRouteById(sb.getRouteId());
		// get ship by shipId
		ShipBean shipBean = aserv.getShipById(sb.getShipId());
		// set the ship and route
		sb.setRouteBean(rb);
		sb.setShipBean(shipBean);
		System.out.println("Val of sb is:" + sb);
		return aserv.updateSchedule(sb);
	}
	@DeleteMapping("/deleteschedule/{sid}")
	public int deleteSchedule(@PathVariable String sid) {
		return aserv.deleteSchedule(sid);
	}
	@PostMapping("/addroute")
	public String addRoute(@RequestBody RouteBean rb) {
		System.out.println("Val of Routebean is:" + rb);
		int result = aserv.addRoute(rb);
		System.out.println("Valof credentialBean is:" + result);
		return result + "";
	}

	@GetMapping("/getallroutes")
	public ArrayList<RouteBean> getAllRoutes() {
		return aserv.getAllRoutes();
	}

	@GetMapping("/getroute/{rid}")
	public RouteBean getRouteById(@PathVariable String rid) {
		return aserv.getRouteById(rid);
	}

	@PutMapping("/updateroute")
	public int updateRoute(@RequestBody RouteBean sb) {
		return aserv.updateRoute(sb);
	}
	@DeleteMapping("/deleteroute/{rid}")
	public int deleteRoute(@PathVariable String rid) {
		return aserv.deleteRoute(rid);
	}
	@PostMapping("/addship")
	public String addShip(@RequestBody ShipBean eb) {
		return "<h1>" + aserv.addShip(eb) + " Record inserted successfuly</h1>";
	}

	@GetMapping("/getallships")
	public ArrayList<ShipBean> getAllShips() {
		return aserv.getAllShips();
	}

	@GetMapping("/getship/{sid}")
	public ShipBean getShipById(@PathVariable String sid) {
		return aserv.getShipById(sid);
	}

	@PutMapping("/updateship")
	public int updateSgip(@RequestBody ShipBean sb) {
		return aserv.updateShip(sb);
	}
	@DeleteMapping("/deleteship/{sid}")
	public int deleteShip(@PathVariable String sid) {
		return aserv.deleteShip(sid);
	}
}

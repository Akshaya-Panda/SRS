package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.RouteBean;
import com.example.demo.bean.ScheduleBean;
import com.example.demo.bean.ShipBean;
import com.example.demo.dao.AdminDao;

@Service
public class AdminService {
	@Autowired
	private AdminDao adao;

	public AdminDao getAdao() {
		return adao;
	}

	public void setAdao(AdminDao adao) {
		this.adao = adao;
	}
	
	@Transactional
	public int addRoute(RouteBean rb) {
		return adao.addRoute(rb);
	}
	@Transactional
	public RouteBean getRouteById(String rid) {
		return adao.getRouteById(rid);
	}
	@Transactional
	public ArrayList<RouteBean> getAllRoutes(){
		return adao.getAllRoutes();
	}
	@Transactional 
	public int updateRoute(RouteBean sb) {
		return adao.updateRoute(sb);
	}
	@Transactional
	public int deleteRoute(String sid) {
		return adao.deleteRoute(sid);
	}
	@Transactional 
	public int addShip(ShipBean sb) {
		return adao.addShip(sb);
	}
	@Transactional
	public ShipBean getShipById(String sid) {
		return adao.getShipById(sid);
	}
	@Transactional
	public ArrayList<ShipBean> getAllShips(){
		return adao.getAllShips();
	}
	@Transactional 
	public int updateShip(ShipBean sb) {
		return adao.updateShip(sb);
	}
	@Transactional
	public int deleteShip(String sid) {
		return adao.deleteShip(sid);
	}
	@Transactional 
	public int addSchedule(ScheduleBean sb) {
		return adao.addSchedule(sb);
	}
	@Transactional 
	public int updateSchedule(ScheduleBean sb) {
		return adao.updateSchedule(sb);
	}
	@Transactional
	public int deleteSchedule(String sid) {
		return adao.deleteSchedule(sid);
	}
}

package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.CredentialsBean;
import com.example.demo.bean.RouteBean;
import com.example.demo.bean.ScheduleBean;
import com.example.demo.bean.ShipBean;

@Repository
public class AdminDao {
	@Autowired
	private SessionFactory sesssionFactory;

	public SessionFactory getSesssionFactory() {
		return sesssionFactory;
	}

	public void setSesssionFactory(SessionFactory sesssionFactory) {
		this.sesssionFactory = sesssionFactory;
	}
	public int addRoute(RouteBean rb) {
		if (rb != null) {
			System.out.println("Val of rb is"+rb);
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.save(rb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}
	public RouteBean getRouteById(String rid) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from RouteBean where routeId=:rrid");
		q.setParameter("rrid", rid);
		t.commit();
		return (RouteBean) q.getSingleResult();
	}
	
	public ArrayList<RouteBean> getAllRoutes() {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from RouteBean");
		List<RouteBean> l = q.list();
		t.commit();
		return (ArrayList<RouteBean>) l;
	}
	public int updateRoute(RouteBean eb) {
		if (eb != null) {
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.update(eb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}
	public int deleteRoute(String sid) {
		int i = 0;
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("delete from RouteBean where routeId=:ssid");
		q.setParameter("ssid", sid);
		i = q.executeUpdate();
		t.commit();
		return i;
	}

	public int addShip(ShipBean eb) {
		if (eb != null) {
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.save(eb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}
	
	public ShipBean getShipById(String sid) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from ShipBean where shipid=:ssid");
		q.setParameter("ssid", sid);
		t.commit();
		return (ShipBean) q.getSingleResult();
	}
	
	public ArrayList<ShipBean> getAllShips() {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from ShipBean");
		List<ShipBean> l = q.list();
		t.commit();
		return (ArrayList<ShipBean>) l;
	}
	public int updateShip(ShipBean eb) {
		if (eb != null) {
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.update(eb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}
	public int deleteShip(String sid) {
		int i = 0;
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("delete from ShipBean where shipid=:ssid");
		q.setParameter("ssid", sid);
		i = q.executeUpdate();
		t.commit();
		return i;
	}
	public int addSchedule(ScheduleBean eb) {
		if (eb != null) {
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.save(eb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}
	public int updateSchedule(ScheduleBean eb) {
		if (eb != null) {
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.update(eb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}
	public int deleteSchedule(String sid) {
		int i = 0;
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("delete from ScheduleBean where scheduleId=:ssid");
		q.setParameter("ssid", sid);
		i = q.executeUpdate();
		t.commit();
		return i;
	}

}

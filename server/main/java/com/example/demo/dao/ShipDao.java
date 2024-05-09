package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.ShipBean;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Repository
public class ShipDao {
	@Autowired
	private SessionFactory sesssionFactory;

	public SessionFactory getSesssionFactory() {
		return sesssionFactory;
	}

	public void setSesssionFactory(SessionFactory sesssionFactory) {
		this.sesssionFactory = sesssionFactory;
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

	public int deleteShip(int sid) {
		int i = 0;
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("delete from ShipBean where shipid=:ssid");
		q.setParameter("ssid", sid);
		i = q.executeUpdate();
		t.commit();
		return i;
	}

	public ArrayList<ShipBean> getAllShips() {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from ShipBean");
		List<ShipBean> l = q.list();
		return (ArrayList<ShipBean>) l;
	}

	public ShipBean getShipById(int sid) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from ShipBean where shipid=:ssid");
		q.setParameter("ssid", sid);
		return (ShipBean) q.getSingleResult();
	}

}

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
import com.example.demo.bean.ProfileBean;
import com.example.demo.bean.ShipBean;

@Repository
public class AuthDao {
	@Autowired
	private SessionFactory sesssionFactory;

	public SessionFactory getSesssionFactory() {
		return sesssionFactory;
	}

	public void setSesssionFactory(SessionFactory sesssionFactory) {
		this.sesssionFactory = sesssionFactory;
	}

	public int addUser(CredentialsBean cb) {
		if (cb != null) {
			System.out.println("Val of cb is"+cb);
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.save(cb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}

	public CredentialsBean getUser(String userId, String password) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from CredentialsBean where userId=:userId and password=:password");
		q.setParameter("userId", userId);
		q.setParameter("password", password);
		System.out.println("val of q is"+q.uniqueResult());
		CredentialsBean cb = (CredentialsBean) q.uniqueResult();
		return cb;
	}
	public int changeLoginStatus(int status, String userId) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("update CredentialsBean set loginStatus=:status where userId=:userId");
		q.setParameter("status", status);
		q.setParameter("userId", userId);
		int i = q.executeUpdate();
		t.commit();
		return i;
	}
	public int registerUser(ProfileBean pb) {
		if (pb != null) {
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.save(pb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}
//	public int updateShip(ShipBean eb) {
//		if (eb != null) {
//			Session s = sesssionFactory.openSession();
//			Transaction t = s.beginTransaction();
//			s.update(eb);
//			t.commit();
//			return 1;
//		} else {
//			return 0;
//		}
//	}

//	public int deleteShip(int sid) {
//		int i = 0;
//		Session s = sesssionFactory.openSession();
//		Transaction t = s.beginTransaction();
//		Query q = s.createQuery("delete from ShipBean where shipid=:ssid");
//		q.setParameter("ssid", sid);
//		i = q.executeUpdate();
//		t.commit();
//		return i;
//	}

//	public ArrayList<ShipBean> getAllShips() {
//		Session s = sesssionFactory.openSession();
//		Transaction t = s.beginTransaction();
//		Query q = s.createQuery("from ShipBean");
//		List<ShipBean> l = q.list();
//		return (ArrayList<ShipBean>) l;
//	}
//
//	public ShipBean getShipById(int sid) {
//		Session s = sesssionFactory.openSession();
//		Transaction t = s.beginTransaction();
//		Query q = s.createQuery("from ShipBean where shipid=:ssid");
//		q.setParameter("ssid", sid);
//		return (ShipBean) q.getSingleResult();
//	}

}

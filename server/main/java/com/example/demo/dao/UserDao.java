package com.example.demo.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.CreditCardBean;
import com.example.demo.bean.PassengerBean;
import com.example.demo.bean.ReservationBean;
import com.example.demo.bean.RouteBean;
import com.example.demo.bean.ScheduleBean;
import com.example.demo.bean.ShipBean;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sesssionFactory;

	public SessionFactory getSesssionFactory() {
		return sesssionFactory;
	}

	public void setSesssionFactory(SessionFactory sesssionFactory) {
		this.sesssionFactory = sesssionFactory;
	}

	public ArrayList<ScheduleBean> getScheduleByRoute(String source, String destination, String searchDate) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q2 = s.createQuery(
				"from ScheduleBean where routeBean in (select routeId from RouteBean where source=:source and destination=:destination) and startDate=:searchDate");
		q2.setParameter("source", source);
		q2.setParameter("destination", destination);
		q2.setParameter("searchDate", searchDate);
		List<ScheduleBean> l2 = q2.list();
		t.commit();
		System.out.println("Val of l2 is:" + l2);
		return (ArrayList<ScheduleBean>) l2;
	}

	public ScheduleBean findScheduleById(String sid) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from ScheduleBean where scheduleId=:ssid");
		q.setParameter("ssid", sid);
		t.commit();
		return (ScheduleBean) q.getSingleResult();
	}

	public RouteBean findRouteById(String rid) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from RouteBean where routeId=:rrid");
		q.setParameter("rrid", rid);
		t.commit();
		return (RouteBean) q.getSingleResult();
	}

	public int addCreditCard(CreditCardBean cb) {
		if (cb != null) {
			System.out.println("Val of creditCardBean is" + cb);
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.save(cb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}

	public CreditCardBean getCreditCardByUser(String rid) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from CreditCardBean where userId=:rrid");
		q.setParameter("rrid", rid);
		t.commit();
		return (CreditCardBean) q.getSingleResult();
	}

	public int updateBalance(CreditCardBean eb) {
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

	public int addReservation(ReservationBean eb) {
		if (eb != null) {
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.save(eb);
//			Serializable id = (Serializable) s.save(eb);
//			System.out.println("Val of id is:"+id);
			t.commit();
//			if (id != null && id instanceof Integer) {
//	            // Cast and return the generated ID
//	            return (Integer) id;
//	        } else {
//	            // Handle the case where the ID is not an instance of Integer
//	            return 0;
//	        }-
			return 1;
		} else {
			return 0;
		}
	}

//	public int addPassengers(ArrayList<PassengerBean> pbs) {
//		if (pbs != null) {
//			System.out.println("Val of passengerBean is"+pbs);
//			int i=0;
//			Session s = sesssionFactory.openSession();
//			Transaction t = s.beginTransaction();
//			for(PassengerBean pb : pbs) {
//				s.save(pb);		
//				i++;
//			}
//			t.commit();
//			return i;
//		} else {
//			return 0;
//		}
//	}
	public int addPassengers(PassengerBean pb) {
		if (pb != null) {
			System.out.println("Val of passengerBean is" + pb);
			Session s = sesssionFactory.openSession();
			Transaction t = s.beginTransaction();
			s.save(pb);
			t.commit();
			return 1;
		} else {
			return 0;
		}
	}
	public ReservationBean findReservationById(int rid) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();		
		Query q = s.createQuery("from ReservationBean where reservationId=:rrid");
		q.setParameter("rrid", rid);
		t.commit();
		return (ReservationBean) q.getSingleResult();
	}
	public ArrayList<PassengerBean> findPassengerById(int rid) {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from PassengerBean where reservationBean.reservationId=:rrid");
		q.setParameter("rrid", rid);
		t.commit();
		List<PassengerBean> l = q.list();
		return (ArrayList<PassengerBean>) l;
	}
	public int repayBalance(double totalFare) {
		if (totalFare != 0) {
			Session s = sesssionFactory.openSession();
			Transaction tx=s.beginTransaction();  
			int tf = (int)totalFare;
			System.out.println("totalFare is:"+tf);
			Query q=s.createQuery("update CreditCardBean set balance=:totalFare+balance where userId='US-003'");  
			q.setParameter("totalFare",tf);  
			int status=q.executeUpdate();  
			System.out.println(status);  
			tx.commit();
			return status;
		} else {
			return 0;
		}
	}
	public int deletePassengers(int rid) {
		int i = 0;
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("delete from PassengerBean where reservationBean.reservationId=:rrid");
		q.setParameter("rrid", rid);
		i = q.executeUpdate();
		t.commit();
		return i;
	}
	public int deleteReservation(int rid) {
		int i = 0;
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("delete from ReservationBean where reservationId=:rrid");
		q.setParameter("rrid", rid);
		i = q.executeUpdate();
		t.commit();
		return i;
	}
	public ArrayList<String> getAllSources(){
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("select source from RouteBean");
		t.commit();
		List<String> l = q.list();
		return (ArrayList<String>) l;
	}
	public ArrayList<String> getAllDestinations(){
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("select destination from RouteBean");
		t.commit();
		List<String> l = q.list();
		return (ArrayList<String>) l;
	}
	public ArrayList<ScheduleBean> getAllSchedule(){
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from ScheduleBean");
		t.commit();
		List<ScheduleBean> l = q.list();
		return (ArrayList<ScheduleBean>) l;
	}
	public int getBalance() {
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from CreditCardBean where userId='US-003'");
		t.commit();
		CreditCardBean cb = (CreditCardBean)q.getSingleResult();
		return cb.getBalance();
	}
	public ArrayList<ReservationBean> getAllReservation(){
		Session s = sesssionFactory.openSession();
		Transaction t = s.beginTransaction();
		Query q = s.createQuery("from ReservationBean");
		t.commit();
		List<ReservationBean> l = q.list();
		return (ArrayList<ReservationBean>) l;
	}

}

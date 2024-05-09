package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.CreditCardBean;
import com.example.demo.bean.PassengerBean;
import com.example.demo.bean.ReservationBean;
import com.example.demo.bean.RouteBean;
import com.example.demo.bean.ScheduleBean;
import com.example.demo.bean.ShipBean;
import com.example.demo.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao udao;

	public UserDao getAdao() {
		return udao;
	}

	public void setAdao(UserDao udao) {
		this.udao = udao;
	}

	@Transactional
	public ArrayList<ScheduleBean> getScheduleByRoute(String source, String destination, String searchDate) {
		return udao.getScheduleByRoute(source, destination, searchDate);
	}

	@Transactional
	public ScheduleBean findScheduleById(String sid) {
		return udao.findScheduleById(sid);
	}

	@Transactional
	public RouteBean findRouteById(String rid) {
		return udao.findRouteById(rid);
	}

	@Transactional
	public int createCreditCard(CreditCardBean cb) {
		return udao.addCreditCard(cb);
	}

	@Transactional
	public CreditCardBean getCreditCardByUser(String uid) {
		return udao.getCreditCardByUser(uid);
	}

	@Transactional
	public int updateBalance(CreditCardBean ccb) {
		return udao.updateBalance(ccb);
	}
	@Transactional
	public int addReservation(ReservationBean eb) {
		return udao.addReservation(eb);
	}
//	@Transactional
//	public int addPassengers(ArrayList<PassengerBean> pbs) {
//		return udao.addPassengers(pbs);
//	}
	@Transactional
	public int addPassengers(PassengerBean pbs) {
		return udao.addPassengers(pbs);
	}
	@Transactional
	public ReservationBean findReservationById(int sid) {
		return udao.findReservationById(sid);
	}
//	@Transactional
//	public ArrayList<PassengerBean> findPassengerById(ReservationBean rb){
//		return udao.findPassengerById(rb);
//	}
	@Transactional
	public int repayBalance(double totalFare) {
		return udao.repayBalance(totalFare);
	}
	@Transactional
	public int deletePassengers(int rid) {
		return udao.deletePassengers(rid);
	}
	@Transactional
	public int deleteReservation(int rid) {
		return udao.deleteReservation(rid);
	}
	@Transactional
	public ArrayList<String> getAllSources() {
		return udao.getAllSources();
	}
	@Transactional
	public ArrayList<String> getAllDestinations() {
		return udao.getAllDestinations();
	}
	@Transactional
	public ArrayList<ScheduleBean> getAllSchedule() {
		return udao.getAllSchedule();
	}
	@Transactional
	public int getBalance() {
		return udao.getBalance();
	}
	@Transactional
	public ArrayList<ReservationBean> getAllReservation() {
		return udao.getAllReservation();
	}
	@Transactional
	public ArrayList<PassengerBean> viewPassengerByReservationId(int rid) {
		return udao.findPassengerById(rid);
	}
	@Transactional
	public ReservationBean viewByReservationId(int rid) {
		return udao.findReservationById(rid);
	}
}

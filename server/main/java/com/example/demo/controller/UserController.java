package com.example.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.CreditCardBean;
import com.example.demo.bean.PassengerBean;
import com.example.demo.bean.ReservationBean;
import com.example.demo.bean.RouteBean;
import com.example.demo.bean.ScheduleBean;
import com.example.demo.bean.ShipBean;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	@Autowired
	private UserService userv;

	public UserService getAserv() {
		return userv;
	}

	public void setAserv(UserService userv) {
		this.userv = userv;
	}

	@PostMapping("/viewschedulebyroute")
	public ArrayList<ScheduleBean> viewScheduleByRoute(@RequestBody RouteBean rb) {
		System.out.println("Val of Routebean is:" + rb.getSearchDate());
		ArrayList<ScheduleBean> sb = userv.getScheduleByRoute(rb.getSource(), rb.getDestination(), rb.getSearchDate());// receive
																														// schedule
																														// Bean
		System.out.println("Val of scheduleBean is:" + sb);
		// find the route details from the route id obtained from sb
		// find the ship details from the ship id obtained from sb
		return sb ;
	}
	public static int generateRandomNum() {
		Random Random = new Random();
		int randomNumber = Random.nextInt(10000);
		return randomNumber;
	}
	@PostMapping("/reserveticket")
	public String reserveTicket(@RequestBody ObjectNode objectNode) {
		ObjectMapper objectMapper = new ObjectMapper();
		ReservationBean rb = objectMapper.convertValue(objectNode.get("reservationBean"), ReservationBean.class);
		ArrayList<PassengerBean> pbs = objectMapper.convertValue(objectNode.get("passengerBeanLists"),
				new TypeReference<ArrayList<PassengerBean>>() {
				});
		System.out.println("Val of rb is:"+rb);
		System.out.println("Val of pbs is:"+pbs);
		//get schedule Bean
		ScheduleBean sb = userv.findScheduleById(rb.getScheduleId());
		System.out.println("Valof sb before: "+sb);
		//journey Date
		String startDate = sb.getStartDate();
		//get current Date for booking date
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String currentDateAsString = currentDate.format(formatter);
		//setting the date
		rb.setBookingDate(currentDateAsString);
		rb.setJourneyDate(startDate);
		//check whether it try to book after the date
		//calculating the total fare
		int noOfPassengers = pbs.size();
		System.out.println("val of routeId"+sb.getRouteId()+"  "+sb.getRouteBean()+sb.getRouteBean().getRouteId());
		RouteBean routeBean = userv.findRouteById(sb.getRouteBean().getRouteId());
		System.out.println("Val of routeBean is:"+routeBean);
		double totalFare = noOfPassengers * routeBean.getFare();
		rb.setTotalFare(totalFare);
		//check whether have sufficient balance or not
		CreditCardBean ccb = getCreditCardByUser("US-003");
		int avlBalance = ccb.getBalance();
		if(avlBalance < totalFare) {
			return "Insufficient Balance:- Avl Balance"+avlBalance+" but toal fare is: "+totalFare;
		}
		//update the balance
		double remBal = avlBalance - totalFare;
		ccb.setBalance((int)remBal);
		int res = userv.updateBalance(ccb);//update in database
		if(res != 1) {
			return "Error in Update Balance";
		}
		//create reservation
		rb.setBookingStatus("Success");
		rb.setNoOfSeats(pbs.size());
		rb.setScheduleBean(sb);
		rb.setUserId("US-003");
		int rId = generateRandomNum()+generateRandomNum()+generateRandomNum();
		rb.setReservationId(rId);
		System.out.println("Val of reservationBean is:"+rb);
		res = userv.addReservation(rb);
		if(res != 1) {
			return "Error in adding Reservation";
		}
		//create passengers
		int noOfPass=0;
		for(PassengerBean pb:pbs) {
			pb.setScheduleBean(sb);
			//pb.setReservationBean(rb);
			pb.setReservationBean(rb);
			noOfPass += userv.addPassengers(pb);
			System.out.println("inside add passenger for loop"+noOfPass);
		}
//		int noOfPass = userv.addPassengers(pbs);
		if(noOfPass < 1) {
			return "Error in adding Passengers";
		}
		System.out.println("Total fare is:"+totalFare);
		System.out.println("Val of ReservationBean is:" + rb);
		System.out.println("Val of passengerBean list is:" + pbs);

		return "Successful Reservation";
	}
	
	public CreditCardBean getCreditCardByUser(String uid) {
		return userv.getCreditCardByUser(uid);
	}
	@PostMapping("/createcreditcard")
	public String createCreditCard(@RequestBody CreditCardBean cb) {
		return userv.createCreditCard(cb)+"";
	}
	@GetMapping("/viewticket/{rid}")
	public Map<ReservationBean, ArrayList<PassengerBean>>  viewTicketById(@PathVariable int rid) {

//	public Map<ReservationBean, ArrayList<PassengerBean>>  viewTicketById(@PathVariable int rid) {
		Map<ReservationBean,ArrayList<PassengerBean>> mp = new HashMap<>();
//    	ReservationBean rb = userv.findReservationById(rid);
//    	System.out.println("Val of rb is:"+rb);
//    	ArrayList<PassengerBean> al = userv.findPassengerById(rb);
//    	System.out.println("val of al is"+al);
//    	mp.put(rb,al);
//    	System.out.println("val of map mp is:"+mp);
    	return mp;
//    	return 1;
	}
	@GetMapping("/viewpassenger/{rid}")
	public ArrayList<PassengerBean>  viewPassengerByReservationId(@PathVariable int rid) {
//		Map<ReservationBean,ArrayList<PassengerBean>> mp = new HashMap<>();
    	ArrayList<PassengerBean> rb = userv.viewPassengerByReservationId(rid);
    	System.out.println("Val of rb is:"+rb);
//    	ArrayList<PassengerBean> al = userv.findPassengerById(rb);
//    	System.out.println("val of al is"+al);
//    	mp.put(rb,al);
//    	System.out.println("val of map mp is:"+mp);
//    	return mp;
    	return rb;
//    	return 1;
	}
	@GetMapping("/viewreservation/{rid}")
	public ReservationBean  viewReservationById(@PathVariable int rid) {
//		Map<ReservationBean,ArrayList<PassengerBean>> mp = new HashMap<>();
    	ReservationBean rb = userv.viewByReservationId(rid);
    	System.out.println("Val of rb is:"+rb);
//    	ArrayList<PassengerBean> al = userv.findPassengerById(rb);
//    	System.out.println("val of al is"+al);
//    	mp.put(rb,al);
//    	System.out.println("val of map mp is:"+mp);
//    	return mp;
    	return rb;
//    	return 1;
	}
	@GetMapping("/cancelticket/{rid}")
	public String cancelTicket(@PathVariable int rid) {
		//get the booking date
		ReservationBean rb = userv.findReservationById(rid);
		String journeyDate = rb.getJourneyDate();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		//get the current date
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String currentDateAsString = currentDate.format(formatter);
		//convert to date format
		Date jd = null,cd=null;
		try {
			jd = format.parse(journeyDate);
			cd = format.parse(currentDateAsString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//check whether ticket can be cancelled or not
		if (jd.before(cd)) {
		    System.out.println("earlier");
		    return "Could not cancelled, date already passed";
		}
		//find total fare that to be refunded
		double totalFare = rb.getTotalFare();
		//repay balance
		int res = userv.repayBalance(totalFare);
		//delete passenger
		res += userv.deletePassengers(rid);
		//delete reservation
		res += userv.deleteReservation(rid);
		return res+"";
	}
	@GetMapping("/getallsourcedestination")
	public Map<String, ArrayList<String>>  getAllSourceAndDestination() {
		Map<String,ArrayList<String>> mp = new HashMap<>();
    	ArrayList<String> sources = userv.getAllSources();
    	ArrayList<String> destinations = userv.getAllDestinations();
    	mp.put("sources",sources);
    	mp.put("destination",destinations);
    	System.out.println("val of map mp is:"+mp);
    	return mp;
	}
	@GetMapping("/getallschedules")
	public ArrayList<ScheduleBean> getAllSchedule(){
		ArrayList<ScheduleBean> allSchedule = userv.getAllSchedule();
		return allSchedule;
	}
	@GetMapping("/getbalance")
	public int getBalance() {
		return userv.getBalance();
	}
	@GetMapping("/getallreservation")
	public ArrayList<ReservationBean> getAllReservation() {
		return userv.getAllReservation();
	}
}

package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.ShipBean;
import com.example.demo.service.ShipService;

@RestController
@RequestMapping("/ship")
@CrossOrigin(origins = "*")
public class ShipController {
	@Autowired
	private ShipService eserv;
	@GetMapping("/")
	public String welcome() {
		return "<h1>Hello welcome to comviva</h1>";
	}
	
	@PostMapping("/addShip")
	public String addShip(@RequestBody ShipBean eb) {
		return "<h1>"+eserv.addShip(eb)+" Record inserted successfuly</h1>";
	}
	@PutMapping("/updateShip")
	public int updateSgip(@RequestBody ShipBean sb) {
		return eserv.updateShip(sb);
	}
	@DeleteMapping("/deleteShip/{sid}")
	public int deleteShip(@PathVariable int sid) {
		return eserv.deleteShip(sid);
	}
	@GetMapping("/getallships")
	public ArrayList<ShipBean> getShips(){
		return eserv.getShips();
	}
	@GetMapping("/getship/{sid}")
	public ShipBean getShipById(@PathVariable int sid) {
		return eserv.getShip(sid);
	}


}

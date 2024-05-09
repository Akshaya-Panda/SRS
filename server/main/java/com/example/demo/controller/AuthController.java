package com.example.demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.CredentialsBean;
import com.example.demo.bean.ProfileBean;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/credentials")
@CrossOrigin(origins = "*")
public class AuthController {
	@Autowired
	private AuthService aserv;

	@PostMapping("/adduser")
	public String addUser(@RequestBody CredentialsBean cb) {
		return "<h1>" + aserv.addUser(cb) + " Record inserted successfuly</h1>";
	}

	@PostMapping("/login")
	public String login(@RequestBody CredentialsBean cb) {
		System.out.println("uname" + cb.getUserId() + "password" + cb.getPassword());
		CredentialsBean receivedCB = aserv.getUser(cb.getUserId(), cb.getPassword());
		System.out.println("Valof credentialBean is:" + receivedCB);
		if (receivedCB == null) {
			return "FAIL";
		} else {
			aserv.changeLoginStatus(1, cb.getUserId());//change the login status to 1
			String code = receivedCB.getUserId().substring(0,2);
			System.out.println("val of code is: "+code);
			if (code.equalsIgnoreCase("US")) {
				System.out.println("val of code is: "+code);
				return "C";// for customer
			} else {
				return "A";//for admin
			}
		}
	}

	public static int generateRandomNum() {
		Random Random = new Random();
		int randomNumber = Random.nextInt(10000);
		return randomNumber;
	}

	public static String generateUserId(String fname) {
		String first2Letter = fname.substring(0, 2);
		String ranUserId = first2Letter.toUpperCase() + "-" + generateRandomNum();
		return ranUserId;
	}

	@PostMapping("/registration")
	public int registration(@RequestBody ProfileBean pb) {
		// generate random userId
		String uid = generateUserId(pb.getFirstName());
		pb.setUserId(uid);
		System.out.println("uname" + pb.getUserId());
		int result = aserv.registerUser(pb);
		System.out.println("Valof credentialBean is:" + result);
		return result; // return data or null as string
	}
	@GetMapping("/logout/{userId}")
	public int logout(@PathVariable("userId") String userId) {
		return aserv.changeLoginStatus(0,userId);
	}

}

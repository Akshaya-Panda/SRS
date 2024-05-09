package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.CredentialsBean;
import com.example.demo.bean.ProfileBean;
import com.example.demo.dao.AuthDao;

@Service
public class AuthService {
	@Autowired
	private AuthDao adao;

	public AuthDao getEdao() {
		return adao;
	}

	public void setAdao(AuthDao adao) {
		this.adao = adao;
	}

	@Transactional
	public int addUser(CredentialsBean eb) {
		return adao.addUser(eb);
	}
	
	@Transactional
	public CredentialsBean getUser(String uname,String psd) {
		return adao.getUser(uname, psd);
	}
	@Transactional
	public int changeLoginStatus(int status, String userId) {
		return adao.changeLoginStatus(status,userId);
	}
	@Transactional
	public int registerUser(ProfileBean pb) {
		return adao.registerUser(pb);
	}
//	@Transactional
//	public int updateShip(ShipBean sb) {
//		return adao.updateShip(sb);
//	}
//
//	@Transactional
//	public int deleteShip(int sid) {
//		return adao.deleteShip(sid);
//	}
//
//	@Transactional
//	public ArrayList<ShipBean> getShips() {
//		return adao.getAllShips();
//	}
//
//	@Transactional
//	public ShipBean getShip(int sid) {
//		return adao.getShipById(sid);
//	}
}

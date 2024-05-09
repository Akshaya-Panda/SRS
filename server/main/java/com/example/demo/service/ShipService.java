package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.bean.ShipBean;
import com.example.demo.dao.ShipDao;

@Service
public class ShipService {
@Autowired
private ShipDao edao;

public ShipDao getEdao() {
	return edao;
}

public void setEdao(ShipDao edao) {
	this.edao = edao;
}
@Transactional
public int addShip(ShipBean eb) {
	return edao.addShip(eb);
}
@Transactional 
public int updateShip(ShipBean sb) {
	return edao.updateShip(sb);
}
@Transactional
public int deleteShip(int sid) {
	return edao.deleteShip(sid);
}
@Transactional
public ArrayList<ShipBean> getShips(){
	return edao.getAllShips();
}
@Transactional
public ShipBean getShip(int sid) {
	return edao.getShipById(sid);
}
}

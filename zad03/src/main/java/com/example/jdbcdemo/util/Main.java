package com.example.jdbcdemo.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Gun;
import com.example.jdbcdemo.service.GunServiceJDBC;

public class Main {

	public static void main(String[] args) throws SQLException {
		GunServiceJDBC gs = new GunServiceJDBC();
		Gun gun1 = new Gun("Glock 9mm", "2016-09-11", false, 0.85);
		Gun gun2 = new Gun("M4A1", "2015-09-11", true, 4.23);
		gs.addGun(gun1);
		gs.addGun(gun2);
		
		List<Gun> guns = new ArrayList<Gun>();

		
		guns = gs.getAllGuns();
		
		for(Gun gun : guns) {
			System.out.println(gun.getName());
		}
		
		List<Gun> guns2 = new ArrayList<Gun>();
		guns2.add(gun1);
		guns2.add(gun2);
		
		gs.addAllGuns(guns2);
		
		guns = gs.getAllGuns();
		
		for(Gun gun : guns) {
			System.out.println(gun.getName());
		}
		
	}

}

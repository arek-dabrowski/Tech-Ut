package com.example.jdbcdemo.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.jdbcdemo.domain.Gun;
import com.example.jdbcdemo.service.GunServiceJDBC;

public class Main {

	public static void main(String[] args) throws SQLException {
		GunServiceJDBC gs = new GunServiceJDBC();
		initGuns(gs);
		
		List<Gun> guns = new ArrayList<Gun>();
		guns = gs.getAllGuns();
		
		
		for(Gun gun : guns) {
			System.out.println(gun.getName());
		}
		
	}
	
	private static void initGuns(GunServiceJDBC gs) {
		List<Gun> guns = new ArrayList<Gun>();
		guns.add(new Gun("Glock 18", "2012-09-11", false, 0.6));
		guns.add(new Gun("M4A1", "2003-04-16", true, 2.52));
		guns.add(new Gun("Remington 870", "2015-02-03", false, 3.6));
		guns.add(new Gun("MP5A2", "1998-05-28", false, 2.54));
		guns.add(new Gun("Beretta 92", "2001-12-22", false, 0.96));
		guns.add(new Gun("Magnum 44", "1980-08-27", true, 1.33));
		
		gs.addAllGuns(guns);
	}

}

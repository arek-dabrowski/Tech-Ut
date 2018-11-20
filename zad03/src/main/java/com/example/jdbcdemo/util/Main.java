package com.example.jdbcdemo.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Gun;
import com.example.jdbcdemo.service.GunServiceJDBC;

public class Main {

	private static void showAll(List<Gun> guns) {
		for(Gun gun : guns) {
			System.out.println(gun);
		}
		System.out.println();
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
	
	public static void main(String[] args) throws SQLException {
		GunServiceJDBC gs = new GunServiceJDBC();
		initGuns(gs);
		
		List<Gun> guns = new ArrayList<Gun>();
		
		System.out.println("Wszystkie rekordy:");
		guns = gs.getAllGuns();
		showAll(guns);
		
		System.out.println("Wszystkie bronie w kolejności alfabetycznej:");
		guns = gs.getAllGunsASC();
		showAll(guns);
		
		System.out.println("Dodawanie nowej broni");
		Gun newGun = new Gun("Colt M1911A1", "1993-01-10", false, 1.1);
		gs.addGun(newGun);
		
		System.out.println("Wszystkie bronie malejąco:");
		guns = gs.getAllGunsDESC();
		showAll(guns);
		
		System.out.println("Wszystkie nieuszkodzone bronie:");
		guns = gs.getAllUndamagedGuns();
		showAll(guns);
		
	}
	
}

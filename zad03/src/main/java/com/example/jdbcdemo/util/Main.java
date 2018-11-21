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
				
		System.out.println("Wszystkie rekordy:");
		showAll(gs.getAllGuns());
		
		System.out.println("Wszystkie bronie w kolejności alfabetycznej:");
		showAll(gs.getAllGunsASC());
		
		System.out.println("Dodawanie nowej broni");
		Gun newGun = new Gun("Colt M1911A1", "1993-01-10", false, 1.1);
		gs.addGun(newGun);
		
		System.out.println("Wszystkie bronie malejąco:");
		showAll(gs.getAllGunsDESC());
		
		System.out.println("Wszystkie nieuszkodzone bronie:");
		showAll(gs.getAllUndamagedGuns());
		
		System.out.println("Wyszukanie broni o id 3:");
		System.out.println(gs.getGun(3));
		System.out.println();
		
		System.out.println("Usunięcie broni o id 4:");
		gs.deleteGun(gs.getGun(4));
		showAll(gs.getAllGuns());
		
		System.out.println("Update broni o id 5:");
		Gun change = gs.getGun(5);
		change.setName("Magnum 44UPDATED");
		gs.updateGun(change);
		showAll(gs.getAllGuns());
		
		System.out.println("Usunięcie wszystkich broni:");
		gs.deleteAllGuns();
		showAll(gs.getAllGuns());
	}
	
}

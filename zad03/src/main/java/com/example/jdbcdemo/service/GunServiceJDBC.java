package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Gun;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GunServiceJDBC implements GunService {
	
	private static Connection connection;
	private static Statement statement;
	
	private final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
	private final String CREATE_TABLE_SQL = "CREATE TABLE Gun(id bigint GENERATED BY DEFAULT AS IDENTITY, name varchar(20) UNIQUE, productionDate date, damaged boolean, weight double)";
	

	private PreparedStatement addGunStmt;
	private PreparedStatement deleteAllGunsStmt;
	private PreparedStatement getAllGunsStmt;
	
	public GunServiceJDBC() throws SQLException {
		
		connection = DriverManager.getConnection(URL);
		statement = connection.createStatement();
		
		ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
		
		boolean tableExists = false;
		while(rs.next()) {
			if("Gun".equalsIgnoreCase(rs.getString("table_name"))) {
				tableExists = true;
				break;
			}
		}
		
		if(!tableExists) statement.executeUpdate(CREATE_TABLE_SQL);
		
		addGunStmt = connection.prepareStatement("INSERT INTO Gun(name, productionDate, damaged, weight) VALUES (?, ?, ?, ?)");
		deleteAllGunsStmt = connection.prepareStatement("DELETE FROM Gun");
		getAllGunsStmt = connection.prepareStatement("SELECT id, name, productionDate, damaged, weight FROM Gun");
	}
	
	@Override
	public int addGun(Gun gun) {
		int count = 0;
		try {
			addGunStmt.setString(1, gun.getName());
			addGunStmt.setString(2, gun.getProductionDate());
			addGunStmt.setBoolean(3, gun.isDamaged());
			addGunStmt.setDouble(4, gun.getWeight());
			count = addGunStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public List<Gun> getAllGuns(){
		List<Gun> guns = new ArrayList<Gun>();
		
		try {
			ResultSet rs = getAllGunsStmt.executeQuery();

			while (rs.next()) {
				Gun g = new Gun();
				g.setId(rs.getInt("id"));
				g.setName(rs.getString("name"));
				g.setProductionDate(rs.getString("productionDate"));
				g.setDamaged(rs.getBoolean("damaged"));
				g.setWeight(rs.getDouble("weight"));
				guns.add(g);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return guns;
	}
	
	@Override
	public void addAllGuns(List<Gun> guns) {
		try {
			connection.setAutoCommit(false);
			for(Gun gun : guns) {
				addGunStmt.setString(1, gun.getName());
				addGunStmt.setString(2, gun.getProductionDate());
				addGunStmt.setBoolean(3, gun.isDamaged());
				addGunStmt.setDouble(4, gun.getWeight());
				addGunStmt.executeUpdate();
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	
}
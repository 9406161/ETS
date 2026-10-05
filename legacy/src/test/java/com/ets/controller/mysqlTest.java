package com.ets.controller;



import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class mysqlTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/et_tb?serverTimezone=Asia/Seoul";
	private static final String USER = "ET";
	private static final String PW = "1234";
	
	@Test
	public void test() throws Exception {
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println("성공");
			System.out.println(con);
		}catch (Exception e) {
			System.out.println("에러");
			e.printStackTrace();
		}
		
		
	}

}

package com.gcit.training.lms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JdbcDemo {

	private static Connection getConnection() {

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void main(String[] args) {

		Connection conn = getConnection();

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter Publisher Name");
		
		try {
			
            System.out.println("Enter Publisher Name");
			String publisherName = scan.nextLine();
			
			System.out.println("Enter Publisher Address");
			String publisherAddress = scan.nextLine();
			
			System.out.println("Enter Publisher Phone");
			String publisherPhone = scan.nextLine();
			
//			String insert = "insert into tbl_author (authorName) values (?)";
//			PreparedStatement stmt = conn.prepareStatement(insert);
//			
//			stmt.setString(1, authorName);
//			stmt.executeUpdate();
			
			String insert = "insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?)";
			PreparedStatement stmt = conn.prepareStatement(insert);
			
			stmt.setString(1, publisherName+","+publisherAddress+","+publisherPhone);
			System.out.println(stmt);
			stmt.executeUpdate();
			
			
			String sql = "select * from tbl_author";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("####### Author Details #####");
			while(rs.next()) {
				System.out.println(rs.getInt("authorId") + " : " + rs.getString("authorName"));
			}
			System.out.println("#########################");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

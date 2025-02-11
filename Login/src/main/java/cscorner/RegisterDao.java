package cscorner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Driver;

public class RegisterDao {
	
	private String dbUrl="jdbc:mysql://localhost:3306/userdb";
	private String dbUname="root";
	private String dbPassword="sumi@123";
	private String dbDriver="com.mysql.cj.jdbc.Driver";
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		
		Connection con=null;
		try {
	con=DriverManager.getConnection(dbUrl,dbUname,dbPassword);
	}catch(SQLException e) {
		e.printStackTrace();
	}
		return con;
	}
	public String insert(Member member) {
		
		loadDriver(dbDriver);
		Connection con=getConnection();
		String result="Data entered sucessfully";
		try
		{
		
		 PreparedStatement ps=con.prepareStatement("insert into member values(?,?,?,?)");
		ps.setString(1,member.getUname());
		ps.setString(2,member.getPassword());
		ps.setString(3,member.getEmail());
		ps.setString(4,member.getPhone());
		ps.executeUpdate();
		
	}catch(SQLException e) {
		e.printStackTrace();
		result="Data not entered";
	}
		return result;
	}
}

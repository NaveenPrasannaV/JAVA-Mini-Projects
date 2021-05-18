package com.jdbc.epay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase
{
	String url = "jdbc:mysql://localhost:3306/epay";
	String user = "root";
	String pwd = "root";
	Connection con;

	public void installdriver()
	{
		try 
		{
			con = DriverManager.getConnection(url,user,pwd);
			//System.out.println("Connecting with server...");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void closedb()
	{
		try 
		{
			con.close();
			//System.out.println("Disconnecting with server...");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void accdisplay(int id, String pass) throws SQLException
	{
		String u2 = "Select * from account where id = ? and pass = ?";
		PreparedStatement p2 = con.prepareStatement(u2);
		p2.setInt(1, id);
		p2.setString(2, pass);
		ResultSet res1 = p2.executeQuery();
		res1.next();
		long accno = res1.getLong("accno");
		String name = res1.getString("name");
		String upiid = res1.getString("upiid");
		long mob = res1.getLong("mob");
		double bal = res1.getDouble("bal");
		System.out.println("----------------------");
		System.out.println("Account No: "+accno);
		System.out.println("Name: "+name);
		System.out.println("Mob: "+mob);
		System.out.println("UPI: "+upiid);
		System.out.println("Bank: e-Pay Payments Bank");
		System.out.println("IFSC: EPAY123456");
		System.out.println("----------------------");
		System.out.println("Balance: "+bal);
		System.out.println("----------------------");
	}
	
	public void addacc(int id, String pass, long accno, String name, long mob, String upiid, long cardno) throws SQLException
	{
		double bal = 0;
		String u1 = "Insert into account(id,pass,accno,name,mob,bal,upiid,cardno) values(?,?,?,?,?,?,?,?)";
	    PreparedStatement p1 =	con.prepareStatement(u1);
	    p1.setInt(1, id);
	    p1.setString(2, pass);
	    p1.setLong(3, accno);
	    p1.setString(4, name);
	    p1.setLong(5, mob);
	    p1.setDouble(6,bal);
	    p1.setString(7, upiid);
	    p1.setLong(8, cardno);
	    p1.execute();
	    System.out.println("Your e-Account has been Created Successfully!");
	}
	
	public double upiaccess(String upiid) throws SQLException
	{
		String u2 = "Select * from account where upiid = ?";
		PreparedStatement p2 = con.prepareStatement(u2);
		p2.setNString(1, upiid);
		ResultSet res1 = p2.executeQuery();
		res1.next();
		String name = res1.getString("name");
		double bal = res1.getDouble("bal");
		System.out.println();
		System.out.println("Account Holder: "+name);
		return bal;
	}
	
	public double netaccess(int id, String pass) throws SQLException
	{
		String u2 = "Select * from account where id = ? and pass = ?";
		PreparedStatement p2 = con.prepareStatement(u2);
		p2.setInt(1, id);
		p2.setNString(2, pass);
		ResultSet res1 = p2.executeQuery();
		res1.next();
		long accno = res1.getLong("accno");
		String name = res1.getString("name");
		double bal = res1.getDouble("bal");
		System.out.println();
		System.out.println("Account No:"+accno);
		System.out.println("Account Holder: "+name);
		return bal;
	}
	
	public double cardaccess(long cardno) throws SQLException
	{
		String u2 = "Select * from account where cardno = ?";
		PreparedStatement p2 = con.prepareStatement(u2);
		p2.setLong(1, cardno);
		ResultSet res1 = p2.executeQuery();
		res1.next();
		long accno = res1.getLong("accno");
		String name = res1.getString("name");
		double bal = res1.getDouble("bal");
		System.out.println();
		System.out.println("Account No:"+accno);
		System.out.println("Account Holder: "+name);
		return bal;
	}
	
	public void updatebalupi(double bal, String upiid) throws SQLException
	{
		String u1 = "update account set bal = ? where upiid = ?";
	    PreparedStatement p1 =	con.prepareStatement(u1);
	    p1.setDouble(1, bal);
	    p1.setString(2, upiid);
	    p1.execute();
	}
	
	public void updatebalnet(double bal, int id) throws SQLException
	{
		String u1 = "update account set bal = ? where id = ?";
	    PreparedStatement p1 =	con.prepareStatement(u1);
	    p1.setDouble(1, bal);
	    p1.setInt(2, id);
	    p1.execute();
	}
	
	public void updatebalcard(double bal, long cardno) throws SQLException
	{
		String u1 = "update account set bal = ? where cardno = ?";
	    PreparedStatement p1 =	con.prepareStatement(u1);
	    p1.setDouble(1, bal);
	    p1.setLong(2, cardno);
	    p1.execute();
	}
	
	public String merchantverify(String mupi) throws SQLException
	{
		String u2 = "Select * from account where upiid = ?";
		PreparedStatement p2 = con.prepareStatement(u2);
		p2.setString(1, mupi);
		ResultSet res1 = p2.executeQuery();
		res1.next();
		String name = res1.getString("name");
		return name;
	}
	
	public double merchantbal(String mupi) throws SQLException
	{
		String u2 = "Select * from account where upiid = ?";
		PreparedStatement p2 = con.prepareStatement(u2);
		p2.setString(1, mupi);
		ResultSet res1 = p2.executeQuery();
		res1.next();
		double mbal = res1.getDouble("bal");
		return mbal;
	}
	
	public void updatembal(double mbal, String mupi) throws SQLException
	{
		String u1 = "update account set bal = ? where upiid = ?";
	    PreparedStatement p1 =	con.prepareStatement(u1);
	    p1.setDouble(1, mbal);
	    p1.setString(2, mupi);
	    p1.execute();
	}
	
	
}
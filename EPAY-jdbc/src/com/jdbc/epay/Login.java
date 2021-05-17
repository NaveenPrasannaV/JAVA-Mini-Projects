package com.jdbc.epay;

import java.sql.SQLException;
import java.util.Scanner;

public class Login
{
	int id;
	String pass; 
	int temp = 0;
	public void userlogin()
	{
		System.out.print("User ID: ");
		Scanner sc1 = new Scanner(System.in);
		id = sc1.nextInt();
		System.out.println();
		System.out.print("Password : ");
		pass = sc1.next();
		System.out.println();
		
		DataBase d1 = new DataBase();
		d1.installdriver();
		temp=0;
		System.out.println("***e-Pay Banking Portal***");
		System.out.println();
		try 
		{
			d1.accdisplay(id,pass);
		} 
		catch (SQLException e) 
		{
			System.out.println("Invalid Credential!");
			temp = 1;
		}
		if(temp == 1)
		{
			userlogin();
		}
		d1.closedb();
		System.out.println("Logout Press 9 ->");
		int n = sc1.nextInt();
		if(n>=0 && n<=9)
		{
			MainClass.welcome();
		}
		else
		{
			MainClass.welcome();
		}
		
	}
}

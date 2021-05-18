package com.jdbc.epay;

import java.sql.SQLException;
import java.util.Scanner;

public class CreateAcc 
{
	int id;
	String pass;
	long accno;
	String name;
	long mob;
	String upiid;
	long cardno;
	
	public void adddetails()
	{
		System.out.println();
		System.out.println("Please Continue by Filling the Details...");
		System.out.println();
		System.out.print("Full Name (Without Space): ");
		Scanner sc1 = new Scanner(System.in);
		name = sc1.next();
		System.out.println();
		System.out.print("Mobile No (10 Digits): ");
		mob = sc1.nextLong();
		System.out.println();
		System.out.print("Account No (12 Digits): ");
		accno = sc1.nextLong();
		System.out.println();
		System.out.print("Add Debit/Credit Card No (16 Digits): ");
		cardno = sc1.nextLong();
		System.out.println();
		System.out.print("Add your UPI Id (<xyz>@upi): ");
		upiid = sc1.next();
		System.out.println();
	}
	
	public void newid()
	{
		System.out.println();
		System.out.print("Create Your Customer-ID (Only 4 Digits): ");
		Scanner s1 = new Scanner(System.in);
		int a = s1.nextInt();
		if(a>=1000 && a<=9999)
		{
			id = a;
		}
		else
		{
			System.out.println("ID is invalid! Please enter correctly");
			newid();
		}
	}
	
	public void newpass()
	{
		int upper=0;
		int lower=0;
		int spl=0;
		int num=0;
		System.out.println();
		System.out.print("Create Your Account Password (*Pass -> 2-UpperCase, 1-SpecialChar, 4-Numericals): ");
		Scanner s1 = new Scanner(System.in);
		String b = s1.next();
		
		System.out.print("Re-enter Account Password: ");
		String d = s1.next();
		
		if(b.equals(d) == true)
		{
			if(b.length() >= 10)
			{
						for(int i=0; i<=b.length()-1; i++)
						{
							int c = b.charAt(i);
							
							if(c >= 65 && c <= 90)
							{
								upper++;
							}
							else if(c >= 97 && c <= 122)
							{
								lower++;
							}
							else if(c >= 48 && c <= 57)
							{
								num++;
							}
							else
							{
								spl++;
							}
						}
					
					if(upper >= 2 && lower >= 2 && spl >= 1 && num >= 3)
					{
						pass = b;
						System.out.println("Password Created Successfully!");
						System.out.println();
					}
					else
					{
						System.out.println("Password is invalid! Password must satisfy *pass");
						newpass();
					}
			}
			else
			{
				System.out.println("Password is invalid! Password Should exceed 10 Digits");
				newpass();
			}
		}
		else
		{
			System.out.println("Password mismatch!");
			newpass();
		}
		
	}
	
	public void newaccount()
	{
		System.out.println("***Welcome to e-Pay***");
		System.out.println("You're one step ahead to create your e-Account");
		newid();
		newpass();
		adddetails();
		System.out.println("Please Verify the e-Account Details");
		System.out.println("------------------------------------");
		System.out.println("Account Holder: "+name);
		System.out.println("Mobile: "+mob);
		System.out.println("Account No: "+accno);
		System.out.println("Card No: "+cardno);
		System.out.println("UPI Id: "+upiid);
		System.out.println("------------------------------------");
		System.out.println("To Continue -> Press 6 | Edit Details -> Press 4");
		Scanner sc1 = new Scanner(System.in);
		int a = sc1.nextInt();
		if(a == 6)
		{
			DataBase d1 = new DataBase();
			d1.installdriver();
			try 
			{
				d1.addacc(id, pass, accno, name, mob, upiid, cardno);
			} 
			catch (SQLException e) 
			{
				System.out.println("Error: Details may already Present/Try with other values");
				newaccount();
			}
			d1.closedb();
			
			System.out.println();
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
		else
		{
			newaccount();
		}
		
	}
}

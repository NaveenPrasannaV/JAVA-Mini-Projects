package com.jdbc.epay;

import java.sql.SQLException;
import java.util.Scanner;

public class QuickPay 
{	
	double bal;
	double amount;
	String comment = "e-Payments";
	String mode;
	String status = "FAILED";
	String mname;
	String mupi;
	double mbal;
	
	public void upipay()
	{
		System.out.println("***e-Pay through UPI***");
		System.out.println();
		System.out.println("Enter UPI Id: ");
		Scanner sc2 = new Scanner(System.in); 
		String upiid = sc2.next();
		DataBase d1 = new DataBase();
		d1.installdriver();
		try 
		{
			this.bal = d1.upiaccess(upiid);
			mode = "UPI";
			payable(this.bal, mode);
			d1.updatebalupi(this.bal, upiid);
		} 
		catch (SQLException e) 
		{
			System.out.println("UPI Id invalid!");
			upipay();
		}
		d1.closedb();
	}
	
	public void netpay()
	{
		System.out.println("***e-Pay through NetBanking***");
		System.out.println();
		System.out.print("Enter Customer Id: ");
		Scanner sc3 = new Scanner(System.in);
		int id = sc3.nextInt();
		System.out.print("Enter Password: ");
		String pass = sc3.next();
		DataBase d1 = new DataBase();
		d1.installdriver();
		try 
		{
			this.bal = d1.netaccess(id, pass);
			mode = "NetBanking";
			payable(this.bal, mode);
			d1.updatebalnet(this.bal, id);
		} 
		catch (SQLException e) 
		{
			System.out.println("Customer Id/Password invalid!");
			netpay();
		}
		d1.closedb();
	}
	
	public void cardpay()
	{
		System.out.println("***e-Pay through Debit/Credit***");
		System.out.println();
		System.out.print("Enter Card No(16 Digits): ");
		Scanner sc3 = new Scanner(System.in);
		long cardno = sc3.nextLong();
		DataBase d1 = new DataBase();
		d1.installdriver();
		try 
		{
			this.bal = d1.cardaccess(cardno);
			mode = "Debit/Credit Card";
			payable(this.bal, mode);
			d1.updatebalcard(this.bal, cardno);
		} 
		catch (SQLException e) 
		{
			System.out.println("Invalid Card details!");
			cardpay();
		}
		d1.closedb();
	}
	
	public void payable(double mybal, String mode1)
	{
		System.out.println();
		System.out.println("Processing payment...");
		DataBase d1 = new DataBase();
		d1.installdriver();
		if(mybal > 0)
		{
			if(mybal >= amount)
			{
				mybal = mybal - amount;
				this.bal = mybal;
				this.mbal = this.mbal + amount;
				try 
				{
					d1.updatembal(mbal, mupi);
				} 
				catch (SQLException e) 
				{
					System.out.println("Error: Merchant Balance updation not responding");
				}
				mode = mode1;
				System.out.println("Amount has been debited Successfully");
				status = "SUCCEED";
			}
			else
			{
				System.out.println("Payment Failed due to insufficient Balance");
				this.bal = mybal;
			}
		}
		else
		{
			System.out.println("Payment Failed due to low Balance");
			this.bal = mybal;
		}
		d1.closedb();
	}
	
	public void receipt()
	{
		System.out.println("------------e-Pay Receipt------------------");
		System.out.println("A payment of Rs: "+amount+" /- has been initiated");
		System.out.println("Merchant UPI: "+mupi);
		System.out.println("Payment Status: "+status);
		System.out.println("Comments: "+comment);
		System.out.println("Mode of Transaction: "+mode);
		System.out.println("Remaining Balance: "+bal);
		System.out.println("-------------------------------------------");
	}
	
	public void merchant()
	{
		Scanner sc1 = new Scanner(System.in);
		System.out.print("Enter Merchant UPI: ");
		this.mupi = sc1.next();
		DataBase d1 = new DataBase();
		d1.installdriver();
		try 
		{
			this.mname = d1.merchantverify(this.mupi);
			System.out.println("Merchant Name: "+this.mname);
		} 
		catch (SQLException e) 
		{
			System.out.println("Merchant UPI ID is invalid!");
			merchant();
		}
		System.out.println();
		d1.closedb();
	}
	
	public void balmerchant()
	{
		DataBase d1 = new DataBase();
		d1.installdriver();
		try 
		{
			this.mbal = d1.merchantbal(mupi);
		} 
		catch (SQLException e) 
		{
			System.out.println("Error: Merchant Bank is not responding");
			payoption();
		}
		d1.closedb();
	}
	
	public void payoption()
	{
		System.out.println();
		System.out.println("***Welcome to e-Pay Quick Payment Portal***");
		System.out.println();
		Scanner sc1 = new Scanner(System.in);
		
		merchant();
		balmerchant();
		
		System.out.print("Enter the Amount to Pay: ");
		amount = sc1.nextDouble();
		System.out.print("Enter the Comments (Optional): ");
		comment = sc1.next();
		System.out.println("Choose the Payment Options");
		System.out.println("1 -> Pay through NetBanking");
		System.out.println("2 -> Pay through Debit/Credit Card");
		System.out.println("3 -> Pay through UPI"); 
		int a = sc1.nextInt();
		if(a == 1)
		{
			netpay();
		}
		else if(a == 2)
		{
			cardpay();
		}
		else if(a == 3)
		{
			upipay();
		}
		else
		{
			System.out.println("Invalid Selection!");
			payoption();
		}
		receipt();
		
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

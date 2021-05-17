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
			bal = d1.upiaccess(upiid);
			mode = "UPI";
			payable(bal, mode);
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
			bal = d1.netaccess(id, pass);
			mode = "NetBanking";
			payable(bal, mode);
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
			bal = d1.cardaccess(cardno);
			mode = "Debit/Credit Card";
			payable(bal, mode);
			d1.updatebalcard(this.bal, cardno);
		} 
		catch (SQLException e) 
		{
			System.out.println("Invalid Card details!");
			cardpay();
		}
		d1.closedb();
	}
	public void payable(double bal, String mode1)
	{
		System.out.println();
		System.out.println("Processing payment...");
		DataBase d1 = new DataBase();
		if(bal > 0)
		{
			if(bal >= amount)
			{
				bal = bal - amount;
				this.bal = bal;
				mode = mode1;
				System.out.println("Amount has been debited Successfully");
				status = "SUCCEED";
			}
			else
			{
				System.out.println("Payment Failed due to insufficient Balance");
				this.bal = bal;
			}
		}
		else
		{
			System.out.println("Payment Failed due to low Balance");
			this.bal = bal;
		}
	}
	
	public void receipt()
	{
		System.out.println("------------e-Pay Receipt------------------");
		System.out.println("A payment of Rs: "+amount+" /- has been initiated");
		System.out.println("Payment Status: "+status);
		System.out.println("Comments: "+comment);
		System.out.println("Mode of Transaction: "+mode);
		System.out.println("Remaining Balance: "+bal);
		System.out.println("-------------------------------------------");
	}
	
	public void payoption()
	{
		System.out.println();
		System.out.println("***Welcome to e-Pay Quick Payment Portal***");
		System.out.println();
		System.out.print("Enter the Amount to Pay: ");
		Scanner sc1 = new Scanner(System.in);
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

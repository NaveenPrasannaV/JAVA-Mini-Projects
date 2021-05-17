package com.jdbc.epay;

import java.util.Scanner;

public class MainClass 
{
	public static void welcome()
	{
		System.out.println();
		System.out.println("Choose your options");
		System.out.println("1 -> Login");
		System.out.println("2 -> Quick Payment");
		System.out.println("3 -> Create Account");
		Scanner sc1 = new Scanner(System.in);
		int a = sc1.nextInt();
		if(a == 1)
		{
			System.out.println("Login Processing...");
			System.out.println();
			Login l1 = new Login();
			l1.userlogin();
		}
		else if(a == 2)
		{
			System.out.println("Quick payment Processing...");
			System.out.println();
			QuickPay q1 = new QuickPay();
			q1.payoption();
		}
		else if(a == 3)
		{
			System.out.println("Creating account Processing...");
			System.out.println();
			CreateAcc c1 = new CreateAcc();
			c1.newaccount();
		}
		else
		{
			System.out.println("Invalid input!");
			welcome();
		}
	}
	public static void main(String[] args)
	{
		System.out.println("***Welcome to e-Pay***");
		welcome();
	}
}

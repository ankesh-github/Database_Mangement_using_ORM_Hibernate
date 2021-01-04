package org.dbms.javaApp;

import java.util.Scanner;

public class Functionality {
	private static String admin_mail="";

	//home

	public static void home() {
		Scanner sc=App.getScanner();
		System.out.println("    DATA BASE MANAGEMENT SYSTEM      ");
		System.out.println("------------------------------------");
		System.out.println("Sign Up /Login as");
		System.out.println("------------------------------------");

		System.out.println("Admin:   1");
		System.out.println("Student: 2");
		System.out.println("Exist:   3");

		System.out.println("-------------------------------------");

		int choice=0;

		try {
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}

		switch(choice) {
		case 1: {
			adminHome();
			break;
		}
		case 2:{
			studentHome();
			break;
		}
		case 3:{
			System.exit(0);
			break;
		}
		default:{
			System.out.println("please insert valid choice !!");
			break;
		}
		}

	}

	public static void studentHome() {
		Scanner sc=App.getScanner();
		System.out.println("   STUDENT HOME     ");
		System.out.println("-------------------");
		System.out.println(" Login :    1");
		System.out.println("Sign Up:    2");
		System.out.println("Home  :     3");
		System.out.println("Exist :     4");

		System.out.println("-------------------------------------");

		int choice=0;

		try {
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}

		switch(choice) {
		case 1: {
			studentLoginFunctionality();
			return;
		}
		case 2:{
			Student.signUp();
			studentHome();
			return;
		}
		case 3:{
			home();
			return;
		}
		case 4:{
			System.exit(0);
			return;
		}
		default:{
			System.out.println("please insert valid choice !!");
			break;
		}
		}



	}

	////login function /////

	public static void studentLoginFunctionality() {
		Scanner sc=App.getScanner();
		System.out.println("   Login     ");
		System.out.println("-------------");
		System.out.println("enter email id");
		String email=sc.next();
		boolean out=Validation.getMailFormat(email);
		if(out==true) {
			boolean rs=Student.studentValidation( email);
			if(rs==true) {
				studentOperation(email);
			}

		}
		else {
			System.out.println("please enter valid email id");
			studentHome();
		}

	}


	public static void studentOperation(String email) {

		Scanner sc=App.getScanner();
		System.out.println("-------------------------------------");

		System.out.println("   OPERATION     ");
		System.out.println("-------------------");
		System.out.println(" view  :       1");
		System.out.println(" update:       2");
		System.out.println(" delete:       3");
		System.out.println(" student home: 4");
		System.out.println(" admin home:   5");
		System.out.println(" Exist :       6");

		System.out.println("-------------------------------------");

		int choice=0;//by default

		try {
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}

		switch(choice) {
		case 1: {
			Student.getPersonalData(email);
			studentOperation(email);
			break;
		}
		case 2:{
			Student.update(email);
			studentOperation(email);

			break;
		}
		case 3:{
			Student.getDelete(email);

		}
		case 4:{
			studentHome();
			break;
		}
		case 5:{
			//adminHome();
			break;
		}
		case 6:{
			System.exit(0);
			break;
		}
		default:{
			System.out.println("please insert valid choice !!");
			break;
		}
		}

	}

	

	// admin operation //

	public static void adminOperation(String email) {
		Scanner sc=App.getScanner();
		System.out.println("      ADMIN OPERATION         ");
		System.out.println("-------------------------");
		System.out.println(" student database :    1");
		System.out.println(" admin database   :    2");
		System.out.println("   Home           :    3");
		System.out.println("   Exist          :    4");

		System.out.println("-------------------------------------");

		int choice=0;

		try {
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}

		switch(choice) {
		case 1: {
			studentDataBase();
			adminOperation(email);
			return;
		}
		case 2:{
			adminDataBase(email);
			adminOperation(email);
			return;
		}
		case 3:{
			home();
			break;
		}
		case 4:{
			System.exit(0);
			break;
		}
		default:{
			System.out.println("please insert valid choice !!");
			break;
		}
		}




	}

	public static void adminDataBase(String email) {
		Scanner sc=App.getScanner();
		System.out.println("-------------------------------------");

		System.out.println("   OPERATION     ");
		System.out.println("-------------------");
		System.out.println(" view  :       1");
		System.out.println(" update:       2");
		System.out.println(" admin home:   3");
		System.out.println(" Exist :       4");

		System.out.println("-------------------------------------");

		int choice=0;//by default

		try {
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}

		switch(choice) {
		case 1: {
			Admin.getPersonalData(email);
			adminDataBase(email);
			return;
		}
		case 2:{
			Admin.update(email);
			adminDataBase(email);

			return;
		}

		case 3:{
			adminHome();
			return;
		}

		default:{
			System.out.println("please insert valid choice !!");
			break;
		}
		}



	}

	public static void adminHome() {
		Scanner sc=App.getScanner();
		System.out.println("   ADMIN HOME     ");
		System.out.println("-------------------");
		System.out.println(" Login :    1");
		System.out.println("Home  :     2");
		System.out.println("Exist :     3");

		System.out.println("-------------------------------------");

		int choice=0;

		try {
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}

		switch(choice) {
		case 1: {
			boolean rs=adminLoginFunctionality();
			if(rs==true) {

				adminOperation(admin_mail);
				return;
			}
			else
			{
				adminHome();
				return;
			}
		}

		case 2:{
			home();
			return;
		}
		case 3:{
			System.exit(0);
			return;
		}
		default:{
			System.out.println("please insert valid choice !!");
			return;
		}
		}




	}

	public static boolean adminLoginFunctionality() {
		Scanner sc=App.getScanner();
		System.out.println("   Login     ");
		System.out.println("-------------");
		System.out.println("enter email id");
		String email=sc.next();
		boolean out=Validation.getMailFormat(email);
		if(out==true) {
			boolean rs=Admin.adminValidation( email);
			if(rs==true) {
				admin_mail=email;
				return true;
			}

			else {
				return false;
			}

		}
		else {
			System.out.println("enter valid email format");
			return false;
		}

	}


	public static void studentDataBase() {
		Student.getTable();
		System.out.println("------------------------------------------");
		System.out.println("         choose the email id               ");
		System.out.println("-------------------------------------------");
		int count=0;
		int flag=0;
		String email="";
		do {
			Scanner sc=App.getScanner();
			email=sc.next();
			boolean rs=Validation.getMailFormat(email);
			if(rs==true) {
				flag=1;
				break;
			}
			count++;
		}while(count<3);

		if(flag==0) {
			adminHome();
			return;
		}
		else {
			studentOperation(email);
			adminOperation(email);
			return;

		}
	}


















}

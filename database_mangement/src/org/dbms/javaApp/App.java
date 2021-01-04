package org.dbms.javaApp;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {

	private static  Scanner sc;
	public static Scanner getScanner() {
		if(sc==null) {
			return new Scanner(System.in);

		}
		return sc;
	}
	public static void main(String[] args) {
		Functionality.home();

		System.out.println("app end");
	}

	public static Session session=null;
	public static Session getConfiguration() 
	{
		if(session==null) {
			Configuration cfg=new Configuration().configure();
			SessionFactory sf=cfg.buildSessionFactory();
			Session session=sf.openSession();
			return session;

		}

		return session;
	}
















}

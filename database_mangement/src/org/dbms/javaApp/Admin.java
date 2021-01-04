package org.dbms.javaApp;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.*;

@Entity
@Table(name="Admin_table")
public class Admin implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	@Column(name="id")
	private int id;

	@Column(name="Email_id")
	private String email;

	@Column(name="Admin_Name")
	private String name;

	@Column(name="contact_num")
	private long number;

	@Column(name="password")
	private String password;

	private static Admin admin;
	public static Admin getAdmin() {
		if(admin==null) {
			admin=new Admin();

		}
		return admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Admin(int id, String email, String name, long number, String password) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.number = number;
		this.password = password;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", email=" + email + ", name=" + name + ", number=" + number + ", password="
				+ password + "]";
	}
	
	
	public static void update(String email) {
		Scanner sc=App.getScanner();

		System.out.println("---------------------------------");
		System.out.println("   what do you want to update    ");
		System.out.println("---------------------------------");

		System.out.println("   email:   1");
		System.out.println("   name:    2");
		System.out.println("   contact: 3");
		System.out.println("   student database:4");
		System.out.println("   admin database:  5");
		System.out.println("   exist:   6");
		System.out.println("---------------------------------");

		int choice=0;

		try {
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}


		switch(choice) {
		case 1:{
			getEmailUpdate(email);
			update(email);
			return;
		}
		case 2:{
			getNameUpdate(email);
			update(email);
			return;
		}
		case 3:{
			getNumberUpdate(email);
			update(email);
			return;
		}
		case 4:{
			Functionality.studentDataBase();
			return;
		}
		case 5:{
			Functionality.adminDataBase(email);
			return;
		}
		case 6:{
			System.exit(0);
			return;
		}


		default:{
			System.out.println("please enter valid choice ");
			break;

		}


		}
	}


	//email updation

	public static void getEmailUpdate(String mail) {

		Scanner sc=App.getScanner();
		System.out.println("-----------------------------------------------");
		System.out.println("insert new email id;which you want to udate  :");
		System.out.println("-----------------------------------------------");

		String input=sc.nextLine();

		Session session=null;
		session=App.getConfiguration();

		session.beginTransaction();
		Query query=session.createQuery("update from Admin set email=:input where email=:mail");
		query.setParameter("mail", mail);
		query.setParameter("input", input);
		int i=query.executeUpdate();
		session.getTransaction().commit();
		if(i>0) {
			System.out.println("record updated");
		}
		else {
			System.out.println("record is not available");
		}
		session.close();

	}

	//name updation function

	

	//number updation function

	public static void getNumberUpdate(String mail) {

		Scanner sc=App.getScanner();
		System.out.println("------------------------------------");
		System.out.println("insert number; you want to update:");
		System.out.println("------------------------------------");

		long input=0;

		try {
			input=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}


		Session session=null;
		session=App.getConfiguration();

		session.beginTransaction();
		Query query=session.createQuery("update from Admin set number=:input where email=:mail");
		query.setParameter("mail", mail);
		query.setParameter("input", input);
		int i=query.executeUpdate();
		session.getTransaction().commit();
		if(i>0) {
			System.out.println("record is updated sucessfully");
		}
		else {
			System.out.println("record is not available");
		}
		session.close();

	}

	//name updation function

	public  static void getNameUpdate(String mail) {

		Scanner sc=App.getScanner();
		System.out.println("------------------------------------------");
		System.out.println("insert name,which you want to update :");
		System.out.println("------------------------------------------");

		sc.nextLine();
		String input=sc.nextLine();
		//sc.next();

		Session session=null;
		session=App.getConfiguration();

		session.beginTransaction();
		Query query=session.createQuery("update from Admin set name=:input where email=:mail");
		query.setParameter("mail", mail);
		query.setParameter("input", input);
		int i=query.executeUpdate();
		session.getTransaction().commit();
		if(i>0) {
			System.out.println("record is updated sucessfully");
		}
		else {
			System.out.println("record is not updated");
		}
		session.close();

	}

	//personal data function

	public static void getPersonalData(String mail) {
		Session session=null;
		session=App.getConfiguration();
		Query query=session.createQuery("from Admin where email=:mail");
		query.setParameter("mail", mail);
		Iterator<Admin>iterator=((org.hibernate.Query) query).iterate();
		while(iterator.hasNext()) {
			Admin admin=(Admin)iterator.next();
			System.out.println(admin.getEmail()+"  "+admin.getName()+"  "+admin.getNumber()+" ");
		}


	}

	//get total data base

	public static void getTable() {
		Session session=null;
		session=App.getConfiguration();
		Query query=session.createQuery("from Admin");
		List<Admin>admins=((org.hibernate.query.Query) query).list();
		for(Admin a:admins) {
			System.out.println(a);
		}

	}

	//delete function

	
	//incryption function

	

	public static boolean adminValidation(String email) {
		Scanner sc=new Scanner(System.in);
		int flag=0;
		String password=null;

		Session session=null;

		long number=0;
		session=App.getConfiguration();
		Query query=session.createQuery("from Admin where email=:mail");
		query.setParameter("mail", email);
		Iterator<Admin>iterator=((org.hibernate.Query) query).iterate();
		while(iterator.hasNext()) {
			Admin admin=(Admin)iterator.next();
			password=admin.getPassword();
			number=admin.getNumber();
		}
		if(password==null) {
			System.out.println("you are not valid admin");
			return false;
		}
		else {
			System.out.println("please enter password");

			String pwd=sc.next();

			password=Student.getDecryption(password);
			if(pwd.equals(password)) {
				System.out.println("login sucessfully ");
				return true;
			}
			else {
				System.out.println("Incorrect password ");
				System.out.println("Re-enter Password: 0");
				System.out.println("forgot Password :1");
				sc=new Scanner(System.in);
				int choice=5;
				
				try {
				 choice=sc.nextInt();
				}
				catch(Exception e) {
					System.out.println("input mismatch exception!!");
					 
				}

				switch(choice) {
				case 0:{
					int count=0;
					do {
						System.out.println("enter password: ");
						pwd=sc.next();
						if(pwd.equals(password)) {
							System.out.println("login sucessfully ");
							return true;
						}
						count++;
					}while(count<3);
					if(count>=3) {
						System.out.println("sorry;you have exeed maximum attempt !!");
						return false;

					}

				}
				case 1:{
					return forgotPassword(email,number);

				}
				}
			}
		}
		return false;

	}

	public static boolean forgotPassword(String email, long number) {
		Scanner sc=App.getScanner();
		System.out.println("-----OTP------- ");
		System.out.println("OTP for "+email+"   1");
		System.out.println("OTP for "+number+"  2");
		System.out.println("------------------------");
		int n=0;
		try {
			n=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}

		switch(n) {

		case 1:{

			int increment=0;
			boolean isCorrect=false;
			do {
				int otp=Student.otpGeneration(email,number);
				System.out.println(otp);
				System.out.println("enter OTP");
				int user_otp=sc.nextInt();
				if(user_otp==otp) {
					isCorrect=true;
					return updatePassword(email);

				}
				System.out.println("resend OPT ");
				System.out.println("resend: 0");
				System.out.println("exist:  1");
				int input=sc.nextInt();
				if(input==0) {
					increment++;
				}
				else {
					System.out.println("you did not choice resend option");
					return false;
				}


			}while(increment<3);
			if(isCorrect==false) {
				System.out.println("sorry!!! you have exeed maximum number");
				return false;

			}
		}
		case 2:{
			int increment=0;
			boolean isCorrect=false;
			do {
				int otp=Student.otpGeneration(email,number);
				System.out.println(otp);
				System.out.println("enter OTP");
				int user_otp=sc.nextInt();
				if(user_otp==otp) {
					isCorrect=true;
					return updatePassword(email);

				}
				System.out.println("resend OPT ");
				System.out.println("resend: 0");
				System.out.println("exist:  1");
				int option=1;
				
				try {
				 n=sc.nextInt();
				}
				catch(Exception e) {
					System.out.println("input mismatch exception!!");
					 
				}

				if(option==0) {
					increment++;
				}
				else {
					System.out.println("you did not choice resend option");
					return false;
				}


			}while(increment<3);
			if(isCorrect==false) {
				System.out.println("sorry!!! you have exeed maximum number");
				return false;

			}

		}
		default:{
			System.out.println("insert valid number:");
			return false;
		}
		}


	}


	public  static boolean updatePassword(String mail) {
		int count=0;
		int flag=0;
		String pwd=null;
		do {
			Scanner sc=App.getScanner();
			System.out.println("  new password:   ");
			pwd=sc.next();
			System.out.println("  confirm password: ");
			String con=sc.next();
			if(pwd.equals(con)){
				flag=1;
				break;

			}
		}while(count<3);
		if(flag==1) {
			pwd= Student.getIncryption(pwd);
			Session session=null;
			session=App.getConfiguration();

			session.beginTransaction();
			Query query=session.createQuery("update from Admin set password=:pwd where email=:mail");
			query.setParameter("mail", mail);
			query.setParameter("pwd", pwd);
			int i=query.executeUpdate();
			session.getTransaction().commit();
			if(i>0) {
				System.out.println("password is updated sucessfully");
				return true;
			}
			else {
				System.out.println("incorrect password");
				return false;
			}
		}
		else {
			System.out.println("Sorry; you have exceed maximum number!!!");
			Functionality.home();
		}
		return false;

	}


	





}























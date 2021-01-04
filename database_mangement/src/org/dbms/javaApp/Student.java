package org.dbms.javaApp;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Table;

import org.hibernate.Session;

@Entity
@Table(name="Student_Table")

//student class

public class Student implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	@Column(name="id")
	private int id;

	@Column(name="Email_id")
	private String email;

	@Column(name="Name")
	private String name;

	@Column(name="Contact_num")
	private long contact;

	@Column(name="password")
	private String password;

	@Override
	public String toString() {
		return "Student [id=" + id + ", email=" + email + ", name=" + name + ", contact=" + contact + ", password="
				+ password + ", marks=" + marks + "]";
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int id, String email, String name, long contact, String password, int marks) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.contact = contact;
		this.password = password;
		this.marks = marks;
	}

	//getter and setter method

	@Column(name="Marks")
	private int marks;

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
		boolean rs=Validation.getMailFormat(email);
		if(rs==true) {
			this.email = email;
		}
		else {
			System.out.println("please insert valid email!!");
			signUp();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) 

	{
		boolean rs=Validation.getNumberFormat(contact);
		if(rs==true) {
			this.contact = contact;
		}
		else {
			System.out.println("please insert valid number!!");
			signUp();
		}

	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}


	//private object method

	private static Student student;
	public static Student getStudent() {
		if(student==null) {
			student=new Student();

		}
		return student;
	}



	//registration function

	public static void signUp() {
		
		student=new Student();

		try {

			Scanner sc=App.getScanner();
			
			System.out.println("--------------------------------");
			System.out.println("insert email id");
			String email=sc.nextLine();
			student.setEmail(email);

			System.out.println("insert name:");
			String name=sc.nextLine();
			student.setName(name);

			System.out.println("contact number: ");
			System.out.println("please avoid country code....(0/91) ");
			long contact=sc.nextLong();
			student.setContact(contact);


			System.out.println("marks");
			int marks=sc.nextInt();
			sc.nextLine();
			student.setMarks(marks);

			System.out.println("password: ");
			String password=sc.nextLine();
			password=getIncryption(password);
			student.setPassword(password);

		}
		catch(Exception e) {
			System.out.println("imput miss match exception!!");

		}



		Session session=null;
		session=App.getConfiguration();
		session.save(student);
		System.out.println("Signup sucessfully ");
		session.close();


	}

	//updation function

	public static void update(String email) {
		Scanner sc=App.getScanner();

		System.out.println("---------------------------------");
		System.out.println("   what do you want to update    ");
		System.out.println("---------------------------------");

		System.out.println("   email:   1");
		System.out.println("   name:    2");
		System.out.println("   contact: 3");
		System.out.println("   marks:   4");
		System.out.println("   exist:   5");
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
			break;
		}
		case 2:{
			getNameUpdate(email);
			break;
		}
		case 3:{
			getNumberUpdate(email);
			break;
		}
		case 4:{
			getMarksUpdate(email);
			break;
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
		Query query=session.createQuery("update from Student set email=:input where email=:mail");
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

	public static void getMarksUpdate(String mail) {

		Scanner sc=App.getScanner();
		System.out.println("---------------------------------- :");
		System.out.println("insert marks,you want to update :");
		System.out.println("---------------------------------- :");

		int input=0;

		try {
			input=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}


		Session session=null;
		session=App.getConfiguration();

		session.beginTransaction();
		Query query=session.createQuery("update from Student set marks=:input where email=:mail");
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
		Query query=session.createQuery("update from Student set contact=:input where email=:mail");
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
		Query query=session.createQuery("update from Student set name=:input where email=:mail");
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
		Query query=session.createQuery("from Student where email=:mail");
		query.setParameter("mail", mail);
		Iterator<Student>iterator=((org.hibernate.Query) query).iterate();
		while(iterator.hasNext()) {
			Student student=(Student)iterator.next();
			System.out.println(student.getEmail()+"  "+student.getName()+"  "+student.getContact()+" "+student.getMarks());
		}


	}

	//get total data base

	public static void getTable() {
		Session session=null;
		session=App.getConfiguration();
		Query query=session.createQuery("from Student");
		List<Student>students=((org.hibernate.query.Query) query).list();
		for(Student s:students) {
			System.out.println(s);
		}

	}

	//delete function

	public static void getDelete(String mail) {
		Scanner sc=new Scanner(System.in);
		System.out.println("---------------------------- ");
		System.out.println("        Please confirm       ");
		System.out.println("---------------------------- ");
		System.out.println("     Confirm             : 1 ");
		System.out.println("     go to Student home  : 2 ");
		System.out.println("     go to admin home    : 3 ");

		int choice=0;

		try {
			choice=sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("input mismatch exception!!");

		}

		switch(choice) {
		case 1:{
			Session session=null;
			session=App.getConfiguration();
			session.beginTransaction();
			Query query=session.createQuery("delete from Student where email=:mail");
			query.setParameter("mail", mail);
			int i=query.executeUpdate();
			session.getTransaction().commit();

			if(i>0)
				System.out.println("sucessfully deleted");
			else
				System.out.println("data is not present!!");

		}
		case 2:{
			Functionality.studentHome();
		}
		case 3:{
			Functionality.studentHome();
		}
		default:{
			System.out.println("please insert valid choice !!");
			break;
		}
		}


	}


	//incryption function

	public static String getIncryption(String password) {
		char ch[]=password.toCharArray();
		for(int i=0;i<ch.length;i++) {
			ch[i]=(char)(ch[i]+5);
		}

		return new String(ch);
	}

	//Decryption Function
	public static String getDecryption(String password) {
		char ch[]=password.toCharArray();
		for(int i=0;i<ch.length;i++) {
			ch[i]=(char)(ch[i]-5);
		}

		return new String(ch);
	}

	public static boolean studentValidation(String email) {
		Scanner sc=new Scanner(System.in);
		int flag=0;
		String password=null;

		Session session=null;

		long number=0;
		session=App.getConfiguration();
		Query query=session.createQuery("from Student where email=:mail");
		query.setParameter("mail", email);
		Iterator<Student>iterator=((org.hibernate.Query) query).iterate();
		while(iterator.hasNext()) {
			Student student=(Student)iterator.next();
			password=student.getPassword();
			number=student.getContact();
		}
		if(password==null) {
			System.out.println("you have no account please; sign up");
			return false;
		}
		else {
			System.out.println("please enter password");

			String pwd=sc.next();

			password=getDecryption(password);
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
				int otp=otpGeneration(email,number);
				String emailOTP=Integer.toString(otp);
				SendEmail.sendEmail(email, emailOTP);
				System.out.println("check your mail for varification code!!");
				System.out.println("enter OTP");
				String user_otp=sc.next();
				if(user_otp.equals(emailOTP)) {
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
				int otp=otpGeneration(email,number);
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
			pwd= getIncryption(pwd);
			Session session=null;
			session=App.getConfiguration();

			session.beginTransaction();
			Query query=session.createQuery("update from Student set password=:pwd where email=:mail");
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


	public static int otpGeneration(String email,long number) {

		Random random=new Random();
		int otp=random.nextInt(1000)+1000;

		return otp;

	}



















}

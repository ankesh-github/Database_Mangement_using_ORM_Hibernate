package org.dbms.javaApp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	
	///email validation///
	
	public static boolean getMailFormat(String mail) {
		
		String pattern="^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + 
							"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"; 
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(mail);
		return(m.find()&&m.group().equals(mail)); 

	}
	
	///number format validation////
	
	public static boolean getNumberFormat(long num) {
		String st=Long.toString(num);
		Pattern p=Pattern.compile("^[6-9][0-9]{9}$");;
		Matcher m=p.matcher(st);

		return (m.find()&&m.group().equals(st));
	}
}

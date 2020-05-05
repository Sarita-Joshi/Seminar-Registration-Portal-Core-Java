
package com;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class User implements Serializable{
	
	public String name;
	public String mobileNo;
	public String emailId;
	public int sid;
	public int qualifications; //any criteria
	public String method;
	
	
	public User(int i){sid=i;}
	
	
	public User(String name, String mobileNo, String emailId, int sid,
			int qualifications) {		
		this.name = name;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.sid = sid;
		this.qualifications = qualifications;
	}
	
	


	public User(String emailId, int sid, String method) {
		super();
		this.emailId = emailId;
		this.sid = sid;
		this.method = method;
	}


	public void accept() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("enter name:");
		this.name = sc.nextLine();
		System.out.print("enter mobile number:");
		this.mobileNo = sc.next();
		System.out.print("enter email id:");
		this.emailId = sc.next();
		System.out.print("enter qualification(ME=1/BE=2/other=3):");
		this.qualifications = sc.nextInt();	
	//	sc.close();
	}
	
	


	@Override
	public String toString() {
		return  name + ", " + mobileNo + ", " + emailId + "\n";
	}
	

	


	
		
	
	

}

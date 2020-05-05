package com;

import java.io.Serializable;
import java.util.Scanner;

public class Speaker implements Serializable{

	public String name;
	public String qualn;
	public String emailId;
	public String curWork;
	public int sid;
	public int yearsExp;
	
	public Speaker(int i){sid=i;}
	
	public Speaker(String name, String qualn, String emailId, String curWork, int sid, int yearsExp) {
		super();
		this.name = name;
		this.qualn = qualn;
		this.emailId = emailId;
		this.curWork = curWork;
		this.sid = sid;
		this.yearsExp = yearsExp;
	}

	@Override
	public String toString() {
		return  name + ", " + ", " + emailId + "\n";
	}

}

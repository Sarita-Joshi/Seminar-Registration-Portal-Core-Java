package com;

import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.*;

public class Seminar implements Serializable{

	public String name, domain;
	public float duration;
	public int capacity;
	public Date date;
	public float price;
	public utility util;
	public int id;
	
	@Override
	public String toString() {
			
		return id + "= [name=" + name + ", domain=" + domain + ", duration="
				+ duration + ", date=" + date + ", Cost=" + price + "]\n";
	}

	
	public Seminar() {
		util = new utility();
		
	}

	public Seminar(int id, String name, String domain, float duration, int capacity, float price,
			Date date) {
		this.id=id;
		this.name = name;
		this.domain = domain;
		this.duration = duration;
		this.capacity = capacity;
		this.price = price;
		this.date = date;
		util = new utility(capacity);
	}
	
	public Seminar(String name, String domain, float duration, int capacity, float price,
			Date date) {
		
		this.name = name;
		this.domain = domain;
		this.duration = duration;
		this.capacity = capacity;
		this.price = price;
		this.date = date;
		util = new utility(capacity);
	}
	
	
	@SuppressWarnings("deprecation")
	public void addSeminar() throws ParseException{
		Scanner sc = new Scanner(System.in);
		System.out.println("seminar name : ");
		name = sc.nextLine();
		System.out.println("seminar domain : ");
		domain = sc.nextLine();
		System.out.println("seminar duration : ");
		duration = sc.nextFloat();
		System.out.println("seminar date : ");		
		SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d = dt.parse(sc.next());		
		date = new Date(d.getYear(), d.getMonth(), d.getDate());
		System.out.println("seminar capacity : ");
		capacity = sc.nextInt();				
		System.out.println("seminar cost : ");
		price = sc.nextFloat();
		//sc.close();		
		util.setNoOfSeats(capacity);
	}
	
	public int getId(){
		return id;		
	}

	public void setId(int i){
		id=i;		
	}

	
	
	
}

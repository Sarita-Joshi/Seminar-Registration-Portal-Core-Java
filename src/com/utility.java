package com;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.PriorityQueue;

import jdbcCode.Connect;

public class utility implements Serializable{	

	public int noOfSeats;	
	
	public utility() {
	
		this.noOfSeats = 5;
	}

	public utility( int noOfSeats) {		
		this.noOfSeats = noOfSeats;			
	}
	
	public int getNoOfSeats() {
		return noOfSeats;
	}
	
    public void setNoOfSeats(int id) {
    	noOfSeats = id;    	
	}

	public void addRecord(int id) {		
		
		Connection con = Connect.getConnection();
		User user = new User(id);
		System.out.println("Enter personal information:");		
		user.accept();						
		Connect.insertUser(con, user);			
		System.out.println("Thank you.");
		Connect.closeConnection(con);		
		
	}
	
public void addRecord(User user) {		
		System.out.println("util");
		Connection con = Connect.getConnection();		
		//System.out.println("Enter personal information:");
		Connect.insertUser(con, user);			
		System.out.println("Thank you.");
		Connect.closeConnection(con);		
		
	}

	

public Boolean payment(String user, String mth, int id){
		Connection con = Connect.getConnection();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery("select * from user where email='"+user+"'");
			if(rs.next()){
				int pid = rs.getInt(6);
				rs = stmt.executeQuery("select * from seminar where sid="+id);				
				rs.next();
				int amount = (int) rs.getFloat(6);
				Connect.insertPayment(con, id, user, mth, amount,pid);				
				return true;			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			//return "invalid email";
		}
		return false;
		
		
	}

	public ArrayList<User> selecterList(int id)  {
		
		ArrayList<User> userList = new ArrayList<User>(noOfSeats);
		ArrayList<User> removeList = new ArrayList<User>();
		Connection con=Connect.getConnection();
		ResultSet rs = Connect.displayUserList(con,id);		
		int no=noOfSeats;
		try {
			while(rs.next() && no>0){	
				User u = new User(rs.getString("name"),rs.getString("mobile"),rs.getString("email"),
						rs.getInt("sid"),rs.getInt("qualifications"));
				userList.add(u);
				no--;
			}
			while(rs.next()){
				User u = new User(rs.getString("name"),rs.getString("mobile"),rs.getString("email"),
						rs.getInt("sid"),rs.getInt("qualifications"));
				removeList.add(u);			
			}
			if(!removeList.isEmpty()){
			Connect.removeApplications(con,removeList);
			}
			if(userList.isEmpty()){
				System.out.println("There are no users.");
			}else{
				System.out.println("Available seats :" + no);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	
		Connect.closeConnection(con);
		return userList;
		
	}

	public ArrayList<User> getPendingPaymentList(int id) {
		ArrayList<User> paymentList = new ArrayList<User>();	
		Connection con=Connect.getConnection();
		ResultSet rs = Connect.pendingPaymentList(con,id);			
		try {			
			while(rs.next())
			{	
				User u = new User(rs.getString("name"),rs.getString("mobile"),rs.getString("email"),
						rs.getInt("sid"),rs.getInt("qualifications"));
				paymentList.add(u);
			}
			if(paymentList.isEmpty()){
				System.out.println("There are no pending payments");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		
		Connect.closeConnection(con);
		return paymentList;				
	}


	
	
}
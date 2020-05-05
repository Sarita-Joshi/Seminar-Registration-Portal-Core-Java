package jdbcCode;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.Seminar;
import com.Speaker;
import com.User;


public class Connect {
	
	public static Connection getConnection(){
		Connection con = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");				
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement","root", "root");
		} catch (Exception e) {		
			e.printStackTrace();
		} 
				
		return con;		
	}
	
	public static int insertSeminar(Connection con,Seminar s1){		
		
		try {
			PreparedStatement stmt = con.prepareStatement("insert into seminar"
					+ "(name,domain,duration, capacity, price, date) values(?,?,?,?,?,?)");			
			stmt.setString(1, s1.name);
			stmt.setString(2, s1.domain);
			stmt.setDouble(3, s1.duration);
			stmt.setInt(4, s1.capacity);
			stmt.setDouble(5, s1.price);
			stmt.setDate(6, s1.date);
			stmt.executeUpdate();
			System.out.println("Inserted!");
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select sid from seminar where name='"+s1.name+"'and domain='"+ s1.domain+"'");
			if(rs.next()){
				return rs.getInt(1);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return 0;
	}
	
public static void insertSpeaker(Connection con,Speaker s1){		
		
		try {
			PreparedStatement stmt = con.prepareStatement("insert into speaker"
					+ "(name,qualification,email, sid, yearsOfExperience) values(?,?,?,?,?)");			
			stmt.setString(1, s1.name);
			stmt.setString(2, s1.qualn);
			stmt.setString(3, s1.emailId);
			stmt.setInt(4, s1.sid);
			stmt.setInt(5, s1.yearsExp);
			stmt.executeUpdate();
			System.out.println("Inserted!");			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	
	}

public static void insertPayment(Connection con,int id, String em, String mth, int amount,int pid){		
	
	try {
		PreparedStatement stmt = con.prepareStatement("insert into payment"
				+ "(email, sid, method, amount,pid) values(?,?,?,?,?)");			
		stmt.setString(1, em);
		stmt.setInt(2, id);
		stmt.setString(3, mth);
		stmt.setInt(4, amount);
		stmt.setInt(5, pid);
		stmt.executeUpdate();
		System.out.println("Inserted!");			
		
	} catch (SQLException e) {			
		e.printStackTrace();
	}

}

	public static ResultSet displaySeminar(Connection con){
		//Connect.makeChanges(con);
		ResultSet rs = null;
		try {
			 Statement stmt = con.createStatement();			 
			 rs = stmt.executeQuery("select * from seminar");	
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("aa");
		}
		
		return rs;
	}
	
	private static void makeChanges(Connection con){
		Statement stmt;
		try {
			stmt = con.createStatement();
			 stmt.executeUpdate("delete from seminar where date < curdate()");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static ResultSet displayUserList(Connection con, int id){
	ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();			
			 rs = stmt.executeQuery("select * from user where sid="+id + " order by qualifications");				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void insertUser(Connection con,User s1){
		
		try {
			PreparedStatement stmt = con.prepareStatement("insert into user"
					+ "(name, mobile, email, qualifications, sid, payment) values(?,?,?,?,?,?)");
			stmt.setString(1, s1.name);
			stmt.setString(2, s1.mobileNo);
			stmt.setString(3, s1.emailId);
			stmt.setInt(4, s1.qualifications);
			stmt.setInt(5, s1.sid);
			stmt.setInt(6, 0);
			stmt.executeUpdate();
			System.out.println("Inserted!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResultSet pendingPaymentList(Connection con, int id){
	ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();			
			 rs = stmt.executeQuery("select * from user where sid="+id +" and payment not in (select pid from payment where sid="+id+")");				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void removeApplications(Connection con, ArrayList<User> list){
		Statement stmt;
		Iterator<User> itr = list.iterator();
		try {
			stmt = con.createStatement();
			while(itr.hasNext()){
				User u = itr.next();
			    stmt.executeUpdate("delete from user where name='"+u.name+"' and sid=" + u.sid);
			}
			System.out.println("done!");
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}

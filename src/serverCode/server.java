package serverCode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jdbcCode.Connect;

import com.Seminar;
import com.Speaker;
import com.User;

public class server implements Runnable{
	
	ServerSocket server1;
	
	Socket socket;
	DataInputStream inFromClient;
	DataOutputStream outToClient;
	ObjectOutputStream oos;
    ObjectInputStream  ois;
	OutputStreamWriter os;
	ResultSet rs;
	
	Map<Integer, Seminar> map = new HashMap<Integer, Seminar>();
	Vector<Seminar> sem = new Vector<>();
	int count=0,id;
	boolean flag=true;
	
	public server(int port) {
		try {
			
			server1 = new ServerSocket(port);
				
		System.out.println("Server started...");
		
		socket = server1.accept();
		
		System.out.println("client accepted...");
		
		inFromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		outToClient = new DataOutputStream(socket.getOutputStream());
		 oos = new ObjectOutputStream(socket.getOutputStream());
          ois = new ObjectInputStream( socket.getInputStream());
          socket.setSoTimeout(1000000);
        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
public void getData(){
	Connection con = Connect.getConnection();
	String d=null;
	ResultSet rs = Connect.displaySeminar(con);
	
	map.clear();
	sem.clear();
		try {
			while(rs.next()){				
					map.put(rs.getInt(1), new Seminar(rs.getInt(1), rs.getString(2), 				
						rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getFloat(6), rs.getDate(7)));
			sem.add(new Seminar(rs.getInt(1), rs.getString(2), 				
						rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getFloat(6), rs.getDate(7)));		
			}	
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	
}
	
	
	void checkSeminar() throws SQLException{
		Connection con = Connect.getConnection();
	
		ResultSet rs = Connect.displaySeminar(con);
		
			while(rs.next()){
				if(!map.containsKey(rs.getInt(1))) {
					map.put(rs.getInt(1), new Seminar(rs.getInt(1), rs.getString(2), 				
						rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getFloat(6), rs.getDate(7)));
				}
			}	
		
		con.close();
		
	}
	
	@Override
	public void run() {
		try {
			System.out.println("Server process...");
			//Map<Integer,Seminar> map = new HashMap<Integer,Seminar>();
			int count=0,id,ch;
			boolean flag=true;
			Scanner sc = new Scanner(System.in);
			
		
				
			int st1=0;
				String menu = "\n1.Register for seminar\n2.Accepted applications\n3.Payment\n4.New seminar\n5.Check pending Payments\n6.exit";				
				
//				oos.writeObject(menu);
//				oos.flush();
				
				while(st1!=6){				
					
					getData();				
					checkSeminar();
					st1 = Integer.parseInt((String) ois.readObject());
					System.out.println("hi2");
					switch(st1){
					case 1:{
						oos.writeObject(sem);
						oos.flush();						
						User user = (User)ois.readObject();
						map.get(user.sid).util.addRecord(user);						
						break;
					}
					case 2:{						
						oos.writeObject(sem);
						oos.flush();						
						id = Integer.parseInt((String)ois.readObject());						
						ArrayList<User> list = map.get(id).util.selecterList(id);					
						oos.writeObject(list);
						oos.flush();
						
						break;
					}
					
					case 4:{
						
						Seminar s1 = (Seminar)ois.readObject();				
					Connection con1 = Connect.getConnection();
					System.out.println("hi1");
					id = Connect.insertSeminar(con1, s1);
					try {
						con1.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					s1.setId(id);
					map.put(id,s1);
					oos.writeObject(id);
					oos.flush();
					Speaker s2 = (Speaker)ois.readObject();	
					 Connect.insertSpeaker(Connect.getConnection(), s2);
					
						break;
					}
					case 5:{
										
						oos.writeObject(sem);
						oos.flush();						
						id = Integer.parseInt((String)ois.readObject());						
						ArrayList<User> list = map.get(id).util.getPendingPaymentList(id);					
						oos.writeObject(list);
						oos.flush();
						break;						
					}
		case 3:{
						
						oos.writeObject(sem);
						oos.flush();								
						//payment ack						
						User u= (User)ois.readObject();
						String em = u.emailId;
						String method = u.method;
						id = u.sid;
						Boolean b = map.get(id).util.payment(em,method,id);
						if(b) {
						oos.writeObject("Thank you");
						oos.flush();
						}
						else {
						oos.writeObject("invalid email");
						oos.flush();
						}
						break;
					}
					default:
						flag=false;	
					}
					st1=0;
				 
				}
						
				
			} catch (Exception e) {
				
				System.out.println(e);
			}
		
	}
	
	

}

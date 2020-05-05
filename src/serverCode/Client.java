package serverCode;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
	
	Socket socket;
	DataInputStream inFromServer;
	DataOutputStream outToServer;
	ObjectOutputStream oos;
    ObjectInputStream  ois;
	static Client client;
	static boolean count=false;
	public Client(int port) {		
		
		try {
			
			socket = new Socket("localhost", port);		
			oos = new ObjectOutputStream(socket.getOutputStream());
	        ois = new ObjectInputStream(socket.getInputStream());
	        socket.setSoTimeout(1000000);
	       
		inFromServer = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		outToServer = new DataOutputStream(socket.getOutputStream());
		count=true;
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public static synchronized Client getInstance(){
		if(!count) {
			client = new Client(2001) ;
			
		}
		return client;
	}
	
	public void sendData(Object o) {
		// TODO Auto-generated method stub
		try {
			oos.writeObject(o);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Object readData() {
		// TODO Auto-generated method stub
		try {
			return ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		try{
				Client c = Client.getInstance();
				Scanner sc = new Scanner(System.in);
				String str="";
				
							
				while(str!="6"){
					str = (String)c.ois.readObject();
					System.out.println(str);
					System.out.print("Client: ");
					str = sc.nextLine();				
					c.oos.writeObject(str);	
					c.oos.flush();												
				}
				
			} catch (IOException e) {		
				e.printStackTrace();
			}		
			
		

		
	}


	

}

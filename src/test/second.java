package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import serverCode.server;

public class second {

	public static void main(String[] args) {
		
		Runnable r1 = new server(2001);
	//	Runnable r2 = new server(2002);
		
		ExecutorService pool =  Executors.newFixedThreadPool(2);
		
		pool.execute(r1);
		//pool.execute(r2);
		
		pool.shutdown();

	}

}

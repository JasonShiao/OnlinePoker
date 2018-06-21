package com.onlinePoker.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocketHandler extends Thread {
	
	public Socket soc;
	
	private BufferedReader in;
	private PrintWriter out;

	public ClientSocketHandler(String serverIP) throws IOException {
		
		super();
		this.soc = new Socket(serverIP, 9000);
		
		in = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		out = new PrintWriter(this.soc.getOutputStream(), true);
		
		System.out.println(in.readLine());
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		
	}
	
	public String sendToServer(String message) throws IOException {
		
		out.println(message);
		return in.readLine();
	}
	
}

package com.onlinePoker.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientSocketHandler extends Thread {
	
	public Socket soc;
	
	private BufferedReader in;
	private PrintWriter out;

	public ClientSocketHandler(Socket soc) throws IOException {
		
		super();
		this.soc = soc;
		
		in = new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		out = new PrintWriter(this.soc.getOutputStream(), true);
		
		System.out.println(in.readLine());
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		
		while(true) {
			try {
				soc.setSoTimeout(500);
				String fromServer = in.readLine();
				if(!fromServer.equals("") && fromServer != null) {
					System.out.println(fromServer);
				}
			} catch (IOException e) {
				
			}
		}
		
	}
	
	public String sendToServer(String message) throws IOException {
		soc.setSoTimeout(0);
		out.println(message);
		return in.readLine();
	}
	
}

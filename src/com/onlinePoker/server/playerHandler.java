package com.onlinePoker.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class playerHandler extends Thread {

	private Socket soc;
	private Card cardList[];
	private BufferedReader in;
	private PrintWriter out;
	
	private int userIndex;
	private String playerName;
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public playerHandler(Socket soc, String playerName, int userIndex) throws IOException {
		this.soc = soc;
		this.playerName = playerName;
		this.userIndex = userIndex;
		
		in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		
		out = new PrintWriter(soc.getOutputStream(), true); 
		
	    //Server.printWriters.add(new PrintWriter(soc.getOutputStream(), true));
		//soc.setSoTimeout(500);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		
		
		
	}
	
	
}

package com.onlinePoker.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class Server {

	private int numPlayer;
	static ArrayList<String> readyList = new ArrayList<String>();
	//static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();
	static ArrayList<playerHandler> playerHandlers = new ArrayList<playerHandler>();
	ServerSocket ss;
	
	
	public Server() {
		numPlayer = 0;
	}
	
	
	void start() throws IOException {
		
		System.out.println("Waiting for clients ...");
		// Only be used to accept incoming client connection, not communication
		ss = new ServerSocket(9000); // Port: 9000
		
		while(true) {
			
			try {
				Socket soc = ss.accept(); // Once accept a connection, return a Socket object
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
				
				out.println("Please enter an username.");
				
				String username = null;
				username = in.readLine();
				while(!validUser(username)) {
					out.println("Invalid username or the username has been used by others.");
					username = in.readLine();
				}
				
				playerHandlers.add(new playerHandler(soc, username, numPlayer));
				
				numPlayer += 1;
				System.out.println("Player#" + numPlayer + ", " + username + ", connected!");
				
				out.println("Hi, " + username + ", Welcome to Online Poker!");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// Player number = 3 or 4 and everyone is ready
			// Break the loop
				
		}
		
		
	}
	
	
	public boolean gameStart() {
		
		// User action/response
		
		
		return true;
	}
	
	
	private boolean validUser(String s) {
		
		if(s == null) {
			return false;
		}
		
		for(playerHandler ph: playerHandlers) {
			if(s.equals(ph.getPlayerName())) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Server pokerServer = new Server();
		
		while(true) {
			pokerServer.start();
			pokerServer.gameStart();
		}
		
	}

}

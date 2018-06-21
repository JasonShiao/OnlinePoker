package com.onlinePoker.client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String serverIP;
	public String username;
	
	public ClientSocketHandler socketHandler;
	
	private BufferedReader userInput;
	
	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public String getUsername() {
		
		return username;
		
	}

	public boolean setUsername(String username) throws IOException {
		
		String response = socketHandler.sendToServer(username);
		if(response.equals("Invalid username or the username has been used by others.")) {
			return false;
		}else{
			this.username = username;
			return true;
		}
		
	}

	
	public Client() {
		serverIP = null;
		username = null;
		userInput = new BufferedReader(new InputStreamReader(System.in));
	}
	
	
	public void Connect() throws UnknownHostException, IOException {
		
		socketHandler = new ClientSocketHandler(getServerIP());
		socketHandler.start();
		
	}

	
	public static void main(String[] args) {
		
		Client client = new Client();
		
		ClientGUI clientGUI = new ClientGUI(client);
		Thread GUIThread = new Thread(clientGUI);
		
		System.out.println("Start client.");
		
		GUIThread.start();
		
	}
}

package com.onlinePoker.client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String serverIP;
	private String username;
	private boolean serverIPFlag;
	private boolean usernameFlag;
	
	//public ClientSocketHandler socketHandler;
	
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

	public void setUsername(String username) {
		this.username = username;
	}
	
	public boolean isServerIPFlag() {
		return serverIPFlag;
	}

	public void setServerIPFlag(boolean serverIPFlag) {
		this.serverIPFlag = serverIPFlag;
	}

	public boolean isUsernameFlag() {
		return usernameFlag;
	}

	public void setUsernameFlag(boolean usernameFlag) {
		this.usernameFlag = usernameFlag;
	}

	
	
	public Client() {
		serverIP = null;
		username = null;
		serverIPFlag = false;
		usernameFlag = false;
		userInput = new BufferedReader(new InputStreamReader(System.in));
	}
	
	
	/*public void Connect() throws UnknownHostException, IOException {
		
		socketHandler = new ClientSocketHandler(getServerIP());
		socketHandler.start();
		
	}*/

	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		Client client = new Client();
		ClientGUI clientGUI = new ClientGUI(client);
		Thread GUIThread = new Thread(clientGUI);
		ClientSocketHandler socketHandler;
	
		System.out.println("Start client.");
		
		Socket tmp_soc = null;
		
		clientGUI.showIPSettingPage();
		while(client.serverIPFlag == false) {
			Thread.sleep(50); // MUST do something or sleep a while in the while loop
			if(client.getServerIP() != null) {
				try {
					tmp_soc = new Socket(client.getServerIP(), 9000);
				} catch (IOException e) {
					client.serverIPFlag = false;
					client.setServerIP(null);
					System.out.println("Fail to Connect. Try again!");
				}
			}
		}
		
		socketHandler = new ClientSocketHandler(tmp_soc);
		
		clientGUI.showUsernameSettingPage();
		while(client.usernameFlag == false) {
			Thread.sleep(50); // MUST do something or sleep a while in the while loop
			if(client.getUsername() != null) {
				String response = socketHandler.sendToServer(client.username);
				System.out.println(response);
				if(response.equals("Invalid username or the username has been used by others.")) {
					client.setUsername(null);
					client.usernameFlag = false;
				}
			}
		}
		
		/* End of Log-in */
		
		/*  Game Panel  */
		clientGUI.showGamepanel();
		
		GUIThread.start();
		socketHandler.start();
		
		
		
		
		//GUIThread.start();
		
	}
}

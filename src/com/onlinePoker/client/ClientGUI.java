package com.onlinePoker.client;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientGUI extends JFrame implements Runnable {	
	
	private JPanel Initpanel;
	private JTextField txtInit;
	private JButton btnConfirm;
	private JLabel lblInit;
	private JPanel Gamepanel;
	private JLabel lblWelcomeLabel;
	
	private Client client;

	public ClientGUI(Client client) {
		
		this.client = client;
		
		getContentPane().setLayout(new CardLayout(0, 0));
		
		
		Initpanel = new JPanel();
		getContentPane().add(Initpanel, "name_545972988502585");
		Initpanel.setLayout(null);
		
		txtInit = new JTextField();
		txtInit.setBounds(296, 282, 252, 46);
		
		txtInit.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		
		txtInit.setHorizontalAlignment(SwingConstants.CENTER);
		Initpanel.add(txtInit);
		txtInit.setColumns(12);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		btnConfirm.setBounds(366, 340, 116, 100);
		
		Initpanel.add(btnConfirm);
		
		lblInit = new JLabel("");
		lblInit.setBounds(260, 210, 327, 60);
		lblInit.setHorizontalAlignment(SwingConstants.CENTER);
		lblInit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		Initpanel.add(lblInit);
		
		Initpanel.setVisible(true);
		
		Gamepanel = new JPanel();
		getContentPane().add(Gamepanel, "name_545972965405386");
		Gamepanel.setLayout(null);
		
		lblWelcomeLabel = new JLabel("Welcome");
		lblWelcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLabel.setBounds(0, 300, 800, 100);
		Gamepanel.add(lblWelcomeLabel);
		
		Gamepanel.setVisible(false);
		
		setSize(new Dimension(800, 800));
		setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void run() {
		
		showSettingPage();
		
		
		
	}
	
	
	public void showSettingPage() {
		
		lblInit.setText("Enter server IP Address");
		txtInit.setText("localhost");
		txtInit.selectAll();
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.setServerIP(txtInit.getText());
				try {
					client.Connect();
				} catch (IOException e1) {
					e1.printStackTrace();
					System.out.println("Invalid IP Address or Connection Error");
				} finally {
					showUsernameSettingPage();
				}
			}
		});
	
	}
	
	public void showUsernameSettingPage() {
		
		lblInit.setText("Enter your name");
		txtInit.setText("Default_User");
		
		ActionListener listeners[] = btnConfirm.getActionListeners();
		for(ActionListener listener: listeners) {
			btnConfirm.removeActionListener(listener);
		}
		btnConfirm.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				btnConfirm.setEnabled(false);
				try {
					if(client.setUsername(txtInit.getText())){
						System.out.println("username set: " + client.getUsername());
					} else {
						btnConfirm.setEnabled(true);
						System.out.println("Invalid username or the username has been used by others.");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("Error occurs when sending packets to server!");
				}
				
			}
			
		});
		
		
	}
	
	
}

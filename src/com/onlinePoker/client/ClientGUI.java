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
		
		//showSettingPage();
		
		
		
	}
	
	
	public void showIPSettingPage() {
		
		lblInit.setText("Enter server IP Address");
		txtInit.setText("localhost");
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( !client.isServerIPFlag() ) {
					client.setServerIP(txtInit.getText());		
					client.setServerIPFlag(true);
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
				if( !client.isUsernameFlag() ) {
					client.setUsername(txtInit.getText());
					client.setUsernameFlag(true);
				}
			}
		});
	}

	public void showGamepanel() {
		Initpanel.setVisible(false);
		Gamepanel.setVisible(true);
	}
	
	
}

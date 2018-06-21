
import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;

public class Client extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String serverIP;
	public String username;
	public Socket soc;
	
	private BufferedReader userInput;
	private BufferedReader in;
	private PrintWriter out;
	
	
	private JPanel Initpanel;
	private JTextField txtInit;
	private JButton btnConfirm;
	private JLabel lblInit;
	private JPanel Gamepanel;
	private JLabel lblNewLabel;
	
	public void initConnect() {
		
		lblInit.setText("Enter server IP Address");
		txtInit.setText("localhost");
		txtInit.selectAll();
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serverIP = txtInit.getText();
				System.out.println("Start client.");
				
				try {
					soc = new Socket(serverIP, 9000);
					
					userInput = new BufferedReader(new InputStreamReader(System.in)); // Read data from console
					in = new BufferedReader(new InputStreamReader(soc.getInputStream())); // Read data from Socket
					out = new PrintWriter(soc.getOutputStream(), true);
					
					requestUsername();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		
	}
	
	public void requestUsername(){
		
		try {
			System.out.println(in.readLine());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblInit.setText("Enter your name");
		txtInit.setText("Default_User");
		
		ActionListener listeners[] = btnConfirm.getActionListeners();
		for(ActionListener listener: listeners) {
			btnConfirm.removeActionListener(listener);
		}
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = txtInit.getText();
				System.out.println("username set: " + username);
				out.println(username);
				
				// Tell whether the username is available
				
				
				start();
				
			}
		});
		
	}
	
	public void start() {
		
		Initpanel.setVisible(false);
		Gamepanel.setVisible(true);
		
		
	}
	
	
	public Client() {
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
		
		lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 300, 800, 100);
		Gamepanel.add(lblNewLabel);
		
		Gamepanel.setVisible(false);
		
		setSize(new Dimension(800, 800));
		setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	

	
	public static void main(String[] args) {
		
		Client client = new Client();
		
		client.initConnect();
		
		
	}
}

package gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import javax.swing.*;
import peer.Client;

public class ClientPasswordInputWindow extends JFrame {
	// OVERVIEW: ClientPasswordInputWIndow is a subclass of JFrame, it is
	// public, it is created
	// when a client is asked for a password

	// Data Type
	private String password = ""; // cannot be null
	private ServerVariable sV; // cannot be null
	private String userName; // cannot be null
	private JButton cancelButton; // cannot be null
	private JButton enterButton; // cannot be null
	private JPasswordField passwordInputField; // cannot be null

	// Constructor
	public ClientPasswordInputWindow(ServerVariable b, String userName) {
		// EFFECTS:create a ClientPasswordInputWindow with a server reference
		// and a user name
		// then call the initiComponents() function
		this.sV = b;
		this.userName = userName;
		initComponents();
	}

	private void initComponents() {
		// EFFECTS: initialize the GUI components for the class,this function
		// should be called by the constructor only
		// set the layout for the components;
		// initialize GUI components and add text on each compnents;
		// add mouse release listener for enterButton and cancelButton;
		// add Key pressed listener for passwordInputField;
		// set this to be non resizable;
		passwordInputField = new javax.swing.JPasswordField();
		enterButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Please enter the password");
		setAlwaysOnTop(true);
		setResizable(false);

		passwordInputField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				passwordInputFieldKeyPressed(evt);
			}
		});

		enterButton.setText("Enter");
		enterButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				try {
					enterButtonMouseReleased(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				cancelButtonMouseReleased(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addGap(21, 21, 21)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				enterButton,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				81,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				cancelButton,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				82,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																passwordInputField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																201,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(24, Short.MAX_VALUE)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												passwordInputField,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																enterButton)
														.addComponent(
																cancelButton))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		pack();
	}

	// Mutator
	private void passwordInputFieldKeyPressed(java.awt.event.KeyEvent evt) {
		// EFFECTS: if the evt is null then throws NullPointerException
		// else if "Enter" key is pressed then set password equals to
		// passwordInputField.getPassword();
		// then try to create a client with hostName,Port,userName and password
		// if an IOException occurs, then catch and printStackTrace();
		// then run the client thread and dipose this;
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			char[] temp;
			temp = passwordInputField.getPassword();
			for (int i = 0; i < temp.length; i++) {
				password = password.concat(Character.toString(temp[i]));
			}
			InetSocketAddress address = (InetSocketAddress) sV.getAddress();
			try {
				Client c = new Client(address.getHostName(), address.getPort(),
						this.userName, this.password);
				c.start();
				dispose();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private void enterButtonMouseReleased(java.awt.event.MouseEvent evt)
			throws IOException {
		// EFFECTS: if IO problems occur then throw IOException
		// else if set password equals to passwordInputField.getPassword();
		// then create a client with hostName,Port,userName and password
		// then run the client thread and dipose() this
		password = new String(passwordInputField.getPassword());
		InetSocketAddress address = (InetSocketAddress) sV.getAddress();
		Client c = new Client(address.getHostName(), address.getPort(),
				userName, password);
		c.start();
		dispose();

	}

	private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {
		// EFFECTS: close this
		dispose();
	}

	// Observers
	public String getInputPassword() {
		// EFFECTS: return the password in a String
		return this.password;
	}

}

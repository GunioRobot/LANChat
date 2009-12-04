package gui;

import java.io.IOException;
import javax.swing.*;
import peer.Server;

public class CreateServerWindow extends JFrame {
	// OVERVIEW: CreateServerWindow is a public class that extends JFrame

	// Data Type
	// All the rep variance cannot be null
	//AF(c) = c.userName = this.userName, 
	private static final long serialVersionUID = 1L;
	private String userName;
	private JButton cancelButton;
	private JButton createrServerButton;
	private java.awt.Checkbox passwordCheckbox;
	private java.awt.TextField passwordField;
	private JTextField serverNameField;
	private JLabel serverNameLabel;

	// Constructor
	public CreateServerWindow(String userName) {
		// EFFECTS: create a CreateSeverWindow object
		// set this.userName equals to userName
		// call initComponents() functions
		this.userName = userName;
		initComponents();
	}

	private void initComponents() {
		// EFFECTS: initialize the GUI components for the class,this function
		// should be called by the constructor only
		// set the layout for the components;
		// initialize GUI components and add text on each components;
		// add listeners to components;
		createrServerButton = new javax.swing.JButton();
		cancelButton = new javax.swing.JButton();
		serverNameLabel = new javax.swing.JLabel();
		serverNameField = new javax.swing.JTextField();
		passwordCheckbox = new java.awt.Checkbox();
		passwordField = new java.awt.TextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Create a Server");
		setAlwaysOnTop(true);
		setResizable(false);
		setLocationByPlatform(true);
		createrServerButton.setText("Create");
		createrServerButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				try {
					createrServerButtonMouseReleased(evt);
				} catch (IOException e) {
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

		serverNameLabel.setFont(new java.awt.Font("Times New Roman", 0, 12));
		serverNameLabel
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		serverNameLabel.setText("Server Name");

		passwordCheckbox.setFont(new java.awt.Font("Times New Roman", 0, 12));
		passwordCheckbox.setLabel("Password Enable");
		passwordCheckbox.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				passwordCheckboxMouseReleased(evt);
			}
		});

		passwordField.setEditable(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout
				.setHorizontalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								layout
										.createSequentialGroup()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addGap(
																				24,
																				24,
																				24)
																		.addComponent(
																				serverNameLabel,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				83,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				serverNameField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				244,
																				Short.MAX_VALUE))
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																layout
																		.createSequentialGroup()
																		.addGap(
																				39,
																				39,
																				39)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								passwordCheckbox,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								createrServerButton,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								103,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								passwordField,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								204,
																								Short.MAX_VALUE)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												cancelButton,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												102,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(
																												8,
																												8,
																												8)))))
										.addGap(32, 32, 32)));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																serverNameLabel,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																25,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																serverNameField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																21,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																passwordCheckbox,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																passwordField,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																createrServerButton)
														.addComponent(
																cancelButton))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		pack();
	}

	// Mutators
	private void passwordCheckboxMouseReleased(java.awt.event.MouseEvent evt) {
		// EFFECTS: if evt is null then throws NullPointerException;
		// else if the state of passwordCheckbox is true then set passwordField
		// to be non-editable
		// else if the state of passwordCheckbox is false then set passwordField
		// to be editable
		if (passwordCheckbox.getState() == true) {
			passwordField.setEditable(false);
		} else {
			passwordField.setEditable(true);
		}
	}

	private void createrServerButtonMouseReleased(java.awt.event.MouseEvent evt)
			throws IOException {
		// EFFECTS: if evt is null then throws NullPointerException
		// else if IO problems occur then throw IOException
		// else if create a Server object with required input arguments
		// then start the server thread and dispose THIS;

		Server s = new Server(serverNameField.getText(), passwordField
				.getText(), passwordCheckbox.getState(), userName);
		s.start();
		dispose();
	}

	private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {
		// EFFECTS: if evt is Null then throws NullPointerException
		// else if disopse THIS
		dispose();
	}

	public boolean repOk() {
		return (userName != null && cancelButton != null && createrServerButton != null &&
				passwordCheckbox != null && passwordField != null && serverNameField != null &&
				serverNameLabel != null);
	}
	
	public String toString(){
		
		return this.userName;
	}
}

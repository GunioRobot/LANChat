package gui;

/*
 * StartupWindow.java
 *
 * Created on 2009-11-12, 0:34:36
 */
import java.awt.Window;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.*;
import networking.*;
import peer.*;

public class StartupWindow extends JFrame implements
		ServerFinder.ServerListener {
	// OVERVIEW: StartupWindow is a public and mutable class
	// it extends JFrame class and implements ServerFinder.ServerListener
	// interface

	// Data Type
	// All the rep invar cannot be null
	private JLabel NickNameLabel;
	private JTextField NickNameTextField;
	private JButton QuitButton;
	private JButton ServerButton;
	private JList ServerList;
	private JScrollPane jScrollPane1;
	private JButton joinServerButton;
	private JLabel mainTitle;
	private Vector<ServerVariable> svList = new Vector<ServerVariable>();
	private JLabel DefineLabel;
	private JLabel HostNameLabel;
	private JTextField HostNameTextF;
	private JLabel PortLabel;
	private JTextField PortTextField;
	private JButton JoinYourDefinedServerButton;
	private JTextField passwordTF;
	private JLabel passwordLabel;

	// Constructors
	public StartupWindow() throws IOException {
		// EFFECTS: if IO problems occur then throw IOException
		// else if create a new StartupWindow object by calling the
		// initComponents() and initOthers functions
		initComponents();
		initOthers();
	}

	private void initOthers() throws IOException {
		// EFFECTS: if IO problems occur then throw IO Exception
		// else if initialize a server finder thread and start it
		ServerFinder finder = new ServerFinder(ServerAnnouncer.defaultAddress,
				ServerAnnouncer.defaultPort);
		finder.addServerListener((ServerFinder.ServerListener) this);
		finder.start();
	}

	private void initComponents() {
		// EFFECTS: initialize the GUI components for the class,this function
		// should be called by the constructor only
		// set the layout for the components;
		// initialize GUI components and add text on each components;
		// add listeners to components;
		// initialize the data for ServerList;

		joinServerButton = new javax.swing.JButton();
		ServerButton = new javax.swing.JButton();
		NickNameTextField = new javax.swing.JTextField();
		NickNameLabel = new javax.swing.JLabel();
		mainTitle = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		ServerList = new javax.swing.JList();
		QuitButton = new javax.swing.JButton();
		JoinYourDefinedServerButton = new javax.swing.JButton();
		DefineLabel = new javax.swing.JLabel();
		HostNameTextF = new javax.swing.JTextField();
		PortTextField = new javax.swing.JTextField();
		HostNameLabel = new javax.swing.JLabel();
		PortLabel = new javax.swing.JLabel();
		passwordLabel = new JLabel();
		passwordTF = new JTextField();
		passwordTF.setText("");
		passwordLabel.setText("Password");
		JoinYourDefinedServerButton.setText("Join Specific Server");
		JoinYourDefinedServerButton
				.addMouseListener(new java.awt.event.MouseAdapter() {
					public void mouseReleased(java.awt.event.MouseEvent evt) {
						try {
							JoinYourDefinedServerButtonMouseReleased(evt);
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
		DefineLabel.setFont(new java.awt.Font("Times New Roman", 1, 12));
		DefineLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		DefineLabel.setText("Define Your Own Server");

		HostNameLabel.setText("Host IP");

		PortLabel.setText("Port");

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("LANChat");
		setLocationByPlatform(true);
		joinServerButton.setText("Join Server");
		joinServerButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				joinServerButtonMouseReleased(evt);
			}
		});

		ServerButton.setText("Create a Server");
		ServerButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				ServerButtonMouseReleased(evt);
			}
		});

		NickNameLabel.setText("NickName");

		mainTitle.setFont(new java.awt.Font("Times New Roman", 1, 24));
		mainTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		mainTitle.setText("LANChat");
		mainTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		ServerList.setEnabled(true);
		ServerList.setListData(svList);
		ServerList.setModel(new javax.swing.AbstractListModel() {
			public int getSize() {
				return svList.size();
			}

			public Object getElementAt(int i) {
				return svList.get(i);
			}

		});

		jScrollPane1.setViewportView(ServerList);

		QuitButton.setText("Quit");
		QuitButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				QuitButtonMouseReleased(evt);
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
										.addContainerGap()
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																mainTitle,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																428,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																layout
																		.createSequentialGroup()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				226,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								DefineLabel,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								196,
																								Short.MAX_VALUE)
																						.addGroup(
																								layout
																										.createSequentialGroup()
																										.addComponent(
																												NickNameLabel,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												68,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																QuitButton,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																124,
																																Short.MAX_VALUE)
																														.addComponent(
																																joinServerButton,
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																124,
																																Short.MAX_VALUE)
																														.addComponent(
																																ServerButton,
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																124,
																																Short.MAX_VALUE)
																														.addComponent(
																																NickNameTextField,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																124,
																																Short.MAX_VALUE)))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								layout
																										.createSequentialGroup()
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																passwordLabel,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																69,
																																Short.MAX_VALUE)
																														.addComponent(
																																PortLabel,
																																javax.swing.GroupLayout.Alignment.TRAILING,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																69,
																																Short.MAX_VALUE)
																														.addComponent(
																																HostNameLabel,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																69,
																																Short.MAX_VALUE))
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(
																												layout
																														.createParallelGroup(
																																javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(
																																passwordTF,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																123,
																																Short.MAX_VALUE)
																														.addComponent(
																																PortTextField,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																123,
																																Short.MAX_VALUE)
																														.addComponent(
																																HostNameTextF,
																																javax.swing.GroupLayout.DEFAULT_SIZE,
																																123,
																																Short.MAX_VALUE)))
																						.addComponent(
																								JoinYourDefinedServerButton,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								196,
																								Short.MAX_VALUE))))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												mainTitle,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												45,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								NickNameTextField,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								23,
																								Short.MAX_VALUE)
																						.addComponent(
																								NickNameLabel,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								23,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				ServerButton,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				25,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				joinServerButton,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				25,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				QuitButton,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				25,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				DefineLabel,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				20,
																				javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								HostNameLabel,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								HostNameTextF))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								PortLabel)
																						.addComponent(
																								PortTextField,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								false)
																						.addComponent(
																								passwordLabel,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								passwordTF))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				JoinYourDefinedServerButton,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				25,
																				Short.MAX_VALUE))
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																256,
																Short.MAX_VALUE))
										.addGap(17, 17, 17)));

		pack();
	}

	// Producers
	private void ServerButtonMouseReleased(java.awt.event.MouseEvent evt) {
		// EFFECTS: if evt is null then throws nullpointerException
		// else if create a new CreateServerWindow with text from
		// NickNameTextField and set it visible
		CreateServerWindow cSW = new CreateServerWindow(NickNameTextField
				.getText());
		cSW.setVisible(true);

	}

	private void joinServerButtonMouseReleased(java.awt.event.MouseEvent evt) {
		// EFFECTS: if evt is null then throws NullPointerException
		// else if IO exception occurs then call printStackTrace() function
		// else if IndexOutOfBoundsException occurs then create a Dialog with a
		// error message
		// else if get the data from the selected ServerVariable object and
		// check if password is required
		// if a password is required then create a ClientPasswordInputWindow and
		// set it visible
		// if a password is not required then create a Client thread with
		// required input agurments and start it
		try {
			ServerVariable temp = svList.get(ServerList.getSelectedIndex());

			if (temp.isPasswordRequired() == true) {
				ClientPasswordInputWindow cpInputWindow = new ClientPasswordInputWindow(
						temp, NickNameTextField.getText());
				cpInputWindow.setVisible(true);
			} else {
				InetSocketAddress address = (InetSocketAddress) temp
						.getAddress();
				try {
					Client c = new Client(address.getHostName(), address
							.getPort(), NickNameTextField.getText(), "");
					c.start();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Please Select a Server");
		}

	}

	private void JoinYourDefinedServerButtonMouseReleased(
			java.awt.event.MouseEvent evt) throws NumberFormatException,
			IOException {
		// EFFECTS: if cannot parse HostNameTextF.getText() to Integer then
		// throw NumberFormatException
		// else if evt is null then throws NullPointerException
		// else if IO problems occur then throws IOException
		// else create a Client thread with required input arguments and start
		// it

		Client c = new Client(this.HostNameTextF.getText(), Integer
				.parseInt(this.PortTextField.getText()), NickNameTextField
				.getText(), passwordTF.getText());
		c.start();
	}

	private void QuitButtonMouseReleased(java.awt.event.MouseEvent evt) {
		// EFFECTS: if evt is null then throws NullPointerException
		// else if quit the application
		System.exit(0);
	}

	public static void main(String args[]) {
		// EFFECTS: if IO Exception occurs then call printStackTrace() function
		// else if start up the application by create a StartupWindow Object and
		// set it visible
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new StartupWindow().setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	// Mutators
	public void serverFound(SocketAddress address, String serverName,
			int numMembers, boolean needsPassword) {
		// EFFECTS: if ServerList is null then throw NullPointerException
		// else if create a ServerVariable object with inputs
		// then search if there is a same server in the svList
		// if there is a same ServerVariable in the svList already then updates
		// the # of members
		// if no same server exists then add the new ServerVariable in the
		// svList
		// Finally, reset the ServerList data with svList and reset the user
		// selected index
		int a = ServerList.getSelectedIndex();
		ServerVariable sv = new ServerVariable(address, serverName, numMembers,
				needsPassword);

		for (ServerVariable server : svList) {
			if (server.isSameServer(sv)) {
				server.setNumMembers(numMembers);
				ServerList.setListData(svList);
				ServerList.setSelectedIndex(a);
				return;
			}
		}
		svList.add(sv);
		ServerList.setListData(svList);
		ServerList.setSelectedIndex(a);
	}

}
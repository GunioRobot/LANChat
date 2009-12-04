package gui;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

import peer.Server;

public class ServerWindow extends JFrame {
	// OVERVIEW: ServerWindow is a public class and a subclass of JFrame

	// Data Type
	// All the rep invar cannot be null
	private static final long serialVersionUID = 1L;
    private JFileChooser fc = new JFileChooser();
	private Vector<String> users = new Vector<String>(1);
	private JButton SendButton;
	private JTextPane displayText;
	private JTextPane TextInputPanel;
	private JList UserLisT;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JButton shareFileButton;
	private Window a = new Window(this);
	private Server server;

	// Constructors
	public ServerWindow(Server serverT) {
		// EFFECTS: create a new ServerWindow object with a Client thread and
		// call the initComponents()
		System.out.println("Create Server");
		this.server = serverT;
		initComponents();
	}

	private void initComponents() {
		// EFFECTS: initialize the GUI components for the class,this function
		// should be called by the constructor only
		// set the layout for the components;
		// initialize GUI components and add text on each components;
		// add listeners to components;
		// initialize the data for UserList;
		//
		SendButton = new javax.swing.JButton();
		jScrollPane3 = new javax.swing.JScrollPane();
		UserLisT = new javax.swing.JList();
		jScrollPane2 = new javax.swing.JScrollPane();
		TextInputPanel = new javax.swing.JTextPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		displayText = new javax.swing.JTextPane();
		shareFileButton = new javax.swing.JButton();
	
		
		
		shareFileButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		shareFileButton.setText("Share a File");
		
		a.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// Kill the client Server here
				server.interrupt();
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Server @ " + server.getLocalAddress() + server.getPort());
		setLocationByPlatform(true);
		shareFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				try {
					shareFileButtonMouseReleased(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		SendButton.setFont(new java.awt.Font("Times New Roman", 1, 18));
		SendButton.setText("Send");
		SendButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				SendButtonMouseReleased(evt);
			}
		});
		UserLisT.setListData(users);
		UserLisT.setModel(new javax.swing.AbstractListModel() {
			public int getSize() {
				return users.size();
			}

			public Object getElementAt(int i) {
				return users.get(i);
			}
		});
		jScrollPane3.setViewportView(UserLisT);

		TextInputPanel.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TextInputPanelKeyPressed(evt);
			}
		});
		jScrollPane2.setViewportView(TextInputPanel);

		displayText.setEditable(false);
		jScrollPane1.setViewportView(displayText);

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
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addGap(
																				10,
																				10,
																				10)
																		.addComponent(
																				jScrollPane2,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				318,
																				Short.MAX_VALUE))
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jScrollPane1,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				318,
																				Short.MAX_VALUE)))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																shareFileButton,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																119,
																Short.MAX_VALUE)
														.addComponent(
																SendButton,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																119,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																119,
																Short.MAX_VALUE))
										.addContainerGap()));
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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																200,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																200,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																75,
																Short.MAX_VALUE)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				shareFileButton,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				33,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				SendButton,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addContainerGap()));

		pack();
	}

	// Mutators
	private void SendButtonMouseReleased(java.awt.event.MouseEvent evt) {
		// EFFECTS: if evt is null then throw NullPointerException
		// else if set text equals to the text from TextInputPanel
		// then clear the TextInputPanel and sent the text to the server
		String text = TextInputPanel.getText();
		TextInputPanel.setText("");
		server.sendString(text);

	}

	private void TextInputPanelKeyPressed(java.awt.event.KeyEvent evt) {
		// EFFECTS:if evt is NULL then throw NullPointerException
		// else if both Control key and Enter key is downed then add a new line
		// for TextInputPanel
		// else if only Enter key is downed then set text to the text from the
		// TextInputPanel
		// then clear TextInputPanel and send text to sever
		// else do nothing
		if (evt.isControlDown() && (evt.getKeyCode() == KeyEvent.VK_ENTER)) {
			TextInputPanel.setText(TextInputPanel.getText() + "\n");
		} else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

			evt.consume();
			String text = TextInputPanel.getText();
			TextInputPanel.setText("");
			server.sendString(text);
		} else {

		}
	}

	private void shareFileButtonMouseReleased(java.awt.event.MouseEvent evt) throws IOException {
		//EFFECTS: if evt is null then throw NullPointerException
		//         else if IO problems occur then throws IOException
		//         else if create file chooser dialog and let user choose a file
		//		   then get the file name and share the file on the http server
		//         then send a msg that contains information about the file to the server
		int returnVal = fc.showOpenDialog(ServerWindow.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String temp;
			temp = server.shareFile(file.getName());
			server.sendString(file.getName() + " is shared @ \n" + temp);
		}

	}

	public void addText(String text) {
		// EFFECTS: if text is null then throw NullPointerException
		// else if add text to displayText
		displayText.setText(displayText.getText() + text + "\n");
	}

	public void updateUserList(Vector<String> users) {
		// EFFECTS: if users is null then throw NullpointerException
		// else if set the data of userList to be users.
		this.users = users;
		UserLisT.setListData(users);
	}

	// Producer
	public void Dialog(String msg) {
		// EFFECTS: if msg is null then throw NullPointerException
		// else if create a Dialog that displays msg
		JOptionPane.showMessageDialog(null, msg);
	}

}

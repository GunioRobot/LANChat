package gui;

import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.*;

import peer.Client;

public class ClientWindow extends JFrame {
	// OVERVIEW: ClientWindow is a public class and a subclass of JFrame

	// Data Type
	// Rep Invariant:
	// None of the fields can be null
	// AF(c) = c.client = this.client, c.users = for(0<i<userList.size())
	// userList.get(i);
	private JFileChooser fc = new JFileChooser();
	private JButton shareFileButton;
	private Client client;
	private JTextPane displayText;
	private JButton SendButton;
	private JTextPane TextInputPanel;
	private JList userList;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private Vector<String> users = new Vector<String>(1);
	private Window a = new Window(this);

	// Constructor
	public ClientWindow(Client ClientT) {
		// EFFECTS: create a new ClientWindow object with a Client thread and
		// call the initComponents()
		this.client = ClientT;
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
		shareFileButton = new JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		TextInputPanel = new javax.swing.JTextPane();
		jScrollPane3 = new javax.swing.JScrollPane();
		userList = new javax.swing.JList();
		SendButton = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		displayText = new javax.swing.JTextPane();
		shareFileButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
		shareFileButton.setText("Share a File");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Client Channel");
		setLocationByPlatform(true);
		shareFileButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				try {
					shareFileButtonMouseReleased(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		a.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// kill the client thread
				client.interrupt();
			}

			@Override
			public void windowClosing(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowOpened(WindowEvent e) {

			}
		});
		TextInputPanel.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				TextInputPanelKeyPressed(evt);
			}
		});
		jScrollPane2.setViewportView(TextInputPanel);
		userList.setListData(users);
		userList.setModel(new javax.swing.AbstractListModel() {
			public int getSize() {
				return users.size();
			}

			public Object getElementAt(int i) {
				return users.get(i);
			}
		});
		jScrollPane3.setViewportView(userList);

		SendButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
		SendButton.setText("Send");
		SendButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				SendButtonMouseReleased(evt);
			}
		});

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
								javax.swing.GroupLayout.Alignment.TRAILING,
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
																311,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																311,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																SendButton,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																123,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.Alignment.TRAILING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																123,
																Short.MAX_VALUE)
														.addComponent(
																shareFileButton,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																123,
																Short.MAX_VALUE))
										.addContainerGap()));
		layout
				.setVerticalGroup(layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
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
																214,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane3,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																214,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addGroup(
																layout
																		.createSequentialGroup()
																		.addComponent(
																				shareFileButton,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				35,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				SendButton,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				40,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																81,
																Short.MAX_VALUE))
										.addGap(16, 16, 16)));

		pack();
	}

	// Mutators
	private void SendButtonMouseReleased(java.awt.event.MouseEvent evt) {
		// EFFECTS: if evt is null then throw NullPointerException
		// else if set text equals to the text from TextInputPanel
		// then clear the TextInputPanel and sent the text to the server
		String text = TextInputPanel.getText();
		TextInputPanel.setText("");
		client.send(text);
	}

	private void shareFileButtonMouseReleased(java.awt.event.MouseEvent evt)
			throws IOException {
		// EFFECTS: if evt is null then throw NullPointerException
		// else if IO problems occur then throws IOException
		// else if create file chooser dialog and let user choose a file
		// then get the file name and share the file on the http server
		// then send a msg that contains information about the file to the
		// server
		int returnVal = fc.showOpenDialog(ClientWindow.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String url = client.shareFile(file.getCanonicalPath());
			client.send(file.getName() + " is shared @ \n" + url);
		}

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
			// Display msg from input panel to output panel
			evt.consume();
			String text = TextInputPanel.getText();
			TextInputPanel.setText("");
			client.send(text);
		} else {
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
		userList.setListData(users);
	}

	// producer
	public void Dialog(String msg) {
		// EFFECTS: if msg is null then throw NullPointerException
		// else if create a Dialog that displays msg
		JOptionPane.showMessageDialog(null, msg);
	}

	public boolean repOk() {
		return (fc != null && shareFileButton != null && client != null
				&& displayText != null && SendButton != null
				&& TextInputPanel != null && userList != null
				&& jScrollPane1 != null && jScrollPane2 != null
				&& jScrollPane3 != null && users != null);
	}

	public String toString(){
		return new String("Client is " + this.client + "User list contains" + userList.toString());
	}
}

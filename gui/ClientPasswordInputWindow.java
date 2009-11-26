package gui;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import peer.Client;



/**
 *
 * @author H2O
 */
public class ClientPasswordInputWindow extends JFrame {
	private String password = "";
	private ServerVariable sV;
	private String userName;
    /** Creates new form clientPasswordInputWindow */
    public ClientPasswordInputWindow(ServerVariable b, String userName) {
    	this.sV = b;
    	this.userName = userName;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(enterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(passwordInputField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(passwordInputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enterButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void passwordInputFieldKeyPressed(java.awt.event.KeyEvent evt) {                                              
        // TODO add your handling code here:
    	if(evt.getKeyCode() == KeyEvent.VK_ENTER){
    	char[] temp;
    	temp = passwordInputField.getPassword();
    	for(int i =0 ; i < temp.length; i++){
    	password = password.concat(Character.toString(temp[i]));
    	}
    	InetSocketAddress address = (InetSocketAddress) sV.getAddress();
    	try{
    	Client c = new Client(address.getHostName(),address.getPort(), this.userName,this.password);
    	c.start();
    	dispose();
    	}
    	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	}
    }                                             

    private void enterButtonMouseReleased(java.awt.event.MouseEvent evt) throws IOException {                                          
        // TODO add your handling code here:
    	char[] temp;
    	temp = passwordInputField.getPassword();
    	for(int i =0 ; i < temp.length; i++){
    	password = password.concat(Character.toString(temp[i]));
    	}
    	InetSocketAddress address = (InetSocketAddress) sV.getAddress();
    	Client c = new Client(address.getHostName(),address.getPort(), this.userName,this.password);
    	c.start();
    	dispose();
       
    }                                         

    private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        dispose();
    }                                          

    /**
    * @param args the command line arguments
    */
    public String getInputPassword(){
    	return this.password;
    }
    
 

    // Variables declaration - do not modify                     
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton enterButton;
    private javax.swing.JPasswordField passwordInputField;
    // End of variables declaration                   

}

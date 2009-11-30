package peer;

import gui.ClientWindow;
import gui.PasswordException;

import java.io.IOException;
import java.net.InetSocketAddress;

import networking.ChannelUpdate;
import networking.Join;
import networking.Message;
import networking.MessageType;
import networking.TextMessage;

public class Client extends Peer{
//

    private String clientHandle;
    private String password;
    private boolean hasPassword;
    private ClientWindow window;
    
    //Constructor
    public Client(String serverAddress, int serverPort, String clientHandle, String password) throws IOException{
	//EFFECTS: If serverPort is in use throw SocketException
    //         else initialize clientChat gui and instantiate client with serverAddress, serverPort, clientHandle, and password
    //			send join message to server
        super(serverAddress, serverPort);
        
        window = new ClientWindow(this);
        window.setVisible(true);
        
        this.clientHandle = clientHandle;
        this.password = password;
        
        System.out.println("Starting client on " + this.getLocalAddress() + " : " + this.getPort());
        Message join = new Join(clientHandle, password, this.getLocalAddress(), this.getPort());
		
        this.send(join);
    }
    
//Mutator
    public void hasPassword(boolean b){
    //EFFECTS: set hasPassword to b
        hasPassword = b;
    }
    
//Observer
    public String getClientHandle(){
    //EFFECTS: returns client handle
        return clientHandle;
    }
    
    public boolean getHasPassword(){
    //EFFECTS: returns hasPassword
    	return hasPassword;
    }
    
//Client Send/Recv
    public void send(String message) throws IOException{
    //EFFECTS: Creates a new TextMessage m with message and sends m to server
        System.out.println("Client");
        Message m = new TextMessage(clientHandle, message, password);
        send(m);
    }
    
    private void receive(Message message){
    //REQUIRES: message is CHANNEL_UPDATE
    //EFFECTS: parse message into string and update client chat gui
        String s = msgParse(message);
        display(s);
    }

    private String msgParse(Message message){
    //EFFECTS: returns a string of data + " " + clientHandle + " " + message
        ChannelUpdate m = (ChannelUpdate)message;
        String s = (m.date + " " + m.clientHandle + " " + m.message);
        return s;
    }
    
    private void display(String message){
    //EFFECTS: updates client chat gui with message
    }
    
    @Override
    protected void handleMessage(Message message, InetSocketAddress source) {
    	
    	super.handleMessage(message, source);

        switch(message.getType()) {
            case TEXT_MESSAGE:
                break;
            case CHANNEL_UPDATE:
                try{
                    receive(message);
                } catch(Exception e){
                	System.out.println(e);
                }
                break;
          //case CHANNEL_STATUS:
                //ChannelStatus m = (ChannelStatus) message;
                //window.updateUserList(m.clientList);
                //break;
            case ANNOUNCE:
                break;
            case JOIN:
                break;
            case LEAVE:
                break;
            case REFUSE:
                window.dispose();
                //throw new PasswordException();
                break;
            default:
                System.out.println("Peer: received a " + message.getType());
        }
    }

	@Override
	public String getPeerName() {
	//EFFECTS: returns client handle
		return this.clientHandle;
	}
}

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
    boolean hasPassword;
    private ClientWindow window;
    
    public Client(String serverAddress, int serverPort, String clientHandle, String password) throws IOException{
        super(serverAddress, serverPort);
        window = new ClientWindow(this);
        window.setVisible(true);
        this.clientHandle = clientHandle;
        this.password = password;
        
        Message join = new Join(clientHandle, password, this.getLocalAddress(), this.getPort());
		this.send(join);
    }
    
    public String getClientHandle(){
        return clientHandle;
    }
    
    public void hasPassword(boolean b){
        hasPassword = b;
    }
    
    public void send(String message) throws IOException{
        System.out.println("Client");
        Message m = new TextMessage(clientHandle, message, password);
        send(m);
    }
    
    public static void receive(Message message){
        if(message.getType()==MessageType.CHANNEL_UPDATE){
            String s = msgParse(message);
            display(s);
        }
    }

    private static String msgParse(Message message){
        //TODO: format msg properly
        ChannelUpdate m = (ChannelUpdate)message;
        String s = (m.date + " " + m.clientHandle + " " + m.message);
        return s;
    }
    
    private static void display(String message){
        //TODO: GUI display msg in client window
    	window.setString(message);
        //System.out.println(message);
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
		return this.clientHandle;
	}
}

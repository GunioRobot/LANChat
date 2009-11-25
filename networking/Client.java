package networking;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Client extends Peer{

	String clientHandle;
	String password;
	boolean hasPassword;
	
	public Client(String serverAddress, int serverPort, String clientHandle, String password) throws IOException{
		super(serverAddress, serverPort);
		this.clientHandle = clientHandle;
		this.password = password;
		
		Message join = new Join(clientHandle, password, this.getLocalAddress(), this.getPort());
		this.send(join);
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
			ClientDisplay(s);
		}
	}
	
	public void createClientWindow(){
		
	}

	private static String msgParse(Message message){
		//TODO: format msg properly
		ChannelUpdate m = (ChannelUpdate)message;
		String s = "\n[" + Time.time() + "] " + m.clientHandle + ":  '" + m.message + "'\n";
		return s;
	}
	
	private static void ClientDisplay(String message){
		//TODO: GUI display msg in client window
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
	        case ANNOUNCE:
	        	break;
	        case JOIN:
	        	break;
        	case REFUSE:
        		//Do something here
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

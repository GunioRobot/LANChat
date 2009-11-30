package peer;

import gui.ServerWindow;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import networking.ChannelStatus;
import networking.ChannelUpdate;
import networking.Join;
import networking.Leave;
import networking.Message;
import networking.Refuse;
import networking.ServerAnnouncer;
import networking.TextMessage;

public class Server extends Peer{
//OVERVIEW: Server receives messages from clients and sends messages back to all clients
    
    private ArrayList<ClientInfo> clientList;
    
    private String serverName;
    protected String password;
    private String clientHandle;
    public boolean needsPassword;
    
    private ServerAnnouncer announcer;
    
    private ServerWindow window;
    
//Constructor
    public Server(String serverName, String password, String clientHandle) throws IOException {
    //EFFECTS: If serverPort is in use throw SocketException
    //         else initialize serverChat gui and instantiate server with serverPort, serverName, password, clientHandle, and empty clientList
    //			set needsPassword to password.isEmpty()
        super();
        
        window = new ServerWindow(this);
        window.setVisible(true);
        
        clientList = new ArrayList<ClientInfo>();
        this.serverName=serverName;
        this.password=password;
        needsPassword=password.isEmpty();
        this.clientHandle=clientHandle;
        
        this.announcer = new ServerAnnouncer(this);
        this.announcer.start();
        
        System.out.println("Starting server on " + this.getLocalAddress() + " : " + this.getPort());

        //Add ourselves to the client list and update
        addClient(new ClientInfo(clientHandle, this.getLocalAddress(), this.getPort()));
        clientUpdate();
        System.out.println("Server started on port " + this.getPort());
    }
    
//Mutators
    public boolean addClient(ClientInfo client) throws NullPointerException{
    //EFFECTS: If client is null throw NullPointerException
    //         if client has same handle or same socket address as existing client return false
    //			else add client to clientList and return true
        System.out.println("[" + clientHandle + "] adding client: " + client.clientHandle + " " + client.clientAddress + " " + client.clientPort);
        for(ClientInfo knownClient : clientList) {
        	if(knownClient.clientHandle.equals(client.clientHandle)){
                    System.out.println("Username already taken");
                    return false;
            }
        }
        clientList.add(client);
        return true;
    }
    
    public boolean removeClient(ClientInfo client){
    //EFFECTS: If client is null throw NullPointerException
    //            Removes client from the clientList
        int i = clientList.indexOf(client);
        if(i<0) return false;
        else clientList.remove(i);
        return true;
    }
    
    public void setPassword(String password){
        //EFFECTS: sets this.password to password
            this.password = password;
	}
    
//Observers 
    public String getClientHandle(){
    //EFFECTS: return client handle
        return clientHandle;
    }
    
    public synchronized int getNumMembers(){
    //EFFECTS: return the number of members in clientList
        return clientList.size();
	}
	  
    public String getServerName(){
    //EFFECTS: return server name
    	return serverName;
    }
    
    public int getServerPort(){
    //EFFECTS: return server port
    	return serverAddress.getPort();
    }
    
//Helper
    private void clientUpdate(){
    //EFFECTS: updates gui with list of clients and sends ChannelStatus to all clients with list of clients
        ChannelStatus status = new ChannelStatus(generateClientHandles());
        try {
			send(status);
		} catch (IOException e) {
			System.out.println("[" + clientHandle + "]: Failed to send ChannelStatus");
		}
        window.updateUserList(generateClientHandles());
    }
    
    private Vector<String> generateClientHandles(){
    //EFFECTS: generates and returns a collection of all the clientHandle current on the server
        Vector<String> v = new Vector<String>();
        for(int i=0; i<clientList.size(); i++){
            v.add(clientList.get(i).clientHandle);
        }
        return v;
    }

//Server Send/Recv
    public void send(String message){
    //EFFECTS: if message is null, throw NullPointerException
    //			else sends message to all clients
    	TextMessage m = new TextMessage(clientHandle, message, "");
    	try {
			send(m);
		} catch (IOException e) {
			System.out.println("Failed sending message");
			e.printStackTrace();
		}
    }
    @Override
    public void send(Message message) throws IOException{
    //REQUIRES: message is not null
    //EFFECTS: sends message to all clients
    	for(ClientInfo client : clientList) {
            sendTo(message, client.clientSocketAddress);
        }        
    }
    
    public void receive(Message message) throws IOException{
    //EFFECTS: sends message to all clients and update gui with message
    	TextMessage tm = (TextMessage)message;
        this.send(new ChannelUpdate(tm.clientHandle, tm.message, new Date()));
        String text = msgParse(message);
        window.addText(text);
    }
    
    private String msgParse(Message message){
    //EFFECTS: takes message and returns a string with Time.time() + " " + clientHandle + " " + message
        TextMessage m = (TextMessage)message;
        String s = ("[" + m.clientHandle + "]: " + m.message);
        return s;
    }
    
    @Override
    protected void handleMessage(Message message, InetSocketAddress source) {
    //EFFECTS: parse message based on type and handles them
    	
    	super.handleMessage(message, source);
    	
        switch(message.getType()) {
            case TEXT_MESSAGE:
                try{
                    TextMessage t = (TextMessage)message;
                    if(t.password.equals(this.password)){
                        receive(message);
                        clientUpdate();
                    }
                } catch(IOException e){
                	System.out.println(e);
                }
                break;
            case CHANNEL_UPDATE:
                break;
            case CHANNEL_STATUS:
            	break;
            case ANNOUNCE:
                break;
            case JOIN:
                Join j = (Join)message;
                Refuse refuse;
                if(j.password.equals(this.password)){
                	if(addClient(new ClientInfo(j.clientHandle, j.clientAddress, j.clientPort))) {
                		clientUpdate();
                		System.out.println("clients: " + getNumMembers());
                		break;
                	} else {
                		refuse = new Refuse("Username already taken");
                	}
                } else {
                	refuse = new Refuse("Invalid Password");
                }
                try {
                    sendTo(refuse, new InetSocketAddress(j.clientAddress, j.clientPort));
                } catch (IOException e) {
                    e.printStackTrace();
                }     
                break;
            case LEAVE:
                Leave l = (Leave)message;
                if(l.password.equals(this.password)){
                    removeClient(new ClientInfo(l.clientHandle, l.clientAddress, l.clientPort) );
                    clientUpdate();
                    System.out.println("clients: " + getNumMembers());
                }
                break;
            case REFUSE:
                break;
            default:
                System.out.println("Peer: received a " + message.getType());
        }

    }
    
	@Override
	public String getPeerName() {
	//EFFECTS: returns server name
		return this.serverName;
	}

}

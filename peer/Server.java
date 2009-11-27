package peer;

import gui.ServerWindow;
import gui.CreateServerWindow;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import networking.Join;
import networking.Leave;
import networking.Message;
import networking.Refuse;
import networking.TextMessage;

public class Server extends Peer{
//OVERVIEW: Server receives messages from all clients and sends messages back to all client
    
    private ArrayList<ClientInfo> clientList;
    public int serverPort;
    public String serverName;
    protected String password;
    public boolean needsPassword;
    private String clientHandle;
    private ServerWindow window;
    
    //Constructor
    public Server(int serverPort, String serverName, String password, String clientHandle) throws SocketException {
    //EFFECTS: If serverPort is in use throw SocketException
    //            else creates a new server on localhostaddress:serverPort called serverName with password instantiates an empty clientList.
        super(serverPort);
        window = new ServerWindow(this);
        clientList = new ArrayList<ClientInfo>(1);
        this.serverPort=serverPort;
        this.serverName=serverName;
        this.password=password;
        needsPassword=password=="";
        this.clientHandle=clientHandle;
        clientUpdate();
    }
    
    public String getClientHandle(){
        return clientHandle;
    }

    //Client List
    public boolean addClient(ClientInfo client) throws NullPointerException{
    //EFFECTS: If client is null throw NullPointerException
    //            Adds client to the clientList
        System.out.println(client.clientHandle + " " + client.clientAddress + " " + client.clientPort);
        for(int i=0; i<clientList.size(); i++){
            if(clientList.get(i).clientHandle.equals(client.clientHandle) ||
                clientList.get(i).same(client)){
                System.out.println("Username already used");
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
    
    public void clientUpdate(){
        //TODO create ChannelStatus using generate ClientHandle, call window.updategui()
       // ChannelStatus m = new ChannelStatus();
       // m.clientList=generateClientHandles();
       // send(m);
        window.updateUserList(generateClientHandles());
        //System.out.println("[clientUpdate]");
    }
    
    public Vector<String> generateClientHandles(){
    //EFFECTS: generates and returns a collection of all the clientHandle current on the server
        Vector<String> v = new Vector<String>();
        for(int i=0; i<clientList.size(); i++){
            v.add(clientList.get(i).clientHandle);
        }
        return v;
    }
    
    public int getNumMembers(){
    //EFFECTS:
        return clientList.size();
    }
    
    public void setPassword(String password){
        this.password = password;
    }

//Server Send/Recv Stuff
    @Override
    public void send(Message message) throws IOException{
    //REQUIRES: message is TEXT_MESSAGE
    //EFFECTS: sends message to all clients
        Iterator<ClientInfo> itr = clientList.iterator();
        while( itr.hasNext() ){
            sendTo(message, itr.next().clientSocket);
        }        
    }
    
    public void receive(Message message) throws IOException{
    //Receive message from client
        this.send(message);
        String s = msgParse(message);
        display(s);
    }
    
    private static String msgParse(Message message){
        //TODO: format msg properly
        TextMessage m = (TextMessage)message;
        String s = (m.clientHandle + " " + m.message);
        return s;
    }
    
    public void display(String message){
        //TODO: send formatted msg to display
        //System.out.println(message);
    }
    
    @Override
    protected void handleMessage(Message message, InetSocketAddress source) {
    	
    	super.handleMessage(message, source);
    	
        switch(message.getType()) {
            case TEXT_MESSAGE:
                try{
                    TextMessage m = (TextMessage)message;
                    if(m.password.equals(this.password)){
                        receive(message);
                        clientUpdate();
                    }
                } catch(IOException e){
                	System.out.println(e);
                }
                break;
            case CHANNEL_UPDATE:
                break;
            //case CHANNEL_STATUS:
               //break;
            case ANNOUNCE:
                break;
            case JOIN:
                Join m = (Join)message;
                if(m.password.equals(this.password)){
                    addClient(new ClientInfo(m.clientHandle, m.clientAddress, m.clientPort) );
                    clientUpdate();
                    System.out.println("clients: " + getNumMembers());
                }
                else{
                    try {
                        sendTo(new Refuse("Invalid Password"), new InetSocketAddress(m.clientAddress, m.clientPort));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }     
                }
                break;
            case LEAVE:
                Leave n = (Leave)message;
                if(n.password.equals(this.password)){
                    removeClient(new ClientInfo(n.clientHandle, n.clientAddress, n.clientPort) );
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
		return this.serverName;
	}

}

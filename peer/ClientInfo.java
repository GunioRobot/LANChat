package peer;

import java.net.InetSocketAddress;

public class ClientInfo {
	
    //DataType
    public String clientHandle;
    public String clientAddress;
    public int clientPort;
    public InetSocketAddress clientSocket;
    public boolean hasFile;
    
    //Constructor
    public ClientInfo(String clientHandle, String clientAddress, int clientPort){
    //EFFECTS: creates a new ClientInfo with clientHandle, clientAddress, and clientPort in its field.
    //			set clientSocket as clientAddress + clientPort.
    //			set hasFile to false.
        super();
        this.clientHandle = clientHandle;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
        clientSocket = new InetSocketAddress(clientAddress, clientPort);
        hasFile = false;
    }
    
    public boolean same(ClientInfo other){
    //EFFECTS: returns true if this.clientSocket does not equal other.clientSocket else return false
        if(this.clientSocket==other.clientSocket
           ) return false;
        return true;
    }

}


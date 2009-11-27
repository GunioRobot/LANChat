package peer;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ClientInfo {
    //DataType
    public String clientHandle;
    public String clientAddress;
    public int clientPort;
    public InetSocketAddress clientSocket;
    public boolean hasFile;
    
    public ClientInfo(String clientHandle, String clientAddress, int clientPort){
        super();
        this.clientHandle = clientHandle;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
        clientSocket = new InetSocketAddress(clientAddress, clientPort);
        hasFile = false;
    }
    
    public boolean same(ClientInfo other){
        if(this.clientSocket==other.clientSocket
           ) return false;
        return true;
    }
}


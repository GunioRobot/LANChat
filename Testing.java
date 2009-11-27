import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

import peer.Client;
import peer.Peer;
import peer.Server;

import networking.Announce;
import networking.ChannelUpdate;
import networking.Join;
import networking.Message;
import networking.Refuse;
import networking.ServerAnnouncer;
import networking.ServerFinder;
import networking.TextMessage;


public class Testing {


    public static class MyServerListener implements ServerFinder.ServerListener {
        public MyServerListener() {

        }
        @Override
        public void serverFound(SocketAddress address, String serverName, int numMembers, boolean needsPassword) {
            System.out.println("ServerListener: Server found at " + address + " named " + serverName + ", " + numMembers + " members, needsPassword=" + needsPassword);
        }
    }



	public static void main(String[] args) throws InterruptedException, IOException {

		InetAddress addr2  = Inet4Address.getLocalHost();
		System.out.println(addr2.getHostAddress());
		
		InetSocketAddress addr = new InetSocketAddress(54000);
		
		// Setup a server
		//Peer server = new peer.Server(addr.getPort(), "server1", "pw", "mr server");
		Peer server = new peer.Server(56000, "server1", "pw", "mr server");
		server.setDaemon(true);
		server.start();
        
        Message[] messages = {new Join("rob", "pw", "127.0.0.1", 56780),
        					  new TextMessage("rob", "message1", "password"),
        					  new TextMessage("will", "message2", "password"),
        					  new ChannelUpdate("rob", "message2", new Date()),
        					  new Announce("server1", addr.getHostName(), 64000, 0, true),
        					  new Refuse("invalid password")};
        
        // construct a client and join to the server
        //Peer client1 = new Client(addr.getHostName(), addr.getPort(), "Monkey", "pw");
        Peer client1 = new Client(addr.getHostName(), 56000, "Monkey", "pw");
        client1.setDaemon(true);
        client1.start();
        
        //construct another client
        Peer client2 = new Client(addr.getHostName(), addr.getPort(), "Giraffe", "pw");
        client2.setDaemon(true);
        client2.start();
        
        // Send some messages
        client1.send(new TextMessage("rob", "message1", "pw"));
        client1.send(new TextMessage("rob", "message2", "pw"));
        
        // Send some messages
        /*for(Message message : messages) {
        	client.send(message);
        }*/
        
        
        String mAddr = "230.0.0.1";
        int mPort = 45000;

        MyServerListener listener = new MyServerListener();
        ServerFinder finder = new ServerFinder(mAddr, mPort);
        finder.addServerListener(listener);
        finder.start();

        ServerAnnouncer announcer = new ServerAnnouncer(mAddr, mPort, 1000, (Server) server);
        announcer.start();
        
        
        Thread.sleep(3000);
	}

}

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

import networking.Announce;
import networking.ChannelUpdate;
import networking.Client;
import networking.Join;
import networking.Message;
import networking.Peer;
import networking.Refuse;
import networking.Server;
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
		Peer server = new Server(addr.getPort(), "server", "pw");
		server.setDaemon(true);
		server.start();
		
        Peer client = new Client(addr.getHostName(), addr.getPort(), "username", "pw");
        client.setDaemon(true);
        client.start();
        
        Message[] messages = {new Join("rob", "pw", client.getLocalAddress(), client.getPort()),
        					  new TextMessage("rob", "message1", "password"),
        					  new TextMessage("will", "message2", "password"),
        					  new ChannelUpdate("rob", "message2", new Date()),
        					  new Announce("server1", addr.getHostName(), 64000, 0, true),
        					  new Refuse("Invalid Password")};

        // Send some messages to ourself
        /*for(Message message : messages) {
        	client.send(message);
        }*/
        
        client.send(new Join("rob", "pw", client.getLocalAddress(), client.getPort()));
        client.send(new TextMessage("rob", "message1", "pw"));
        client.send(new TextMessage("rob", "message2", "pw"));
        
        
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

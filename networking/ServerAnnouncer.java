package networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

import peer.Server;

public class ServerAnnouncer extends Thread {

    private MulticastSocket socket;
    private InetSocketAddress address;
    private int delay;
    private Server server;
    
    public static String defaultAddress = "230.0.0.1";
    public static int defaultPort = 45000;
    public int defaultDelay = 10000;
    
    public ServerAnnouncer(Server server) throws IOException {
        socket = new MulticastSocket(defaultPort);
        socket.joinGroup(Inet4Address.getByName(defaultAddress));
        socket.setTimeToLive(32);
        this.setDaemon(true);
        this.address = new InetSocketAddress(defaultAddress, defaultPort);
        this.server = server;
        this.delay = defaultDelay;
    }
    
    public ServerAnnouncer(String multicastAddress, int announcePort, int delay, Server server) throws IOException {
        socket = new MulticastSocket(announcePort);
        socket.joinGroup(Inet4Address.getByName(multicastAddress));
        socket.setTimeToLive(32);
        this.setDaemon(true);
        this.address = new InetSocketAddress(multicastAddress, announcePort);
        this.delay = delay;
        this.server = server;
    }

    @Override
    public void run() {
        while(!this.isInterrupted()) {
            try {
                Announce announce = new Announce(server.getServerName(), server.getLocalAddress(), server.getServerPort(), server.getNumMembers(), server.needsPassword);
                byte[] data = announce.getBytes();
                DatagramPacket packet = new DatagramPacket(data, data.length, address);
                System.out.println("ServerAnnouncer: sending announce to " + packet.getAddress().getHostName() + ":" + packet.getPort());
                socket.send(packet);
            } catch (IOException ex) {
                System.out.println("ServerAnnouncer: failed sending announce: " + ex);
            }
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

}

package gui;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ServerVariable {
	// OVERVIEW: ServerVariable is a public and mutable class
	// DATA TYPE
	private SocketAddress address; // cannot be null
	private String serverName; // cannot be null
	private int numMembers; // cannot be null
	private boolean needsPassword; // cannot be null

	// Constructor
	public ServerVariable(SocketAddress address, String serverName,
			int numMembers, boolean needsPassword) {
		// EFFECTS: create a ServerVariable object and initialize the rep
		// variance
		this.serverName = serverName;
		this.address = address;
		this.numMembers = numMembers;
		this.needsPassword = needsPassword;
	}

	// Observers
	public SocketAddress getAddress() {
		// EFFECTS: return this.address as a SocketAddress object
		return address;

	}

	public String getServerName() {
		// EFFECTS: return this.serverName as a String
		return serverName;
	}

	public int getNumMembers() {
		// EFFECTS: return this.numMembers as primitive int type
		return numMembers;
	}

	public boolean isPasswordRequired() {
		// EFFECTS: return this.needsPassword as a boolean type
		return this.needsPassword;
	}

	public boolean isSameServer(ServerVariable other) {
		// REQUIRES: other is not null
		// EFFECTS: returns true if other represents a server at the same
		// address
		// and port as this server
		InetSocketAddress addr1 = (InetSocketAddress) address;
		InetSocketAddress addr2 = (InetSocketAddress) other.address;
		return (addr1.getAddress().equals(addr2.getAddress()))
				&& (addr1.getPort() == addr2.getPort());
	}

	// Mutators
	public void setNumMembers(int numMembers) {
		// EFFECTS: set the this.numMembers to be numMembers
		this.numMembers = numMembers;
	}

	public String toString() {
		// EFFECTS: produce and return a String object that contains information
		// about this
		String s = this.serverName + " [" + this.numMembers + " members]";
		if (this.needsPassword) {
			s += " (Requires Password)";
		}
		return s;
	}
}
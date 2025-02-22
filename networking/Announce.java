
package networking;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Announce implements Message {
    // OVERVIEW: A message sent out at regular intervals on a well-known port
    // that indicates the server's name, listening port and number of active members
    // The binary format is:
    // int: Indicates the type of packet (PacketType.CHANNEL_UPDATE)
    // int: Length of the server name in bytes
    // [nameLength] bytes: The client's handle name as a string
    // int: port that the server is listening on
    // int: number of members in the channel

	// AF(c) = [ c.serverName, c.serverAddress, c.serverPort, c.numMembers, c.needsPassword ]
	// The Rep Invariant is
	// serverName != null, serverAddress != null
	
    private final MessageType type = MessageType.ANNOUNCE;
    public final String serverName;
    public final String serverAddress;
    public final int serverPort;
    public final int numMembers;
    public final boolean needsPassword;


    // constructors
    public Announce(String serverName, String serverAddress, int serverPort, int numMembers, boolean needsPassword) {
        // REQUIRES: clientHandle is not null, message is not null
        // EFFECTS: Constructs a new Announce with the given data
        this.serverName = serverName;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.numMembers = numMembers;
        this.needsPassword = needsPassword;
    }

    public Announce(DataInputStream stream) throws IOException {
        // REQUIRES: stream is not null
        // EFFECTS: Parses a new Announce from the given stream, or throws IOException
        // if there was a problem parsing the required fields
        serverName = MessageParser.readString(stream);
        serverAddress = MessageParser.readString(stream);
        serverPort = stream.readInt();
        numMembers = stream.readInt();
        needsPassword = stream.readBoolean();
    }

    public byte[] getBytes() throws IOException {
        // EFFECTS: Returns the binary representation of this Announce as a byte array
        ByteArrayOutputStream byte_out = new ByteArrayOutputStream();
        DataOutputStream stream = new DataOutputStream(byte_out);

        stream.writeInt(type.id);
        MessageParser.writeString(stream, serverName);
        MessageParser.writeString(stream, serverAddress);
        stream.writeInt(serverPort);
        stream.writeInt(numMembers);
        stream.writeBoolean(needsPassword);
        stream.flush();

        return byte_out.toByteArray();
    }

    public MessageType getType() {
        // EFFECTS: returns the type of this packet
        return type;
    }

	public boolean repOk() {
		return (type == MessageType.ANNOUNCE && serverName != null && serverAddress != null);
	}
	
	public String toString() {
		return "[ ANNOUNCE: "+serverName+", "+serverAddress+", "+serverPort+", "+numMembers+", "+needsPassword+" ]";
	}
}


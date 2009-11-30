
package networking;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

public class ChannelStatus implements Message {
    // OVERVIEW: A ChannelUpdate is a packet containing a message, the handle of
    // the client that sent it, and the time it was recieved by the server
    // The binary format is:
    // int: Indicates the type of packet (PacketType.CHANNEL_UPDATE)
    // int: Length of the client handle string in bytes
    // [handleLength] bytes: The client's handle name as a string
    // int: Length of the text message
    // [messageLength] bytes: The message string
    // long: The time the message was received by the server as a UNIX timestamp

    private MessageType type = MessageType.CHANNEL_STATUS;
    public Vector<String> clientHandles;

    // constructors
    public ChannelStatus(Vector<String> clientHandles) {
        // REQUIRES: clientHandles is not null and none of the strings in clientHandles are null
        // EFFECTS: Constructs a new ChannelStatus with the given list of client names
        this.clientHandles = clientHandles;
    }

    public ChannelStatus(DataInputStream stream) throws IOException {
        // REQUIRES: stream is not null
        // EFFECTS: Parses a new ChannelStatus from the given stream, or throws IOException
        // if there was a problem parsing the required fields
        int numClients = stream.readInt();
        clientHandles = new Vector<String>(numClients);
        for(int i=0; i<numClients; i++) {
        	clientHandles.add(MessageParser.readString(stream));
        }
    }

    public byte[] getBytes() throws IOException {
        // EFFECTS: Returns the binary representation of this ChannelStatus as a byte array
        ByteArrayOutputStream byte_out = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byte_out);

        out.writeInt(type.id);
        out.writeInt(clientHandles.size());
        for(String handle : clientHandles) {
        	MessageParser.writeString(out, handle);
        }
        out.flush();
        
        return byte_out.toByteArray();
    }

    @Override
    public MessageType getType() {
        // EFFECTS: returns the type of this packet
        return type;
    }
}

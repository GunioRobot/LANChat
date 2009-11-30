
package networking;

public enum MessageType {
    TEXT_MESSAGE(1),
    CHANNEL_UPDATE(2),
    ANNOUNCE(4),
    JOIN(5),
    REFUSE(6),
    LEAVE(7),
    CHANNEL_STATUS(8),
    UNKNOWN(0);

    public int id;
    private MessageType(int id) {
        this.id = id;
    }
    public static MessageType get(int id) {
    	for(MessageType type : MessageType.values()) {
    		if(type.id == id) {
    			return type;
    		}
    	}
    	return UNKNOWN;
    }
};


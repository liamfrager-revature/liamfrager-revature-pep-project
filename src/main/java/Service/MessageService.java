package Service;
import java.util.ArrayList;
import java.util.List;

import Model.Message;

public class MessageService {

    public List<Message> getAllMessages() {
        return new ArrayList<Message>();
    }
    public Message getMessageByID(int id) {
        return new Message();
    }
    public Message postMessage(Message message) {
        return message;
    }
    public boolean patchMessageByID(int id, Message message) {
        return true;
    }
    public Message deleteMessageByID(int id) {
        return new Message();
    }
    public List<Message> getAllMessagesByAccountID(int id) {
        return new ArrayList<Message>();
    }
    
}

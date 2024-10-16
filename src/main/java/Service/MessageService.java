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
    public boolean postMessage(Message message) {
        return true;
    }
    public boolean patchMessageByID(int id, Message message) {
        return true;
    }
    public boolean deleteMessageByID(int id) {
        return true;
    }
    public List<Message> getAllMessagesByAccountID(int id) {
        return new ArrayList<Message>();
    }
    
}

package DAO;

import java.util.ArrayList;
import java.util.List;

import Model.Message;

public class MessageDAO {
    public List<Message> getAllMessages() {
        return new ArrayList<Message>();
    }
    public Message getMessageByID(int id) {
        return new Message();
    }
    public Message insertMessage(Message message) {
        return message;
    }
    public boolean updateMessageByID(int id, Message message) {
        return true;
    }
    public Message deleteMessageByID(int id) {
        return new Message();
    }
    public List<Message> getAllMessagesByAccountID(int id) {
        return new ArrayList<Message>();
    }
}

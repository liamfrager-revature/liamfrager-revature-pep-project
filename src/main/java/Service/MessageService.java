package Service;
import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;
    public AccountService accountService;

    public MessageService(){
        messageDAO = new MessageDAO();
        accountService = new AccountService();
    }

    /**
     * Get all messages.
     * @return A list of all messages.
     */
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    }
    
    /**
     * Get a message with a given ID.
     * @param id The ID of the message to be returned.
     * @return The message with the given ID.
     */
    public Message getMessageByID(int id) {
        return messageDAO.getMessageByID(id);
    }
    
    /**
     * Add a new message to the database if the message is valid.
     * @param message The message to be added.
     * @return The new message.
     * @throws Exception The message is invalid.
     */
    public Message postMessage(Message message) throws Exception {
        // If the message_text is not blank, is under 255 characters, and posted_by refers to a real, existing user.
        if (message.message_text.length() > 0 && message.message_text.length() < 255 && accountService.userExists(message.posted_by))
            return messageDAO.insertMessage(message);
        throw new Exception();
    }
    
    /**
     * Update a message with a given ID if the given new_message is valid.
     * @param id The ID of the message to update.
     * @param new_message The new message that will replace the old message.
     * @return The updated message.
     * @throws Exception The new message is invalid.
     */
    public Message patchMessageByID(int id, Message new_message) throws Exception {
        // If the message id already exists and the new message_text is not blank and is not over 255 characters
        if (messageExists(id) && new_message.message_text.length() > 0 && new_message.message_text.length() < 255)
            return messageDAO.updateMessageByID(id, new_message);
        throw new Exception();
    }
    
    /**
     * Delete a message with a given ID if it exists.
     * @param id The ID of the message to delete.
     * @return The deleted message, or null if the message didn't exist.
     */
    public Message deleteMessageByID(int id) {
        if (messageExists(id))
            return messageDAO.deleteMessageByID(id);
        return null;
    }
    
    /**
     * Get all messages posted by a user with the given ID.
     * @param id The ID of the user whose messages will be returned. Returns null if the ID is invalid
     * @return A list of all messages by the user with the given ID.
     */
    public List<Message> getAllMessagesByAccountID(int id) {
        return messageDAO.getAllMessagesByAccountID(id);
    }

    
    /**
     * @param id The ID of the message to check.
     * @return A boolean indicating whether a message exists with the given ID.
     */
    public boolean messageExists(int id) {
        return messageDAO.getMessageByID(id) != null;
    }
}

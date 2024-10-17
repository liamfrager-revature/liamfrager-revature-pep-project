package Service;

import java.util.List;
import DAO.MessageDAO;
import Model.Message;
import Exceptions.MessageExceptions.*;

public class MessageService {
    public MessageDAO messageDAO;
    public AccountService accountService;

    /**
     * Constructor for the message service.
     */
    public MessageService() {
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
     * @return The message with the given <code>id</code>.
     */
    public Message getMessageByID(int id) {
        return messageDAO.getMessageByID(id);
    }
    
    /**
     * Add a new message to the database if the message is valid.
     * @param message The message to be added.
     * @return The new message.
     * @throws InvalidMessageTextException <code>message.message_text</code> is either empty or longer than 255 characters.
     * @throws InvalidUserIDException There is no user with the given <code>message.posted_by</code> ID.
     */
    public Message postMessage(Message message) throws InvalidMessageTextException, InvalidUserIDException {
        if (message.message_text.length() <= 0 || message.message_text.length() >= 255)
            throw new InvalidMessageTextException();
        if (!accountService.userExists(message.posted_by))
            throw new InvalidUserIDException(message.posted_by);
        return messageDAO.insertMessage(message);
        
    }
    
    /**
     * Update a message with a given ID if the given new_message is valid.
     * @param id The ID of the message to update.
     * @param new_message The new message that will replace the old message.
     * @return The updated message.
     * @throws InvalidMessageTextException <code>new_message.message_text</code> is either empty or longer than 255 characters.
     * @throws InvalidMessageIDException There is no message with the given <code>id</code>.
     */
    public Message patchMessageByID(int id, Message new_message) throws InvalidMessageTextException, InvalidMessageIDException {
        if (new_message.message_text.length() <= 0 || new_message.message_text.length() >= 255)
            throw new InvalidMessageTextException();
        if (!messageExists(id))
            throw new InvalidMessageIDException();
        return messageDAO.updateMessageByID(id, new_message);
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
     * @return A list of all messages by the user with the given <code>id</code>.
     */
    public List<Message> getAllMessagesByAccountID(int id) {
        return messageDAO.getAllMessagesByAccountID(id);
    }
    
    /**
     * @param id The ID of the message to check.
     * @return A boolean indicating whether a message exists with the given <code>id</code>.
     */
    public boolean messageExists(int id) {
        return messageDAO.getMessageByID(id) != null;
    }
}

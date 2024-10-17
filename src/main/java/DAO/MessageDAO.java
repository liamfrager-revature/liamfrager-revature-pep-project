package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    /**
     * <strong>SQL:</strong> "SELECT * FROM message"
     * @return An list of <code>Message</code> objects for all messages, or null if the operation failed.
     */
    public List<Message> getAllMessages() {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<Message>();
        try {
            String sql = "SELECT * FROM message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * <strong>SQL:</strong> "SELECT * FROM message WHERE message_id = <code>id</code>"
     * @param id The id of the message to be returned.
     * @return A <code>Message</code> object of the message data, or null if the message doesn't exist.
     */
    public Message getMessageByID(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                return message;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * <strong>SQL:</strong> "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (<code>message.posted_by</code>, <code>message.message_text</code>, <code>message.time_posted_epoch</code>)"
     * @param message The message data of the message to be added.
     * @return A <code>Message</code> object of the new message data, or null if the operation failed.
     */
    public Message insertMessage(Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.posted_by);
            preparedStatement.setString(2, message.message_text);
            preparedStatement.setLong(3, message.time_posted_epoch);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int messsageID = generatedKeys.getInt(1);
                    message.message_id = messsageID;
                    return message;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * <strong>SQL:</strong> "UPDATE message SET message_text = <code>message.message_text</code> WHERE message_id = <code>id</code>"
     * @param id The ID of the message to update.
     * @param message A <code>Message</code> object with the new <code>message_text</code>.
     * @return A <code>Message</code> object of the updated message data, or null if the message doesn't exists or the operation failed.
     */
    public Message updateMessageByID(int id, Message message) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, message.message_text);
            preparedStatement.setInt(2, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int messageID = generatedKeys.getInt(1);
                    return this.getMessageByID(messageID);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * <strong>SQL:</strong> "DELETE FROM message WHERE message_id = <code>id</code>"
     * @param id The ID of the message to delete.
     * @return A <code>Message</code> object of the deleted message data, or null if the message doesn't exist or the operation failed.
     */
    public Message deleteMessageByID(int id) {
        Connection connection = ConnectionUtil.getConnection();
        Message message = getMessageByID(id);
        try {
            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return message;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * <strong>SQL:</strong> "SELECT * FROM message WHERE posted_by =  <code>id</code>"
     * @param id The ID of the user whose messages will be returned.
     * @return A list of <code>Message</code> objects for all of a user's messages, or null if the operation failed.
     */
    public List<Message> getAllMessagesByAccountID(int id) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<Message>();
        try {
            String sql = "SELECT * FROM message WHERE posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Message message = new Message(
                    rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getLong("time_posted_epoch")
                );
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

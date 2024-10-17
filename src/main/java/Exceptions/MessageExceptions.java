package Exceptions;

public class MessageExceptions {

    /**
     * Is thrown when a message's text is either empty or longer than 255 characters.
     */
    public static class InvalidMessageTextException extends Exception {
        public InvalidMessageTextException() {
            super();
        }
        public InvalidMessageTextException(String message) {
            super(message);
        }
    }

    /**
     * Is thrown when there is no message with a given ID.
     */
    public static class InvalidMessageIDException extends Exception {
        public InvalidMessageIDException() {
            super();
        }
        public InvalidMessageIDException(String message) {
            super(message);
        }
    }

    /**
     * Is thrown when a message's posted_by ID doesn't have a corresponding user.
     */
    public static class InvalidUserIDException extends Exception {
        public InvalidUserIDException() {
            super("A user with that id does not exist.");
        }
        public InvalidUserIDException(int id) {
            super("A user with id '" + id + "' does not exist.");
        }
    }
    
}

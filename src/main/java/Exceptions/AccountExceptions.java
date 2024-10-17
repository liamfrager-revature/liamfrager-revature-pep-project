package Exceptions;

public class AccountExceptions {

    /**
     * Is thrown when a given username is blank.
     */
    public static class InvalidUsernameException extends Exception {
        public InvalidUsernameException() {
            super();
        }
    }

    /**
     * Is thrown when a given password is less than 4 characters long.
     */
    public static class InvalidPasswordException extends Exception {
        public InvalidPasswordException() {
            super();
        }
    }

    /**
     * Is thrown when a given username and password is an invalid login.
     */
    public static class InvalidLoginException extends Exception {
        public InvalidLoginException() {
            super();
        }
    }

    /**
     * Is thrown when a given username already exists.
     */
    public static class UserAlreadyExistsException extends Exception {
        public UserAlreadyExistsException() {
            super();
        }
    }
    
}

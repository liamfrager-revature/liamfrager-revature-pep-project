package Exceptions;

public class AccountExceptions {

    /**
     * Is thrown when a given username is blank.
     */
    public static class InvalidUsernameException extends Exception {
        public InvalidUsernameException() {
            super("The given username is invalid.");
        }
    }

    /**
     * Is thrown when a given password is less than 4 characters long.
     */
    public static class InvalidPasswordException extends Exception {
        public InvalidPasswordException() {
            super("The given password is too short.");
        }
    }

    /**
     * Is thrown when a given username and password is an invalid login.
     */
    public static class InvalidLoginException extends Exception {
        public InvalidLoginException() {
            super("Could not find a user with that username and password.");
        }
    }

    /**
     * Is thrown when a given username already exists.
     */
    public static class UserAlreadyExistsException extends Exception {
        public UserAlreadyExistsException() {
            super("A user with that username already exists.");
        }
    }
}

package Service;

import DAO.AccountDAO;
import Model.Account;
import Exceptions.AccountExceptions.*;

public class AccountService {
    public AccountDAO accountDAO;

    /**
     * Constructor for the account service.
     */
    public AccountService() {
        accountDAO = new AccountDAO();
    }

    /**
     * Register a user with a given account if the account does not exist.
     * @param account The account of the user to register.
     * @return The account of the registered user.
     * @throws InvalidUsernameException <code>account.username</code> is blank.
     * @throws InvalidPasswordException <code>account.password</code> is less than 4 characters long.
     * @throws UserAlreadyExistsException a user with username <code>account.username</code> already exists.
     */
    public Account register(Account account) throws InvalidUsernameException, InvalidPasswordException, UserAlreadyExistsException {
        if (account.username.length() <= 0)
            throw new InvalidUsernameException();
        if (account.password.length() < 4)
            throw new InvalidPasswordException();
        if (userExists(account.username))
            throw new UserAlreadyExistsException();
        return accountDAO.createAccount(account);
    }

    /**
     * Login a user with a given account.
     * @param account The account of the user to login.
     * @return The account of the logged in user.
     * @throws InvalidLoginException <code>account.username</code> and <code>account.password</code> do not make a valid login.
     */
    public Account login(Account account) throws InvalidLoginException {
        // If the username and password provided in the request body JSON match a real account existing on the database.
        Account validAccount = accountDAO.getAccountByUsernameAndPassword(account.username, account.password);
        if (validAccount == null)
            throw new InvalidLoginException();
        return validAccount;
    }

    /**
     * @param id The ID of the user to check.
     * @return A boolean indicating whether a user exists with the given <code>id</code>.
     */
    public boolean userExists(int id) {
        return accountDAO.getAccountByID(id) != null;
    }

    /**
     * @param username The username of the user to check.
     * @return A boolean indicating whether a user exists with the given <code>username</code>.
     */
    public boolean userExists(String username) {
        return accountDAO.getAccountByUsername(username) != null;
    }
}

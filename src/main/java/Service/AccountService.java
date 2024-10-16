package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    public AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    /**
     * Register a user with a given account if the account does not exist.
     * @param account The account of the user to register.
     * @return The account of the registered user.
     */
    public Account Register(Account account) throws Exception {
        // If the username is not blank, the password is at least 4 characters long, and an Account with that username does not already exist.
        if (account.username.length() > 0 && account.password.length() >= 4 && userExists(account.username))
            return accountDAO.createAccount(account);
        throw new Exception();
    }

    /**
     * Login a user with a given account.
     * @param account The account of the user to login.
     * @return The account of the logged in user.
     */
    public Account Login(Account account) throws Exception {
        // If the username and password provided in the request body JSON match a real account existing on the database.
        Account validAccount = accountDAO.getAccountByUsernameAndPassword(account.username, account.password);
        if (validAccount != null)
            return account;
        throw new Exception();
    }

    /**
     * @param id The ID of the user to check.
     * @return A boolean indicating whether a user exists with the given ID.
     */
    public boolean userExists(int id) {
        return accountDAO.getAccountByID(id) != null;
    }

    /**
     * @param username The username of the user to check.
     * @return A boolean indicating whether a user exists with the given username.
     */
    public boolean userExists(String username) {
        return accountDAO.getAccountByUsername(username) != null;
    }
}

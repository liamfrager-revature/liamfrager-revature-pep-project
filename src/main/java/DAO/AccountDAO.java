package DAO;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    /**
     * <strong>SQL:</strong> "SELECT * FROM account WHERE account_id = <code>id</code>"
     * @param id The ID of the user whose account will be returned.
     * @return An <code>Account</code> object of the user's data, or null if the operation failed.
     */
    public Account getAccountByID(int id) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                Account account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * <strong>SQL:</strong> "SELECT * FROM account WHERE username = <code>username</code>"
     * @param username The username of the user whose account will be returned.
     * @return An <code>Account</code> object of the user's data, or null if the operation failed.
     */
    public Account getAccountByUsername(String username) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                Account account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * <strong>SQL:</strong> "SELECT * FROM account WHERE username = <code>username</code> AND password = <code>password</code>"
     * @param username The username of the user whose account will be returned.
     * @param password The password of the user whose account will be returned.
     * @return An <code>Account</code> object of the user's data, or null if the operation failed.
     */
    public Account getAccountByUsernameAndPassword(String username, String password) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()){
                Account account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * <strong>SQL:</strong> "INSERT INTO account (username, password) VALUES (<code>account.username</code>, <code>account.password</code>)"
     * @param account The account data of the user that will be created.
     * @return An <code>Account</code> object of the new user's data, or null if the operation failed.
     */
    public Account createAccount(Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);
            int updatedRows = preparedStatement.executeUpdate();
            if (updatedRows > 0) {
                return this.getAccountByUsername(account.username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

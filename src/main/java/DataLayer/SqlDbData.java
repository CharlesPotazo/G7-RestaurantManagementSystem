package DataLayer;

import Models.*;
import java.sql.*;
import java.util.*;

public class SqlDbData {

    // Lagi po nag poprompt na need ng throws kaya cinlick lang po namin
    private Connection getConnection() throws SQLException  {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/records", "root", "Charuzu03");
    }

    // Method getting all users from database
    public List<User> getUsers() {
        String selectStatement = "SELECT accountNumber, user_password, userName FROM employees";
        List<User> users = new ArrayList<User>();

        //same po need daw po lagyan ng try and catch tas cinlick lang po namin yung hint 
        try (Connection connection = getConnection();
             PreparedStatement selectCommand = connection.prepareStatement(selectStatement);
             ResultSet reader = selectCommand.executeQuery()) {
            
            while (reader.next()) {
                String accountNumber = reader.getString("accountNumber");
                String userPassword = reader.getString("user_password");
                String userName = reader.getString("userName");

                User readUser = new User();
                readUser.accountNumber = accountNumber;  
                readUser.user_password = userPassword;
                readUser.userName = userPassword;

                users.add(readUser);
            }
        } catch (SQLException e) {
        }
        return users;
    }
    
    public User GetUserByAccNum(String accountNumber)
    {
 
        return null;
    }
}
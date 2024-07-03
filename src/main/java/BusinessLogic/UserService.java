package BusinessLogic;

import Models.*;
import DataLayer.*;
import java.util.*;

public class UserService {

    private SqlDbData sqlDbData;

    public UserService() {
        this.sqlDbData = new SqlDbData(); // the SqlDbData is initialized so that sqlDbData is ready when methods of UserService need the SqlDbData
    }

    public List<User> getAllUsers() {
        return sqlDbData.getUsers(); //get all users from the sql that gui can use
    }

    // Method po na iveverify kung existing yung user thru accNumber and password
    public boolean verifyUser(String accountNumber, String userPassword) {
        List<User> users = getAllUsers();
        boolean result = false;
        for (User user : users) {
            if (user.accountNumber.equals(accountNumber) && user.user_password.equals(userPassword)) {
                result = true;
                return result;
            }
        }
        return result;
    }

}

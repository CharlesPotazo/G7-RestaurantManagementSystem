package DataLayer;

import Models.*;
import java.sql.*;
import java.util.*;

public class SqlDbData {

    List<Food> foods;

    // Lagi po nag poprompt na need ng throws kaya cinlick lang po namin
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/records", "root", "Charuzu03");
    }

    // Method getting all users from database
    public List<User> getUsers() { //this string connection Get all the user
        String selectStatement = "SELECT accountNumber, user_password, userName FROM employees";
        List<User> users = new ArrayList<User>();

        //same po need daw po lagyan ng try and catch tas cinlick lang po namin yung hint 
        try (Connection connection = getConnection(); PreparedStatement selectCommand = connection.prepareStatement(selectStatement); ResultSet reader = selectCommand.executeQuery()) {

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
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return users;
    }

    public List<Food> getFoods() { //this string connection Get all the food 
        String selectStatement = "SELECT foodName,price,quantity,sold, wasteCount FROM stackedfoods ORDER BY sold DESC";
        List<Food> foods = new ArrayList<Food>();

        //same po need daw po lagyan ng try and catch tas cinlick lang po namin yung hint 
        try (Connection connection = getConnection(); PreparedStatement selectCommand = connection.prepareStatement(selectStatement); ResultSet reader = selectCommand.executeQuery()) {

            while (reader.next()) {
                String foodName = reader.getString("foodName");
                int price = reader.getInt("price");
                int quantity = reader.getInt("quantity");
                int sold = reader.getInt("sold");
                int wasteCount = reader.getInt("wasteCount");

                Food readFood = new Food();
                readFood.foodName = foodName;
                readFood.price = price;
                readFood.quantity = quantity;
                readFood.sold = sold;
                readFood.wasteCount = wasteCount;
                foods.add(readFood);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return foods;
    }

    public Food GetFoodByName(String foodName) { //this string connection Get the food by their name
        String findCommand = "SELECT * FROM stackedfoods WHERE foodName = ?";
        Food food = null;

        try (Connection connection = getConnection(); PreparedStatement selectCommand = connection.prepareStatement(findCommand)) {

            selectCommand.setString(1, foodName);

            try (ResultSet reader = selectCommand.executeQuery()) {
                if (reader.next()) {
                    food = new Food();
                    food.foodName = reader.getString("foodName");
                    food.price = reader.getInt("price");
                    food.quantity = reader.getInt("quantity");
                    food.sold = reader.getInt("sold");
                    food.wasteCount = reader.getInt("wasteCount");
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return food;
    }

    public void updateFood(Food food) {  //this string connection update if there are needed changes in out food data base
        String updateStatement = "UPDATE stackedfoods SET quantity = ?, sold = ?, wasteCount = ? WHERE foodName = ?";

        try (Connection connection = getConnection(); PreparedStatement updateCommand = connection.prepareStatement(updateStatement)) {

            updateCommand.setInt(1, food.quantity);
            updateCommand.setInt(2, food.sold);
            updateCommand.setInt(3, food.wasteCount);
            updateCommand.setString(4, food.foodName);

            updateCommand.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

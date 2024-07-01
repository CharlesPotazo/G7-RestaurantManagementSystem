package BusinessLogic;

import DataLayer.*;
import Models.*;
import java.util.List;

public class FoodService {

    private SqlDbData foodDatabase;

    public FoodService() {
        foodDatabase = new SqlDbData();
    }

    public Food getFoodByFoodName(String foodName) {
        return foodDatabase.GetFoodByName(foodName);
    }

    public List<Food> getAllFoods() {
        return foodDatabase.getFoods();
    }

    public void SubtractQuantity(String foodName, int amount) {
        Food food = foodDatabase.GetFoodByName(foodName);

        if (food != null && food.quantity >= amount) {
            food.quantity -= amount;

            foodDatabase.updateFood(food);
        }
    }
    
       public void AddSoldQuantity(String foodName, int amount) {
        Food food = foodDatabase.GetFoodByName(foodName);

   
        if (food != null && food.quantity >= amount) {
        
            food.quantity += amount;

         
            foodDatabase.updateFood(food);
        
        }
    }
    

    public double calculateTotal(List<Food> foodItems) {
        double total = 0;
        for (Food item : foodItems) {
            total += item.price * item.quantity;
        }
        return total;
    }
}

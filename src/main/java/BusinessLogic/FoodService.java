package BusinessLogic;

import DataLayer.*;
import Models.*;
import java.util.List;

public class FoodService {

    private SqlDbData foodDatabase;

    public FoodService() {
        foodDatabase = new SqlDbData(); // the SqlDbData is initialized so that sqlDbData is ready  when methods of UserService need the SqlDbData
    }

    public Food getFoodByFoodName(String foodName) {
        return foodDatabase.GetFoodByName(foodName); //get the food by their name. We used this po pag may specific po kaming food lang na need imanipulate yung data
    }

    public List<Food> getAllFoods() { // get all the food
        return foodDatabase.getFoods();
    }

    public void SubtractQuantity(String foodName, int amount) { //subtract quantity to that specific food
        Food food = foodDatabase.GetFoodByName(foodName); //check if that food exists

        if (food != null && food.quantity >= amount) {
            food.quantity = food.quantity - amount; //yung logic po sa pag babawas ng quantity

            foodDatabase.updateFood(food); //update or refresh the food table in out database
        }
    }

    public void AddSoldQuantity(String foodName, int amount) { // add sold quantity to that specific food. We used this in our reports Menu po
        Food food = foodDatabase.GetFoodByName(foodName);//check if that food exists

        if (food != null) {

            food.sold = amount + food.sold;//yung logic po sa pag aadd ng quantity

            foodDatabase.updateFood(food);//update or refresh the food table in out database
        }
    }

    public void AddWasteCountQuantity(String foodName, int amount) {// add waste count to that specific food. We used this in our waste tracking  Menu po
        Food food = foodDatabase.GetFoodByName(foodName);//check if that food exists

        if (food != null) {

            food.wasteCount = food.wasteCount + amount;//yung logic po sa pag aadd ng quantity

            foodDatabase.updateFood(food);//update or refresh the food table in out database
        }
    }

    public void AddQuantity(String foodName, int amount) {// add quantity to that specific food. We used this in our waste tracking  Menu po
        Food food = foodDatabase.GetFoodByName(foodName);//check if that food exists

        if (food != null) {

            food.quantity = food.quantity + amount;//yung logic po sa pag aadd ng quantity

            foodDatabase.updateFood(food);//update or refresh the food table in out database
        }
    }
}

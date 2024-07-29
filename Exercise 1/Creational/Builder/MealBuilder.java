
package Creational.Builder;

/**
 *
 * @author Ajithaa_Malini
 */
public class MealBuilder {
    private String mainDish;
    private String sideDish;
    private String drink;
    private String dessert;

    public MealBuilder setMainDish(String mainDish) {
        this.mainDish = mainDish;
        return this;
    }

    public MealBuilder setSideDish(String sideDish) {
        this.sideDish = sideDish;
        return this;
    }

    public MealBuilder setDrink(String drink) {
        this.drink = drink;
        return this;
    }

    public MealBuilder setDessert(String dessert) {
        this.dessert = dessert;
        return this;
    }

    public Meal build() {
        return new Meal(mainDish, sideDish, drink, dessert);
    }

    public static void main(String[] args) {
        MealBuilder builder = new MealBuilder();
        Meal meal = builder.setMainDish("Steak")
                           .setSideDish("Salad")
                           .setDrink("Wine")
                           .setDessert("Cheesecake")
                           .build();
        
        System.out.println(meal);

        Meal anotherMeal = builder.setMainDish("Burger")
                                  .setSideDish("Fries")
                                  .setDrink("Soda")
                                  .setDessert("Ice Cream")
                                  .build();

        System.out.println(anotherMeal);
    }
}


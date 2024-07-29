
package Creational.Builder;

/**
 *
 * @author Ajithaa_Malini
 */
public class Meal {
    private String mainDish;
    private String sideDish;
    private String drink;
    private String dessert;

    public Meal(String mainDish, String sideDish, String drink, String dessert) {
        this.mainDish = mainDish;
        this.sideDish = sideDish;
        this.drink = drink;
        this.dessert = dessert;
    }

    @Override
    public String toString() {
        return "Meal [mainDish=" + mainDish + ", sideDish=" + sideDish + ", drink=" + drink + ", dessert=" + dessert + "]";
    }
}


package Behavioural.Observer;

/**
 *
 * @author Ajithaa_Malini
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}


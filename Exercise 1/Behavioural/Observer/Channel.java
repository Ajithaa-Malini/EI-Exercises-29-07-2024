
package Behavioural.Observer;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Ajithaa_Malini
 */


public class Channel {
    private List<User> users = new ArrayList<>();

    public void registerObserver(User user) {
        users.add(user);
    }

    public void removeObserver(User user) {
        users.remove(user);
    }

    public void notifyObservers(String message) {
        for (User user : users) {
            user.update(message);
        }
    }
}

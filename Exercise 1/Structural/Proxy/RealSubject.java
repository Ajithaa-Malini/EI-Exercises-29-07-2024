
package Structural.Proxy;
/*A class that implements the Subject interface and handles the actual request.*/
/**
 *
 * @author Ajithaa_Malini
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("Request handled by RealSubject");
    }
}


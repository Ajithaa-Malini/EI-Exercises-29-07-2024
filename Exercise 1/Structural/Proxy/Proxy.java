
package Structural.Proxy;
/*A class that also implements the Subject interface and controls access to a RealSubject instance. It performs some additional operations (like logging, access control, etc.) before delegating the request to the RealSubject.*/
/**
 *
 * @author Ajithaa_Malini
 */
public class Proxy implements Subject {
    private RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        System.out.println("Proxy handling request before delegating to RealSubject");
        realSubject.request();
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}

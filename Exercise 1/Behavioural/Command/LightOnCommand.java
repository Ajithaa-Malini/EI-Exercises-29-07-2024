
package Behavioural.Command;

/**
 *
 * @author Ajithaa_Malini
 */
public class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
    
}

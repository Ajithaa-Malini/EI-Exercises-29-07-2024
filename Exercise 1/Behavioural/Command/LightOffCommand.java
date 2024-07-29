
package Behavioural.Command;

/**
 *
 * @author Ajithaa_Malini
 */
public class LightOffCommand implements Command{
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

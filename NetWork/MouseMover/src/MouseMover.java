import java.awt.*;
import java.util.Random;

/**
 * this program is for moving mouse each 10 seconds
 */
public class MouseMover {
    public static void main(String[] args) {
        //by point we can have current x and y of cursor
        Point point = MouseInfo.getPointerInfo().getLocation();

        //making random number for having next location of cursor
        Random random = new Random();

        try {
            //robot uses for controlling mouse(here we just use its cursor mode)
            Robot robot = new Robot();
            while (true) {
                //i wanna just move it a little thus do this works here
                robot.mouseMove(point.x + (random.nextInt() % 3 - 1), point.y + (random.nextInt() % 3 - 1));
                //do it every 10 s
                Thread.sleep(10000);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

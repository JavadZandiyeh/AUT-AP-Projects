import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import java.io.IOException;

/**
 * this class is for using other classes to make our clock.
 * it prints the clock, tick the time and ptrint the ticked
 * time in place of our time.this make our clock
 * @author Mohammad Javad zandiyeh
 * @version 2020.0.3
 */
public class Run {
    //this is an objest from ClockDisplay class
    private static ClockDisplay clock;

    public static void main(String[] args) throws InterruptedException, IOException {
        getInfo();
        for(int i=0 ; i<200;i++) {
            System.out.println(clock.getDisplay());
            TimeUnit.SECONDS.sleep(1);
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }
    }

    /**
     * this class get the time currunt from user and start tickking from
     * that time.
     */
    public static void getInfo(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("write your clock:    ");
        String displayString = scanner.next();

        int hour = Integer.parseInt(displayString.charAt(0) + "" + displayString.charAt(1));
        int minute = Integer.parseInt (displayString.charAt(3) + "" + displayString.charAt(4));
        int second = Integer.parseInt(displayString.charAt(6) + "" + displayString.charAt(7));
        clock = new ClockDisplay(hour,minute,second);
    }
}

/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before
 * midnight).
 *
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 *
 * @author Mohammad Javad Zandiyeh
 * @version 2020.0.3
 */
public class ClockDisplay {
    private NumberDisplay hour;
    private NumberDisplay minute;
    private NumberDisplay second;
    private String displayString;

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at 00:00.
     */
    public ClockDisplay(){
        hour = new NumberDisplay(24);
        minute = new NumberDisplay(60);
        second = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the
     * parameters.
     */
    public ClockDisplay(int hours, int minutes, int seconds){
        hour = new NumberDisplay(24);
        minute = new NumberDisplay(60);
        second = new NumberDisplay(60);
        hour.setValue(hours);
        minute.setValue(minutes);
        second.setValue(seconds);
        updateDisplay();
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick() {
        second.increment();
        if(second.getDisplayValue().equals("00")){
            minute.increment();
        }
        if((minute.getDisplayValue().equals("00"))&&(second.getDisplayValue().equals("00"))){
            hour.increment();
        }
        updateDisplay();
    }

    /**
     * Update the internal string that represents the display.
     */
    private void updateDisplay(){
        displayString = hour.getDisplayValue() + ":" + minute.getDisplayValue() + ":" + second.getDisplayValue();
    }

    /**
     * this method returns the display after doing the
     * timeTick method.
     * @return an String that is display
     */
    public String getDisplay(){
        timeTick();
        return displayString;
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }

    /**
     * Update the internal string that represents the display.
     */
}

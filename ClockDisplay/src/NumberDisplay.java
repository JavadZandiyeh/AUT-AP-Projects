/**
 * The NumberDisplay class represents a digital number display that can hold
 * values from zero to a given limit. The limit can be specified when creating
 * the display. The values range from zero (inclusive) to limit-1. If used,
 * for example, for the seconds on a digital clock, the limit would be 60,
 * resulting in display values from 0 to 59. When incremented, the display
 * automatically rolls over to zero when reaching the limit.
 *
 * @author Mohammad Javad Zandiyeh
 * @version 2020.0.3
 */
public class NumberDisplay {

    private int limit;
    private int value;

    /**
     * Constructor for objects of class NumberDisplay.
     * Set the limit at which the display rolls over.
     */
    public NumberDisplay(int rollOverDisplay){
        limit = rollOverDisplay;
        value = 0;
    }

    /**
     * Return the current value.
     */
    public int getValue()
    {
        return value;
    }

    /**
     * this method incread the value till limit time.after reaching to limit
     * time the value is zero.
     */
    public void increment(){
        value = (value + 1) % limit;
    }

    /**
     * Return the display value (that is, the current value as a two-digit
     * String. If the value is less than ten, it will be padded with a leading
     * zero).
     */
    public String getDisplayValue(){
        if(value >= 10)
            return "" + value;
        else
            return "0" + value;
    }

    /**
     * Set the value of the display to the new specified value. If the new
     * value is less than zero or over the limit, do nothing.
     */
    public void setValue(int replacementValue){
        if((replacementValue >= 0)&&(replacementValue <= limit)){
            value = replacementValue;
        }
    }
}

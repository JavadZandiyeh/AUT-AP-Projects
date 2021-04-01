import java.io.IOException;

public abstract class Card{

    /**
     * inner class for color of cards
     */
    enum enumColor{
        RED, GREEN, YELLOW, BLUE, BLACK
    }

    /**
     * inner class for side witch this card will be show on
     */
    enum enumTypeOfShow{
        BackHorizontal,FrontHorizontal,BackVertical,FrontVertical
    }

    //colors unicodes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_BLACK = "\u001B[30m";

    protected Card.enumColor color;
    protected Ground ground;

    /**
     * @param ground ground witch cards are on
     * @param color color of card
     */
    public Card(Ground ground, Card.enumColor color){
        this.ground = ground;
        this.color = color;
    }

    /**
     * the task of card
     */
    public abstract void doTask() throws IOException, InterruptedException;

    /**
     * @return true if can put any card
     */
    public abstract boolean canPut();

    /**
     * @return name or content of card
     */
    public String getName(){
        if(this instanceof NumericCard)
            return ""+((NumericCard) this).getNumber()+"  ";
        if(this instanceof SkipCard)
            return "SKP";
        if(this instanceof DrawCard)
            return "D2+";
        if(this instanceof ReverseCard)
            return "REV";
        if(this instanceof WildColorChange)
            return "CLR";
        if(this instanceof WildDraw)
            return "D4+";
        return "   ";
    }

    /**
     * @return color of card
     */
    protected String getColor () {
        switch (color) {
            case RED: {return ANSI_RED;}
            case GREEN: {return ANSI_GREEN;}
            case YELLOW: {return ANSI_YELLOW;}
            case BLUE: {return ANSI_BLUE;}
            case BLACK: {return ANSI_BLACK;}
        }
        return null;
    }

    /**
     * @param line line witch we want to show cards
     * @param type can be the content of enumType
     * @param end say the card that is the card is last card or not
     */
    public void show(int line, enumTypeOfShow type, boolean end){
        String content = null;

        switch (type){
            case BackHorizontal:
            case BackVertical:
                content = "   ";
                break;

            case FrontHorizontal:
            case FrontVertical:
                content = this.getName();
                break;
        }

        if(type.equals(enumTypeOfShow.FrontHorizontal) || type.equals(enumTypeOfShow.FrontVertical))
            System.out.print(this.getColor());

        switch (type) {
            case FrontVertical:
            case BackVertical:
                if(end == true)
                    System.out.print('\u2579' + "-----" + '\u2579');
                else {
                    switch (line % 2) {
                        case 0: System.out.print('\u250c' + "-----" + '\u2510'); break;
                        case 1: System.out.print("| " + content + " |"); break;
                    }
                }
            break;

            case FrontHorizontal:
            case BackHorizontal:
                switch (line % 3) {
                    case 0:
                        System.out.print('\u250c'+"---");
                        if(end == true)
                            System.out.print("--"+'\u2510');
                    break;

                    case 1:
                        System.out.print("|" + content);
                        if(end == true)
                            System.out.print("  |");
                    break;

                    case 2:
                        System.out.print('\u2579'+"---");
                        if(end == true)
                            System.out.print("--"+'\u2579');
                    break;
                }
                break;
            }

        System.out.print(ANSI_RESET);

    }

}



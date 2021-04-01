import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * wild card witch extends card
 * this is an abstract class too
 */
public abstract class WildCard extends Card{

    /**
     * @param ground ground witch cards are on
     * @param color color of card
     */
    public WildCard(Ground ground, Card.enumColor color){
        super(ground, color);
    }

    /**
     * do the task of card
     */
    @Override
    public void doTask() throws IOException, InterruptedException {
        //this method ask the players to notice the color they want ot be this card
        if(ground.getPlayerTurn() instanceof HumPlayer) {
            System.out.println("Witch color for your wild card?(1.RED  2.BLUE  3.GREEN  4.YELLOW)");
            Scanner scanner = new Scanner(System.in);
            int menu = scanner.nextInt();
            while (menu < 1 || menu > 4){
                System.out.println("Not valid chose,try again");
                menu = scanner.nextInt();
            }

            switch (menu) {
                case 1: color = enumColor.RED; break;
                case 2: color = enumColor.BLUE; break;
                case 3: color = enumColor.GREEN; break;
                case 4: color = enumColor.YELLOW; break;
            }
        }
        //if this is a computer player this color will be chosen randomly
        else
            color = setRandomColor();
    }

    /**
     * set a random color for card and retrn this color
     * @return random color
     */
    private Card.enumColor setRandomColor(){
        Random random = new Random();
        int rand = random.nextInt(4);
        switch (rand){
            case 0: return enumColor.RED;
            case 1: return enumColor.BLUE;
            case 2: return enumColor.GREEN;
            case 3: return enumColor.YELLOW;
        }
        return enumColor.RED;
    }

    /**
     * @param color we want to set it
     */
    public void setColor(Card.enumColor color){
        this.color = color;
    }

    /**
     * this method say that is this car can be the next card or not
     * @return true for can put card
     */
    @Override
    public boolean canPut() {
        //if we have a color card witch we can put no wild card can be the next card
        for(Card card: ground.getPlayerTurn().getCards()){
            if(!(card instanceof WildCard)) {
                if (card.canPut())
                    return false;
            }
        }
        return true;
    }
}

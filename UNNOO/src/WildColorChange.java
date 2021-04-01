import java.io.IOException;

public class WildColorChange extends WildCard {
    /**
     * @param ground ground witch cards are on
     * @param color color of card
     */
    public WildColorChange(Ground ground, Card.enumColor color){
        super(ground, color);
    }

    /**
     * this method sets the next cards color
     * and say to player that can put a card like this card color
     */
    @Override
    public void doTask() throws IOException, InterruptedException {
        super.doTask();
        ground.changeGroundCard(this);
        ground.changePlayerTurn(Ground.enumPlayerTurn.Next);
    }
}

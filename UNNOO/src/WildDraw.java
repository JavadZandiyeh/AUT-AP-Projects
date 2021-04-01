import java.io.IOException;

/**
 * this class extends wildCard class
 */
public class WildDraw extends WildCard {

    /**
     * @param ground ground witch cards are on
     * @param color color of card
     */
    public WildDraw(Ground ground, Card.enumColor color) {
        super(ground, color);
    }

    /**
     * like draw card but draw players 4 cards
     */
    @Override
    public void doTask() throws IOException, InterruptedException {
        int i = 1;
        super.doTask();
        ground.changeGroundCard(this);
        ground.changePlayerTurn(Ground.enumPlayerTurn.Next);

        //if next player has draw card the next of this player should draw 4 or...
        while (ground.getPlayerTurn().hasWildDrawCard()){
            ground.show();
            super.doTask();
            ground.changeGroundCard(ground.getPlayerTurn().getWildDrawCard());
            ground.getPlayerTurn().removeCard(ground.getPlayerTurn().getWildDrawCard());
            ground.changePlayerTurn(Ground.enumPlayerTurn.Next);
            i++;
        }

        ground.drawPlayer(4 * i);
        ground.changePlayerTurn(Ground.enumPlayerTurn.Next);
    }

}

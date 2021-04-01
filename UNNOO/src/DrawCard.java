import java.io.IOException;

public class DrawCard extends Card {
    /**
     * @param ground ground witch cards are on
     * @param color color of card
     */
    public DrawCard(Ground ground, Card.enumColor color) {
        super(ground, color);
    }

    /**
     * task of card
     * this card draw next player 2 cards
     * and go to next next player
     */
    @Override
    public void doTask() throws IOException, InterruptedException {
        int i = 1;
        ground.changeGroundCard(this);
        ground.changePlayerTurn(Ground.enumPlayerTurn.Next);

        //if next player has draw card the next of this player should draw 4 or...
        while (ground.getPlayerTurn().hasDrawCard()){
            ground.show();
            ground.changeGroundCard(ground.getPlayerTurn().getDrawCard());
            ground.getPlayerTurn().removeCard(ground.getPlayerTurn().getDrawCard());
            ground.changePlayerTurn(Ground.enumPlayerTurn.Next);
            i++;
        }

        ground.drawPlayer(2 * i);
        ground.changePlayerTurn(Ground.enumPlayerTurn.Next);
    }

    /**
     * @return true if can put any card
     */
    @Override
    public boolean canPut() {
        if (ground.getGroundCard().color.equals(color))
            return true;
        return (ground.getGroundCard() instanceof DrawCard);
    }
}

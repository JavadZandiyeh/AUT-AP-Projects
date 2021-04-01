/**
 * this class extends card
 */
public class SkipCard extends Card {

    /**
     * @param ground ground witch cards are on
     * @param color color of card
     */
    public SkipCard(Ground ground, Card.enumColor color){
        super(ground, color);
    }

    /**
     * changes the turn of players to next of next
     */
    @Override
    public void doTask() {
        ground.changeGroundCard(this);
        ground.changePlayerTurn(Ground.enumPlayerTurn.NextNext);
    }

    /**
     * @return true if can put any card
     */
    @Override
    public boolean canPut() {
        if (ground.getGroundCard().color.equals(color))
            return true;
        return (ground.getGroundCard() instanceof SkipCard);
    }
}

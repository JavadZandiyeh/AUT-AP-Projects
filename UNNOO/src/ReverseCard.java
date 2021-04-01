public class ReverseCard extends Card {

    /**
     * @param ground ground witch cards are on
     * @param color color of card
     */
    public ReverseCard(Ground ground, Card.enumColor color){
        super(ground, color);
    }

    /**
     * this card reverse the side of game and put it self as the ground card
     */
    @Override
    public void doTask() {
        ground.changeGroundCard(this);
        ground.changeSide();
        ground.changePlayerTurn(Ground.enumPlayerTurn.Next);
    }

    /**
     * @return true if can put any card
     */
    @Override
    public boolean canPut() {
        if (ground.getGroundCard().color.equals(color))
            return true;
        return (ground.getGroundCard() instanceof ReverseCard);
    }
}

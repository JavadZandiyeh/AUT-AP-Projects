/**
 * numeric card extends card class
 */
public class NumericCard extends Card {
    private int number;
    /**
     * @param ground ground witch cards are on
     * @param color color of card
     */
    public NumericCard(Ground ground, Card.enumColor color, int number){
        super(ground,color);
        this.number = number;
    }

    /**
     * @return number of card
     */
    public int getNumber() {
        return number;
    }

    /**
     * do its task mean put itself as groundCard and change the turn
     */
    @Override
    public void doTask() {
        ground.changeGroundCard(this);
        ground.changePlayerTurn(Ground.enumPlayerTurn.Next);
    }

    /**
     * @return true if can put any card
     */
    @Override
    public boolean canPut() {
        if(ground.getGroundCard().color.equals(color))
            return true;
        if(ground.getGroundCard() instanceof NumericCard )
            return ((NumericCard) ground.getGroundCard()).getNumber() == number;
        return false;
    }

}

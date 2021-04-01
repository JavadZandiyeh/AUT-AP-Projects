import java.util.Iterator;

/**
 * this class extends player class and is for computer player
 * in this class we chose a valid card and put on ground
 * and this player can show its cards too and etc.
 */
public class ComPlayer extends Player{

    public ComPlayer(int number){
        super(number);
    }

    /**
     * show card of player
     * @param playerTurn the player how is its turn and we want to show its card
     * @param line line ot show cards
     * @param type type of showing cards mean horizontal or vertical
     */
    @Override
    public void showCards(Player playerTurn, int line, enumType type) {
        //using super class method
        super.showCards(playerTurn, line, type);
        //implementation for showing cards based on cards show method

        switch (type) {
            case Horizontal:
                for(Card card: cards) {
                    if(cards.indexOf(card)+1 == cards.size())
                        card.show(line%3, Card.enumTypeOfShow.BackHorizontal,true);
                    else
                        card.show(line%3, Card.enumTypeOfShow.BackHorizontal,false);
                }
            break;

            case Vertical:
                if (line == 2 * cards.size())
                    cards.get(cards.size() -1).show(line, Card.enumTypeOfShow.BackVertical, true);
                else
                    cards.get(line / 2 ).show(line, Card.enumTypeOfShow.BackVertical, false);
            break;
        }
    }

    /**
     * this method chose a valid card and return it
     * @return will be null if is no card to put
     */
    public Card chosenCard(){
        Iterator<Card> it = cards.iterator();
        while (it.hasNext()){
            Card card = it.next();
            if(card.canPut()){
                //returning the first valid card among cards
                Card c = card;
                //remove card from our card
                removeCard(card);
                return c;
            }
        }

        return null;
    }
}

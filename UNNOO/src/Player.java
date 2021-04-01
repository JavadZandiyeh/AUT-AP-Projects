import java.util.ArrayList;
import java.util.Objects;

/**
 * player of game
 * each player has a list of cards a number and a score
 */
public abstract class Player {

    /**
     * inner class for in witch position the players card are
     */
    enum enumType{
        Horizontal,Vertical
    }

    protected ArrayList<Card> cards;
    //number of player in game
    private int number;
    private int score;

    /**
     * @param number its number on game
     */
    public Player(int number){
        cards = new ArrayList<Card>();
        this.number = number;
        //at first has no card and its score is 0
        score = 0;
    }

    /**
     * @return list of its card
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * @return number of player in game
     */
    public int numCards(){
        return cards.size();
    }

    /**
     * @return number of cards of player
     */
    public int getNumCards() {
        return cards.size();
    }

    /**
     * @return the length of cards if be in vertical position
     */
    public int verticalLength(){
        return (2*numCards()) + 1;
    }

    /**
     * @return the length of cards if be in horizontal position
     */
    public int horizontalLength(){
        return (4*numCards()) + 4;
    }

    /**
     * @param card card we want to add it to our cards
     */
    public void addCard(Card card){
        cards.add(card);
    }

    /**
     * @param card card we want to remove it from our cards
     */
    public void removeCard(Card card){
        cards.remove(card);
    }

    /**
     * an abstract method for returning the chosen card
     * @return chosen card
     */
    public abstract Card chosenCard();

    /**
     * show cards
     * @param playerTurn player how is its turn
     * @param line line of show cards
     * @param type type of showing cards
     */
    public void showCards(Player playerTurn, int line, enumType type){
        if (score == 0)
            return;
    }

    /**
     * @return score of player
     */
    public String getScore(){
        //each card has an score
        for(Card card: cards){
            if(card instanceof NumericCard) {
                score += ((NumericCard) card).getNumber();
            }else {
                if (card instanceof WildCard)
                    score += 50;
                else
                    score += 20;
            }
        }
        return "" + score;
    }

    /**
     * @return tru if player has draw card
     */
    public boolean hasDrawCard(){
        for (Card card: cards){
            if(card instanceof DrawCard)
                return true;
        }
        return false;
    }

    /**
     * @return Card witch is the draw card of player
     */
    public Card getDrawCard(){
        for (Card card: cards){
            if(card instanceof DrawCard)
                return card;
        }
        return null;
    }

    /**
     * @return Card witch is the wild draw card of player
     */
    public Card getWildDrawCard(){
        for(Card card: cards){
            if(card instanceof WildDraw)
                return card;
        }
        return null;
    }

    /**
     * @return tru if player has wild draw card
     */
    public boolean hasWildDrawCard(){
        for (Card card: cards){
            if(card instanceof WildDraw)
                return true;
        }
        return false;
    }

    /**
     * check that is this player has any card to put or not
     * @return tru for having card to put
     */
    public boolean canPutAnyCard(){
        for (Card card: cards){
            if (card.canPut()) {
                return true;
            }
        }
        return false;
    }

    /**
     * an override method for check the equality between to players
     * @param o player we want to check its equality
     * @return tru for same players
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        //checking the equality by their number
        return number == player.number &&
                cards.equals(player.cards);
    }

    /**
     * whenever we use equal we have to override hash code to
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(cards, number);
    }


    /**
     * @return an string of players number and their scores
     */
    @Override
    public String toString() {
        return "Player" + (number+1) + " -> score=" + score;
    }
}

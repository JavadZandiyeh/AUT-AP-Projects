import java.util.Scanner;

/**
 * this class extends player class
 * in this class we implements the player task
 * and chose a card for next play
 * also it has a method for showing its card
 */
public class HumPlayer extends Player{
    Scanner scanner = new Scanner(System.in);
    public HumPlayer(int number){
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
                for (Card card : cards) {
                    if (cards.indexOf(card) + 1 == cards.size())
                        if(this.equals(playerTurn))
                            card.show(line % 3, Card.enumTypeOfShow.FrontHorizontal, true);
                        else
                            card.show(line % 3, Card.enumTypeOfShow.BackHorizontal, true);
                    else {
                        if(this.equals(playerTurn))
                            card.show(line % 3, Card.enumTypeOfShow.FrontHorizontal, false);
                        else
                            card.show(line % 3, Card.enumTypeOfShow.BackHorizontal, false);
                    }
                }
            break;

            case Vertical:
                if(line == 2 * cards.size()) {
                    if (this.equals(playerTurn))
                        cards.get(cards.size() -1).show(line, Card.enumTypeOfShow.FrontVertical, true);
                    else
                        cards.get(cards.size() -1).show(line, Card.enumTypeOfShow.BackVertical, true);
                }
                else{
                    if(this.equals(playerTurn))
                        cards.get(line / 2).show(line, Card.enumTypeOfShow.FrontVertical, false);
                    else
                        cards.get(line/2).show(line, Card.enumTypeOfShow.BackVertical, false);
                }
            break;
        }
    }

    /**
     * @return the card witch this player chose
     * if it cant chose any card it returns null
     */
    @Override
    public Card chosenCard() {
        //return null for no card to put
        if(! canPutAnyCard()){
            System.out.println("Can not put any card.");
            return null;
        }

        System.out.println("Card to put:\n1 " + '\u21A0' + " N\n" + '\u21A1' + "\nN");
        int numCard = scanner.nextInt();
        //error handling
        while (numCard < 1 || numCard > getNumCards()){
            System.out.println("It is not in range of your cards,try again.");
            numCard = scanner.nextInt();
        }

        //error handling
        while (! cards.get(numCard -1).canPut()){
            System.out.println("You can not put this card,try again:    ");
            numCard = scanner.nextInt();
            while (numCard < 1 || numCard > getNumCards()){
                System.out.println("It is not in range of your cards,try again.");
                numCard = scanner.nextInt();
            }
        }
        //returning the card an remove it from our cards
        Card c = cards.get(numCard - 1);
        cards.remove(cards.get(numCard - 1));
        return c;
    }

}

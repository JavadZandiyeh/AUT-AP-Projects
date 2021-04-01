import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Mohammad Javad Zandiyeh
 * this class is for making a good cohesion between objects
 * this class implements the Comparator interface
 * for using its compare method
 * in this class we have used from singleton pattern for make
 * sure that will be sse this class for one time and making
 * just one object from it
 * the fields are player how is its turn, ground card, and
 * two array lists for storing players and cards witch are
 * not in the hand of players
 * this class notice the side of game too
 * we have two inner classes one for how to change the player
 * means to next player ir to next next player
 * and other for side witch game go one
 */
public class Ground implements Comparator<Player> {

    /**
     * this an inner class witch notice that how the player
     * change mean next or next next
     */
    enum enumPlayerTurn{
        Next,NextNext
    }

    /**
     * the inner class for side the game go on
     */
    enum enumSide{
        Clockwise,AntiClockwise
    }

    //sing singleton pattern by making an static field from this class
    private static Ground ground;
    //cards witch arr in store
    private ArrayList<Card> storeCards;
    //players
    private ArrayList<Player> players;
    //player how is its turn
    private Player playerTurn;
    //the card witch is in the center of game
    private Card groundCard;
    private Random random;
    //number of players
    private int numPlayers;
    //number of human players
    private int numHumPlayers;
    //side of game turn
    private enumSide side;

    /**
     * a private constructor for using singleton pattern
     * @param numPlayers number of players
     * @param numHumPlayers number of human players
     */
    private Ground(int numPlayers, int numHumPlayers){
        random = new Random();

        storeCards = new ArrayList<Card>();
        registerCards();

        //this loop is continue will the ground card become not wild card
        do {
            groundCard = drawACard();
        }while (groundCard instanceof WildCard);

        this.numPlayers = numPlayers;
        this.numHumPlayers = numHumPlayers;
        players = new ArrayList<Player>();
        registerPlayers();
        firstDistribution();
        //finding a random player
        playerTurn = players.get(random.nextInt(players.size()));
        //first side must be clockwise
        side = enumSide.Clockwise;
    }

    /**
     * getting an instance of this class based on singleton pattern
     * @param numPlayers number of players
     * @param numHumPlayers number of human players
     * @return an instance of this class
     */
    public static Ground getInstance(int numPlayers, int numHumPlayers){
        //this condition check that do we have any object from this class or not
        if (ground == null)
            ground = new Ground(numPlayers, numHumPlayers);
        return ground;
    }

    /**
     * @return player how is its turn
     */
    public Player getPlayerTurn() {
        return playerTurn;
    }

    /**
     * @return central card
     */
    public Card getGroundCard() {
        return groundCard;
    }

    /**
     * @param card the card we want to add it to our card store
     */
    private void addCard(Card card){
        storeCards.add(card);
    }

    /**
     * @param card the card we want to remove it from our store
     */
    private void removeCard(Card card){
        storeCards.remove(card);
    }

    /**
     * making object of players for this game based on number of them
     */
    private void registerPlayers(){
        for (int i=0; i<numHumPlayers; i++)
            players.add(new HumPlayer(i));
        for(int i=numHumPlayers; i<numPlayers; i++)
            players.add(new ComPlayer(i));
    }

    /**
     * adding the cards of game to store cards
     */
    private void registerCards() {
        //numeric cards in four colors
        for(int i=1; i<20; i++) {
            addCard(new NumericCard(this, Card.enumColor.RED, i%10));
            addCard(new NumericCard(this, Card.enumColor.BLUE, i%10));
            addCard(new NumericCard(this, Card.enumColor.GREEN, i%10));
            addCard(new NumericCard(this, Card.enumColor.YELLOW, i%10));
        }
        //move cards in four color
        for(int i=0; i<2; i++){
            addCard(new SkipCard(this, Card.enumColor.RED));
            addCard(new SkipCard(this, Card.enumColor.BLUE));
            addCard(new SkipCard(this, Card.enumColor.GREEN));
            addCard(new SkipCard(this, Card.enumColor.YELLOW));
            addCard(new ReverseCard(this, Card.enumColor.RED));
            addCard(new ReverseCard(this, Card.enumColor.BLUE));
            addCard(new ReverseCard(this, Card.enumColor.GREEN));
            addCard(new ReverseCard(this, Card.enumColor.YELLOW));
            addCard(new DrawCard(this, Card.enumColor.RED));
            addCard(new DrawCard(this, Card.enumColor.BLUE));
            addCard(new DrawCard(this, Card.enumColor.GREEN));
            addCard(new DrawCard(this, Card.enumColor.YELLOW));
        }
        //wild cards in black color
        for(int i=0; i<4; i++){
            addCard(new WildColorChange(this, Card.enumColor.BLACK));
            addCard(new WildDraw(this, Card.enumColor.BLACK));
        }
        //the shuffle method combine the array list members
        Collections.shuffle(storeCards);
    }

    /**
     * first distributing cards to players mean each player 7 cards
     */
    private void firstDistribution(){
        //find random card for each player
        for(int i=0; i<numPlayers; i++){
            for(int j=0; j<7; j++)
                players.get(i).addCard(drawACard());
        }
    }

    /**
     * this method is for find random card between store cards
     * @return the random card
     */
    private Card drawACard(){
        Card drawCard = storeCards.get(random.nextInt(storeCards.size()));
        Card draw = drawCard;
        //we remove this card from store cards because when we pick this
        //card up we dont have it on store
        removeCard(drawCard);
        return draw;
    }

    /**
     * this method draw player turn
     * @param numOfDraw number of cards we have to draw player
     */
    public void drawPlayer(int numOfDraw) {
        for(int i=0; i<numOfDraw; i++)
            //find a card and add it to player trn cards
            playerTurn.addCard(drawACard());
    }

    /**
     * changing the ground card by our card
     * @param newGroundCard the new card we want to put it as ground card
     */
    public void changeGroundCard(Card newGroundCard) {
        //when we want to change the ground card we have to put last card to
        //store cards and if it is a wild card we have to change its color to
        //its standard color means black
        if(groundCard instanceof WildCard)
           ((WildCard) groundCard).setColor(Card.enumColor.BLACK);

        //adding last central card to store
        Card c = groundCard;
        addCard(c);
        //change the card
        groundCard = newGroundCard;
    }

    /**
     * changes the player by going to next or next of next player
     * @param newPlayer can be next or next of next
     */
    public void changePlayerTurn(enumPlayerTurn newPlayer) {
        int i;
        //if our side is clockwise we have to go front
        //and else we have dto go back
        if (side.equals(enumSide.Clockwise))
            i = 1;
        else
            i = (-1);

        //change player turn to the number of new player
        if (newPlayer.equals(enumPlayerTurn.Next))
            playerTurn = players.get((players.indexOf(playerTurn) + numPlayers + i) % numPlayers);
        if (newPlayer.equals(enumPlayerTurn.NextNext))
            playerTurn = players.get((players.indexOf(playerTurn) + numPlayers + (2*i)) % numPlayers);

    }

    /**
     * changing side of game
     */
    public void changeSide(){
       if(side.equals(enumSide.Clockwise))
           side = enumSide.AntiClockwise;
       else
           side = enumSide.Clockwise;
    }

    /**
     * the center of game assistance witch we say the player turn to put
     * its card and say the card to do its task and ets.
     */
    public void play() throws InterruptedException, IOException {
        //before every thing we have ot show the plate
        show();
        //saying the player how is its turn to chose its card
        Card card = playerTurn.chosenCard();

        //if he can put card then the card do its task
        //else we want him to draw a card and try again
        if (card != null) {
            card.doTask();
            return;
        }
        else {
            //draw a card from store
            playerTurn.addCard(drawACard());
            show();

            //chose a new card
            card = playerTurn.chosenCard();
            //if can put new card put it and card do its task else change turn
            if(card != null){
                card.doTask();
                return;
            }
            else {
                changePlayerTurn(enumPlayerTurn.Next);
                return;
            }
        }
    }

    /**
     * showing the plate of game can be in three states
     * for two player game three or four player game and
     * for more than four players
     */
    public void show() throws InterruptedException, IOException {
        //make a pause before showing new state of bord
        Thread.sleep(1000);
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        newLine(1);
        switch (numPlayers){
            case 2:  show_Two(); break;
            case 3:
            case 4:
                     show_ThreeOrFour(); break;
            default: show_FiveOrMore();break;
        }
    }

    /**
     * two player state for game
     * in this type of game two central card is
     * between two players
     */
    private void show_Two() {
        int i;
        //the reason of this for loop is that the cards show are tectonic
        for (i = 0; i < 3; i++) {
            space(9);
            players.get(1).showCards(playerTurn, i, Player.enumType.Horizontal);
            newLine(1);
        }

        //central card
        newLine(4);
        for (i = 0; i < 5; i++){
            space(18);
            showCenter(i);
            newLine(1);
        }
        newLine(4);

        for(i=0; i<3; i++){
            space(9);
            players.get(0).showCards(playerTurn, i, Player.enumType.Horizontal);
            newLine(1);
        }
    }

    /**
     * three player state for plate
     *              player 3
     *
     * player2      central card       player4
     *
     *              player1
     * this is very complicate to present how do i made it:)
     */
    private void show_ThreeOrFour() {
        int i, length, width;

        if (players.get(0).numCards() >= players.get(2).numCards())
            width = players.get(0).horizontalLength();
        else
            width = players.get(2).horizontalLength();

        if(numPlayers == 4) {
            if (players.get(1).numCards() >= players.get(3).numCards())
                length = players.get(1).verticalLength() + 6;
            else
                length = players.get(3).verticalLength() + 6;
        }
        else
            length = players.get(1).verticalLength() + 6;


        if (length < 21)
            length = 21;

        if(width < 32)
            width = 32;


        for (i = 0; i < length; i++) {
            if (i < 3) {
                space(9);
                players.get(2).showCards(playerTurn, i, Player.enumType.Horizontal);
                newLine(1);
            }

            if (i >= 3) {
                if ((i-3) < players.get(1).verticalLength())
                    players.get(1).showCards(playerTurn, i-3, Player.enumType.Vertical);
                else
                    space(7);


                if (i >= 7 && i < 12) {
                    space(width/2 - 7);
                    showCenter(i - 7);
                    space( width - (6 + width/2));
                }

                if (i >= 18 && i < 21) {
                    space(2);
                    players.get(0).showCards(playerTurn, i - 18, Player.enumType.Horizontal);
                    space(width - players.get(0).horizontalLength() + 2);
                }

                if(!(i >= 7 && i < 12)  && !(i >= 18 && i < 21))
                    space(width + 3);

                if(numPlayers == 4) {
                    if ((i-3) <  players.get(3).verticalLength())
                        players.get(3).showCards(playerTurn, i - 3, Player.enumType.Vertical);
                }
                newLine(1);
            }
        }

    }

    /**
     * this is for state of 5 or more players game
     * in this case we show the central card top off
     * cards of players
     */
    private void show_FiveOrMore(){

        newLine(2);
        for (int i = 0; i < 5; i++){
            space(23);
            showCenter(i);
            newLine(1);
        }
        newLine(2);

        for (int i=0; i<players.size(); i++) {
            for (int j=0; j<3; j++){
                players.get(i).showCards(playerTurn, j, Player.enumType.Horizontal);
                newLine(1);
            }
            newLine(1);
        }
    }

    /**
     * showing central card
     * @param line the line of card
     */
    public void showCenter(int line){
        String ANSI_RESET = "\u001B[0m";
        switch (line%5){
            case 0:
                space(7);
                if(side.equals(enumSide.Clockwise))
                    System.out.print('\u27F3');
                else
                    System.out.print('\u21BA');
                space(7);
            break;
            case 1: System.out.print('\u250c' + "-----" + '\u2510' + "  " + groundCard.getColor() + '\u250c' + "-----" + '\u2510' + ANSI_RESET); break;
            case 2: System.out.print("|STORE|" + "  " + groundCard.getColor() + "| " + groundCard.getName() + " |" + ANSI_RESET); break;
            case 3: System.out.print('\u2579' + "-----" + '\u2579' + "  " + groundCard.getColor() + '\u2579' + "-----" + '\u2579' + ANSI_RESET); break;
            case 4: space(4); System.out.print("Player" + (players.indexOf(playerTurn) + 1)); space(5); break;
        }
    }

    /**
     * @param n number of spaces we want to make
     */
    private void space(int n){
        for(int i=0; i<n; i++)
            System.out.print(" ");
    }

    /**
     * @param n number of new lines we want to make
     */
    private void newLine(int n){
        for(int i=0; i<n; i++)
            System.out.println();
    }

    /**
     * @return tru for finish time
     * this method check that does any player finished its card
     * or not
     */
    public boolean isFinish(){
        for (Player player: players){
            if(player.getNumCards() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * the score column for end of game
     */
    public void scoreList(){
        players.sort(this::compare);
        System.out.println("-----------Score column-----------");
        for(int i=0; i<numPlayers; i++) {
            System.out.println("| " +(i+1) + ")" + players.get(i).toString());
        }
        System.out.println("----------------------------------");
    }

    /**
     * override method to sort an array list
     * @param p1 first player
     * @param p2 second player
     * @return integer for its compare
     */
    @Override
    public int compare(Player p1, Player p2) {
        return p1.getScore().compareToIgnoreCase(p2.getScore());
    }
}

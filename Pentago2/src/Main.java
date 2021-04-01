import java.io.IOException;
import java.util.Scanner;

/**
 * this has the main method of project.in this class we show the appropriate
 * menu for game and make some conditions to be chose by players.
 * also we have just multi game.
 * @author Mohammad Javad Zandiyeh
 * @version 0.3
 */
public class Main{

    //making the plate we want to play on
    private static Plate plate = new Plate();
    //adding two players for playing on the plate we chose
    private static Player player1 = new Player("Black" , plate);
    private static Player player2 = new Player("White" , plate);
    //make an object from Scanner class to scan things
    private static Scanner scanner = new Scanner(System.in);
    //this field stores the trn of players and by the method changTurn changes
    private static String turn = "Black";

    public static void main(String[] args) throws IOException, InterruptedException{
        //first vision of game
        System.out.println("Welcome to Pentago!!!");
        System.out.println("\nWe have just multi player option");
        //refers to multi play method
        multiPlay();
    }

    /**
     * this class is for multi game
     */
    private static void multiPlay() throws IOException, InterruptedException {

        //this condition says us that the game will continue till finish condition
        while( ! isFinish()){
            plate.showPlate();
            System.out.print("Write place you want to put your Bead(" + turn + "):    ");


            //scanning the place player want to put its bead on
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            while( ! plate.isEmpty(x, y)){
                System.out.println("You can not put here,please try again.");
                x = scanner.nextInt();
                y = scanner.nextInt();
            }


            //scan the details base on turn of player
            if(turn.equals("Black")){
                player1.putBead(new Bead(player1.getColor(), x, y));
            }
            else{
                player2.putBead(new Bead(player2.getColor(), x, y));
            }

            plate.showPlate();
            //check the finish condition in each situation of putting bead on plate
            if(isFinish()){
                return;
            }


            //scanning the bloc that player wants to turn
            System.out.println("Which of the plates do you want to turn?\n(1|2)\n(3|4)\n");
            int number = scanner.nextInt();

            //the condition for turning clock wise or not if this bloc is not symmetry
            if( ! plate.isSymmetry(number)){
                //the bellow lines get the bloc and turn it to the required side
                System.out.println("which side do you want to turn your plate?(1 or 2)\n\n(1)Clock wise\n\n(2)Against clock wise\n");
                int side = scanner.nextInt();
                if (side == 1) {
                    if (turn.equals("Black")) {
                        player1.turnBloc(number, true);
                    } else {
                        player2.turnBloc(number, true);
                    }
                } else {
                    if (turn.equals("Black")) {
                        player1.turnBloc(number, false);
                    } else {
                        player2.turnBloc(number, false);
                    }
                }
            }
            else{
                System.out.println("This bloc is symmetry.");
                //wait for 0.8 second
                Thread.sleep(800);
            }

            //changes the turn of players
            changTurn();

        }
        //show the last vision of game after finishing
        plate.showPlate();

    }

    /**
     * this method changes the turn of players to do the next put
     */
    private static void changTurn(){
        if(turn.equals("Black")){
            turn = "White";
        }else{
            turn = "Black";
        }
    }

    /**
     * this method is for finding that the play has been finished or became same
     * or no one
     * @return boolean that shows the finish condition
     */
    private static boolean isFinish(){
        //if in a move to players became winner the game is same
        if(player1.isWinner() && player2.isWinner()){
            System.out.println("No one Won,Game Finished.");
            return true;
        }
        if(player1.isWinner()){
            System.out.println("Player1 (Black) Woooooooon!!!");
            return true;
        }
        if(player2.isWinner()){
            System.out.println("Player2 (White) Woooooooon!!!");
            return true;
        }
        //is plate became full and no one win it is the same game
        if(plate.isFull()){
            System.out.println("No one Won,Game Finished.");
            return true;
        }

        return false;
    }

}

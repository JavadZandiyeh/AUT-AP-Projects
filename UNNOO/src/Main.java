import java.io.IOException;
import java.util.Scanner;

/**
 * this is the main class for our game witch we control the
 * game in this class.in this class we just have main class
 * we need to control and show the first menu and make the
 * appropriate loop for game
 * @author Mohammad Javad Zandiyeh
 * @version 0.5
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {
        //scanner
        Scanner scanner = new Scanner(System.in);

        //first menu for chose the type of game
        System.out.println("1)Single game\n2)Multi game\n3)Exit");
        int type = scanner.nextInt();
        //this while loop is for handling the warning of not valid chose
        while (type >3) {
            System.out.println("Not valid chose,try again.");
            type = scanner.nextInt();
        }
        //exit chose
        if(type == 3)
            return;

        //asking the players number
        System.out.print("Write the number of players:    ");
        int numPlayers = scanner.nextInt();
        //this loop is for the number of players can play this game
        //and show a good warning
        while (numPlayers <2 || numPlayers > 15){
            System.out.println("Number of players must be more than 1 and less than 16 players,try again.");
            numPlayers = scanner.nextInt();
        }

        //ground or plate of game
        Ground ground;

        //making the game based on choice of player
        if(type == 1)
            ground = Ground.getInstance(numPlayers, 1);
        else{
            System.out.print("Number of Human players:    ");
            int numHuman = scanner.nextInt();
            //this loop is for handling not valid numbers for human players
            while (numHuman > numPlayers || numHuman < 0){
                System.out.println("Number of human players must be between 0 and number of players,try again.");
                numHuman = scanner.nextInt();
            }
            //ground we want to play on based on singleton pattern
            ground = Ground.getInstance(numPlayers, numHuman);
        }


        ground.show();
        //this is for first random ground card and do its commands
        if(! (ground.getGroundCard() instanceof NumericCard))
            ground.getGroundCard().doTask();

        //play will not finished till no one has 0 score
        while (! ground.isFinish())
            ground.play();

        //score column of game
        ground.scoreList();
    }

}

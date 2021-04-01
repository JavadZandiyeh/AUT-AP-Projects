import java.io.IOException;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

/**
 * main class that match the other classes and show the menu
 */
public class Main{

    //counter for finding that should change turn of player without putting new bead
    private static int counterPass = 0;
    //home(plate) we play on
    private static Home home = new Home();

    public static void main(String[] args) throws InterruptedException, IOException {
        menu();
    }

    /**
     * shows the good menu and call the methods for working on
     * single or multiple game
     */
    private static void menu() throws InterruptedException, IOException {

        Scanner scanner = new Scanner(System.in);
        Player player1 = new Player(home,"Black");
        Player player2 = new Player(home,"White");


        System.out.println("Welcome to eOthello!!!");
        System.out.println("1)Single Play\n2)Multiple play");
        if(scanner.nextInt() == 1){
            singlePlay(player1);
        }
        else{
            multiPlay(player1, player2);
        }

    }

    /**
     * this method execute a single play
     * @param player the player that want to play. other player will be computer
     */
    private static void singlePlay(Player player) throws InterruptedException, IOException {
        ComputerPlayer computerPlayer = new ComputerPlayer(home, "White");

        while( ! isFinish()){
            counterPass = 0;
            counterPass += multiPlayScans(player, computerPlayer);


            computerPlayer.setAllPositions();
            home.showPlate();

            int x, y;
            if( ! isPass()) {
                System.out.print("Computers turn,Wait.");


                computerPlayer.gainAllBeads(Integer.parseInt("" + computerPlayer.put().charAt(0)),
                                      Integer.parseInt("" + computerPlayer.put().charAt(2)));

                deleteRepeatedBeads(computerPlayer, player);
            }
            else{
                System.out.println("Pass.");
                counterPass++;
            }
            Thread.sleep(3000);

        }

        home.printWinner();
        System.out.println("\n! FINISHED ! ");
        return;
    }

    /**
     * execute the multiple game
     * @param player1 first player
     * @param player2 second player
     */
    private static void multiPlay(Player player1, Player player2) throws IOException, InterruptedException {
        while ( ! isFinish()) {
            counterPass = 0;
            counterPass += multiPlayScans(player1, player2);
            counterPass += multiPlayScans(player2, player1);
        }
        home.printWinner();
        System.out.println("\n! FINISHED ! ");
        return;
    }

    /**
     * this method scans the info of each player
     * @param me the player that is its turn
     * @param you the player that is not its turn
     * @return 0 when player can put bead and 1 when player could not put bead
     */
    private static int multiPlayScans(Player me, Player you) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        me.setAllPositions();
        home.showPlate();

        int x, y;
        if( ! isPass()){
            System.out.print(me.getColor() + ":    ");

            String s = scanner.nextLine();
            s = s.replaceAll("\\s", "");


            if(Character.isDigit(s.charAt(0))){
                x = Integer.parseInt("" + s.charAt(0));
                y = colon("" + s.charAt(1));
            }else{
                y = colon("" + s.charAt(0));
                x = Integer.parseInt("" + s.charAt(1));
            }


            while ( ! me.canPut(x - 1, y - 1)) {
                System.out.println("You can not put here,try again.");

                s = scanner.nextLine();
                s = s.replaceAll("\\s", "");


                if(Character.isDigit(s.charAt(0))){
                    System.out.println("dsgadg");
                    x = Integer.parseInt("" + s.charAt(0));
                    y = colon("" + s.charAt(1));
                }else{
                    x = colon("" + s.charAt(0));
                    y = Integer.parseInt("" + s.charAt(1));
                }
            }

            me.gainAllBeads(x - 1, y - 1);

            deleteRepeatedBeads(me, you);
            return 0;
        }
        else{
            System.out.println("Pass.");
            return 1;
        }

    }

    /**
     * @param ch the string we want to change it to integer
     * @return returns the integer of ch
     */
    public static int colon(String ch){

        ch = ch.toUpperCase();

        switch(ch){
            case("A"):{return 1;}
            case("B"):{return 2;}
            case("C"):{return 3;}
            case("D"):{return 4;}
            case("E"):{return 5;}
            case("F"):{return 6;}
            case("G"):{return 7;}
            case("H"):{return 8;}
        }
        return 0;
    }

    /**
     * deleting the same beads from other player(after gaining beads by a player
     * another player lose them)
     * @param me the player that is its turn
     * @param other another player that is not its turn
     */
    private static void deleteRepeatedBeads(Player me, Player other){
        for(Bead bead1: me.getBeads()) {
            Enumeration e = new Vector(other.getBeads()).elements();
            while (e.hasMoreElements()) {
                Bead bead2 = (Bead) e.nextElement();
                if (bead1.getY() == bead2.getY() && bead1.getX() == bead2.getX()) {
                    other.getBeads().remove(bead2);
                }
            }
        }
    }

    /**
     * @return boolean that will be true if no position is available
     *         and will be false if at list on potion is available for putting
     */
    private static boolean isPass(){
        int i = 0;
        for(int k=0; k<8; k++){
            for(int y=0; y<8; y++){
                if(home.getHomes(k, y) == '?'){
                    i++;
                }
            }
        }
        if(i == 0){ return true; }
        return false;
    }

    /**
     * check that is play finished or not
     * @return
     */
    private static boolean isFinish(){
        if(counterPass == 2){
            return true;
        }
        return false;
    }

}

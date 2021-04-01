import java.io.IOException;

/**
 * this class is for saving the condition of each element in plate.
 * in this class we can set the condition of each element to 3 situations.
 * one for black beads another situation for white beads and last is for
 * the possible positions for next move for white player or black player
 * also this class has show method for showing the plate of game.
 * @author Mohammad Javad Zandiyeh
 * @version 0.9
 */
public class Home{
    //situation of elements in plate
    private char[][] homes;
    private int player1Number = 0;
    private int player2Number = 0;

    /**
     * constructor of home class that make each element null
     */
    public Home(){
        homes = new char[8][8];
        for(int i=0 ; i<8; i++){
            for(int j=0; j<8; j++) {
                homes[i][j] = ' ';
            }
        }
    }

    /**
     * getter method that returns an element of homes
     * @param x the x of this place
     * @param y the y of this place
     * @return char that shows the condition of required element
     */
    public char getHomes(int x, int y){
        return homes[x][y];
    }

    /**
     * changes the state of the element we want
     * @param x the x of element we want to change it
     * @param y the y of element we want to change it
     * @param ch the new state of element
     */
    public void changHome(int x, int y, char ch){ homes[x][y] = ch; }

    /**
     * @param x the x of element
     * @param y the y of element
     * @return the element we want
     */
    public String getColor(int x, int y){
        if(homes[x][y] == '\u25A0'){
            return "Black";
        } else if(homes[x][y] == '\u25A1'){
            return "White";
        }else {
            return null;
        }
    }

    /**
     * sets the color of element
     * @param color the color we want to set on element
     * @param x the x of element
     * @param y the y of element
     */
    public void setColor(String color, int x, int y){
        if(color.equals("Black")){
            homes[x][y] = '\u25A0';
        }
        if(color.equals("White")){
            homes[x][y] = '\u25A1';
        }
    }

    /**
     * @param x the x of element we want to now it exists or not
     * @param y the y of element we want to now it exists or not
     * @return boolean that say us is that element exits in this plate or not
     */
    public boolean isExist(int x, int y){
        if(x>=0 && x<8 && y>=0 && y<8){
            return true;
        }
        return false;

    }

    /**
     * check that the element is exist or not and its color is opposite of
     * the color we want or not.
     * @param x the x of element
     * @param y the y of element
     * @param color the color we want to check the opposite of that
     * @return boolean for exist element(home) we want or not
     */
    public boolean checkHome(int x, int y, String color){
        if(isExist(x, y) && getColor(x,y) != null & getColor(x,y) != color){
            return true;
        }

        return false;

    }

    /**
     * this method show the plate of game with white and black and possible homes
     * for next movement
     * throws is a keyword in Java which is used in the signature of method to indicate
     * that this method might throw one of the listed type exceptions.
     * The caller to these methods has to handle the exception using a try-catch block.
     */
    public void showPlate() throws IOException, InterruptedException {

        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        System.out.print("\n  " + "   A  " + "   B  " + "   C  " + "   D  " + "   E  " + "   F  " + "   G  " + "   H  ");

        for(int i = 0; i < 9; i++) {

            System.out.print("\n  |");
            for (int j = 0; j < 8; j++) {
                System.out.print("-----|");
            }
            if (i != 8) {
                System.out.print("\n" + (i + 1) + " |");
                for (int j = 0; j < 8; j++) {
                    System.out.print("  " + homes[i][j] + "  |");
                }
            }

        }

        numberOfBeads();
    }

    /**
     * this method prints the number of beads of each player in console
     */
    private void numberOfBeads(){
        player1Number = 0;
        player2Number = 0;
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(homes[i][j] == '\u25A0'){ player1Number++; }
                if(homes[i][j] == '\u25A1'){ player2Number++; }
            }
        }

        System.out.println("\n Player1: " + player1Number + "    " + "Player2: " + player2Number + "\n");
    }

    /**
     * prints the winner name
     */
    public void printWinner(){
        if(player1Number > player2Number){
            System.out.println("Player1(Black) won!!!");
        }
        if(player2Number > player1Number){
            System.out.println("Player2(White) won!!!");
        }
        if(player1Number == player2Number){
            System.out.println("Same!!!");
        }
    }

}

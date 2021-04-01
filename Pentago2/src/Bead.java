/**
 * in this class we store the detail of each bead of game
 */
public class Bead{
    //each bead has color and the position in plate
    private String color;
    private int x, y;

    /**
     * this is the constructor of class
     * @param color color of bead
     * @param x the x position of bead
     * @param y the y position of bead
     */
    public Bead(String color, int x, int y){
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * @return the color of bead
     */
    public String getColor() {
        return color;
    }

    /**
     * @return y position of bead
     */
    public int getY() {
        return y;
    }

    /**
     * @return c potion of bead
     */
    public int getX() {
        return x;
    }

    /**
     * by its x and y position can find that this bead is on witch bloc
     * @return the number of bloc is on
     */
    public int getBlocNumber(){
        //we know that the first element of each bloc is where
        //and by this we can find that this bead is in witch bloc
        if(x >= 0 && x<6 && y>=0 && y<6) {
            if (x <= 2 && y <= 2) { return 1; }
            if (x <= 2 && y > 2)  { return 3; }
            if (x > 2 && y <= 2)  { return 2; }
            if (x > 2 && y > 2)   { return 4; }
        }
        return 0;
    }

}

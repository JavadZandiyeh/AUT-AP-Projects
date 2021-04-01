/**
 * this class make a player for this game.and player can do
 * lots of works to plate and blocks
 */
public class Player{

    //each player has a color and a plate that plays on
    private String color;
    private Plate plate;


    /**
     * constructor of class
     * @param color color of player
     * @param plate plate witch player plays on
     */
    public Player(String color, Plate plate){
        this.color = color;
        this.plate = plate;
    }

    /**
     * @return color of player
     */
    public String getColor() {
        return color;
    }

    /**
     * this method put bead on plate
     * @param bead bead witch we want to put on bead
     */
    public void putBead(Bead bead) {
        plate.addBead(bead.getBlocNumber(), bead);
    }

    /**
     * this method turns the blocs
     * @param numberOfBloc number of bloc we want to turn it
     * @param clockWise the side we want to turn the bloc
     */
    public void turnBloc(int numberOfBloc, boolean clockWise){
        plate.turn(numberOfBloc, clockWise);
    }

    /**
     * checks that in this session is this player winner of game or not
     * @return true for win
     */
    public boolean isWinner(){

        //by bellow for commands we check among the plate that is the color of plate in a line
        //is the same with the color of this player or not.mean that is this player has bead on
        //a line that makes five places or not

        for (int i=0; i<6; i++){
            if(hasBeadOn(i, 1) && hasBeadOn(i, 2) && hasBeadOn(i, 3) && hasBeadOn(i, 4)){
                if(hasBeadOn(i, 0) || hasBeadOn(i, 5)){
                    return true;
                }
            }

            if(hasBeadOn(1, i) && hasBeadOn(2, i) && hasBeadOn(3, i) && hasBeadOn(4, i)){
                if(hasBeadOn(0, i) || hasBeadOn(5, i)){
                    return true;
                }
            }
        }



        if(hasBeadOn(1, 0) && hasBeadOn(2, 1) && hasBeadOn(3, 2) && hasBeadOn(4, 3) && hasBeadOn(5, 4)){
            return true;
        }
        if(hasBeadOn(0, 1) && hasBeadOn(1, 2) && hasBeadOn(2, 3) && hasBeadOn(3, 4) && hasBeadOn(4, 5)){
            return true;
        }
        if(hasBeadOn(0, 4) && hasBeadOn(1, 3) && hasBeadOn(2, 2) && hasBeadOn(3, 1) && hasBeadOn(4, 0)){
            return true;
        }
        if(hasBeadOn(1, 5) && hasBeadOn(2, 4) && hasBeadOn(3, 3) && hasBeadOn(4, 2) && hasBeadOn(5, 1)){
            return true;
        }

        if(hasBeadOn(1, 1) && hasBeadOn(2, 2) && hasBeadOn(3, 3) && hasBeadOn(4, 4)){
            if(hasBeadOn(0, 0) || hasBeadOn(5, 5)){
                return true;
            }
        }


        if(hasBeadOn(1, 4) && hasBeadOn(2, 3) && hasBeadOn(3, 2) && hasBeadOn(4, 1)){
            if(hasBeadOn(0, 5) || hasBeadOn(5, 0)){
                return true;
            }
        }

        return false;
    }

    /**
     * this is for understanding that this player has any bead on the place we want or not
     * @param x the x of place
     * @param y the y of place
     * @return true for available bead on the place
     */
    private boolean hasBeadOn(int x, int y) {

        //among all values in hash map of plate it search to find the place we want
        for (int i = 1; i < 5; i++){
            for(Integer bloc: plate.getBlocs().keySet()) {
                for (Bead bead : plate.getBlocs().get(bloc)) {
                    if ((bead.getColor().equals(color)) && (bead.getX() == x) && (bead.getY() == y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

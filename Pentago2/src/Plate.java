import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class is for plate of game that has 4 blocs and each bloc has
 * some beads on.in this class we can show the plate of game and also
 * turn the bloc we want.
 */
public class Plate {

    //this is an hash map for storing the blocs number and the beads each bloc has
    private HashMap<Integer, ArrayList<Bead>> blocs;
    //the hole vision of plate
    private String[][] plateVision;

    /**
     * this is the constructor of plate class.
     * we make for blocs and make the first vision of plate
     * in this constructor
     */
    public Plate(){
        blocs = new HashMap<Integer, ArrayList<Bead>>();
        addBlock();
        plateVision = new String[6][6];
        basicVision();
    }

    /**
     * @return hashMap of blocs
     */
    public HashMap<Integer, ArrayList<Bead>> getBlocs() {
        return blocs;
    }

    /**
     * add the four bloc to plate
     */
    private void addBlock(){
        blocs.put(1, new ArrayList<Bead>());
        blocs.put(2, new ArrayList<Bead>());
        blocs.put(3, new ArrayList<Bead>());
        blocs.put(4, new ArrayList<Bead>());
    }

    /**
     * makes the basic vision to empty strings mean: ""
     */
    private void basicVision(){
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                plateVision[i][j] = "";
            }
        }
    }

    /**
     * this method is used before showing the plate for making the new vision
     * because of the putting beads on plate or turning blocs
     */
    private void loadVision(){
        //at first we empty all vision of elements and then we set them in to the new
        //vision of them
        basicVision();
        for(Integer bloc: blocs.keySet()) {
            for (Bead bead : blocs.get(bloc)) {
                plateVision[bead.getX()][bead.getY()] = bead.getColor();
            }
        }
    }

    /**
     * this method is for making the plates rows and colons and showing
     * the vision of each element on our console
     */
    public void showPlate() throws IOException, InterruptedException {
        loadVision();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        for(int i = 0; i < 7; i++){

            if(i == 3) { System.out.println("\n|-----|-----|-----|  |-----|-----|-----|"); }
            System.out.print("\n|-----|-----|-----|  |-----|-----|-----|");

            if (i != 6) {
                System.out.print("\n|");
                for (int j = 0; j < 6; j++) {

                    if(plateVision[j][i].equals("Black")) {
                        System.out.print("  " + '\u25A0' + "  |");
                    }else if(plateVision[j][i].equals("White")){
                        System.out.print("  " + '\u25A1' + "  |");
                    }else{
                        System.out.print("     |");
                    }

                    if(j == 2) {
                        System.out.print("  |");
                    }

                }
            }

        }

        System.out.println("\n");
    }

    /**
     * @param x the x of place we want to know its condition
     * @param y the y of place we want to know its condition
     * @return boolean that is true when the place is empty
     */
    public boolean isEmpty(int x, int y){

        //returns false for not valid places in plate
        if(x >= 6 || x < 0 || y >= 6 || y < 0){
            return false;
        }
        //if the vision of this place is "" it returns true
        if(plateVision[x][y].equals("")){
            return true;
        }

        return false;
    }

    /**
     * @return boolean that is true when the hole plate is full of beads
     */
    public boolean isFull(){
        //we count the number of empty places and if it is 0 we say that
        //the hole plate is full of beads
        int empty = 0;

        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if(plateVision[i][j].equals("")){
                    empty++;
                }
            }
        }

        if(empty == 0){ return true; }
        return false;
    }

    /**
     * this method put beads on the blocks we want
     * @param numberOfBlock number of block we want to put a new bead
     * @param bead the bead we want to put on bloc
     */
    public void addBead(int numberOfBlock, Bead bead){
        blocs.get(numberOfBlock).add(bead);
    }

    /**
     * by this method we turn the required bloc
     * @param numberOfBlock number of bloc we want to trn it
     * @param clockWise the side we want to turn this bloc
     */
    public void turn(int numberOfBlock, boolean clockWise){

        //by the bellow switch-case we define the place of first element of bloc
        int xBasicPlace = 0,yBasicPlace = 0;
        switch(numberOfBlock) {
            case 2:{xBasicPlace = 3; break;}
            case 3:{yBasicPlace = 3; break;}
            case 4:{xBasicPlace = 3; yBasicPlace = 3; break;}
        }

        //by making a new array list and put the new beads places we store the new arrange of beads
        ArrayList<Bead> beads1 = new ArrayList<Bead>();

        //store the new arrange of beads in a new array list
        if(clockWise){
            for(Bead bead: blocs.get(numberOfBlock)){
                if (bead.getX() - xBasicPlace == 0 && bead.getY() -yBasicPlace == 0) {
                    beads1.add(new Bead(bead.getColor(), 2+xBasicPlace, 0+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 1 && bead.getY() -yBasicPlace == 0) {
                    beads1.add(new Bead(bead.getColor(), 2+xBasicPlace, 1+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 2 && bead.getY() -yBasicPlace == 0) {
                    beads1.add(new Bead(bead.getColor(), 2+xBasicPlace, 2+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 2 && bead.getY() -yBasicPlace == 1) {
                    beads1.add(new Bead(bead.getColor(), 1+xBasicPlace, 2+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 2 && bead.getY() -yBasicPlace == 2) {
                    beads1.add(new Bead(bead.getColor(), 0+xBasicPlace, 2+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 1 && bead.getY() -yBasicPlace == 2) {
                    beads1.add(new Bead(bead.getColor(), 0+xBasicPlace, 1+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 0 && bead.getY() -yBasicPlace == 2) {
                    beads1.add(new Bead(bead.getColor(), 0+xBasicPlace, 0+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 0 && bead.getY() -yBasicPlace == 1) {
                    beads1.add(new Bead(bead.getColor(), 1+xBasicPlace, 0+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 1 && bead.getY() -yBasicPlace == 1) {
                    beads1.add(new Bead(bead.getColor(), 1+xBasicPlace, 1+yBasicPlace));
                }
            }
        }
        if(!clockWise){
            for(Bead bead: blocs.get(numberOfBlock)){
                if (bead.getX() - xBasicPlace == 0 && bead.getY() -yBasicPlace == 0) {
                    beads1.add(new Bead(bead.getColor(), 0+xBasicPlace, 2+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 1 && bead.getY() -yBasicPlace == 0) {
                    beads1.add(new Bead(bead.getColor(), 0+xBasicPlace, 1+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 2 && bead.getY() -yBasicPlace == 0) {
                    beads1.add(new Bead(bead.getColor(), 0+xBasicPlace, 0+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 2 && bead.getY() -yBasicPlace == 1) {
                    beads1.add(new Bead(bead.getColor(), 1+xBasicPlace, 0+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 2 && bead.getY() -yBasicPlace == 2) {
                    beads1.add(new Bead(bead.getColor(), 2+xBasicPlace, 0+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 1 && bead.getY() -yBasicPlace == 2) {
                    beads1.add(new Bead(bead.getColor(), 2+xBasicPlace, 1+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 0 && bead.getY() -yBasicPlace == 2) {
                    beads1.add(new Bead(bead.getColor(), 2+xBasicPlace, 2+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 0 && bead.getY() -yBasicPlace == 1) {
                    beads1.add(new Bead(bead.getColor(), 1+xBasicPlace, 2+yBasicPlace));
                }
                if (bead.getX() - xBasicPlace == 1 && bead.getY() -yBasicPlace == 1) {
                    beads1.add(new Bead(bead.getColor(), 1+xBasicPlace, 1+yBasicPlace));
                }
            }
        }

        //clear all beads in the bloc and pt new beads
        blocs.get(numberOfBlock).clear();
        for(Bead bead: beads1) {
            blocs.get(numberOfBlock).add(bead);
        }

        //load vision of game
        loadVision();
    }

    /**
     * by this method we understand that bloc need to turn in a specific side or no
     * @param numberOfBlock number of bloc we want to check its symmetry
     * @return true for symmetry blocs
     */
    public boolean isSymmetry(int numberOfBlock){
        //finding the first place of first element of bloc
        int x = 0,y = 0;
        switch(numberOfBlock) {
            case 2:{x = 3; break;}
            case 3:{y = 3; break;}
            case 4:{x = 3; y = 3; break;}
        }

        //by this if-else we specify the symmetry.we check that is four
        //places that make lozenge are the same or not
        if( (plateVision[x+0][y+0].equals(plateVision[x+2][y+2])) &&
                (plateVision[x+2][y+0].equals(plateVision[x+0][y+2])) &&
                (plateVision[x+2][y+0].equals(plateVision[x+0][y+0])) &&
                (plateVision[x+1][y+0].equals(plateVision[x+1][y+2])) &&
                (plateVision[x+2][y+1].equals(plateVision[x+0][y+1])) &&
                (plateVision[x+1][y+0].equals(plateVision[x+2][y+1]))
        ){
            return true;
        }
        return false;
    }

}


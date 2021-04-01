import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

/**
 * this class is for making the beads of game also by the objects
 * that we create from this class we can find the homes(elements)
 * that player can put its next bead
 */
public class Bead{

    //color of bead
    private String color;
    //position of bead in plate
    private int x,y;
    //map for storing  the position of each bead and the number of beads
    //that we can gain by putting this bead in plate
    private HashMap<String, Integer> positions;
    //home or plate of game
    private Home home;

    /**
     * constructor of this class
     * @param home home(plate) that we want to put bead on it
     * @param color the color of bead
     * @param x the x of bead
     * @param y the y of bead
     */
    public Bead(Home home, String color,int x, int y){
        positions = new HashMap<String, Integer>();
        this.home = home;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * @return map of positions
     */
    public HashMap<String, Integer> getPositions() {
        return positions;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * this method reload the position of bead after putting a new bead
     */
    public void setPositions(){

        int i = 1;
        while( home.checkHome(x+i, y, color) ){
            i++;
            if( (home.isExist(x+i, y)) && (home.getColor(x+i, y) == null) ){
                positions.put(((x+i) + "," + y), (i-1));
                home.changHome(x+i, y,'?');
                break;
            }
        }

        i = 1;
        while( home.checkHome(x, y+i, color) ){
            i++;
            if( (home.isExist(x, y+i)) && (home.getColor(x, y+i) == null) ){
                positions.put((x + "," + (y+i)), (i-1));
                home.changHome(x, y+i,'?');
                break;
            }
        }

        i = 1;
        while( home.checkHome(x-i, y, color) ){
            i++;
            if( (home.isExist(x-i, y)) && (home.getColor(x-i, y) == null) ){
                positions.put(((x-i) + "," + y), (i-1));
                home.changHome(x-i, y,'?');
                break;
            }
        }

        i = 1;
        while( home.checkHome(x, y-i, color) ){
            i++;
            if( (home.isExist(x, y-i)) && (home.getColor(x, y-i) == null) ){
                positions.put((x + "," + (y-i)), (i-1));
                home.changHome(x, y-i,'?');
                break;
            }
        }

        i = 1;
        while( home.checkHome(x+i, y+i, color) ){
            i++;
            if( (home.isExist(x+i, y+i)) && (home.getColor(x+i, y+i) == null) ){
                positions.put(((x+i) + "," + (y+i)), (i-1));
                home.changHome(x+i, y+i,'?');

                break;
            }
        }

        i = 1;
        while( home.checkHome(x-i, y+i, color)){
            i++;
            if( (home.isExist(x-i, y+i)) && (home.getColor(x-i, y+i) == null) ){
                positions.put(((x-i) + "," + (y+i)), (i-1));
                home.changHome(x-i, y+i,'?');
                break;
            }
        }

        i = 1;
        while( home.checkHome(x+i, y-i, color) ){
            i++;
            if( (home.isExist(x+i, y-i)) && (home.getColor(x+i, y-i) == null) ){
                positions.put(((x+i) + "," + (y-i)), (i-1));
                home.changHome(x+i, y-i,'?');
                break;
            }
        }

        i = 1;
        while( home.checkHome(x-i, y-i, color) ){
            i++;
            if( (home.isExist(x-i, y-i)) && (home.getColor(x-i, y-i) == null) ){
                positions.put(((x-i) + "," + (y-i)), (i-1));
                home.changHome(x-i, y-i,'?');
                break;
            }
        }

    }

    /**
     * removes the last positions of beads because after putting new bead
     * in plate all positions chang.
     */
    public void removeLastPositions(){
        Enumeration e = new Vector(positions.keySet()).elements();
        while (e.hasMoreElements()) {
            positions.remove((String) e.nextElement());
        }


        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                if(home.getHomes(i,j) == '?'){ home.changHome(i,j,' ');}
            }
        }

    }

}

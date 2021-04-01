import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;
import static java.lang.Math.abs;

/**
 * this class is for player.each player has numbers of beads and
 * name and play(put bead) in a plate.
 * in this class we load the positions of all beads
 * and add beads.after adding each bead we should reload
 * the positions of all beads.
 */
public class Player {

    //array list of beads
    protected ArrayList<Bead> beads;
    private String color;
    private Home home;

    /**
     * this constructor specify the plate we want to play on it
     * @param home the home(plate) we want to play on
     * @param color color or number of each player that use for
     *              finding the turn of players
     */
    public Player(Home home, String color) {
        this.home = home;
        beads = new ArrayList<Bead>();
        this.color = color;
        basicBeads();
    }

    public ArrayList<Bead> getBeads() {
        return beads;
    }

    public String getColor() {
        return color;
    }

    /**
     * first potions of players bead
     */
    private void basicBeads() {
        if (color.equals("Black")) {
            addBead(3, 4);
            addBead(4, 3);
        } else {
            addBead(4, 4);
            addBead(3, 3);
        }
    }

    /**
     * add the mentioned bead
     * @param x the x potion of element
     * @param y the y potion of element
     */
    private void addBead(int x, int y) {
        beads.add(new Bead(home, color, x, y));
        home.setColor(color, x, y);
    }

    /**
     * this method say that can we put bead in this place or not
     * @param x the x of place
     * @param y the y of place
     * @return boolean for potions that we can put our bead
     */
    public boolean canPut(int x, int y) {

        for (Bead bead : beads) {
            for (String position : bead.getPositions().keySet()) {
                if (Integer.parseInt("" + position.charAt(0)) == x && Integer.parseInt("" + position.charAt(2)) == y) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * by this method we gain all beads between to place
     * @param xPut x of first place
     * @param yPut y of first place
     * @param xLast x of last place
     * @param yLast y of last place
     */
    private void gainBeadsInALine(int xPut, int yPut, int xLast, int yLast) {

        int i = 1;
        if ((xPut == xLast) && (yPut != yLast)) {
            while (i < abs(yPut - yLast)) {
                if (yPut < yLast) {
                    addBead(xPut, yPut + i);
                } else {
                    addBead(xPut, yPut - i);
                }
                i++;
            }
        } else if ((xPut != xLast) && (yPut == yLast)) {
            i = 1;
            while (i < abs(xPut - xLast)) {
                if (xPut < xLast) {
                    addBead(xPut + i, yPut);
                } else {
                    addBead(xPut - i, yPut);
                }
                i++;
            }

        } else if ((xPut != xLast) && (yPut != yLast) && ((xLast + yLast == xPut + yPut) || (abs(xPut - yPut) == abs(xLast-yLast))) ){
            i = 1;
            while (i < abs(xPut - xLast)) {
                if ((xPut < xLast) && (yPut < yLast)) {
                    addBead(xPut + i, yPut + i);
                }
                if ((xPut < xLast) && (yPut > yLast)) {
                    addBead(xPut + i, yPut - i);
                }
                if ((xPut > xLast) && (yPut < yLast)) {
                    addBead(xPut - i, yPut + i);
                }
                if ((xPut > xLast) && (yPut > yLast)) {
                    addBead(xPut - i, yPut - i);
                }
                i++;
            }
        }

    }

    /**
     * gain all beads by putting a new bead in this place
     * @param x x of place
     * @param y y of place
     */
    public void gainAllBeads(int x, int y) {
        Enumeration e = new Vector(beads).elements();
        while (e.hasMoreElements()) {
            Bead bead = (Bead) e.nextElement();
            for (String position : bead.getPositions().keySet()) {
                if ((Integer.parseInt("" + position.charAt(0)) == x) &&
                        (Integer.parseInt("" + position.charAt(2)) == y)
                ) {
                    gainBeadsInALine(x, y, bead.getX(), bead.getY());
                }
            }
        }

        addBead(x, y);


        Enumeration e1 = new Vector(beads).elements();
        while (e1.hasMoreElements()) {
            Bead bead1 = (Bead) e1.nextElement();
            bead1.removeLastPositions();
        }

    }

    /**
     * load the places we can put our beads on them
     */
    public void setAllPositions() {
        Iterator<Bead> it = beads.iterator();
        while (it.hasNext()) {
            it.next().setPositions();
        }
    }

}

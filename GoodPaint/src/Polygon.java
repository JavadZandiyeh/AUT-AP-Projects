import java.util.ArrayList;
import java.util.Objects;

/**
 * this is a super class for rectangle and triangle classes
 * witch has an array list of sides for these shapes
 */
public abstract class Polygon extends Shape{

    //array list for storing sides of shapes
    protected ArrayList<Double> sides;

    /**
     * constructor of class
     * @param sides is vararg for Double objects that get sides of shapes
     */
    public Polygon(Double... sides){
        //beginning of each subclass we must call constructor of superclass
        super();
        this.sides = new ArrayList<Double>();
        for(Double side: sides){
            this.sides.add(side);
        }
    }

    /**
     * @return array list of sides
     */
    public ArrayList<Double> getSides(){
        return sides;
    }

    /**
     * this method overrides the method of shape class
     * @return String that is the details of sides of shapes
     */
    @Override
    public String toString(){

        String toString = new String();

        for(int i=1; i<= sides.size(); i++){
            toString += "side" + i + ":" + sides.get(i-1);
            if(i != sides.size()){
                toString += ",  ";
            }
        }

        return toString;
    }

    /**
     * this method overrides the shape and calculate the area
     * @return area of shape
     */
    @Override
    public double calculateArea(){

        double area = 0;

        for(Double side: sides){
            area += side;
        }
        return area;
    }

    /**
     * this is an override for object class
     * @param o the object we want ot check its equality with our polygon
     * @return true for same polygons
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Polygon)) return false;
        Polygon polygon = (Polygon) o;
        return Objects.equals(sides, polygon.sides);
    }

    /**
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(sides);
    }

}
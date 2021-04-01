import java.util.ArrayList;
import java.util.Objects;
import static java.lang.Math.*;

/**
 * this class is for triangle that has the sides of triangle
 * and by this we can calculate the perimeter and area of that
 */
public class Triangle {

    //array list for sides of shape
    private ArrayList<Double> sides;

    /**
     * constructor of class
     * @param s1 the first side
     * @param s2 the second side
     * @param s3 the third side
     */
    public Triangle(double s1, double s2, double s3){
        sides = new ArrayList<Double>();
        sides.add(s1);
        sides.add(s2);
        sides.add(s3);
    }

    /**
     * @return arryList of Doubles that are our sides
     */
    public ArrayList<Double> getSides(){
        return sides;
    }

    /**
     * calculates the perimeter of shape with heron formula
     * @return perimeter of shape
     */
    public double calculatePerimeter(){
        return sqrt( calculateArea()
                     * (calculateArea() - (2*sides.get(0)))
                     * (calculateArea() - (2*sides.get(1)))
                     * (calculateArea() - (2*sides.get(2)))
        ) / 4;
    }

    /**
     * @return area of shape
     */
    public double calculateArea(){
        return sides.get(0) + sides.get(1) + sides.get(2);
    }

    /**
     * draws the shape
     */
    public void draw(){
        //we draw the name and area and perimeter of shape
        System.out.println("Shape is a " + this.getClass().getSimpleName() +
                " the Area is " + calculateArea() +
                " and the Perimeter is " + calculatePerimeter());
    }

    /**
     * checks that is this shape is a equilateral or not
     * @return true for equilateral
     */
    public boolean isEquilateral(){
        //check the equality of sides
        if(sides.get(0).equals(sides.get(1)) && sides.get(0).equals(sides.get(2))){
            return true;
        }
        return false;
    }

    /**
     * overriding the equals method from Object class
     * @param o is the object we want to check its equality with our shapes
     * @return true for equal shapes
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(sides, triangle.sides);
    }

    /**
     * whenever we override the equals method we mst override the hashCode method too
     * @return an integer that is a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(sides);
    }

    /**
     * overriding the toString method from object class
     * @return an string witch contains name and sides of shape
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() +"{" + "sides=" + getSides() + '}';
    }

}

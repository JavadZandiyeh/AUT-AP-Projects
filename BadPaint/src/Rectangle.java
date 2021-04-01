import java.util.ArrayList;
import java.util.Objects;

/**
 * this class is for rectangle witch has sides and a method
 * for area calculating and a method for perimeter calculating
 */
public class Rectangle {

    //array list for sides of shape
    private ArrayList<Double> sides;

    /**
     * constructor of class
     * @param s1 the first side
     * @param s2 the second side
     * @param s3 the third side
     * @param s4 the fourth side
     */
    public Rectangle(double s1, double s2, double s3, double s4){
        sides = new ArrayList<Double>();
        sides.add(s1);
        sides.add(s2);
        sides.add(s3);
        sides.add(s4);
    }

    /**
     * @return arrayList of sides
     */
    public ArrayList<Double> getSides() {
        return sides;
    }

    /**
     * @return the perimeter of shape
     */
    public double calculatePerimeter(){
        return sides.get(0) * sides.get(1);
    }

    /**
     * @return area of shape
     */
    public double calculateArea(){
        return 2 * (sides.get(0) + sides.get(1));
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
     * by equal method in object class we check the equality shapes
     * @return true for squares
     */
    public boolean isSquare(){
        if(sides.get(0).equals(sides.get(1))){
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
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(sides, rectangle.sides);
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
        return this.getClass().getSimpleName() + "{" + "sides=" + getSides() + '}';
    }
}

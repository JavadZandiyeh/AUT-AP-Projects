import java.util.Objects;
import static java.lang.Math.*;

/**
 * this class is for circles that has the radius of circle
 * and by this we can calculate the perimeter and area of circles
 */
public class Circle{

    private double radius;

    /**
     * @param radius set the radius to this param
     */
    public Circle(double radius){
        this.radius = radius;
    }

    /**
     * @return radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * by this method we calculate the perimeter of radius
     * @return perimeter of circle
     */
    public double calculatePerimeter(){
        return pow(radius,2) * PI;
    }

    /**
     * @return area of circle
     */
    public double calculateArea(){
        return 2 * PI * radius;
    }

    /**
     * this method draws the circle
     */
    public void draw(){
        //printing the shape name and its area and its perimeter
        System.out.println("Shape is a " + this.getClass().getSimpleName() +
                " the Area is " + calculateArea() +
                " and the Perimeter is " + calculatePerimeter());
    }

    /**
     * overriding the equals method from Object class
     * @param o is the object we want to check its equality with our circle
     * @return true for equal circles
     */
    @Override
    public boolean equals(Object o) {
        //here at first we check that is these two objects refers to
        //a same object or not
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        //after finding that this object is instance of circle we
        //can cast it to circle and check the other fields
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    /**
     * whenever we override the equals method we mst override the hashCode method too
     * @return an integer that is a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    /**
     * overriding the toString method from object class
     * @return an string witch contains name and radius of our circle
     */
    @Override
    public String toString() {
        return (this.getClass().getSimpleName() + "{" + "radius=" + getRadius() + '}');
    }
}

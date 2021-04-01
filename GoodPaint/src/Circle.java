import java.util.Objects;
import static java.lang.Math.*;

/**
 * circle class extends shape class and concrete its methods
 */
public class Circle extends Shape{

    //the radius of circle
    private double radius;

    /**
     * constructor of class
     * @param radius is the radius of circle
     */
    public Circle(double radius){
        super();
        this.radius = radius;
    }

    /**
     * @return double witch is radius of circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * this method overrides the same method from its super class
     * @return the perimeter of circle
     */
    @Override
    public double calculatePerimeter(){
        return (pow(radius, 2) * PI);
    }

    /**
     * this method overrides the same method from its super class
     * @return the area of circle
     */
    @Override
    public double calculateArea(){
        return (2 * PI * radius);
    }

    /**
     * this is an override method from object class
     * @param obj the object we want to check its equality with this shape
     * @return tre for equality between our shapes
     */
    @Override
    public boolean equals(Object obj){
        if(this == obj){return true;}
        if(!(obj instanceof Circle)){return false;}
        Circle c = (Circle) obj;
        if(c.radius == radius){return true;}
        return false;
    }

    /**
     * every time we override the equals method we have to override this too
     * @return hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    /**
     * an override method from object class
     * @return string witch has details of circle
     */
    @Override
    public String toString() {
        return "Circle::  " + "radius=" + radius ;
    }

}

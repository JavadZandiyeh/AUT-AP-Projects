import static java.lang.Math.*;

/**
 * this class is for triangle details and extends polygon class
 */
public class Triangle extends Polygon{

    /**
     * constructor of class
     * @param sides the slides of shape
     */
    public Triangle(Double... sides){
        super(sides);
    }

    /**
     * this method say use that is this triangle equilateral or not
     * @return true for equilateral
     */
    public boolean isEquilateral(){
        if(sides.get(0).equals(sides.get(1)) &&
                sides.get(0).equals(sides.get(2))
        ){
            return true;
        }
        return false;
    }

    /**
     * overrides the method of polygon class
     * @return String witch has he details of this shape
     */
    @Override
    public String toString(){
        return this.getClass().getSimpleName() + "::  " + super.toString();
    }

    /**
     * is an override for shape class
     * @return perimeter of shape
     */
    @Override
    public double calculatePerimeter(){
        //calculate the perimeter of shape with heron rule
        return abs(calculateArea() *
                (calculateArea() - (2*sides.get(0))) *
                (calculateArea() - (2*sides.get(1))) *
                (calculateArea() - (2*sides.get(2)))
        ) / 8;
    }

}

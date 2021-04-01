/**
 * this is an subclass for polygon and has details of rectangle
 */
public class Rectangle extends Polygon{

    /**
     * constructor of class
     * @param sides are the sides of shape
     */
    public Rectangle(Double... sides){
        super(sides);
    }

    /**
     * in this method we check that is this rectangle square or not
     * @return true for square rectangles
     */
    public boolean isSquare(){
        if(sides.get(0).equals(sides.get(1)) &&
                sides.get(0).equals(sides.get(2)) &&
                sides.get(0).equals(sides.get(3))
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
        //putting the name of class before the sides of it
        return this.getClass().getSimpleName() + "::  " + super.toString();
    }

    /**
     * is an override for shape class
     * @return perimeter of shape
     */
    @Override
    public double calculatePerimeter(){
        return sides.get(0) * sides.get(1);
    }

}

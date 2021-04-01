/**
 * this class is a super class for Circle and Polygon that is
 * an abstract class. we just make abstract methods on this class
 * and concrete them in subclasses
 */
public abstract class Shape {

    /**
     * this is a method that has been override from Object class
     * @return an string witch contains the details of shapes
     */
    @Override
    public abstract String toString();

    /**
     * this method has been override from Object class too
     * @param obj the object we want to check its equality with this shape
     * @return boolean that will be true for equal shape with this shape
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * is an abstract method witch calculate the area
     * @return double witch is area
     */
    public abstract double calculateArea();

    /**
     * is an abstract method witch calculate the perimeter
     * @return double witch is perimeter
     */
    public abstract double calculatePerimeter();

    /**
     * draw shapes
     */
    public void draw(){
        //making an string witch will store witch shape is this shape
        String draw = new String();

        //we find by instansof command that is this shape circle or rectangle
        //or a triangle
        if(this instanceof Circle){
            Circle circle = (Circle)this;
            draw = circle.getClass().getSimpleName();
        }
        if(this instanceof Rectangle){
            Rectangle rectangle = (Rectangle)this;
            draw = rectangle.getClass().getSimpleName();
        }
        if(this instanceof Triangle){
            Triangle triangle = (Triangle)this;
            draw = triangle.getClass().getSimpleName();
        }

        System.out.println(draw + ":: Area is: " + calculateArea() +
                "  ,Perimeter is: " + calculatePerimeter());
    }

}

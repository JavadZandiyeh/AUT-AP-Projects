import java.util.ArrayList;

/**
 * this class is for paint that can add shapes on and
 * print and draw them also this class can search among
 * shapes to find witch of them are square or equilateral
 */
public class Paint{

    //an array list witch stores our shapes
    private ArrayList<Shape> shapes;

    /**
     * constructor of paint class that get a new space for
     * shapes array list
     */
    public Paint(){
        shapes = new ArrayList<Shape>();
    }

    /**
     * adding a new shape to our paint class
     * @param shape the shape we want to add to our class
     */
    public void addShape(Shape shape){
        shapes.add(shape);
    }

    /**
     * this method draws all shapes
     */
    public void drawAll(){
        System.out.println("Draw all shape:.....................................");
        for(Shape shape: shapes){
            //the draw method has been demodulation in shape class
            shape.draw();
        }
        System.out.println("....................................................");
    }

    /**
     * prints all shapes details
     */
    public void printAll(){
        System.out.println("Print all shape:.....................................");
        for(Shape shape: shapes){
            //the to string method has been override in each shapes
            System.out.println(shape.toString());
        }
        System.out.println("....................................................");
    }

    /**
     * this method seek to all shapes and prints their details
     * if they are square or equilateral
     */
    public void describeEqualSides(){
        System.out.println("Equality sides:.....................................");
        for(Shape shape: shapes) {
            //we have to now that this shape is a rectangle or
            //a triangle to find it can be square or equilateral
            if (shape instanceof Rectangle) {
                Rectangle rectangle = (Rectangle) shape;
                if(rectangle.isSquare()){
                    System.out.println(rectangle.toString());
                }
            }
            if (shape instanceof Triangle) {
                Triangle triangle = (Triangle) shape;
                if(triangle.isEquilateral()){
                    System.out.println(triangle.toString());
                }
            }
        }
        System.out.println("....................................................");
    }

}

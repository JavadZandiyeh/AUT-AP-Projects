import java.util.ArrayList;

/**
 * this is the pain class witch is for making and gathering the
 * circles and rectangles and triangles to a super class and has
 * print and draw methods to print and draw all shapes details
 */
public class Paint {

    //making 3 array lists to store the shapes
    private ArrayList<Circle> circles;
    private ArrayList<Triangle> triangles;
    private ArrayList<Rectangle> rectangles;

    /**
     * constructor of class that make an space for
     * our array lists
     */
    public Paint(){
        circles = new ArrayList<Circle>();
        triangles = new ArrayList<Triangle>();
        rectangles = new ArrayList<Rectangle>();
    }

    /**
     * add circles to array list of our class
     * @param circle the circle we want to add it to our class
     */
    public void addCircle(Circle circle){
        this.circles.add(circle);
    }

    /**
     * add triangles to array list of our class
     * @param triangle the triangle we want to add it to our class
     */
    public void addTriangle(Triangle triangle){
        this.triangles.add(triangle);
    }

    /**
     * the method for adding the rectangles to array list of our class
     * @param rectangle the rectangle we want to add it to our class
     */
    public void addRectangle(Rectangle rectangle){
        this.rectangles.add(rectangle);
    }

    /**
     * this method prints add details about all shapes of our class
     */
    public void printAll(){

        //we have three for-each to print all details for all shapes of our class

        for(Circle circle: circles){
            System.out.print(circle.toString() + " ");
        }
        System.out.println();

        for(Triangle triangle: triangles){
            System.out.print(triangle.toString() + " ");
        }
        System.out.println();

        for(Rectangle rectangle: rectangles){
            System.out.print(rectangle.toString() + " ");
        }
        System.out.println();
    }

    /**
     * by this class we draw  all shapes witch have been added to paint class
     */
    public void drawAll(){
        //three for-each statements to draw all shapes
        for(Circle circle: circles){
            circle.draw();
        }
        System.out.println();

        for(Triangle triangle: triangles){
            triangle.draw();
        }
        System.out.println();

        for(Rectangle rectangle: rectangles){
            rectangle.draw();
        }
        System.out.println();
    }

}

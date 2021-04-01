/**
 * this class is for making object from our paint class
 * and add some shapes to it and see how the methods of
 * paint class works
 * @version 0.0
 * @author Mohammad Javad Zandiyeh
 */
public class Main {


    public static void main(String[] args) {
        //making an object from paint class to add shapes on
        Paint paint = new Paint();

        //adding some shapes to paint
        paint.addShape(new Circle(19));
        paint.addShape(new Circle(3));
        paint.addShape(new Rectangle(1.0,4.0,1.0,4.0));
        paint.addShape(new Rectangle(8.0,5.0,8.0,5.0));
        paint.addShape(new Rectangle(6.0,6.0,6.0,6.0));
        paint.addShape(new Triangle(2.0,2.0,2.0));
        paint.addShape(new Triangle(4.0,4.0,6.0));

        //draw all shapes
        paint.drawAll();
        //print all shapes details
        paint.printAll();
        //print shapes that has equal sides
        paint.describeEqualSides();

    }

}

//add the Scanner library
import java.util.Scanner;

/**
 * it is the main class that communicate with other classes
 * @author Mohamad javad zandiyeh
 * @version 0.0
 */
public class Run {
    public static void main(String[] args) {

        //make a new object from Scanner library
        Scanner scanner = new Scanner(System.in);

        //get the day and capacity of class
        System.out.println("Write the day and the capacity of your lab:");
        String dayOfWeek = scanner.next();
        int capacity =  scanner.nextInt();

        //make an object from Lab class
        Lab l1 = new Lab(capacity,dayOfWeek);

        //make an array of students
        Student[] std = new Student[capacity];

        //get and set the students information
        for(int counter = 0; counter < capacity; counter++){

            std[counter] = new Student();

            System.out.print("first name of " + (counter+1) + "th student : \n");
            String f = scanner.next();
            std[counter].setFirstName(f);

            System.out.print("last name of " + (counter+1) + "th student : \n");
            String l = scanner.next();
            std[counter].setLastName(l);

            System.out.print("id of " + (counter+1)+ "th student : \n");
            String i = scanner.next();
            std[counter].setId(i);

            System.out.print("grade of " + (counter+1) + "th student : \n");
            int g = scanner.nextInt();
            std[counter].setGrade(g);

            std[counter] = new Student(std[counter].getFirstName(),std[counter].getLastName(),std[counter].getId(),std[counter].getGrade());
        }

        //set students in lab class
        l1.setStudents(std);

        //print the information of lab
        l1.print();

    }
}


import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * this class is for getting detail from user and making the correct output
 */
public class Main {

    //this two ArrayLists are use for storing the courses and the students
    // that user add to our collection
    private static LinkedHashSet<Course> courses = new LinkedHashSet<Course>();
    private static LinkedHashSet<Student> students = new LinkedHashSet<Student>();

    /**
     * find the course by its code number
     * @param code the code of class we want to find it
     * @return the match course with the input code of class but it will be null if no course match
     */
    private static Course searchCourse(String code){
        for(Course c: courses){
            if(c.getCode().equals(code)){
                return c;
            }
        }
        return null;
    }

    /**
     * find the student by its name
     * @param name name of student we want to find it
     * @return the student that matches to the input name
     */
    private static Student searchStudent(String name){
        for(Student s: students){
            if(s.getName().equals(name)){
                return s;
            }
        }
        return null;
    }

    /**
     * this class get the details of courses from user and add this courses to our list
     * @param scanner to scanner to get the details
     */
    private static void addCourseToSet(Scanner scanner){
        int numberOfCourses = scanner.nextInt();
        for(int counter = 0; counter < numberOfCourses; counter++){
            String nameOfCourse = scanner.next();
            String codeOfCourse = scanner.next();
            int unitOfCourse = scanner.nextInt();
            int capacityOfCourse = scanner.nextInt();

            courses.add(new Course(codeOfCourse, unitOfCourse, nameOfCourse, capacityOfCourse));
        }
    }

    /**
     * this class get the details of students from user and add the student to our list
     * @param scanner to scanner to get the details
     */
    private static void addStudentToSet(Scanner scanner){
        int numberOfStudents = scanner.nextInt();
        for(int counter1 = 0; counter1 <numberOfStudents; counter1++){
            String nameOfStudent = scanner.next();
            String idOfStudent = scanner.next();
            float averageOfStudent = scanner.nextFloat();
            int numberOfCourseOfStudent = scanner.nextInt();

            boolean checker = false;
            if((idOfStudent.length() == 7)||(averageOfStudent > 20 )||(averageOfStudent < 0)) {
                checker = true;
            }

            Student std = new Student(nameOfStudent, idOfStudent, averageOfStudent);

            if(checker == true){ students.add(std);}


            for (int counter2 = 0; counter2 < numberOfCourseOfStudent; counter2++) {
                String codeOfCourseToAdd = scanner.next();
                if(checker == true) {
                    std.addCourse(searchCourse(codeOfCourseToAdd));
                }
            }

        }
    }

    /**
     * this class get the details of students and the courses they want to remove from user and remove the courses from our list
     * @param scanner to scanner to get the details
     */
    private static void removeAndAddToSet(Scanner scanner){
        int stdToRemoveOrAdd = scanner.nextInt();
        for(int counter3 = 0; counter3 < stdToRemoveOrAdd; counter3++){
            String nameStd = scanner.next();
            int numCourse = scanner.nextInt();

            for (int counter4 = 0; counter4 < numCourse; counter4++){
                String codeOfCourse = scanner.next();
                if((searchCourse(codeOfCourse) != null)&&(searchStudent(nameStd) != null)){
                    if(searchStudent(nameStd).isInclude(searchCourse(codeOfCourse))){
                        searchStudent(nameStd).removeCourse(searchCourse(codeOfCourse));
                    }
                    else{
                        searchStudent(nameStd).addCourse(searchCourse(codeOfCourse));
                    }
                }
            }
        }
    }

    /**
     * print the output that show the details of courses and students
     */
    private static void show(){
        for(Course c: courses){
            c.showDetails();
        }
        for(Student s: students){
            s.showDetailOfCourses();
        }
    }

    //use the methods to get the input and make the output
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        addCourseToSet(scanner);
        addStudentToSet(scanner);
        removeAndAddToSet(scanner);
        show();
    }
}

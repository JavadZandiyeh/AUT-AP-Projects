/**
 * The Student class represents a student in a student administration system.
 * It holds the student details relevant in our context.
 *
 * @author Mohamad javad zandiyeh
 * @version 0.0
 */
public class Student {

    //student first name
    private String firstName;

    //student last name
    private String lastName;

    //student id
    private String id;

    //student grade
    private int grade;


    /**
     * Create a new student with a given name and ID number.
     *
     * @param fName first name of student
     * @param lName last name of student
     * @param sID id of student
     */
    public Student(String fName, String lName, String sID, int iGrade){
        firstName = fName;
        lastName = lName;
        id = sID;
        grade = iGrade;
    }

    /**
     *constructor without parameter
     */
    public Student(){}

    /**
     * @return firstName of student
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param fName set first name of a student
     */
    public void setFirstName(String fName) {
        firstName = fName;
    }


    /**
     * @return last name of student
     */
    public String getLastName(){ return lastName;}
    /**
     * @param lName set the last name of student
     */
    public void setLastName(String lName){lastName = lName;}


    /**
     * @param Grade set the id of student
     */
    public void setGrade(int Grade){
        grade = Grade;
    }
    /**
     * @return grade of student
     */
    public int getGrade(){
        return grade;
    }

    /**
     * @param id set the id of student
     */
    public void setId(String id){
        this.id = id;
    }
    /**
     * @return id of student
     */
    public String getId(){
        return id;
    }


    /**
     * print the information
     * Print the student’s last name and ID number to the output terminal.
     */
    public void print() {
        System.out.println("first name: " + firstName + "   last name: " + lastName + "  student ID: " + id + " grade: " + grade);
    }

}

/**
 * lab class enroll the students in lab in a specific day
 * @author Mohammad Javad Zandiyeh
 * @version 0.0
 */
public class Lab {

    private Student[] students ;
    private int avg = 0;
    private String day;
    private int capacity;
    private int currentSize = 0;

    /**
     * the constructor of Lab class
     * @param cap the capacity of lab class
     * @param d the day of lab class
     */
    public Lab(int cap, String d) {
        capacity = cap;
        day = d;
        students = new Student[capacity];
    }

    /**
     * register the students in lab class
     * @param std a student for lab class
     */
    public void enrollStudent(Student std) {
        if (currentSize < capacity) {
            students[currentSize] = std;
            currentSize++;
        }
        else {
            System.out.println("Lab is full!!!");
        }
    }

    /**
     * print the students information
     */
    public void print() {
        for(int i = 0; i < currentSize; i++) {
            students[i].print();
        }
        calculateAvg();
        System.out.println("Average of " + capacity + " students on " + day + " is: " + avg);
    }


    /**
     * @param students set the student in class
     */
    public void setStudents(Student[] students) {
        for(int i = 0; i < capacity; i++) {
            this.students[i] = students[i];
            enrollStudent(students[i]);
        }
    }
    /**
     * @return student information
     */
    public Student[] getStudents() {
        return students;
    }


    /**
     * it calculate the average grade of students
     */
    public void calculateAvg() {
        for(int i = 0; i < currentSize; i++){
            avg += students[i].getGrade();
        }
        avg /= currentSize;
    }
    /**
     * @return the average grade of students
     */
    public int getAvg() {
        return avg;
    }


    /**
     * @param day set the day of lab class
     */
    public void setDay(String day) {
        this.day = day;
    }
    /**
     * @return the day of class
     */
    public String getDay() {
        return day;
    }


    /**
     * @param capacity set the capacity of class
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    /**
     * @return capacity of class
     */
    public int getCapacity() {
        return capacity;
    }

}
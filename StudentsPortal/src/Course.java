import java.util.LinkedHashSet;

/**
 * this class has the details of courses like: code,name,unit and capacity
 * also it has list of the students that are in this class
 */
public class Course {

    //fields of class
    private String code;
    private int unit;
    private String name;
    private LinkedHashSet<Student> students;
    private int residueCapacity;

    /**
     * this the constructor of this class
     * @param code code of class
     * @param unit number of units this course has
     * @param name name of this course
     * @param capacity the maximum capacity of course
     */
    public Course(String code, int unit, String name, int capacity){
        this.code = code;
        this.unit = unit;
        this.name = name;
        this.residueCapacity = capacity;
        students = new LinkedHashSet<Student>();
    }

    //we have some getter
    public int getResidueCapacity() {
        return residueCapacity;
    }
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
    public int getUnit() {
        return unit;
    }
    public String getCollege(){
        return (code.charAt(0) + "" + code.charAt(1));
    }

    /**
     * this class add the students to its list.
     * Hint: the conditions of capacity and other conditions has been checked in Student class
     * Hint: the residue capacity is decrease after adding a student
     * @param student the student we want to add it to our course
     */
    public void addStudent(Student student){
            students.add(student);
            residueCapacity--;
    }

    /**
     * this class remove the students from its list.
     * Hint: the residue capacity is increase after removing a student
     * @param student the student we want to remove it from our course
     */
    public void removeStudent(Student student){
        students.remove(student);
        residueCapacity++;
    }

    /**
     * show the appropriate list of students of this course
     */
    public void showDetails(){
        System.out.print(name + ": ");
        for(Student s: students){
            System.out.print(s.getName() +" ");
        }
        System.out.println();
    }
}

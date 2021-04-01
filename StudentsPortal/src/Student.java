import java.util.LinkedHashSet;

/**
 * this class has details for student.Like: name,id,average,tokenUnits and ...
 * also his class has an list of courses this student has been added to it
 */
public class Student {
    private String name;
    private String id;
    private float average;
    private LinkedHashSet<Course> courses;
    private int tokenUnits;
    private String college;
    private int possibleUnits;

    /**
     * this is the constructor of student class that matches the infos
     * @param name name of student
     * @param id id of student
     * @param average average number of student
     */
    public Student(String name, String id, float average){
        this.name = name;
        this.id = id;
        this.average = average;
        college = (id.charAt(2) + "" + id.charAt(3));
        tokenUnits = 0;
        setPossibleUnits();
        courses = new LinkedHashSet<Course>();
    }

    //appropriate getter to return the name of student
    public String getName(){
        return name;
    }

    /**
     * this method identify the maximum units that this student can get base on its grade
     */
    private void setPossibleUnits(){
        if((average < 10) && (average >= 0)){
            possibleUnits = 0;
        }
        if((average < 15) && (average >= 10)){
            possibleUnits = 15;
        }
        if(average >= 15){
            possibleUnits = 20;
        }
    }

    /**
     * this is the method that let the student to join to the course.
     * this method check the conditions like that which college is the
     * course is and is this course has the capacity to jain to it o not
     * @param course the class that the student want to join on
     */
    public void addCourse(Course course){
        if(((course.getResidueCapacity() > 0)&&(college.equals(course.getCollege())))&&((tokenUnits + course.getUnit()) <= possibleUnits)) {
            courses.add(course);
            course.addStudent(this);
            tokenUnits += course.getUnit();
        }
    }

    /**
     * this method let the student to left from the course he or she want
     * @param course the course that the student want to left from
     */
    public void removeCourse(Course course){
        courses.remove(course);
        course.removeStudent(this);
        tokenUnits -= course.getUnit();
    }

    /**
     * this method shows all detail of the student
     */
    public void showAllDetail(){
        System.out.print(
                "Name:  " + name +
                "\nId:   " + id +
                "\nAverage:   " + average +
                "\nPossibleUnits:   " + possibleUnits +
                "\nToken Units:   " + tokenUnits +
                "\nCourses:   ");

        for (Course c: courses){
            System.out.print(c.getName() + " ");
        }
        System.out.println();
    }

    /**
     * this method prints the course(s) is this student join on it
     */
    public void showDetailOfCourses(){
        System.out.print(name + ": ");
        for (Course c: courses){
            System.out.print(c.getName() + " ");
        }
        System.out.println();
    }

    public boolean isInclude(Course c){
        if(courses.contains(c)){
            return true;
        }
        else {
            return false;
        }
    }
}

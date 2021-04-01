/**
 * this class has the infos of users of library
 * @version 0.0
 * @author Mohammad javad Zandiyeh
 */
public class User {

    //fields of User class
    /**
     * first name of user
     */
    private String firstName;
    /**
     * last name of user
     */
    private String lastName;
    /**
     * idName of library
     */
    private String idNum;

    /**
     * the constructor of User class
     * @param firstName set the first name of users
     * @param lastName  set the last name of users
     * @param idNum set the id of students that can not be more than 10 fields
     */
    public User(String firstName, String lastName, String idNum){
        this.firstName = firstName;
        this.lastName = lastName;
        do {
            this.idNum = idNum;
        }while (idNum.length() !=10);
    }

    /**
     * @return an string that is the first name of user
     */
    public String getFirstName(){
       return firstName;
    }

    /**
     * @return an string that is the last name of user
     */
    public String getLastName(){
       return lastName;
    }

    /**
     * @return id of user on string type
     */
    public String getIdNum(){
       return lastName;
    }

    /**
     * prints the identity of user
     */
    public void print(){
        System.out.println("Full Name: " + firstName + " " + lastName + " | " + "ID: " + idNum);
    }

    /**
     * ckeck the   of users
     * @param u is the user that we want check that is same with our user or not
     * @return  true, for equality between this user and the user that has been passed to this method
     */
    public boolean isSame(User u){
        if(this.idNum.equals(u.idNum)){
            return true;
        }
        else {
            return false;
        }
    }
}

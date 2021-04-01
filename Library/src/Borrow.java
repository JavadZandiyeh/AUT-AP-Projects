import java.util.Date;

/**
 * this class is for borrowing the books
 * and show the details for borrowed book and borrower
 * @version 0.0
 * @author Mohammad javad Zandiyeh
 */
public class Borrow {

    /**
     * the user that want to borrow the book
     */
    private User borrower;
    /**
     * the book that going to be borrowed
     */
    private Book book;
    /**
     * the date of system for know for issued Date of borrow
     */
    private Date issuedDate = new Date();
    /**
     * is the deadLine of borrow
     */
    private Date deadlineDate;

    /**
     * @return the book that been borrowed
     */
    public Book getBook(){
        return book;
    }
    /**
     * @return the borrower of book
     */
    public User getBorrower(){
        return borrower;
    }

    /**
     * the constructor of class
     * @param bookToBorrow set the book we want to borrow
     * @param borrower set the user that wants to borrow the book
     * @param deadline set the deadline of borrow
     */
    public Borrow(Book bookToBorrow, User borrower, Date deadline){
            book = bookToBorrow;
            this.borrower = borrower;
            deadlineDate = deadline;

    }

    /**
     * prints the info of borrow
     */
    public void print(){

        /**
         * dNow is the date of now
         */
        Date dNow = new Date();

        /**
         * diff is the difference between deadline and now
         */
        long diff = deadlineDate.getTime() - dNow.getTime();

        /**
         * hours between deadline and now
         */
        long diffHours = diff / (60 * 60 * 1000) % 24;
        /**
         * days between deadline and now
         */
        long diffDays = diff / (24 * 60 * 60 * 1000) % 30;
        /**
         * months between deadline and now
         */
        long diffMonth = diff / (24 * 60 * 60 * 1000 * 30) %12;


        //printing the details of borrow
        System.out.println( "Borrower => Full Name: " + borrower.getFirstName() + " " + borrower.getLastName() + " | " + "ID: " + borrower.getIdNum() + "\n" +
                            "Book => Title: " + book.getTitle() + " | " + "Author: " + book.getAuthor() +"\n" +
                            "IssuedDate => " + issuedDate + "\n" +
                            "Deadline => " + deadlineDate + "\n" +
                            "Remaining => " + (diffMonth + " months, " + diffDays + " days, " + diffHours + " hours.")
                            );
    }

    /**
     * say that is the book deadline passed or not
     * @return boolean to show is the deadline passed or not
     */
    public boolean isPassedDeadline(){

        if(deadlineDate.before(new Date())){
            return true;
        }
        else{
            return false;
        }
    }
}

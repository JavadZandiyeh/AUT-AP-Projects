import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * this class contains 3 ArrayLists of books, users and borrows
 * that stores the books and users of library and books that were borrowed by users
 * and 2 fields of name and address that are the name and address of library
 * @version 0.0
 * @author Mohammad javad Zandiyeh
 */
public class Library {

    //properties of Library class
    /**
     * make an ArrayList of books
     */
    private ArrayList<Book> books = new ArrayList<Book>();
    /**
     * make an ArrayList of users
     */
    private ArrayList<User> users = new ArrayList<User>();
    /**
     * make an ArrayList of borrows
     */
    private ArrayList<Borrow> borrows = new ArrayList<Borrow>();
    /**
     * the name fields of Library
     */
    private String name;
    /**
     * the address of Library
     */
    private String address;

    /**
     * this is the constructor of Library class
     * @param name  set the name of library
     * @param address  set the address of library
     */
    public Library(String name, String address){
        this.name = name;
        this.address = address;
    }

    /**
     * prints the name of address to console
     */
    public void print(){
        System.out.println("Name: " + name + " | " + "Address: " + address);
    }

    /**
     * add the users to this library
     * @param userToAdd an object of User class that want to add to library
     */
    public void addUser(User userToAdd){
        int pivot = 0;

        Iterator<User> itu = users.iterator();
        while (itu.hasNext()){
            if(itu.next().isSame(userToAdd)){
                System.out.println("This user has been available.");
                pivot++;
                break;
            }
        }
        if(pivot == 0) {
            users.add(userToAdd);

            //print hints
            System.out.print("User ");
            userToAdd.print();
            System.out.println(" added to users.");
        }
    }

    /**
     * removes the users from library
     * @param userToRemove user we want to remove it from library
     */
    public void removeUser(User userToRemove){
        users.remove(userToRemove);

        //print hints
        System.out.print("User ");
        userToRemove.print();
        System.out.println(" removed from users.");
    }

    /**
     * add the book to this library
     * @param bookToAdd the book we want to add it to library
     */
    public void addBook(Book bookToAdd){
        int pivot = 0;

        Iterator<Book> itb =books.iterator();
        while (itb.hasNext()){
            if(itb.next().isSame(bookToAdd)){
                System.out.println("This book has been available.");
                pivot++;
                break;
            }
        }
        if(pivot == 0) {
            books.add(bookToAdd);

            //print hints
            System.out.print("Book ");
            bookToAdd.print();
            System.out.println(" added to library.");
        }
    }

    /**
     * remove the book we want
     * @param bookToRemove the book is going to remove
     */
    public void removeBook(Book bookToRemove){
        books.remove(bookToRemove);

        //print hints
        System.out.print("Book ");
        bookToRemove.print();
        System.out.println(" removed from library.");

    }

    /**
     * by this method we can save the borrows in an ArrayList
     * and remove the book from library and user from users
     * @param bookToBorrow the book we want to borrow it it
     * @param borrower the user that want borrow the book
     * @param deadline  is the deadline of borrow
     */
    public void borrowBook(Book bookToBorrow, User borrower, Date deadline){

        Date dNow = new Date();

        if(dNow.after(deadline)){
            System.out.println("Not valid date.Pleas try again.");
        }
        else {
            Borrow borrow = new Borrow(bookToBorrow, borrower, deadline);

            borrows.add(borrow);
            books.remove(bookToBorrow);
            users.remove(borrower);

            //print hints
            System.out.print("User ");
            borrower.print();
            System.out.println(" removed from users till deliver the book.");
            System.out.print("Book ");
            bookToBorrow.print();
            System.out.println(" removed from library.");
        }
    }

    /**
     * add the book that has been delivered and the user that delivered the book
     * by making appropriate messages
     * @param borrow is an object from Borrow class that has info from book
     *               user,deadline and issued date
     */
    public void giveBackBook(Borrow borrow){
        borrows.remove(borrow);
        books.add(borrow.getBook());
        users.add(borrow.getBorrower());

        //print hints
        System.out.print("User ");
        borrow.getBorrower().print();
        System.out.println(" added to users again.");
        System.out.print("Book ");
        borrow.getBook().print();
        System.out.println(" gave back to library.");
    }

    /**
     * prints the info about books that the deadline of them has been passed
     * and some other infos
     */
    public void printPassedDeadlineBorrows(){
        Iterator<Borrow> it = borrows.iterator();

        while (it.hasNext()){
            if(it.next().isPassedDeadline()){
                it.next().print();
            }
            else {
                System.out.println("No passed deadline book.");
            }
        }
    }
}

import java.util.Calendar;
import java.util.Date;

/**
 * this is a testing class to test other classes jabs
 * for example we added two 2 libraries and 5 users and 5 books for each library
 * to test how does the other classes works
 * @version 0.0
 * @author Mohammad javad Zandiyeh
 */
public class TestLibrarySystem {

    public static void main(String[] args) {

        //Making system for managing libraries
        LibrarySystem system = new LibrarySystem();

        //Making 10 libraries
        Library lib1 = new Library("Brooklyn Public Library", "Brooklyn, NY, USA");
        Library lib2 = new Library("National Library of China", "Beijing, China");

        //Making 10 users
        User user1 = new User("Ali", "Alavi", "0382078122");
        User user2 = new User("Javad", "Mohammady", "4935870358");
        User user3 = new User("Reza", "Mostafavy", "4518485151");
        User user4 = new User("Bagher", "Hasany", "7846852856");
        User user5 = new User("Hasan", "Farvardin", "7816522641");
        User user6 = new User("Maria", "Roshany", "7856131651");
        User user7 = new User("Kamran", "Karamy", "5157352654");
        User user8 = new User("Farzaneh", "Karimy", "7945365673");
        User user9 = new User("Javad", "Bahman", "5761562346");
        User user10 = new User("Saleh", "Hamedanian", "3541589226");

        //Making 10 books
        Book book1 = new Book("The History of Tom Jones, a Foundling", "Henry Fielding");
        Book book2 = new Book("Pride and Prejudice", "Austen");
        Book book3 = new Book("Le Père Goriot", "Honoré de Balzac");
        Book book4 = new Book("The Red and the Black", "Stendhal");
        Book book5 = new Book("David Copperfield", "Charles Dickens");
        Book book6 = new Book("Madame Bovary", "Gustave Flaubert");
        Book book7 = new Book("Moby-Dick", "Herman Melville");
        Book book8 = new Book("Wuthering Heights", "Emily Brontë");
        Book book9 = new Book("The Brothers Karamazov", "Dostoevsky");
        Book book10 = new Book("War and Peace", "Tolstoy");


        //Adding 2 libs to system
        system.addLibrary(lib1);
        system.addLibrary(lib2);


        //Adding 5 users to lib1
        lib1.addUser(user1);
        lib1.addUser(user2);
        lib1.addUser(user3);
        lib1.addUser(user4);
        lib1.addUser(user5);

        //Adding 5 users to lib2
        lib2.addUser(user6);
        lib2.addUser(user7);
        lib2.addUser(user8);
        lib2.addUser(user9);
        lib2.addUser(user10);

        //Adding 5 books to lib1
        lib1.addBook(book1);
        lib1.addBook(book2);
        lib1.addBook(book3);
        lib1.addBook(book4);
        lib1.addBook(book5);

        //Adding 5 books to lib2
        lib2.addBook(book6);
        lib2.addBook(book7);
        lib2.addBook(book8);
        lib2.addBook(book9);
        lib2.addBook(book10);

        //making a bug between added books and users and libraries
        System.out.print("\n\n\n\n\n");

        //examining the methods of library
        Date d1 = new Date(119, Calendar.JANUARY, 23, 3, 47, 32);
        Date d2 = new Date(121, Calendar.JANUARY, 23, 3, 47, 32);

        system.printAllLibraries();     System.out.println();
        lib1.print();   System.out.println();
        user7.print();  System.out.println();
        book3.print();  System.out.println();
        lib1.removeUser(user4); System.out.println();
        lib2.removeBook(book7); System.out.println();
        lib1.borrowBook(book2, user3, d1); System.out.println();
        lib1.borrowBook(book2, user3, d2); System.out.println();
        lib1.printPassedDeadlineBorrows();  System.out.println();
        system.removeLibrary(lib2); System.out.println();
        lib1.giveBackBook(new Borrow(book2, user3, d1));
    }
}

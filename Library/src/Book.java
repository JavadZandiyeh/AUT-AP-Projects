/**
 * this class has the infos of books of library
 * @version 0.0
 * @author Mohammad javad Zandiyeh
 */
public class Book {

    /**
     * title of book
     */
    private String title;
    /**
     * author of book
     */
    private String author;

    /**
     * @return  an string that contains the title of book
     */
    public String getTitle(){
        return title;
    }
    /**
     * @return an string that contains the author of book
     */
    public String getAuthor(){
        return author;
    }

    /**
     * the constructor of Book class that make the title and author
     * @param title set the title of book
     * @param author set the author of book
     */
    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    /**
     * prints the infos of book
     */
    public void print(){
        System.out.println("Title: " +  title + " | " + "Author: " + author);
    }

    /**
     * this method check that is this book is same by book (b) or no
     * @param b is the book that want to check is same with our book or not
     * @return  true for same books and false for not same book
     */
    public boolean isSame(Book b){
        if((this.author.equals(b.author))&&(this.title.equals(b.title))){
            return true;
        }
        else{
            return false;
        }
    }
}

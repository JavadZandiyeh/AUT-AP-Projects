import java.util.ArrayList;
import java.util.Iterator;

/**
 * this class make a system that contains libraries
 * @version 0.0
 * @author Mohammad javad Zandiyeh
 */
public class LibrarySystem {

    /**
     * this is ArrayList of libraries for handling the the libraries
     * that are adding or removing to the library system
     */
    private ArrayList<Library> libraries = new ArrayList<Library>();

    /**
     * this method add the libraries to system and show the appropriate messages
     * @param libraryToAdd is an object of Library class that is adding to the system
     */
    public void addLibrary(Library libraryToAdd) {
        libraries.add(libraryToAdd);

        //print hints
        System.out.print("Library ");
        libraryToAdd.print();
        System.out.println(" added to libraries.");
    }

    /**
     * this method remove the libraries to system and show the appropriate messages
     * @param libraryToRemove  is an object of Library class that is removing to the system
     */
    public void removeLibrary(Library libraryToRemove) {
        libraries.remove(libraryToRemove);

        //print hints
        System.out.print("Library ");
        libraryToRemove.print();
        System.out.println(" removed from libraries.");
    }

    /**
     * this method prints all library information , means name and address of them
     */
    public void printAllLibraries() {
        Iterator<Library> it = libraries.iterator();
        while (it.hasNext()) {
            it.next().print();
        }
    }

}

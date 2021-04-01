import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class to hold details of audio files.
 *
 * @author David J. Barnes and Michael K�lling
 * @version 2011.07.31
 */
public class MusicCollection {

    //this are two ArrayLists to store all musics and love musics
    private ArrayList<MusicPlayer> allMusic;
    private ArrayList<MusicPlayer> loveMusic;

    //the getters we need
    public ArrayList<MusicPlayer> getAllMusic(){
        return allMusic;
    }
    public ArrayList<MusicPlayer> getLoveMusic(){
        return loveMusic;
    }

    /**
     * Create a MusicCollection
     */
    public MusicCollection() {
        allMusic = new ArrayList<MusicPlayer>();
        loveMusic = new ArrayList<MusicPlayer>();
    }

    /**
     * Add a file to the collection.
     * @param filename The file to be added.
     */
    public void addMusic(String filename, ArrayList<MusicPlayer> m) {
        MusicPlayer musicPlayer = new MusicPlayer(filename);
        m.add(musicPlayer);
    }

    /**
     * Return the number of files in the collection.
     * @return The number of files in the collection.
     */
    public int getNumberOfMusic(ArrayList<MusicPlayer> m) {
        return m.size();
    }

    /**
     * List a file from the collection.
     * @param index The index of the file to be listed.
     */
    public void listMusic(int index, ArrayList<MusicPlayer> m) {
        int i=0;
        Iterator<MusicPlayer> it = m.iterator();
        while (it.hasNext()){
            if(index == i) {
                it.next().print();
                break;
            }
            else {
                it.next();
                i++;
            }
        }
    }

    /**
     * Show a list of all the files in the collection.
     */
    public void listAllMusic(ArrayList<MusicPlayer> m){

        Iterator<MusicPlayer> it = m.iterator();
        while (it.hasNext()){
                it.next().print();
        }
    }

    /**
     * Remove a file from the collection.
     * @param index The index of the file to be removed.
     */
    public void removeMusic(int index, ArrayList<MusicPlayer> m) {
        if(validIndex(index,m)) {
            int i = 0;
            Iterator<MusicPlayer> it = m.iterator();
            while (it.hasNext()) {
                if (index == i) {
                    it.remove();
                } else {
                    it.next();
                }
            }
        }
    }

    /**
     * Start playing a file in the collection.
     * Use stopPlaying() to stop it playing.
     * @param index The index of the file to be played.
     */
    public void startPlaying(int index, ArrayList<MusicPlayer> m) {
        if(validIndex(index,m)) {
            int i = 0;
            Iterator<MusicPlayer> it = m.iterator();
            while (it.hasNext()) {
                if (index == i) {
                    it.next().startPlaying(it.next().getAddress());
                } else {
                    it.next();
                    i++;
                }
            }
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying(int index, ArrayList<MusicPlayer> m) {
        if(validIndex(index,m)){
            int i=0;
            Iterator<MusicPlayer> it = m.iterator();
            while (it.hasNext()){
                if(index == i){
                    it.next().stop(it.next().getAddress());
                }
                else {
                    it.next();
                    i++;
                }
            }
        }
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @param m the ArrayList to be checked
     * @return true if the index is valid, false otherwise.
     */
    private boolean validIndex(int index, ArrayList<MusicPlayer> m) {
        if((index >= 0)&&(m.size() >= index)){
            return true;
        }
        else {
            return false;
        }

        // The return value.
        // Set according to whether the index is valid or not.
    }

    /**
     * this method is for searching an filename by all of the musics to find availibility
     * and print the  music detatils
     * @param filename the name of file we want to find it
     * @param musicPlayers  the arrayList we want to search among it
     */
    public void search(String filename, ArrayList<MusicPlayer> musicPlayers){
        for(MusicPlayer m: musicPlayers){
            if(m.getAddress().equals(filename)) {
                m.print();
            }
        }
    }
}
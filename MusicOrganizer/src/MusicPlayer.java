/**
 * Provide basic playing of MP3 files via the javazoom library.
 * See http://www.javazoom.net/
 *
 * @author David J. Barnes and Michael K�lling.
 * @version 2011.07.31
 */
public class MusicPlayer {

    // The current player. It might be null.
    private boolean isPlaying;
    private String address;
    private String yearOfPublish;
    private String singer;


    /**
     * Constructor for objects of class MusicFilePlayer
     */
    public MusicPlayer(String address) {
        this.address = address;
        yearOfPublish = "dose not specified";
        singer = "un non singer";
        isPlaying = false;
    }

    /**
     * the getter method for address
     * @return an string that contains the address
     */
    public String getAddress(){return address;}

    /**
     * prints the detail about the music
     */
    public void print(){
        System.out.println("singer: " + singer + "\nyear of publish: " + yearOfPublish + "\naddress: " + address);
    }

    /**
     * Start playing the given audio file.
     * The method returns once the playing has been started.
     * @param filename The file to be played.
     */
    public void startPlaying(String filename) {
        System.out.println(filename + " is playing...");
        isPlaying = true;

    }

    /**
     * for showing the message for stopping the music
     * @param filename name off music want to stop working
     */
    public void stop(String filename) {

        System.out.println( filename + "is stopped!");
        isPlaying = false;
    }

    /**
     * check the equality between two musics
     * @param musicPlayer is the music we want to compare it with our music
     * @return true for same musics
     */
    public boolean equals(MusicPlayer musicPlayer){
        if(this.address.equals(musicPlayer.address) && this.singer.equals(musicPlayer.singer)){
            return true;
        }
        return false;
    }
}
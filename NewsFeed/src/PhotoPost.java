public class PhotoPost extends Post{
    private String filename;
    private String caption;

    public PhotoPost(String username, String filename,String caption){

        super(username);
        this.caption = caption;
        this.filename = filename;

    }

    public void display(){
        System.out.println(super.toString());
        System.out.println("[" + filename + "]" + " " + caption);
        System.out.println("...........................");
    }
}

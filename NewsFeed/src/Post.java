import java.util.ArrayList;

public class Post {
    private String username;
    private long timestamp;
    private int likes;
    private ArrayList<String> comment;

    public Post(String username){
        timestamp = System.currentTimeMillis();
        this.username = username;
        likes = 0;
        comment = new ArrayList<String>();
    }

    public void addComment(String cmt){
        comment.add(cmt);
    }

    public void Like(){
        likes++;
    }

    public String toString(){
        String text = (username + "\n" + timestamp);

        if(likes > 0){
            text += (" - " + likes + "people likes this.\n");
        }
        else{
            text += ("\n");
        }


        if (comment.isEmpty()){
            return (text + "No comment yet.\n");
        }
        else{
            return (text + " " + comment.size() + "comment(s) click here to see.\n");
        }
    }

}

import java.util.ArrayList;

public class NewsFeed {
    private ArrayList<Post> posts;

    public NewsFeed(){
        posts = new ArrayList<Post>();
    }

    public void addPost(Post p){
        posts.add(p);
    }

    public void showPosts(){
        for(Post p: posts){
            if(p instanceof PhotoPost){
                ((PhotoPost) p).display();
            }
            if(p instanceof MessagePost){
                ((MessagePost) p).display();
            }
        }
    }

}

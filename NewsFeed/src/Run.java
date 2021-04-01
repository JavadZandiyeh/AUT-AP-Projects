public class Run {
    public static void main(String[] args){
        NewsFeed news = new NewsFeed();

        Post m1 = new MessagePost("Ali Reza","Hello Javad.How arre you?");
        m1.addComment("gooooddddddddd");
        m1.addComment("worstttttttttt");
        m1.Like();
        news.addPost(m1);

        Post m2 = new MessagePost("Javad", "Thanks.How about you?");
        news.addPost(m2);

        Post p1 = new PhotoPost("Ali Reza", "c://sticker1", "amazing");
        news.addPost(p1);

        Post p2 = new PhotoPost("Javad", "c://pict/sticker", "question");
        news.addPost(p2);

        news.showPosts();

    }
}

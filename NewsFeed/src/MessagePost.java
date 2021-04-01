public class MessagePost extends Post{
    private String message;

    public MessagePost(String username, String message){
        super(username);
        this.message = message;
    }

    public String getText(){
        return message;
    }
    public void display(){
        System.out.println(super.toString());
        System.out.println(message);
        System.out.println("...........................");
    }

}

public class Subscriber implements Observer {
    private String name;
    private Channel channel;

    public Subscriber(String name){
        channel = new Channel();
        this.name = name;
    }

    @Override
    public void update(){
        System.out.println("Hey " + name + ", Video Uploaded: " + channel.title);
    }

    @Override
    public void subscribeChannel(Channel channel){
        this.channel = channel;
    }
}

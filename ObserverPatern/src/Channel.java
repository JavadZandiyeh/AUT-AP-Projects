import java.util.ArrayList;

public class Channel implements Subject {
    private ArrayList<Subscriber> subscribers;
    public String title;

    public Channel(){
        subscribers = new ArrayList<Subscriber>();
    }

    @Override
    public void subscribers(Subscriber subscriber){
        subscribers.add(subscriber);
    }

    @Override
    public void unSubscribers(Subscriber subscriber){
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(){
        for (Subscriber subscriber: subscribers){
            subscriber.update();
        }
    }

    @Override
    public void upload(String title){
        this.title = title;
        notifySubscribers();
    }
}

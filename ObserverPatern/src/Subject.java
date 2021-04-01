public interface Subject {
    void subscribers(Subscriber subscriber);

    void unSubscribers(Subscriber subscriber);

    void notifySubscribers();

    void upload(String title);
}

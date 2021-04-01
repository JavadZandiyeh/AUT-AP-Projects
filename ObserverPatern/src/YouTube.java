public class YouTube {

    public static void main(String[] args) {
        Channel ch = new Channel();

        Subscriber s1 = new Subscriber("Ali");
        Subscriber s2 = new Subscriber("Reza");
        Subscriber s3 = new Subscriber("Abbas");


        s1.subscribeChannel(ch);
        s2.subscribeChannel(ch);
        s3.subscribeChannel(ch);

        ch.subscribers(s1);
        ch.subscribers(s2);
        ch.subscribers(s3);

        ch.upload("How to use observer pattern.");
        ch.unSubscribers(s2);
        ch.upload("How to use GUI in java.");

    }

}

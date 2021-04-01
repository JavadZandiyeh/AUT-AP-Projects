package Javad;

public class StaticPractice {
    public static void main(String[] args) {
        Peykan p = new Peykan();
        System.out.println(p.getPrice());
        Peykan.setLength(190);
        System.out.println(Peykan.getLength());
        System.out.println(p.getLength());
    }
}

class Peykan{
    private static int length;
    private int price;

    static {
        length = 400;
    }
    public Peykan(){
        price = 12;
    }
    public static int getLength() {
        return length;
    }

    public int getPrice() {
        return price;
    }

    public static void setLength(int length) {
        Peykan.length = length;
    }
}

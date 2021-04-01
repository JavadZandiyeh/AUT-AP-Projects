public class Main {
    public static void main(String[] str){
        hashset h = new hashset();

        h.addToSet("c");
        h.addToSet("b");
        h.addToSet("a");
        h.addToSet("d");
        h.addToSet("d");
        h.addToSet("c");
        h.removeFromSet("d");
        h.print();
        h.getInput();




        hashmap h1 =new hashmap();
        h1.add("fb" , "289");
        h1.add("b" ,"142");
        h1.add("gh","348");
        h1.print("b");
    }
}

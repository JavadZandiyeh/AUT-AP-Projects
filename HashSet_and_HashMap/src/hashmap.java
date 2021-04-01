import java.util.HashMap;

public class hashmap {
    HashMap<String, String> phoneBook = new HashMap<String, String>();

    public void add(String a ,String b){
        phoneBook.put(a,b);
    }
    public void print(String a){
        System.out.println(phoneBook.get(a));
    }

}
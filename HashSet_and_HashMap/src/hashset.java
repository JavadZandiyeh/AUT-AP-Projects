import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class hashset {
    HashSet<String> set1 = new HashSet<String>();
    public void addToSet(String str){
        set1.add(str);
    }
    public void removeFromSet(String str){
        set1.remove(str);
    }
    public void print(){
        Iterator<String> it = set1.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
    public void getInput(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        String[] wordArr = input.split(" ");

        HashSet<String> words = new HashSet<String>();
        for (String s : wordArr) {
            words.add(s);
        }

        Iterator<String> it = words.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}

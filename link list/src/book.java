import java.util.*;

public class book {

    public static void main(String[] args){
        String[] things = {"a","b","c","d","e"};
        LinkedList<String> list1 = new LinkedList<String>();
        for (String x: things) {
            list1.add(x);
        }


        String[] things2 = {"a2","b2","c2","d2","e2"};
        LinkedList<String> list2 = new LinkedList<String>();
        for(String y: things2){
            list2.add(y);
        }

        list1.addAll(list2);
        list2.clear();

        printMe(list1);
        removeStuff(list1, 3, 5);
        list1.addFirst("g");
        printMe(list1);
        System.out.println(list1.contains("bb"));
        reverseMe(list1);
        printMe(list1);
        list1.remove("b");
        printMe(list1);
        printMe(list2);
    }

    private static void printMe(List<String> l){
        for(String b: l){
            System.out.print(b + "    ");
        }
        System.out.println();
    }

    private static void removeStuff(List<String> l, int from, int to){
        l.subList(from, to).clear();
    }

    private static void reverseMe(List<String> l){
        ListIterator<String> b = l.listIterator(l.size());

        while(b.hasPrevious()){
            System.out.print(b.previous() + "    " );
        }
        System.out.println();
    }


}

import java.util.HashSet;
import java.util.Scanner;

public class InputReader {

    /*private HashSet<String> reader;
    public InputReader(){
        reader =new HashSet<String>();
    }*/

    public HashSet<String> getInput(){
        Scanner reader = new Scanner(System.in);

        HashSet<String> words = new HashSet<String>();

        System.out.print("> ");
        String inputLine = reader.nextLine().trim().toLowerCase().replaceAll("\\s"," ");
        String[] wordArray = inputLine.split(" ");
        for (String s: wordArray){
            words.add(s);
        }

        return words;
    }

    public String getInputt(){
        Scanner reader = new Scanner(System.in);

        String word = reader.next();

        return word;
    }
 }

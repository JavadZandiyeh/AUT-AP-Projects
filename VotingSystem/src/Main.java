import java.util.ArrayList;
import java.util.Scanner;

/**
 * in this class i made an menu and by this menu user can make a voting
 * or he can vote on voting and also can see the result of the voting
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        VotingSystem votingSystem = new VotingSystem();

        int number = 0;
        //we have an menu with 4 elements and the program do its work after chose an element
        //this program finishes after chose exit
        while (number != 4){
            System.out.println("Do you want to:\n" + "1)Add voting\n2)Vote\n3)Result\n4)Esit");
            number = scanner.nextInt();


            if (number == 1) {
                System.out.print("Write your question:    ");
                String question = scanner1.nextLine().trim().toLowerCase().replaceAll("\\s", " ");

                System.out.print("Write the type of your question.(0) for one choice and (1) for multiple choice:    ");
                int type = scanner.nextInt();

                System.out.print("Write your choices:    ");
                String ch = scanner1.nextLine().trim().toLowerCase().replaceAll("\\s"," ");
                String[] cho = ch.split(" ");
                ArrayList<String> choices = new ArrayList<String>();
                for (String s : cho) {
                    choices.add(s);
                }

                votingSystem.createVoting(question, type, choices);

            }
            if (number == 2) {
                System.out.println("write your first name:    ");
                String firstName = scanner.next();
                System.out.println("write your last name:    ");
                String lastName = scanner.next();
                Person person = new Person(firstName, lastName);

                System.out.println("Write the number of voting you want to participate on:    ");
                int numberOfVoting = scanner.nextInt();
                votingSystem.printVoting(numberOfVoting);

                System.out.println("\nwrite your choice");
                Scanner scanner2 = new Scanner(System.in);

                String s = scanner2.nextLine().trim().toLowerCase().replaceAll("\\s", " ");
                String[] ss = s.split(" ");
                ArrayList<String> arr = new ArrayList<String>();
                for (String ff: ss){
                    arr.add(ff);
                }
                votingSystem.vote(numberOfVoting, person,arr);

            }
            if(number == 3){
                System.out.println("Write the number of voting you want to find result till now:    ");
                int numberOfVoting = scanner.nextInt();

                votingSystem.printResults(numberOfVoting);
            }
        }

    }
}

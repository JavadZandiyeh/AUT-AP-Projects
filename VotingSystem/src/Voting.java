import ir.huri.jcal.JalaliCalendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Voting {

    //fields of voting
    private int type;
    private String question;
    private ArrayList<Person> voters;
    private HashMap<String, HashSet<Vote>> listOfVotesToChoices;

    /**
     * constructor of voting
     * @param type type of voting
     * @param question question of voting
     */
    public Voting(int type, String question){
        this.type = type;
        this.question = question;
        voters = new ArrayList<Person>();
        listOfVotesToChoices = new HashMap<String, HashSet<Vote>>();
    }

    /**
     * make a new choice for question
     * @param choice the choice we want to add to our question
     */
    public void creatChoice(String choice){
        listOfVotesToChoices.put(choice, new HashSet<Vote>());
    }

    /**
     * vote to this voting
     * @param person the person who want to vote
     * @param choices the choices of this person for voting
     */
    public void vote(Person person, ArrayList<String> choices){
        if(!voters.contains(person)) {
            voters.add(person);

            if (type == 1) {
                for (String s : choices) {
                    JalaliCalendar calendar = new JalaliCalendar();
                    listOfVotesToChoices.get(s).add(new Vote(person, calendar.getDayOfWeekString()));
                }
            }

            if (type == 0) {
                JalaliCalendar calendar = new JalaliCalendar();
                listOfVotesToChoices.get(choices.get(0)).add(new Vote(person, calendar.toString()));
            }
        }
    }

    /**
     * make a random vote the person
     * @param person person that wants the random vote
     */
    public void randomVote(Person person){
        if(!voters.contains(person)){
            voters.add(person);
            JalaliCalendar calendar = new JalaliCalendar();
            Random random = new Random();
            listOfVotesToChoices.get(random.nextInt(listOfVotesToChoices.size())).add(new Vote(person, calendar.toString()));
        }
    }

    /**
     * prints the name of all voters
     */
    public void printVoters(){
        for (Person person: voters){
            System.out.println(person.toString());
        }
    }

    /**
     * is for printing the result
     */
    public void printVotes(){
        for(String s: listOfVotesToChoices.keySet()){
            System.out.println(s + ":" + listOfVotesToChoices.get(s).size());
        }
    }

    /**
     * prints the choices of the question
     */
    public void printChoices(){
        System.out.println(listOfVotesToChoices.keySet());
    }

    /**
     * prints the question
     */
    public void printQuestion() {
        System.out.println(question);
    }
}

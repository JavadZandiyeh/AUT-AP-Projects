import java.util.ArrayList;

/**
 * this class cam=n support a list of voting on it
 */
public class VotingSystem {
    //an array of voting that have been created
    public ArrayList<Voting> votingList;

    /**
     * make a new array list
     */
    public VotingSystem(){
        votingList = new ArrayList<Voting>();
    }

    /**
     * this method make a new voting
     * @param question the question of question
     * @param type the type of voting(one or more chose)
     * @param listOfToChoices list of choices
     */
    public void createVoting(String question, int type, ArrayList<String> listOfToChoices){
        Voting voting = new Voting(type , question);
        votingList.add(voting);
        for (String s: listOfToChoices){
            voting.creatChoice(s);
        }
    }

    /**
     * prints all questions of all voting
     */
    public void printVotingQuestion(){
        for(Voting v: votingList){
            v.printQuestion();
        }
    }

    /**
     * prints the question and the choices of the question we pass to it
     * @param v the number of question we want to print in=ts question
     */
    public void printVoting(int v){
       votingList.get(v).printQuestion();
       votingList.get(v).printChoices();
    }

    /**
     * we can vote with this method
     * @param i number of voting in the array list
     * @param person the person who want to vote
     * @param choices the choices of voting
     */
    public void vote(int i, Person person, ArrayList<String> choices){
        votingList.get(i).vote(person, choices);
    }

    /**
     * make a choice and vote that randomly
     * @param i number of voting we want to vote on it
     * @param person person who want to vote
     */
    public void randomVote(int i, Person person){
        votingList.get(i).randomVote(person);
    }

    /**
     * prints the result of voting
     * @param i number of voting we want to understand its result
     */
    public void printResults(int i){
        votingList.get(i).printVotes();
    }

}

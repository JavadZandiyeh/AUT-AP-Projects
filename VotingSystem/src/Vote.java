import java.util.Objects;

/**
 * this class is for details of each vote
 */
public class Vote {
    //person and date of this vote
    private Person person;
    private String date;

    /**
     * constructor of vote class
     * @param person person to vote
     * @param date date of voting
     */
    public Vote(Person person, String date){
        this.person = person;
        this.date = date;
    }

    /**
     * @return Person who vote
     */
    public Person getPerson() {
        return person;
    }

    /**
     * @return date of voting
     */
    public String getDate() {
        return date;
    }

    /**
     * @param o the vote we want to find that is equal with this vote or not
     * @return true for equal votes and false for not equal votes
     */
    public boolean equals(Object o) {

        if (this == o){return true;}
        if (!(o instanceof Vote)){return false;}

        Vote vote = (Vote) o;
        return person.equals(vote.person);

    }

    /**
     * @return int that is the hashCode
     */
    public int hashCode() {
        return Objects.hash(person, date);
    }
}

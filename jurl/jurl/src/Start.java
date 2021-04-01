/**
 * this class is the start place for our program
 * in this class, user can choose 2 models of program
 * one for console mode and another for ui mode
 * our console mode name is 'jurl' and our ui mode
 * name is 'Insomnia'
 */
public class Start {

    /**
     * @param args inputs of user from command line that specifies
     *             type of executing program
     */
    public static void main(String[] args) {
        args = new String[2];
        args[0] = "--start";
        args[1] = "insomnia";
        //this try catch is for starting and if user can't start
        //program we help him/her with note in catch block
        try {
            if(args[0].toLowerCase().equals("--start")){
                if(args[1].toLowerCase().equals("jurl"))
                    new Console();
                else if(args[1].toLowerCase().equals("insomnia"))
                    new UI();
                else
                    throw new Exception();
            }
            else
                throw new Exception();
        }catch (Exception e) {
            System.out.println("$NOTE: you must start program by args " +
                    "'--start jurl' or '--start Insomnia',try again.");
        }
    }
}
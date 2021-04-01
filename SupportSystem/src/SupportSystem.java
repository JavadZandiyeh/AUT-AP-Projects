import java.util.HashSet;

public class SupportSystem {
    private InputReader reader;
    private Responder responder;

    public SupportSystem(){
        reader = new InputReader;
        responder = new Responder;
    }


    public void start(){
        boolean finished = false;
        printWelcome();

        while (!finished){
            String input = reader.getInputt();
            HashSet<String> input2 = reader.getInput();

            if((input.startsWith("bye"))||(input2.contains("bye"))){
                finished = true;
            }
            else{
                String response = responder.generateResponse();
                String response2 = responder.generateResponse(input);
                System.out.println(response);
            }
        }

        printGoodbye();
    }
}

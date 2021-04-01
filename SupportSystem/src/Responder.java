import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Responder {

    private Random randomGenerator;
    private ArrayList<String> responses;
    private HashMap<String, String> responseMap;

    public Responder(){
        randomGenerator = new Random();
        responses = new ArrayList<String>();
        responseMap = new HashMap<String ,String>();
        fillResponses();
    }

    public void fillResponses(){
        responses.add ("That sounds odd. Could you describe \n" + "that problem in more detail?");
        responses.add ("No other customer has ever \n" + "complained about this before. \n" + "What is your system configuration?");
        responses.add ("That’s a known problem with Vista." + "Windows 7 is much better.");
        responses.add ("I need a bit more information on that.");
        responses.add ("Have you checked that do not \n" + "have a dll conflict?");
        responses.add ("That is explained in the manual. \n" + "Have you read the manual?");
        responses.add ("Your description is a bit \n" + "wishy -washy. Have you got an expert \n" + "there with you who could describe \n" + "this more precisely?");
        responses.add ("That’s not a bug, it’s feature!");
        responses.add ("Could you elaborate on that?");

        responseMap.put("slow",
             "I think this has to do with your hardware.\n"
             + "Upgrading your processor should sole all"
             + "Performance problems. \n"
             +"Have you got a problem with our software."
        );

        responseMap.put("bug",
                "Well ,you know, all software has some bugs.\n"
                +"But our software engineers are working hard"
                +"hard to fix them. \n"
                +"Can you describe the problem a bit further?"
        );

        responseMap.put("expensive",
                "The cost of our product is quite competitive.\n"
                +"Have you look around and "
                +"really compared our features?"
        );
    }

    public String generateResponse(){
        int index = randomGenerator.nextInt((responses.size());
        return responses.get(index);
    }

    public String generateResponse(String word){
        String response = responseMap.get(word);
        if(response != null){
            return response;
        }
        else{
            return pickDeafaultResponse();
        }
    }

    public String generateResponse(HashSet<String> words){
        for (String w :words){
            if(responseMap.get(w) != null){
                return responseMap.get(w);
            }
            else {
                return pickDeafaultResponse();
            }
        }

    }
}

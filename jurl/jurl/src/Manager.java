import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * this class is for managing the requests and responses of it
 */
public class Manager {

    //name of directory that requests been saved on, in this class just
    //use it for finding requests from there not saving there
    private static final String saveRequestPlace = "./SavedRequests";

    //project text
    private String[] project;
    //this is the request that we have to check it out from project text
    private Request currentRequest;
    //this are requests that we want to fill their response field later
    //mean after sending it to server
    private ArrayList<Request> requests;
    //list of request senders that send our request to server and get
    //appropriate response and then fill
    private ArrayList<RequestSender> senders;
    //this is for say to program to wait till all requests be sent
    public static CountDownLatch latch;

    /**
     * constructor of class for initializing array lists
     */
    public Manager(){
        requests = new ArrayList<Request>();
        senders = new ArrayList<RequestSender>();
    }

    /**
     * this is the most important method of manager and connects method of this
     * class to each other for answering to demand that has been sent on it
     * @param project the project we have to analyse it
     * @return requests that user wants that their response field been filled
     */
    public ArrayList<Request> doTask(String[] project) throws FinishWorkingException {

        //before every thing we have to clean last works that
        //are about last demands been sent to manager
        //because now we are doing a new process and work
        requests.clear();
        senders.clear();

        //getting project from ui or console to working on
        this.project = project;

        //first we have to find current request
        // that is in body of project text
        findCurrentRequest();

        //these are requests that have to execute them by
        // 'fire' command from user specified
        findSavedRequestsToExecute();

        //here if no request founded from project text we have to
        //return null and say to console/ui that no request is available
        //for finding its response
        if(requests.size() == 0)
            return null;

        //after finding requests from project we have to specify request senders
        //for each request to help us to sending our request and filling response field
        for(Request request: requests) {
            RequestSender sender = new RequestSender(request);
            senders.add(sender);
        }

        //for each request we have to make a count in latch,
        // because we want all requests be sent correctly
        latch = new CountDownLatch(requests.size());

        //making a executor service for executing our threads
        // (mean our request senders)
        ExecutorService pool = Executors.newCachedThreadPool();
        for(RequestSender sender: senders)
            pool.execute(sender);


        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new FinishWorkingException("An Interrupted exception " +
                    "happened on waiting till all responses been gotten");
        }

        //finishing work of executor service
        pool.shutdown();

        //returning filled requests from response field
        return requests;
    }

    //--------------------------------------My gadgets----------------------------------
    /**
     * this method checks that is input param an argument or not
     * @param s input that we want to check its argumentation
     * @return true if s is argument
     */
    private boolean isArgument(String s){
        if(s.startsWith("-"))
            return true;
        if(s.equals("create") || s.equals("list") || s.equals("fire"))
            return true;
        return false;
    }

    /**
     * this method is for checking that the project[i] is integer or not
     * @param s the String we want to check that is it integer or not
     * @return true if is integer
     */
    private boolean isInteger(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /**
     * this method is for checking that the project[i] is available or not
     * @param index the index we want to check its availability
     * @return true if available
     */
    private boolean isAvailable(int index){
        if(index < project.length)
            return true;
        return false;
    }
    //----------------------------------------------------------------------------------


    //-----------------------gathering requests from project----------------------------
    /**
     * this method make a new instance of staff for finding the current
     * request as an object of 'Request' class then add it to requests
     * that we want to send them to server
     */
    private void findCurrentRequest(){
        Staff currentRequestFinder = new Staff(project);
        currentRequest = currentRequestFinder.call();
        if(! (currentRequest == null))
            requests.add(currentRequest);
    }

    /**
     * in this method we try to find requests from project text that user specify it
     * with command 'fire' with a directory name that request been saved on
     */
    private void findSavedRequestsToExecute(){
        for (int i = 0; i < project.length; i++){
            if(project[i].equals("fire")){
                i++;
                if(! isAvailable(i))
                    throw new FinishWorkingException("Write name of request directory" +
                            " you want to execute, after 'fire' command");

                if(isArgument(project[i]))
                    throw new FinishWorkingException("Name of request directory" +
                            " can't be same as arguments");

                File directory = new File(saveRequestPlace + File.separator + project[i]);
                if(! directory.exists())
                    throw new FinishWorkingException("No directory with name " + project[i] + " is available");


                i++;
                if(! isAvailable(i))
                    throw new FinishWorkingException("Write number of request(s) you want to execute, " +
                            "after " + project[i-1]);

                if(directory.listFiles().length == 0)
                    throw new FinishWorkingException("Directory is empty");


                while (isInteger(project[i])){
                    Request request = findRequestFromDirectory(directory, project[i]);

                    if(request == null)
                        throw new FinishWorkingException("Don't choose a not available" +
                                " number for request(s) you want to fire.");

                    requests.add(request);

                    i++;
                    if(! isAvailable(i))
                        break;
                }
            }
        }
    }

    /**
     * this method find request from saving directory by its name
     * @param directory the directory we want to find request from it
     * @param nameFile name of file we want to read request object from it
     * @return request that read form file
     */
    private Request findRequestFromDirectory(File directory,String nameFile){
        for (File file: directory.listFiles()){
            if(file.getName().equals(nameFile)){
                try (FileInputStream fis = new FileInputStream(file);
                     ObjectInputStream ois = new ObjectInputStream(fis)){

                    Object object = ois.readObject();
                    if(object instanceof Request) {
                        Request request = (Request) object;
                        return request;
                    }else
                        throw new FinishWorkingException("This file is not a request object");

                } catch (IOException e) {
                    throw new FinishWorkingException("A problem on opening" +
                            " files from saving directory happened");
                } catch (ClassNotFoundException e) {
                    throw new FinishWorkingException(e.getMessage());
                }
            }
        }
        return null;
    }
    //----------------------------------------------------------------------------------

    /**
     * this method is for showing help to user
     * @return an string that contains help part
     */
    public static String getHelp() {
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to help part of program\n");
        builder.append(" '--url'             -> url of server you want to send request to\n");
        builder.append(" '--method' or '-M'  -> setting method for request\n");
        builder.append(" '--headers' or '-H' -> setting headers for request\n");
        builder.append(" '-i'                -> showing headers of response\n");
        builder.append(" '--help' or '-H'    -> show this details\n");
        builder.append(" '-f'                -> follow redirects\n");
        builder.append(" '--output' or '-O'  -> save response body\n");
        builder.append(" '--save' or '-S'    -> save this request\n");
        builder.append(" '--data' or '-d'    -> form data message body making\n");
        builder.append(" '--json' or '-j'    -> json message body making\n");
        builder.append(" '--upload'          -> uploading files\n");
        builder.append(" 'create'            -> making a new directory\n");
        builder.append(" 'fire'              -> send multi requests\n");
        builder.append(" 'list'              -> show list of file\n");

        return builder.toString();
    }

    /**
     * @return current request for doing works on it in console or ui
     */
    public Request getCurrentRequest(){
        return currentRequest;
    }

}

/**
 * this class if a exception class and have constructor to set
 * the message we want to show to user and a method for getting
 * this message
 */
class FinishWorkingException extends RuntimeException{
    private String message;

    public FinishWorkingException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
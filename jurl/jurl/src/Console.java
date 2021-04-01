import java.io.*;
import java.util.ArrayList;

/**
 * from start class if user choose console model we come to this
 * class and starting to analyse its demands
 * note that user demands can have a request from user or not
 * just for making works on local pc like creating or saving and etc.
 */
public class Console{
    //we want to use red color for start line of
    // getting user demands thus we put these here
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    //places for saving requests, in this class we just us it for
    //showing list of requests not saving requests
    private static final String saveRequestPlace = "./SavedRequests";

    //the main array of demands of user includes requests from server
    //or some local demands
    private String[] project;
    //reader of demands from console
    private BufferedReader reader;
    //the condition of finishing program
    private boolean finish;
    //manager manages or requests to server
    private Manager manager;
    //the answer we have to show to user after its demands
    private StringBuilder outPut;
    //array list of users requests that manager have to manage them
    private ArrayList<Request> requests;

    /**
     * constructor of program and also manager of user demands
     */
    public Console() {
        //condition for finishing program
        finish = false;
        //we need a reader to read the users demand
        reader = new BufferedReader(new InputStreamReader(System.in));
        //manager that manage the request part of project
        manager = new Manager();
        //array list for requests we want to show their responses
        requests = new ArrayList<Request>();
        //output we have to show to user after his/her demand
        outPut = new StringBuilder();


        //starting program
        while (! finish) {
            //first we clear the output from last responses to demands
            outPut.delete(0, outPut.length());

            //getting input from user
            System.out.print(ANSI_RED + ">jurl " + ANSI_RESET);
            try {
                project = reader.readLine().split("\\s+");
            }catch (IOException e){
                System.out.println("$ERROR: " + e.getMessage());
            }

            //we have to analyse input of user
            analysingProject();

            //if user wants to close program we have to set finish true
            finishChecking();
        }

        //after finishing program we close reader
        try {
            reader.close();
        }catch (IOException e){
            System.out.println("$ERROR: " + e.getMessage());
        }
    }

    /**
     * this method analyse project and do appropriate works
     * against demands of project text
     */
    public void analysingProject(){
        try {
            //checks
            checkSaveDirectoryCreation();
            checkSavedRequestsListVisibility();
            checkHelpVisibility();

            //before calling manager to mange users request we have to refine
            //project text and if it is null we have not call it
            refiningProject();

            //after refining project if any request is available program sends
            //the project text to manager to analyse request part
            if(project.length != 0)
                startSendingRequest();

            //after analysing if every thing is ok we have to show response(s)
            //and some other answers against demands that been gathered in output
            System.out.print(outPut);
        }catch (FinishWorkingException e){ // note that this exception class is manual, for making program finish after it
            System.out.println("$ERROR: " + e.getMessage());
        }
    }

    //------------------------------------My gadgets------------------------------------
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
     * this method is for checking that the project[i] is available or not
     * @param index the index we want to check its availability
     * @return true if available
     */
    private boolean isAvailable(int index){
        if(index < project.length)
            return true;
        return false;
    }

    /**
     * deletes arguments of project that their content are '-100'
     */
    private void refiningProject(){
        ArrayList<String> pj = new ArrayList<>();

        for (String s: project){
            if(! s.equals("-100"))
                pj.add(s);
        }

        String[] pj1 = new String[pj.size()];
        for (int i = 0; i < pj1.length; i++)
            pj1[i] = pj.get(i);

        project = pj1;
    }
    //----------------------------------------------------------------------------------


    //-----------------------Checking parts(before sending request)---------------------
    /**
     * adding help part to our output
     */
    private void checkHelpVisibility(){
        for (int i = 0; i < project.length; i++){
            if(project[i].equals("-h") || project[i].equals("--help")) {
                outPut.append(Manager.getHelp() + "\n");
                project[i] = "-100";
            }
        }
    }

    /**
     * checks that if user wants to make directory, make it
     */
    private void checkSaveDirectoryCreation(){
        for(int i = 0; i < project.length; i++){
            if(project[i].equals("create")){
                project[i] = "-100";
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("Chose a name for your directory");

                if(isArgument(project[i]))
                    throw new FinishWorkingException("Your directory name doesn't be" +
                            " same as arguments,try creation again");

                File file = new File(saveRequestPlace + File.separator + project[i]);
                if(! file.exists()) {
                    file.mkdir();
                    outPut.append("#NOTE: directory created\r\n");
                }
                project[i] = "-100";
            }
        }
    }

    /**
     * this class check that if user want to see list of directory,
     * show it to him/her
     */
    private void checkSavedRequestsListVisibility(){
        for (int i = 0; i < project.length; i++) {
            if (project[i].equals("list")) {
                project[i] = "-100";
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("chose a directory to show its list of saved requests");

                if(isArgument(project[i]))
                    throw new FinishWorkingException(project[i] + " does't be same as arguments");

                File file = new File(saveRequestPlace + File.separator + project[i]);
                project[i] = "-100";
                File[] allFiles = file.listFiles();

                if(! file.exists())
                    throw new FinishWorkingException("No such directory is available");

                if(allFiles.length == 0)
                    outPut.append("#NOTE: Empty directory for showing its requests\r\n");

                for(File f: allFiles) {
                    try(FileInputStream fis = new FileInputStream(f);
                        ObjectInputStream obs = new ObjectInputStream(fis)){


                        Object object = obs.readObject();
                        Request request;
                        if(object instanceof Request) {
                            request = (Request) object;
                            outPut.append(request.getVision() + "\r\n");
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                       throw new FinishWorkingException("An error happened on reading file of requests");
                    }
                }
            }
        }
    }

    /**
     * check if user wants to finish program
     * we close program
     */
    private void finishChecking(){
        for(int i = 0; i < project.length; i++) {
            if (project[i].equals("exit")) {
                finish = true;
                project[i] = "-100";
                return;
            }
        }
        finish = false;
    }
    //----------------------------------------------------------------------------------


    //------------------------checking parts(after sending request)---------------------
    /**
     * first of all we have to send our project text for manager and then manager
     * finds requests from project text and send them to server and get the response
     * at last sets the response field of request(note that each request has a field
     * witch is its response) and return it to us and here we save it as 'requests'
     * in fact this field has its response on it and can show it
     */
    private void startSendingRequest() {
        //here we get filled requests from manager
        requests = manager.doTask(project);

        //after getting requests we have to check if user want to do
        //saving on its response body or itself
        saveCurrentRequest();
        saveResponseBody();
        fillOutput();
    }

    /**
     * this method is for saving body of response
     */
    private void saveResponseBody(){
        for(int i = 0; i < project.length; i++){
            if(project[i].equals("-O") || project[i].equals("--output")){
                i++;

                String directory;
                if(isAvailable(i) && (! isArgument(project[i])))
                    directory = project[i];
                else
                    directory = ("output_[" + System.currentTimeMillis() + "]");

                Request currentRequest = manager.getCurrentRequest();
                if(currentRequest != null) {
                    currentRequest.getResponse().saveBody(directory);
                    outPut.append("#NOTE: Response saved in directory by name: " + directory);
                }else
                    throw new FinishWorkingException("No request is available to save its response");
            }
        }
    }

    /**
     * this method is for saving current request
     */
    private void saveCurrentRequest(){
        for(int i = 0; i < project.length; i++){
            if(project[i].equals("-S") || project[i].equals("--save")){
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("Chose a directory to save your request on");
                if(isArgument(project[i]))
                    throw new FinishWorkingException("Your directory name not to be same as arguments");

                String directory = project[i];
                Request currentRequest = manager.getCurrentRequest();
                if(currentRequest != null) {
                    currentRequest.save(directory);
                    outPut.append("#NOTE: Request saved in " + directory + "\r\n");
                }
                else
                    throw new FinishWorkingException("No request is available to save it");
            }
        }
    }

    /**
     * adding notes, errors,and responses of server to output and
     * show it for user
     */
    private void fillOutput(){
        //if no request mean no response and no output
        if(requests == null)
            return;

        for(Request request: requests){
            Response response = request.getResponse();
            //if no response against requests no output will be
            if(response == null)
                continue;

            //note that for showing current response(no for firing saved requests) we
            //have two thing that can show response headers or not and user say if
            if((manager.getCurrentRequest() != null) && (manager.getCurrentRequest().equals(request))){
                for(String element: project){
                    if(element.equals("-i"))
                        outPut.append(response.getResponseHeaders() + "\r\n");
                }
            }else
                outPut.append(response.getResponseHeaders() + "\r\n");

            //if response contains a picture we have to save it two directory
            //and say the user to see it from there
            if(response.getImage() != null) {
                response.saveImage();
                outPut.append("image saved in './SavedImages' directory\r\n");
            }else
                outPut.append(response.getResponseBody() + "\r\n");
        }
    }
    //----------------------------------------------------------------------------------
}

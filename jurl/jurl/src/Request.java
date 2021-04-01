import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * this class contains details of request
 */
public class Request implements Serializable {

    private static final String saveRequestPlace = "./SavedRequests";
    enum Method{ GET, DELETE, POST, PUT, PATCH }
    enum MessageBodyType{ FormData, Json, BinaryData, Urlencoded}

    private URL url;
    private Method method;
    private HashMap<String, String> headers;
    private byte[] messageBody;
    private String name;
    private boolean followRedirect;
    private transient Response response;
    private HashMap<String, String> keysAndValues;
    private MessageBodyType bodyType;
    private String binaryData;

    /**
     * constructor
     * @param url url of request
     * @param method method of request
     * @param headers headers of request
     * @param messageBody message body of request
     * @param followRedirect boolean that says follow redirect or not
     * @param keysAndValues keys and values of form data message body
     * @param bodyType type of message body
     * @param binaryData file path for binary data
     */
    public Request(URL url, Method method, HashMap<String, String> headers, byte[] messageBody,
                   boolean followRedirect, HashMap<String, String> keysAndValues, MessageBodyType bodyType,
                   String binaryData){
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.messageBody = messageBody;
        this.followRedirect = followRedirect;
        this.keysAndValues = keysAndValues;
        this.bodyType = bodyType;
        this.binaryData = binaryData;
    }

    //------------------------------getters------------------------------

    /**
     * @return url of request
     */
    public URL getUrl() {
        return url;
    }

    /**
     * @return method of request
     */
    public Method getMethod() {
        return method;
    }

    /**
     * @return headers of request
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * @return follow redirect of request
     */
    public boolean getFollowRedirect(){
        return followRedirect;
    }

    /**
     * @return good vision of request
     */
    public String getVision(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(name + " . " + "url: " + url + " | ");

        stringBuilder.append("method: ");
        switch (method){
            case GET: stringBuilder.append("GET" + " | "); break;
            case PUT: stringBuilder.append("PUT" + " | "); break;
            case POST: stringBuilder.append("POST" + " | "); break;
            case PATCH: stringBuilder.append("PATCH" + " | "); break;
            case DELETE: stringBuilder.append("DELETE" + " | "); break;
        }

        if(headers != null) {
            stringBuilder.append("headers:");
            for (String key : headers.keySet())
                stringBuilder.append(" " + key + ": " + headers.get(key));
        }

        return stringBuilder.toString();
    }

    /**
     * @return response of request
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @return message body type of request
     */
    public MessageBodyType getBodyType() {
        return bodyType;
    }

    /**
     * @return message body for sending url_encoded requests
     */
    public byte[] getMessageBody_Basic() {
        return messageBody;
    }

    /**
     * @return keys and values for sending form data requests
     */
    public HashMap<String, String> getBodyAsFormData_KeysAndValues() {
        return keysAndValues;
    }

    /**
     * @return files path for sending binary data
     */
    public String getBodyAsBinaryData() {
        return binaryData;
    }

    /**
     * @return String as body of json requests
     */
    public String getBodyAsJson(){
        StringBuilder builder = new StringBuilder();
        for(byte b: messageBody)
            builder.append((char)b);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(builder.toString());
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }
    //-------------------------------------------------------------------

    //----------------------------setters--------------------------------

    /**
     * @param directory for saving requests in a directory
     *                 we find its name base on other requests on that directory
     */
    public void setName(File directory) {
        if(directory.listFiles().length == 0) {
            name = "1";
            return;
        }

        ArrayList<String> fileNames = new ArrayList<>();

        for(File file: directory.listFiles())
            fileNames.add(file.getName());

        int[] numbers = new int[directory.listFiles().length];
        for(int i = 0; i < fileNames.size(); i++)
            numbers[i] = Integer.parseInt(fileNames.get(i));

        Arrays.sort(numbers);

        name = "" + (numbers[directory.listFiles().length - 1] + 1);
    }

    /**
     * @param url url of request
     */
    public void setUrl(URL url){
        this.url = url;
    }

    /**
     * @param response response against this request
     */
    public void setResponse(Response response) {
        this.response = response;
    }
    //-------------------------------------------------------------------

    /**
     * @param directory this method saves this request in directory
     */
    public void save(String directory){
        //making save place for saving requests there
        File saveDirectory = new File(saveRequestPlace);
        if(! saveDirectory.exists())
            saveDirectory.mkdir();


        directory = (saveRequestPlace + File.separator + directory);
        File file1 = new File(directory);
        if(! file1.exists())
            file1.mkdir();

        setName(file1);

        try(FileOutputStream fos = new FileOutputStream(directory + File.separator + name);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(this);
            oos.flush();
        } catch (FileNotFoundException e) {
            throw new FinishWorkingException(e.getMessage());
        } catch (IOException e) {
            throw new FinishWorkingException(e.getMessage());
        }
    }

}
































//    Arguments:{
//            "-O", "--output",
//            "-M", "–-method",
//            "-H", "–-headers",
//            "-h", "–-help",
//            "-S", "--save",
//            "-d", "--data",
//            "-j", "–-json",
//            "–-upload",
//            "-i", "-f"
//            "fire"
//    };
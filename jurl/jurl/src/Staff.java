import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Callable;

/**
 * this class works is to find current request from
 * project text and send it for manager
 */
public class Staff implements Callable {
    //text of project that staff must find its request
    private String[] project;
    //body of message
    private String messageBody;
    //boundary of message
    private long boundaryy;

    /**
     * @param project array that, we have to find requests from there
     */
    public Staff(String[] project){
        this.project = project;
    }

    //---------------------------------My gadgets---------------------------------

    /**
     * @param index the index that we have to check its available from project text
     * @return true if this index is available
     */
    private boolean isAvailable(int index){
        if(index < project.length)
            return true;
        return false;
    }

    /**
     * say us that is this an argument or not
     * @param s string we want to find that is argument or not
     * @return true if is argument
     */
    private static boolean isArgument(String s){
        if(s.startsWith("-"))
            return true;
        if(s.equals("create") || s.equals("list") || s.equals("fire"))
            return true;
        return false;
    }
    //----------------------------------------------------------------------------

    //---------------------------Checkout request parts---------------------------
    /**
     * finding url from project text
     * @return url of current request
     */
    private URL getURL() {
        for(int i = 0; i < project.length; i++) {
            if (project[i].equals("-U") || project[i].equals("--url")) {
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("URL part not to be empty");

                if(isArgument(project[i]))
                    throw new FinishWorkingException("URL part not to be same as arguments");

                if (! project[i].startsWith("http"))
                    project[i] = ("http://" + project[i]);

                URL url;
                try {
                    url = new URL(project[i]);
                }catch (MalformedURLException e){
                    throw new FinishWorkingException("Malformed URL");
                }

                return url;
            }
        }

        return null;
    }

    /**
     * @return true if user wants to follow redirect the request
     */
    private boolean getFollowRedirect(){
        for (String s: project){
            if(s.equals("-f"))
                return true;
        }
        return false;
    }

    /**
     * @return method of request
     */
    private Request.Method getMethod(){
        for(int i = 0; i < project.length; i++) {
            if ((project[i].equals("-M") || project[i].equals("--method"))) {
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("Method part not to be empty");
                if(isArgument(project[i]))
                    throw new FinishWorkingException("After method part you have to" +
                            " put a method not an argument");

                switch (project[i].toUpperCase()) {
                    case "GET": return Request.Method.GET;
                    case "DELETE": return Request.Method.DELETE;
                    case "POST": return Request.Method.POST;
                    case "PUT": return Request.Method.PUT;
                    case "PATCH": return Request.Method.PATCH;
                }
                throw new FinishWorkingException("This method is not allowed");
            }
        }
        return Request.Method.GET;
    }

    /**
     * @return headers of request
     */
    private HashMap<String, String> getHeaders(){
        for(int i = 0; i < project.length; i++){
            if(project[i].equals("-H") || project[i].equals("--headers")){
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("If you want to have headers, it can't be empty");
                if(isArgument(project[i]))
                    throw new FinishWorkingException("For headers don't put argument");
                if(! correctHeaderFormat(project[i]))
                    throw new FinishWorkingException("Incorrect format for header part");

                String header = project[i];
                header = header.substring(1, header.length() - 1);

                String[] header_partition = header.split(";" );

                HashMap<String, String> headers = new HashMap<>();
                for(String s: header_partition){
                    int splitIndex = s.indexOf(':');
                    headers.put(s.substring(0, splitIndex), s.substring(splitIndex + 1));
                }
                return headers;
            }
        }
        return null;
    }

    //start message body analysing
    /**
     * @return type of message body
     */
    private Request.MessageBodyType getMessageBodyType(){
        for (int i = 0; i < project.length; i++){
            if(project[i].equals("-d") || project[i].equals("--data")){
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("If you want to have message body,it doesn't be empty");
                if(isArgument(project[i]))
                    throw new FinishWorkingException("Message body not to be same as arguments");
                if(! correctFormDataText(project[i]))
                    throw new FinishWorkingException("Incorrect format for form data message body");

                messageBody = project[i];
                return Request.MessageBodyType.FormData;
            }

            if(project[i].equals("--urlencoded")){
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("If you want to have message body,it doesn't be empty");
                if(isArgument(project[i]))
                    throw new FinishWorkingException("Message body not to be same as arguments");
                if(! correctFormDataText(project[i]))
                    throw new FinishWorkingException("Incorrect format for urlencoded message body");

                messageBody = project[i];
                return Request.MessageBodyType.Urlencoded;
            }

            if(project[i].equals("--json") || project[i].equals("-j")){
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("If you want to have message body,it doesn't be empty");
                if(isArgument(project[i]))
                    throw new FinishWorkingException("Message body not to be same as arguments");
                if(! correctJsonText(project[i]))
                    throw new FinishWorkingException("Incorrect format for json message body");

                messageBody = project[i];
                return Request.MessageBodyType.Json;
            }

            if(project[i].equals("--upload")){
                i++;

                if(! isAvailable(i))
                    throw new FinishWorkingException("If you want to have message body,it doesn't be empty");
                if(isArgument(project[i]))
                    throw new FinishWorkingException("Message body not to be same as arguments");
                if(! correctBinaryDataText(project[i]))
                    throw new FinishWorkingException("Incorrect format for binary data message body");

                messageBody = ("\"" + "file=" + project[i] + "\"");
                return Request.MessageBodyType.BinaryData;
            }
        }
        return null;
    }

    /**
     * @return keys and values that message body contains
     */
    private HashMap<String, String> getMessageBody_KeysAndValues(){
        HashMap<String, String> messageBody_map = new HashMap<String, String>();
        String firstSplit;
        String secondSplit;
        int cutter;

        Request.MessageBodyType type = getMessageBodyType();
        if(type == null)
            return null;

        switch (type){
            case BinaryData:
            case FormData:
            case Urlencoded:
                firstSplit = "&";
                secondSplit = "=";
                cutter = 1;
                break;
            case Json:
                firstSplit = ",";
                secondSplit = ":";
                cutter = 2;
                break;
            default:
                return null;
        }


        messageBody = messageBody.substring(cutter, messageBody.length() - (cutter));
        String[] messages  = messageBody.split(firstSplit);

        for (String message: messages){
            String key = message.substring(0, message.indexOf(secondSplit));
            String value = message.substring(message.indexOf(secondSplit) + 1);
            messageBody_map.put(key, value);
        }

        return messageBody_map;
    }

    /**
     * @return bytes of request body in this type of request message body
     */
    private byte[] urlencoded(){
        StringBuffer requestParams = new StringBuffer();
        HashMap<String, String> params = getMessageBody_KeysAndValues();
        if (params != null && params.size() > 0) {
            try {
                Iterator<String> paramIterator = params.keySet().iterator();
                while (paramIterator.hasNext()) {
                    String key = paramIterator.next();
                    String value = params.get(key);
                    requestParams.append(URLEncoder.encode(key, "UTF-8"));
                    requestParams.append("=").append(
                            URLEncoder.encode(value, "UTF-8"));
                    requestParams.append("&");
                }
                requestParams.deleteCharAt(requestParams.length() -1);
                return requestParams.toString().getBytes();
            }catch (UnsupportedEncodingException e){
                throw new FinishWorkingException(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @return bytes of request body in this type of request message body
     */
    private byte[] formData(){
        HashMap<String, String> map = getMessageBody_KeysAndValues();
        if(map.size() == 0)
            return null;

        long boundary = System.currentTimeMillis();
        boundaryy = boundary;

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            for (String key : map.keySet()) {
                baos.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
                if (key.contains("file")) {
                    baos.write(("Content-Disposition: form-data;  name=\"file\"; filename=\"" +
                            (new File(map.get(key))).getName() + "\"\r\nContent-Type: " +
                            URLConnection.guessContentTypeFromName((new File(map.get(key))).getName())
                            + "\r\nContent-Transfer-Encoding: binary"
                            + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                    try (BufferedInputStream tempBufferedInputStream =
                                 new BufferedInputStream(new FileInputStream(new File(map.get(key))))) {
                        byte[] filesBytes = tempBufferedInputStream.readAllBytes();
                        baos.write(filesBytes);
                        baos.write("\r\n".getBytes(StandardCharsets.UTF_8));
                    }

                } else {
                    baos.write(("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                    baos.write((map.get(key) + "\r\n").getBytes(StandardCharsets.UTF_8));
                }
            }

            baos.write(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

            return baos.toByteArray();

        } catch (IOException e) {
            throw new FinishWorkingException(e.getMessage());
        }
    }

    /**
     * @return bytes of request body in this type of request message body
     */
    private byte[] json(){
        HashMap<String, String> map = getMessageBody_KeysAndValues();
        StringBuilder builder = new StringBuilder();

        if(map == null)
            return null;

        builder.append("{");
        for(String key: map.keySet()){
            builder.append("\"" + key + "\"");
            builder.append(":");
            builder.append("\"" + map.get(key) + "\"");
            builder.append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append("}");

        byte[] bytes = builder.toString().getBytes();
        return bytes;
    }
    //end message body analysing

    /**
     * @return bytes of message body base on their type
     */
    private byte[] getMessageBody(){
        Request.MessageBodyType type = getMessageBodyType();
        if(type == null)
            return null;

        switch (type) {
            case BinaryData:
            case FormData:
                return formData();
            case Json: return json();
            case Urlencoded: return urlencoded();
        }
        return null;
    }
    //----------------------------------------------------------------------------

    //------------------------Correct formats of partitions-----------------------

    /**
     * @param header the text that we want to find that is its shape correct
     *               for a header part or not
     * @return true if is correct
     */
    private boolean correctHeaderFormat(String header){

        int count1 = 0, count2 = 0;
        for (int i = 0; i < header.length(); i++) {
            if (header.charAt(i) == ':')
                count1++;
            if(header.charAt(i) == ';')
                count2++;
        }

        if( (header.startsWith("\"")) && (header.endsWith("\"")) && (count1 == count2 + 1))
            return true;
        return false;
    }

    /**
     * @param text the text that we want to find that is its shape correct
     *               for a form data part or not
     * @return true if is correct
     */
    private boolean correctFormDataText(String text){
        int counter = 0, counter1 = 0;
        for (char s: text.toCharArray()){
            if(s == '=')
                counter++;
            if(s == '&')
                counter1++;
        }

        if(text.startsWith("\"") && text.endsWith("\"") && (counter ==  counter1 + 1))
            return true;
        return false;
    }

    /**
     * @param text the text that we want to find that is its shape correct
     *               for a json part or not
     * @return true if is correct
     */
    private boolean correctJsonText(String text){
        int counter = 0, counter1 = 0;
        for (char s: text.toCharArray()){
            if(s == ':')
                counter++;
            if(s == ',')
                counter1++;
        }

        if(text.startsWith("\"" + "{") && text.endsWith("}" + "\"") && (counter == counter1 + 1))
            return true;
        return false;
    }

    /**
     * @param text the text that we want to find that is its shape correct
     *               for a binary part or not
     * @return true if is correct
     */
    private boolean correctBinaryDataText(String text){
        File file = new File(text);
        if(file.exists())
            return true;
        return false;
    }
    //----------------------------------------------------------------------------

    /**
     * as you know all callable classes must override call() method
     * in this method we analyse all thing and return the required
     * thing from this class
     * @return requests that found from project text
     */
    @Override
    public Request call(){

        URL url = getURL();
        if(url == null)
            return null;

        Request.Method method = getMethod();

        boolean followRedirect = getFollowRedirect();

        Request.MessageBodyType bodyType = getMessageBodyType();

        byte[] body = getMessageBody();

        HashMap<String, String> headers = getHeaders();

        //here note that if our request type arr in this types we have to
        //add something to our header to stipulating that
        if(method.equals(Request.Method.POST) || method.equals(Request.Method.PUT) || method.equals(Request.Method.PATCH)){
            if(body == null)
                throw new FinishWorkingException("Your request with this type of " +
                        "method must have a message body");

            if(headers == null)
                headers = new HashMap<>();

            switch (bodyType) {
                case Urlencoded:
                    headers.put("Content-Type", "application/x-www-form-urlencoded");
                    headers.put("Accept", "txt/html");
                    break;
                case BinaryData:
                case FormData:
                    headers.put("Content-Type", "multipart/form-data; boundary=" + boundaryy);
                    headers.put("Accept", "txt/html");
                    break;
                case Json:
                    headers.put("Content-Type", "application/json");
                    headers.put("Accept", "application/json");
                    break;
            }
        }

        HashMap<String, String> keysAndValues = getMessageBody_KeysAndValues();

        //url and method are not null
        //header and message body can be null
        Request request = new Request(url, method, headers, body, followRedirect, keysAndValues, bodyType, messageBody);
        return request;
    }
}

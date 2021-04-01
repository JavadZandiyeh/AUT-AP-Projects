import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import com.google.gson.*;

/**
 * this class contains details of responses
 */
public class Response implements Serializable{

    enum responseBodyType { Json, Html, Raw };

    private static final String saveResponsePlace = "./SavedResponses";
    private static final String saveResponseImage = "./SavedImages";

    private HttpURLConnection connection;

    private int code;
    private String message;
    private Map<String, List<String>> headerFields;
    private String responseHeaders;
    private String responseBody;
    private Image image;
    private long volume;
    private long time;

    /**
     * constructor of class
     * @param connection connection that we get responses from
     */
    public Response(HttpURLConnection connection){
        this.connection = connection;
        try {
            code = connection.getResponseCode();
            message = connection.getResponseMessage();
            headerFields = connection.getHeaderFields();
            setResponse();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * getting hole response from server
     * @throws IOException exception of reading details from server
     */
    private void setResponse() throws IOException{
        StringBuilder builder = new StringBuilder();

        //set the response header
        for (String key : headerFields.keySet()) {
            builder.append(key + ": ");
            for (String value : headerFields.get(key)) {
                builder.append("    " + value + "\r\n");
            }
        }
        responseHeaders = builder.toString();

        //set the response body
        builder.delete(0, builder.length());
        try(InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);

            responseBody = builder.toString();
            volume = responseBody.getBytes().length;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * if response of request is a image we set it as image field
     * @param image
     */
    public void setImage(Image image){
        this.image = image;
    }

    //---------------------------------------getters---------------------------------------

    /**
     * @return response body as raw data
     */
    public String getResponseBodyAsRawData(){
        return responseBody;
    }

    /**
     * @return response body as html
     */
    public String getResponseBodyAsHtml() {
        if(responseBody == null)
            return null;

        StringBuilder builder = new StringBuilder();

        for(char c: responseBody.toCharArray()){
            if(c == '>'){
                builder.append('>' + "\r\n");
            }else
                builder.append(c);
        }
        return builder.toString();
    }

    /**
     * @return response body as json
     */
    public String getResponseBodyAsJson(){
        if(responseBody == null)
            return null;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(responseBody);
        String prettyJsonString = gson.toJson(je);

        return prettyJsonString;
    }

    /**
     * @return response body type
     */
    public responseBodyType getBodyType(){
        if(responseBody == null)
            return null;

        if(responseBody.toLowerCase().contains("<html"))
            return responseBodyType.Html;
        if (responseBody.startsWith("{"))
            return responseBodyType.Json;
        return responseBodyType.Raw;
    }

    /**
     * @return body of response
     */
    public String getResponseBody(){
        if(responseBody == null)
            return null;

        switch (getBodyType()){
            case Json: return getResponseBodyAsJson();
            case Raw: return getResponseBodyAsRawData();
            case Html: return getResponseBodyAsHtml();
        }
        return getResponseBodyAsRawData();
    }

    /**
     * @return headers of response
     */
    public String getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * @return status code of response
     */
    public int getCode() {
        return code;
    }

    /**
     * @return statues message of response
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return time that takes to server response to request
     */
    public long getTime(){
        return time;
    }

    /**
     * @param time time that takes to response
     */
    public void setTime(long time){
        this.time = time;
    }

    /**
     * @return headers of response
     */
    public Map<String, List<String>> getHeaderFields() {
        return headerFields;
    }

    /**
     * @return image of resposne
     */
    public Image getImage() {
        return image;
    }

    /**
     * @return volume of gotten data
     */
    public long getVolume() {
        return volume;
    }
    //-------------------------------------------------------------------------------------

    /**
     * this method save the body of response in directory
     * @param directory name of directory that we want to save our response body
     */
    public void saveBody(String directory) {
        //making save place for saving responses there
        File saveDirectory = new File(saveResponsePlace);
        if(! saveDirectory.exists())
            saveDirectory.mkdir();

        if(directory == null)
            directory = ("output_[" + System.currentTimeMillis() + "]");

        File file1 = new File(saveResponsePlace + File.separator + directory );

        try (FileOutputStream fos = new FileOutputStream(file1);
             Formatter formatter = new Formatter(fos)){

            formatter.format(responseBody);
        }catch (IOException e){
            throw new FinishWorkingException("Input/Output Problem on saving response body");
        }
    }

    /**
     * saving image of response if is available
     */
    public void saveImage(){
        //making save place for saving image responses there
        File saveImage = new File(saveResponseImage);
        if(! saveImage.exists())
            saveImage.mkdir();

        File file = new File(saveImage + File.separator + System.currentTimeMillis());
        try{
            ImageIO.write((BufferedImage) image, "png", file);
        } catch (FileNotFoundException e) {
            System.out.println("$ERROR: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("$ERROR: " + e.getMessage());
        }
    }
}

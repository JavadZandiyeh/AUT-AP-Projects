import javax.imageio.ImageIO;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * task of this class is to send the request and fill the response part
 * of it by calling response class
 */
public class RequestSender implements Runnable {
    //our connection to server
    private HttpURLConnection connection;
    //request want to send it to server
    private Request request;

    /**
     * @param request request that we want to send it to server
     */
    public RequestSender(Request request){
        this.request = request;
    }

    //-----------------------------------------send request-----------------------------------------

    /**
     * starting connection with server
     */
    private void startConnection() {
        try {
            long startTime = System.currentTimeMillis();
            URL url = request.getUrl();
            connection = (HttpURLConnection) url.openConnection();
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(request.getFollowRedirect());

            switch (request.getMethod()) {
                case PATCH: {
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                    sendPost_Put_PatchRequest();
                    break;
                }
                case POST: {
                    connection.setRequestMethod("POST");
                    sendPost_Put_PatchRequest();
                    break;
                }
                case PUT: {
                    connection.setRequestMethod("PUT");
                    sendPost_Put_PatchRequest();
                    break;
                }
                case DELETE: {
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    sendDeleteRequest();
                    break;
                }
                case GET: {
                    sendGetRequest();
                    break;
                }
            }


            Response response = new Response(connection);
            try {
                Image image = ImageIO.read(url);
                if (image != null)
                    response.setImage(image);
            }catch (Exception e){}

            long finishTime = System.currentTimeMillis();
            response.setTime(finishTime - startTime);
            request.setResponse(response);
        }catch (IOException e){
            throw new FinishWorkingException(e.getMessage());
        }
    }

    /**
     * after checking that user wants to redirect or not we have to check
     * that server helps us to a location or not and after that we redirect
     * //to that place
     * @return true if server wants to redirect
     * @throws IOException exception on connection with server
     */
    private boolean followRedirectChecking() throws IOException {
        boolean redirect = false;
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }

        if (redirect) {
            // get redirect url from "location" header field
            String newUrl = connection.getHeaderField("Location");
            request.setUrl(new URL(newUrl));
        }
        return redirect;
    }

    /**
     * sending delete request to server
     * @throws ProtocolException problem on protocol of request
     */
    private void sendDeleteRequest() throws ProtocolException {
        connection.setRequestMethod("DELETE");
    }

    /**
     * sending get request to server
     * @throws ProtocolException problem on protocol of request
     */
    private void sendGetRequest() throws ProtocolException {
        connection.setRequestMethod("GET");
        connection.setDoInput(true); // true if we want to read server's response
        connection.setDoOutput(false); // false indicates this is a GET request
    }

    /**
     * sending post,put or patch request to server
     * @throws ProtocolException problem on protocol of request
     */
    private void sendPost_Put_PatchRequest() throws IOException {
        connection.setDoInput(true);// true indicates the server returns response
        connection.setDoOutput(true);// true indicates POST request

        if (request.getHeaders() != null) {
            for (String key : request.getHeaders().keySet()) {
                String value = request.getHeaders().get(key);
                connection.setRequestProperty(key, value);
            }
        }

        try(DataOutputStream bos = new DataOutputStream(connection.getOutputStream())) {
            bos.write(request.getMessageBody_Basic());

            bos.flush();
        }
    }

    //----------------------------------------------------------------------------------------------

    /**
     * all runnable classes override run() method
     * and here we manage the request sending
     */
    @Override
    public void run() {
        try {
            startConnection();

            //here we check that if user wants to follow redirect
            //and servers response help us to redirect, redirect to
            //that location
            if(request.getFollowRedirect()) {
                if (followRedirectChecking())
                    startConnection();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("$111ERROR: " + e.getMessage());
            return;
        }finally {
            Manager.latch.countDown();
            if (connection != null)
                connection.disconnect();
        }
    }
}

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * this class is for server part of program
 * here we get the requests and send appropriate
 * response to client
 */
public class Server {

    public static void main(String[] args) {
        //each server before each must have a server socket as a welcome place
        //for connecting sockets on
       try (ServerSocket welcomingSocket = new ServerSocket(6000)){
           //making a socket and put it in accept mode till client want to connect
           try (Socket serverConnection = welcomingSocket.accept();
                OutputStream outputStream = serverConnection.getOutputStream();
                InputStream inputStream = serverConnection.getInputStream()){

               //here we use buffer for reading input from client
                byte[] buffer = new byte[2048];

                StringBuilder builder = new StringBuilder();

                while (true){
                    int read = inputStream.read(buffer);
                    String reading = new String(buffer, 0, read);

                    if(reading.equals("over"))
                        break;

                    builder.append(reading + " ");
                    outputStream.write(builder.toString().getBytes());
                }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}

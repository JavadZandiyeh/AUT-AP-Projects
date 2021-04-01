import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * this class is client of program that connects to server and
 * send its requests on and get response of it
 */
public class Client {

    public static void main(String[] args) {
        //make a socket and connect it to server in port 6000
        //also this server is in local host
        try (Socket clientSocket = new Socket("localhost", 6000)) {
            try (OutputStream outputStream = clientSocket.getOutputStream();
                 InputStream inputStream = clientSocket.getInputStream()){

                //for reading we use buffer reading
                byte[] buffer = new byte[2048];

                //we get the demands from console from users
                Scanner scanner = new Scanner(System.in);

                //while server is in connection mode we can send our requests
                while (! clientSocket.isClosed()){
                    String write = scanner.nextLine();
                    outputStream.write(write.getBytes());

                    int read = inputStream.read(buffer);
                    if(read == -1)
                        break;

                    System.out.println(new String(buffer, 0, read));
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

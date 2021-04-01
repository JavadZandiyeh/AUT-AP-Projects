import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 1000)) {
            try (OutputStream outputStream = clientSocket.getOutputStream();
                 InputStream inputStream = clientSocket.getInputStream()){
                byte[] buffer = new byte[2048];
                Scanner scanner = new Scanner(System.in);

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

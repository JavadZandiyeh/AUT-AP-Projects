import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * server of program
 * this server has a executor service to manage threads
 */
public class Server {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        try (ServerSocket welcomingSocket = new ServerSocket(1000)){
            int countOfResponses = 0;

            //also servers must be all time on welcoming mode
            //but here we imagine that this server can response
            //to requests just 10 times and after that must
            //start it manually
            while (countOfResponses < 10){
                Socket serverConnectionSocket = welcomingSocket.accept();
                pool.execute(new connectionHandler(serverConnectionSocket));
                countOfResponses++;
            }
            pool.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * this class is a runnable class and use it for connection
 * between client and server
 * each client connect to one of this connection handlers
 * to get its responses
 */
class connectionHandler implements Runnable {
    Socket socket;
    public connectionHandler(Socket serverConnectionSocket){
        socket = serverConnectionSocket;
    }

    @Override
    public void run() {
        try(OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream()){
            StringBuilder builder = new StringBuilder();

            byte[] buffer = new byte[2048];
            while (true) {
                int length = is.read(buffer);
                String reading = new String(buffer, 0, length);

                if(reading.equals("over"))
                    break;

                builder.append(reading + " ");

                os.write(builder.toString().getBytes());
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

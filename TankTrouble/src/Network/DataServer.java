package Network;

import Main.ErrorClass;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * this class handles login time of users
 * and also creating new account times
 */
public class DataServer {


    public static void main(String[] args){
        new DataServer();
    }


    //directory witch user details are saved in
    private File directory;

    /**
     * constructor
     */
    public DataServer() {
        //creating directory to save user details
        directory = new File( "./dataServer");
        if(!directory.exists())
            directory.mkdir();

        start();
    }

    /**
     * start accepting requests from client
     */
    private void start(){
        ExecutorService pool = Executors.newCachedThreadPool();
        try(ServerSocket welcomingSocket = new ServerSocket(8080)){
            while (true){
                Socket connectionSocket = welcomingSocket.accept();
                pool.execute(new ClientHandler(connectionSocket));
            }
        }catch (SocketTimeoutException exception) {
            // Output expected SocketTimeoutExceptions.
            exception.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    /**
     * handles client requests
     */
    private class ClientHandler implements Runnable {

        //connection socket with client
        Socket connectionSocket;

        /**
         * constructor of class
         * @param connectionSocket socket that connects to client
         */
        public ClientHandler(Socket connectionSocket){
            this.connectionSocket = connectionSocket;
        }

        /**
         *in this class we open an stream for getting works of clients
         * and then go to three bellow methods for responding to client requests
        */
        @Override
        public void run() {
            String work;
            try(InputStream in = connectionSocket.getInputStream();
                ObjectOutputStream out = new ObjectOutputStream(connectionSocket.getOutputStream())){

                byte[] buffer = new byte[2048];
                int read = in.read(buffer);
                work = new String(buffer, 0, read);

                switch (work){
                    case "login" : doLogin(in, out, buffer); break;
                    case "createAccount" : doCreateAccount(in, out, buffer); break;
                    case "createPlay" : doCreatePlay(in, out, buffer); break;
                }

            } catch (Exception e) {
                //telling server about error
                System.out.println(e.getMessage());
            }
        }

        //------------------bellow three methods respond to client requests------------------
        /**
         * responding to clients work about login
         * @param in input stream
         * @param out output stream
         * @param buffer buffer for reading client requests
         */
        public void doLogin(InputStream in, ObjectOutputStream out, byte[] buffer) throws Exception{

            //getting username and password from client
            String username;
            String password;
            try {
                username = new String(buffer, 0, in.read(buffer));
                password = new String(buffer, 0, in.read(buffer));

            } catch (IOException e) {
                //telling client about error
                out.writeObject(new ErrorClass(e.getMessage()));
                return;
            }

            //file names in this directory is shape 'username_password'
            String fileToSearchFor = password;

            //check if this account is available
            //checking all accounts to find user by checking all files of dir
            for(File file: Objects.requireNonNull(directory.listFiles())) {
                if(file.getName().equals(fileToSearchFor)) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

                        //read object from file and cast it to account object
                        Object obj = ois.readObject();
                        Account account = null;
                        if (obj instanceof Account)
                            account = (Account) obj;
                        if (account == null)
                            throw new IOException();

                        //when find the account, pass the account object
                        //to client for having its details of account
                        if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                            out.writeObject(account);
                            return;
                        }

                    } catch (IOException | ClassNotFoundException e) {
                        //telling client about error
                        out.writeObject(new ErrorClass(e.getMessage()));
                        return;
                    }
                }
            }

            //telling client about error
            out.writeObject(new ErrorClass("No such account"));
        }

        /**
         * responding to clients work about login
         * @param in input stream
         * @param out output stream
         */
        private void doCreateAccount(InputStream in, ObjectOutputStream out, byte[] buffer) throws Exception {
            //getting username and password from client
            String username;
            String password;
            try {
                username = new String(buffer, 0, in.read(buffer));
                password = new String(buffer, 0, in.read(buffer));

            } catch (IOException e) {
                out.writeObject(new ErrorClass(e.getMessage()));
                return;
            }


            //show message for reserved passwords
            for(File f: Objects.requireNonNull(directory.listFiles())){
                if(f.getName().equals(password)) {
                    out.writeObject(new ErrorClass("This password has been reserved before"));
                    return;
                }
            }

            //file names in this directory are in shape 'username_password'
            File file = new File(directory + File.separator + password);
            if(file.exists()) {
                out.writeObject(new ErrorClass("This account is available"));
                return;
            }
            file.createNewFile();


            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
                Account account = new Account(username, password);
                oos.writeObject(account);
                out.writeObject(account);
            } catch (IOException e) {
                out.writeObject(new ErrorClass(e.getMessage()));
            }
        }

        /**
         * responding to clients work about login
         * @param in input stream
         * @param out output stream
         * @param buffer buffer for reading client requests
         */
        private void doCreatePlay(InputStream in, ObjectOutputStream out, byte[] buffer) {

        }
    }
}


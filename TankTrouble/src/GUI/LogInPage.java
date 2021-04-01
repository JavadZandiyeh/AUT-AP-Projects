package GUI;
import Main.ErrorClass;
import Network.Account;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.Formatter;

/**
 * this class is for login page of program
 */
public class LogInPage extends JPanel {

    //basis
    private UIFrame uiFrame;
    private actionHandler handler;

    //user name
    private JLabel userNameLabel;
    private JTextField userName;

    //password
    private JLabel passwordLabel;
    private JPasswordField password;
    private JCheckBox showPassword;
    private Image eye_image;
    private Image eye_not_image;

    //remind me
    private JCheckBox rememberMe;
    private JLabel rememberLabel;

    //go to create new account page
    private JButton newAccountPage;

    //login to program
    private JButton login;


    /**
     * constructor of class
     * @param uiFrame is the uiFrame of program
     */
    public LogInPage(UIFrame uiFrame){
        this.uiFrame = uiFrame;
        handler = new actionHandler();
        putComponents();
    }

    /**
     * this method sets the background picture of this page
     * @param g graphics for drawing background
     */
    @Override
    public void paintComponent(Graphics g) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("./backGround2.jpg"));
        } catch (IOException e) {
            uiFrame.showMessage(e.getMessage());
        }
        g.drawImage(image.getScaledInstance(720, -1, Image. SCALE_SMOOTH), 0, 0, null);
    }

    /**
     * putting components on their place and also add
     * action listeners for them
     */
    private void putComponents(){
        setLayout(null);
        setSize(720, 405);

        //user name label
        userNameLabel = new JLabel();
        userNameLabel.setText("user name: ");
        userNameLabel.setFont(new Font("Courier", Font.HANGING_BASELINE,20));
        userNameLabel.setForeground(new Color(232, 57, 24));
        userNameLabel.setBounds(100, 50, 120, 40);
        add(userNameLabel);

        //user name field
        userName = new JTextField();
        userName.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        userName.grabFocus();
        userName.setBounds(270, 50, 250, 40);
        add(userName);

        //password label
        passwordLabel = new JLabel();
        passwordLabel.setText("password: ");
        passwordLabel.setFont(new Font("Courier", Font.HANGING_BASELINE,20));
        passwordLabel.setForeground(new Color(232, 57, 24));
        passwordLabel.setBounds(100, 120, 120, 40);
        add(passwordLabel);

        //password field
        password = new JPasswordField();
        password.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        password.setBounds(270, 120, 250, 40);
        add(password);

        //password check box
        showPassword = new JCheckBox();
        showPassword.setSelected(false);
        showPassword.setBounds(550, 130, 20, 20);
        try {
            eye_image = ImageIO.read(new File("./eye.png"));
            eye_not_image = ImageIO.read(new File("./eye_not.png"));
        }catch (Exception e){
            uiFrame.showMessage(e.getMessage());
        }
        showPassword.setIcon(new ImageIcon(eye_not_image.getScaledInstance(20, -1, Image. SCALE_SMOOTH)));
        showPassword.addActionListener(handler);
        add(showPassword);


        //remind me later
        rememberMe = new JCheckBox();
        rememberMe.setSelected(false);
        rememberMe.setBounds(270, 190, 20, 20);
        rememberMe.addActionListener(handler);
        add(rememberMe);

        //remind label
        rememberLabel = new JLabel("remember me");
        rememberLabel.setFont(new Font("Courier", Font.HANGING_BASELINE,12));
        rememberLabel.setForeground(new Color(227, 193, 119));
        rememberLabel.setBounds(300, 185, 120, 30);
        add(rememberLabel);



        //login button
        login = new JButton("LOG IN");
        login.setBackground(new Color(0, 0, 0, 206));
        login.setForeground(new Color(227, 193, 119));
        login.setBounds(270, 250, 250, 40);
        login.addActionListener(handler);
        add(login);


        //create new account button
        newAccountPage = new JButton("NEW ACCOUNT");
        newAccountPage.setBackground(new Color(0, 0, 0, 206));
        newAccountPage.setForeground(new Color(227, 193, 119));
        newAccountPage.setBounds(100, 250, 120, 40);
        newAccountPage.addActionListener(handler);
        add(newAccountPage);

        //putting saved username and password on components if needed
        getRememberStatus();
    }

    /**
     * every time we run program we should see our saved user and pass on
     */
    private void getRememberStatus(){
        File dir = new File("./rememberStatus");
        File file = new File(dir + File.separator + "remember.txt");

        if(! dir.exists()) {
            dir.mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String user = bufferedReader.readLine();
            String pass = null;
            if(user != null)
                pass = bufferedReader.readLine();

            if((user != null) && (pass != null)) {
                userName.setText(user);
                password.setText(pass);
                rememberMe.setSelected(true);
            }
        } catch (IOException ex) { }
    }

    /**
     * write username and password to file for remember checkbox
     * @param user username
     * @param pass password
     */
    private void setRememberStatus(String user, String pass){
        //save username and password if remember checkBox is selected
        //creating file for remembering the username or password
        File dir = new File("./rememberStatus");
        if(! dir.exists())
            dir.mkdir();

        File file = new File(dir + File.separator + "remember.txt");

        if(rememberMe.isSelected()) {
            try (Formatter formatter = new Formatter(file)) {
                formatter.format("%s\n%s", user, pass);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * this a inner class for handing actions
     */
    private class actionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //show password check box
            if (e.getSource() == showPassword) {
                if (showPassword.isSelected()) {
                    password.setEchoChar((char) 0);
                    showPassword.setIcon(new ImageIcon(eye_image.getScaledInstance(20, -1, Image.SCALE_SMOOTH)));
                }
                else {
                    password.setEchoChar('*');
                    showPassword.setIcon(new ImageIcon(eye_not_image.getScaledInstance(20, -1, Image.SCALE_SMOOTH)));
                }
                password.grabFocus();
            }

            //login
            if (e.getSource() == login) {
                String user = userName.getText() + "";
                String pass = new String(password.getPassword()) + "";

                if(user.equals("") || pass.equals("")) {
                    uiFrame.showMessage("Fill both username and password");
                    return;
                }

                if(pass.contains(" ")){
                    uiFrame.showMessage("Password can't have any spaces on");
                    return;
                }


                //connect to server and check that is any account
                //available by this username and password or not
                try (Socket clientSocket = new Socket("192.168.83.1", 8080);
                     OutputStream os = clientSocket.getOutputStream();
                     ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())){


                    os.write(("login").getBytes());
                    Thread.sleep(10);
                    os.write(user.getBytes());
                    Thread.sleep(10);
                    os.write(pass.getBytes());


                    Object object = ois.readObject();
                    if(object instanceof ErrorClass) {
                        ErrorClass error = (ErrorClass) object;
                        uiFrame.showMessage(error.getError());
                        return;
                    }
                    if(object instanceof Account) {
                        Account account = (Account)object;
                        uiFrame.setCurrentAccount(account);
                        account.setRememberOfPass(rememberMe.isSelected());
                    }

                } catch (IOException | ClassNotFoundException | InterruptedException ex) {
                    uiFrame.showMessage(ex.getMessage());
                    return;
                }

                //checking if user wants to clear remember check box
                if(! rememberMe.isSelected()){
                    File file = new File("./rememberStatus/remember.txt");
                    if(file.exists()){
                        file.delete();
                        try {
                            file.createNewFile();
                        } catch (IOException ex) {
                            uiFrame.showMessage(ex.getMessage());
                        }
                    }
                }

                //writing username and password to file  if needed
                setRememberStatus(user, pass);

                //going to next page after login
                uiFrame.setPageOfFrame(UIFrame.pages.main);
            }

            //create new account
            if (e.getSource() == newAccountPage)
                uiFrame.setPageOfFrame(UIFrame.pages.newAccount);
        }
    }
}

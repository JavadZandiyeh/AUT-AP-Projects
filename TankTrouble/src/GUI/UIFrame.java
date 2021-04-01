package GUI;

import Network.Account;
import javax.swing.*;
import java.io.File;

/**
 * main frame of program
 */
public class UIFrame extends JFrame {

    //enum for name of pages
    enum pages{ login, newAccount, main, setting, game, computerGame, networkGame}

    //panels of frame
    private LogInPage loginPage;
    private NewAccountPage newAccountPage;
    private MainPage mainPage;
    private SettingPage settingPage;
    private GamePage gamePage;
    private ComputerGamePage computerGamePage;
    private NetworkGamePage networkGamePage;

    //current user of program in this system
    private Account currentAccount;

    /**
     * constructor of class
     */
    public UIFrame() {
        setLookAndFeel();
        setBounds(290, 150,720, 405);
        init();
        setPageOfFrame(pages.login);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * setting design type of components
     */
    private void setLookAndFeel(){
        //setting frames features
        try{
            for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,
                    "you have not installed Nimbus look and feel");
        }
    }

    /**
     * initializing pages
     */
    private void init(){
        //first initializing the panels that are shown in frame
        loginPage = new LogInPage(this);
        newAccountPage = new NewAccountPage(this);
        mainPage = new MainPage(this);
        settingPage = new SettingPage(this);
        gamePage = new GamePage(this);
        computerGamePage = new ComputerGamePage(this);
        networkGamePage = new NetworkGamePage(this);
        currentAccount = null;

        //create a file for saving username and password
        //if user wants to  remember it later
        File rememberFile = new File("./rememberStatus");
        if(!rememberFile.exists())
            rememberFile.mkdir();

        //create a directory for tanks of users
        File tanks = new File("./tanks");
        if(! tanks.exists())
            tanks.mkdir();
    }

    /**
     *setting current page(panel) of frame
     */
    public void setPageOfFrame(pages pageOfFrame) {

        JPanel panel = null;
        switch (pageOfFrame){
            case login: panel = loginPage; break;
            case newAccount: panel = newAccountPage; break;
            case main: panel = mainPage; break;
            case setting: panel = settingPage; break;
            case game: panel = gamePage; break;
            case networkGame: panel = networkGamePage; break;
            case computerGame: panel = computerGamePage; break;
        }

        //removing last page
        getContentPane().removeAll();
        repaint();
        //putting current page
        add(panel);
        setTitle(panel.getClass().getName().replace("GUI.", ""));
    }

    /**
     * @return setting page
     */
    public SettingPage getSettingPage(){
        return settingPage;
    }

    /**
     * @param currentAccount account witch is now in program on this system
     */
    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Account getCurrentAccount(){
        return currentAccount;
    }

    /**
     * this method used for showing messages by optionPane
     * @param message the message we want to show it
     */
    public void showMessage(String message){
        JOptionPane optionPane = new JOptionPane();
        optionPane.showMessageDialog(null, message,
                "Hint", JOptionPane.INFORMATION_MESSAGE );
    }
}

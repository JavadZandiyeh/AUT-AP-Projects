package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * this class is a panel for main page of program
 */
public class MainPage extends JPanel {

    //basis
    private UIFrame uiFrame;
    private ActionHandler handler;

    //buttons of main page
    private JButton setting;
    private JButton computerGame;
    private JButton netWorkGame;
    private JButton exit;


    /**
     * @param uiFrame frame that we add this page on
     */
    public MainPage(UIFrame uiFrame) {
        this.uiFrame = uiFrame;
        handler = new ActionHandler();
        putComponents();
    }

    /**
     * putting components on page
     */
    private void putComponents(){
        setLayout(null);
        setSize(720, 405);

        setting = new JButton("Setting");
        setting.setBounds(225,92, 250, 40);
        setting.setBackground(new Color(0, 0, 0, 206));
        setting.setForeground(new Color(227, 193, 119));
        setting.addActionListener(handler);
        add(setting);

        computerGame = new JButton("Computer Game");
        computerGame.setBounds(225,152, 250, 40);
        computerGame.setBackground(new Color(0, 0, 0, 206));
        computerGame.setForeground(new Color(227, 193, 119));
        computerGame.addActionListener(handler);
        add(computerGame);

        netWorkGame = new JButton("Network Game");
        netWorkGame.setBounds(225,212, 250, 40);
        netWorkGame.setBackground(new Color(0, 0, 0, 206));
        netWorkGame.setForeground(new Color(227, 193, 119));
        netWorkGame.addActionListener(handler);
        add(netWorkGame);

        exit = new JButton("Exit");
        exit.setBounds(225,272, 250, 40);
        exit.setBackground(new Color(0, 0, 0, 206));
        exit.setForeground(new Color(227, 193, 119));
        exit.addActionListener(handler);
        add(exit);
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
     * this class is for handling buttons events
     */
    private class ActionHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == setting) {
                uiFrame.getSettingPage().putComponents();
                uiFrame.setPageOfFrame(UIFrame.pages.setting);
            }
            if(e.getSource() == computerGame)
                uiFrame.setPageOfFrame(UIFrame.pages.computerGame);

            if(e.getSource() == netWorkGame)
                uiFrame.setPageOfFrame(UIFrame.pages.networkGame);


            if(e.getSource() == exit)
                uiFrame.setPageOfFrame(UIFrame.pages.login);

        }
    }
}

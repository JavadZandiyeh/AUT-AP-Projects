package GUI;

import Network.Account;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SettingPage extends JPanel {

    private UIFrame uiFrame;
    private ActionHandler handler;


    private JLabel timePlaying_Label;
    private JLabel timePlaying_Filed;

    private JLabel username_Label;
    private JLabel username_Field;

    private JLabel win_computer_Label;
    private JLabel win_computer_Field;
    private JLabel loss_computer_Label;
    private JLabel loss_computer_Field;

    private JLabel win_net_Label;
    private JLabel win_net_Field;
    private JLabel loss_net_Label;
    private JLabel loss_net_Field;

    private JComboBox tanks;
    private JLabel shapeOfTank;
//    private ImageIcon tank;


    private JLabel healthTank_Label;
    private JTextField healthTank_Field;

    private JLabel powerOfBalls_Label;
    private JTextField powerOfBalls_Field;

    private JLabel healthOfWalls_Label;
    private JTextField healthOfWalls_Field;

    private JButton mainMenuButton;

    public SettingPage(UIFrame uiFrame){
        this.uiFrame = uiFrame;
        handler = new ActionHandler();
        initComponents();
    }

    private void initComponents(){
        setLayout(null);
        setSize(720, 405);

        username_Label = new JLabel("Username");
        username_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        username_Label.setForeground(new Color(227, 193, 119));
        username_Label.setBounds(5, 20, 200, 20);
        add(username_Label);
        username_Field = new JLabel();
        username_Field.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        username_Field.setForeground(new Color(227, 107, 123));
        username_Field.setBounds(200, 20, 200, 20);
        add(username_Field);

        timePlaying_Label = new JLabel("Time of playing");
        timePlaying_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        timePlaying_Label.setForeground(new Color(227, 193, 119));
        timePlaying_Label.setBounds(5, 60, 200, 20);
        add(timePlaying_Label);
        timePlaying_Filed = new JLabel();
        timePlaying_Filed.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        timePlaying_Filed.setForeground(new Color(227, 107, 123));
        timePlaying_Filed.setBounds(200, 60, 200, 20);
        add(timePlaying_Filed);

        win_computer_Label = new JLabel("Win_computer game");
        win_computer_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        win_computer_Label.setForeground(new Color(227, 193, 119));
        win_computer_Label.setBounds(5, 100, 200, 20);
        add(win_computer_Label);
        win_computer_Field = new JLabel();
        win_computer_Field.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        win_computer_Field.setForeground(new Color(227, 107, 123));
        win_computer_Field.setBounds(200, 100, 200, 20);
        add(win_computer_Field);

        win_net_Label = new JLabel("Win_network game");
        win_net_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        win_net_Label.setForeground(new Color(227, 193, 119));
        win_net_Label.setBounds(5, 140, 200, 20);
        add(win_net_Label);
        win_net_Field = new JLabel();
        win_net_Field.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        win_net_Field.setForeground(new Color(227, 107, 123));
        win_net_Field.setBounds(200, 140, 200, 20);
        add(win_net_Field);

        loss_computer_Label = new JLabel("Loss_computer game");
        loss_computer_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        loss_computer_Label.setForeground(new Color(227, 193, 119));
        loss_computer_Label.setBounds(5, 180, 200, 20);
        add(loss_computer_Label);
        loss_computer_Field = new JLabel();
        loss_computer_Field.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        loss_computer_Field.setForeground(new Color(227, 107, 123));
        loss_computer_Field.setBounds(200, 180, 200, 20);
        add(loss_computer_Field);

        loss_net_Label = new JLabel("Loss_network game");
        loss_net_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        loss_net_Label.setForeground(new Color(227, 193, 119));
        loss_net_Label.setBounds(5, 220, 200, 20);
        add(loss_net_Label);
        loss_net_Field = new JLabel();
        loss_net_Field.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        loss_net_Field.setForeground(new Color(227, 107, 123));
        loss_net_Field.setBounds(200, 220, 200, 20);
        add(loss_net_Field);


        healthOfWalls_Label = new JLabel("Health of walls");
        healthOfWalls_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        healthOfWalls_Label.setForeground(new Color(227, 193, 119));
        healthOfWalls_Label.setBounds(400, 20, 100, 25);
        add(healthOfWalls_Label);
        healthOfWalls_Field = new JTextField();
        healthOfWalls_Field.setFont(new Font("Courier", Font.HANGING_BASELINE,12));
        healthOfWalls_Field.setForeground(new Color(227, 107, 123));
        healthOfWalls_Field.setBounds(600, 20, 100, 25);
        add(healthOfWalls_Field);

        healthTank_Label = new JLabel("Health of tanks");
        healthTank_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        healthTank_Label.setForeground(new Color(227, 193, 119));
        healthTank_Label.setBounds(400, 60, 100, 25);
        add(healthTank_Label);
        healthTank_Field = new JTextField();
        healthTank_Field.setFont(new Font("Courier", Font.HANGING_BASELINE,12));
        healthTank_Field.setForeground(new Color(227, 107, 123));
        healthTank_Field.setBounds(600, 60, 100, 25);
        add(healthTank_Field);


        powerOfBalls_Label = new JLabel("Power of balls");
        powerOfBalls_Label.setFont(new Font("Courier", Font.HANGING_BASELINE,14));
        powerOfBalls_Label.setForeground(new Color(227, 193, 119));
        powerOfBalls_Label.setBounds(400, 100, 100, 25);
        add(powerOfBalls_Label);
        powerOfBalls_Field = new JTextField();
        powerOfBalls_Field.setFont(new Font("Courier", Font.HANGING_BASELINE,12));
        powerOfBalls_Field.setForeground(new Color(227, 107, 123));
        powerOfBalls_Field.setBounds(600, 100, 100, 25);
        add(powerOfBalls_Field);


        tanks = new JComboBox();
        tanks.setBounds(550, 165, 100, 20);
        tanks.addItem("Green");
        tanks.addItem("Red");
        tanks.addItem("Sand");
        tanks.addItem("Blue");
        tanks.setSelectedItem("Green");
        tanks.addActionListener(handler);
        add(tanks);

        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setBounds(5,290, 120, 40);
        mainMenuButton.setBackground(new Color(0, 0, 0, 206));
        mainMenuButton.setForeground(new Color(227, 193, 119));
        mainMenuButton.addActionListener(handler);
        add(mainMenuButton);

        shapeOfTank = new JLabel();
        shapeOfTank.setBounds(480, 160, 50, 50);
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

    public void putComponents(){
        Account account = uiFrame.getCurrentAccount();
        username_Field.setText(account.getUsername() + "");
        timePlaying_Filed.setText(account.getTimeOfPlayingGame() + "");
        win_computer_Field.setText(account.getNumberOfWin_ComputerGame() + "");
        win_net_Field.setText(account.getNumberOfWin_ServerGame() + "");
        loss_computer_Field.setText(account.getNumberOfLoss_ComputerGame() + "");
        loss_net_Field.setText(account.getNumberOfLoss_ServerGame() + "");
        healthOfWalls_Field.setText(account.getHealthOfWalls() + "");
        healthTank_Field.setText(account.getHealthOfTanks() + "");
        powerOfBalls_Field.setText(account.getPowerOfBalls() + "");
        setShapeOfTank("Green");
        tanks.setSelectedItem("Green");
    }

    private void setShapeOfTank(String color){

        try {

            BufferedImage image;
            switch (color) {
                case "Red":  image = ImageIO.read(new File("./Tank_Red.png")); break;
                case "Sand": image = ImageIO.read(new File("./Tank_Sand.png")); break;
                case "Blue": image = ImageIO.read(new File("./Tank_Blue.png")); break;
                default: image = ImageIO.read(new File("./Tank_Green.png")); break;
            }

            File tank = new File(
                    "./tanks" + File.separator + uiFrame.getCurrentAccount().getPassword());
            ImageIO.write(image, "png", tank);
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance
                    (50, 50, BufferedImage.SCALE_SMOOTH));
            shapeOfTank.setIcon(imageIcon);
            shapeOfTank.setOpaque(true);
            shapeOfTank.setVisible(true);
        } catch (IOException ex) {
            uiFrame.showMessage(ex.getMessage());
        }
        add(shapeOfTank);
    }

    private class ActionHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == tanks)
                setShapeOfTank(Objects.requireNonNull(tanks.getSelectedItem()).toString());

            if(e.getSource() == mainMenuButton)
                uiFrame.setPageOfFrame(UIFrame.pages.main);

        }
    }
}

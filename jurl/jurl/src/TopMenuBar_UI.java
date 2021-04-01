import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TopMenuBar_UI extends JMenuBar {
    private UI ui;

    //Menus of MenuBar
    private JMenu application;
    private JMenu view;
    private JMenu help;

    //Items of Menus
    private JMenuItem option;
    private JMenuItem exit;
    private JMenuItem toggleFullScreen;
    private JMenuItem toggleSlider;
    private JMenuItem about;
    private JMenuItem insomniaHelp;

    //option menuItem, components
    private JPanel optionPanel;
    private JCheckBox follow_check;
    private JCheckBox tray_check;

    //handler
    private ActionHandler_Items actionHandler_items;

    /**
     * constructor of class to initialize components and add utilities
     */
    public TopMenuBar_UI(UI ui){
        this.ui = ui;
        actionHandler_items = new ActionHandler_Items();

        application = new JMenu("Application");
        view = new JMenu("View");
        help = new JMenu("Help");

        //adding menus to menu bar
        add(application);
        add(view);
        add(help);

        //creating menu items
        option = new JMenuItem("Option");
        exit = new JMenuItem("Exit");
        toggleFullScreen = new JMenuItem("Toggle Full Screen");
        toggleSlider = new JMenuItem("Toggle Slider");
        about = new JMenuItem("About");
        insomniaHelp = new JMenuItem("Insomnia Help");

        //adding menu items to menus
        application.add(option);
        application.add(exit);
        view.add(toggleFullScreen);
        view.add(toggleSlider);
        help.add(about);
        help.add(insomniaHelp);

        //set the list that will be shown after clicking option item
        setListForOption();
        setAcceleratorAndMnemonic();
        addActionListeners();
    }

    /**
     * setting the check list for option part
     */
    private void setListForOption() {
        //first line of list
        JLabel followRedirect = new JLabel("Follow Redirect");
        follow_check = new JCheckBox();
        follow_check.setToolTipText("follow redirect check");
        follow_check.setSelected(true);

        //second line of list
        JLabel systemTray = new JLabel("System Tray");
        tray_check = new JCheckBox();
        tray_check.setToolTipText("system tray check");
        tray_check.setSelected(true);

        //setting the lay out of panel
        optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(2, 2));

        //adding lines to panel
        optionPanel.add(followRedirect);
        optionPanel.add(follow_check);
        optionPanel.add(systemTray);
        optionPanel.add(tray_check);
    }

    /**
     * setting accelerator and mnemonic for components
     */
    private void setAcceleratorAndMnemonic(){
        application.setMnemonic('A');
        view.setMnemonic('V');
        help.setMnemonic('H');

        option.setMnemonic('O');
        option.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.VK_CONTROL));

        exit.setMnemonic('E');
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.VK_CONTROL));

        toggleFullScreen.setMnemonic('F');
        toggleFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.VK_CONTROL));

        toggleSlider.setMnemonic('S');
        toggleSlider.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.VK_CONTROL));

        about.setMnemonic('M');
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.VK_CONTROL));

        insomniaHelp.setMnemonic('I');
        insomniaHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.VK_CONTROL));
    }

    /**
     * adding action listeners for components
     */
    private void addActionListeners(){
        option.addActionListener(actionHandler_items);
        exit.addActionListener(actionHandler_items);
        toggleFullScreen.addActionListener(actionHandler_items);
        toggleSlider.addActionListener(actionHandler_items);
        about.addActionListener(actionHandler_items);
        insomniaHelp.addActionListener(actionHandler_items);
    }

    /**
     * this class if for handling events done on items
     */
    private class ActionHandler_Items implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem item = (JMenuItem)e.getSource();
            String text = item.getText();

            if(text.equals("Option")){
                JOptionPane optionPane = new JOptionPane(optionPanel,
                        JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
                        new Object[] {}, null);
                JDialog dialog = optionPane.createDialog("Options");
                dialog.setVisible(true);
            }

            if(text.equals("Toggle Full Screen")){
                if(ui.getExtendedState() == Frame.MAXIMIZED_BOTH)
                    ui.setExtendedState(JFrame.NORMAL);
                else
                    ui.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }

            if(text.equals("Toggle Slider")){
                if(ui.getPreviousRequests_ui().isVisible())
                    ui.getPreviousRequests_ui().setVisible(false);
                else
                    ui.getPreviousRequests_ui().setVisible(true);
            }

            if(text.equals("About")){
                String message = "Name : Mohammad Javad Zandiyeh\n" +
                        "Id : 9831032\nEmail : mjzandiyeh1379@aut.ac.ir\n";
                JOptionPane.showMessageDialog(null, message,
                        "About", JOptionPane.INFORMATION_MESSAGE);
            }

            if(text.equals("Insomnia Help")){
                JPanel helpPanel = new JPanel();
                StringBuilder htmlText = new StringBuilder();
                htmlText.append("<html>");
                for(char c: Manager.getHelp().toCharArray()) {
                    if (c == '\n')
                        htmlText.append("<br>");
                    else
                        htmlText.append(c);
                }
                htmlText.append("</html>");

                JLabel label = new JLabel(htmlText.toString());
                label.setFont(new Font("Arial", 10, 16));
                helpPanel.add(label);

                JOptionPane helpPane = new JOptionPane(helpPanel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION,
                        null, new Object[] {}, null);
                JDialog dialog = helpPane.createDialog("Help");
                dialog.setVisible(true);
            }

            if(text.equals("Exit")){
                if(tray_check.isSelected())
                    ui.setExtendedState(JFrame.ICONIFIED);
                else
                    System.exit(0);
            }
        }
    }

    /**
     * this method say that is check box of follow redirect selected or not
     * @return true for selected follow redirect
     */
    public boolean getFollow_check(){
        if(follow_check.isSelected())
            return true;
        else
            return false;
    }

    /**
     * when we open a request we have to set its follow redirect thus we made this method
     * @param follow_check boolean that say us is follow_check box must be selected or not
     */
    public void setFollow_check(boolean follow_check){
        this.follow_check.setSelected(follow_check);
    }
}

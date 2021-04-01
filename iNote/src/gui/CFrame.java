package gui;

import utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * this class is for gui designing of not pad
 * in this class we make the frame fo program and add some
 * sub panels on for init menu bar and list panel and also
 * text area panel
 */
public class CFrame extends JFrame implements ActionListener {

    private CMainPanel mainPanel;

    private JMenuItem newItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;


    /**
     * constructor of class for adding basic parts to program
     * @param title title of frame
     */
    public CFrame(String title) {
        super(title);
        initMenuBar(); //create menuBar
        initMainPanel(); //create main panel
    }

    /**
     * this method make and add menu items to our program
     */
    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu jmenu = new JMenu("File");

        newItem = new JMenuItem("New");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        newItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        jmenu.add(newItem);
        jmenu.add(saveItem);
        jmenu.add(exitItem);

        menuBar.add(jmenu);
        setJMenuBar(menuBar);
    }

    /**
     * in this method we add CMainPanel class to frame
     * witch has been made from text area and list
     */
    private void initMainPanel() {
        mainPanel = new CMainPanel();
        add(mainPanel);
    }

    /**
     * this is an override method of actionListener class
     * @param e is the action witch happen
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //here we find the menu item that has been clicked on
        //and do the suitable contact
        if (e.getSource() == newItem) {
            mainPanel.addNewTab();
        } else if (e.getSource() == saveItem) {
            mainPanel.saveNote();
        } else if (e.getSource() == exitItem) {
            //TODO: Phase1: check all tabs saved ...    :)
            mainPanel.saveAllNote();
            System.exit(0);
        } else {
            System.out.println("Nothing detected...");
        }
    }
}

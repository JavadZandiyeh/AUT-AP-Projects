package gui;

import utils.FileUtils;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * this class is for text and list part of program
 */
public class CMainPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JList<File> directoryList;

    /**
     * constructor
     */
    public CMainPanel() {
        setLayout(new BorderLayout());
        initDirectoryList(); // add JList to main Panel
        initTabbedPane(); // add TabbedPane to main panel
        addNewTab(); // open new empty tab when user open the application
    }

    /**
     * the text part is a tabbed pane witch has some text area panels
     */
    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * set design for list
     */
    private void initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory();
        directoryList = new JList<>(files);

        directoryList.setBackground(new Color(211, 211, 211));
        directoryList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        directoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        directoryList.setVisibleRowCount(-1);
        directoryList.setMinimumSize(new Dimension(130, 100));
        directoryList.setMaximumSize(new Dimension(130, 100));
        directoryList.setFixedCellWidth(130);
        directoryList.setCellRenderer(new MyCellRenderer());
        directoryList.addMouseListener(new MyMouseAdapter());

        add(new JScrollPane(directoryList), BorderLayout.WEST);
    }

    /**
     * adding new tab to text part
     */
    public void addNewTab() {
        JTextArea textPanel = createTextPanel();
        textPanel.setText("");
        tabbedPane.addTab("Tab " + (tabbedPane.getTabCount() + 1), textPanel);
    }

    /**
     * in this we open a tab for our text part
     * @param content content of note
     */
    public void openExistingNote(String content) {
        JTextArea existPanel = createTextPanel();
        existPanel.setText(content);

        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab("Tab " + tabIndex, existPanel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }

    /**
     * saving note to file in three ways
     */
    public void saveNote() {
        JTextArea textPanel = (JTextArea) tabbedPane.getSelectedComponent();
        String note = textPanel.getText();
        if (!note.isEmpty()) {

            //inputStream_base writing file
            //FileUtils.fileOutPutStream(note);

            //buffer_base writing file
            //FileUtils.fileWriter(note);

            //objectSerialization_base writing file
            FileUtils.objectOutputStream(note);

        }
        updateListGUI();
    }

    /**
     * saving all notes
     * in this class we use saveNote method
     */
    public void saveAllNote() {
        for (Component component: tabbedPane.getComponents()) {
            JTextArea textArea = (JTextArea) component;
            tabbedPane.setSelectedComponent(textArea);
            saveNote();
        }
    }

    /**
     * @return created text panel
     */
    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

    /**
     * after saving a text we have to update the ui to see
     * the saved note in our list
     */
    private void updateListGUI() {
        File[] newFiles = FileUtils.getFilesInDirectory();
        directoryList.setListData(newFiles);
    }

    /**
     * this method is for handling mouse events
     */
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                //we can understand witch of the list items has benn selected by
                //finding the location of mouse pointer
                int index = directoryList.locationToIndex(eve.getPoint());
                System.out.println("Item " + index + " is clicked...");

                //TODO: Phase1: Click on file is handled... Just load content into JTextArea   :)

                File[] curr = FileUtils.getFilesInDirectory();
                //we can read contents from files in buffering and input streaming way

                //buffer_base reading file
                //String content = FileUtils.fileReader(curr[index]);

                //inputStream_base reading file
                //String content = FileUtils.fileInputStream(curr[index]);

                //object_base reading file
                String content = FileUtils.objectInputStream(curr[index]);

                openExistingNote(content);
            }
        }
    }

    private class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
            if (object instanceof File) {
                File file = (File) object;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }
}

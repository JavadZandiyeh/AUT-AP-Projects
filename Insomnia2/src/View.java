import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

public class View{

    //.....................action handlers......................
    private ActionHandler_ComboBox actionHandler_comboBox = new ActionHandler_ComboBox();
    private ActionHandler_Items actionHandler_items = new ActionHandler_Items();
    private ActionHandler_Buttons actionHandler_buttons = new ActionHandler_Buttons();
    private ActionHandler_CheckBox actionHandler_checkBox = new ActionHandler_CheckBox();
    private TreeSelectionHandler selectionHandler_tree = new TreeSelectionHandler();

    //..................basic panels and frame..................

    private JFrame frame;
    private JPanel previousRequests;
    private JPanel newRequests;
    private JPanel response;
    private JMenuBar topMenuBar;

    //.....................new requests.........................

    //ComboBox beside url
    private JComboBox urlType_newRequest;
    //Text fields for url
    private JTextField url_newRequest;
    //Button of send request
    private JButton send_newRequest;
    //Button for save in a file
    private JButton save_newRequest;
    //file for writing saving events on
    private FileWriter file;

    //TabbedPane for new request panel
    private JTabbedPane tab_newRequest;
    //Panels for new request tab
    private JPanel body_newRequest;
    private JPanel header_newRequest;
    private JPanel query_newRequest;
    private JPanel auth_newRequest;

    //ComboBox for request body type
    private JComboBox messageType_newRequest;
    //body panel contents
    private JPanel formData_newRequest;
    private JTextArea json_newRequest;
    private JPanel binaryData_newRequest;

    //Text for binary file
    private JTextField filePath_newRequest;
    //Button for file choose
    private JButton chooseFile_newRequest;
    //Button for reset file
    private JButton resetFile_newRequest;

    //Button for make a new header
    private JButton newHeader_newRequest;
    //Button for make a new query
    private JButton newQuery_newRequest;

    //array list for headers
        private ArrayList<JPanel> headers_panels_newRequest;

    //.....................previous requests...............................

    //Tree for previous requests
    private JTree tree_previousRequest;
    private JScrollPane treeScroll_previousRequest;
    private DefaultMutableTreeNode Method_TreeHead_previousRequest;
    private DefaultMutableTreeNode GET_TreeNode_previousRequest;
    private DefaultMutableTreeNode DELETE_TreeNode_previousRequest;
    private DefaultMutableTreeNode POST_TreeNode_previousRequest;
    private DefaultMutableTreeNode PATCH_TreeNode_previousRequest;
    private DefaultMutableTreeNode PUT_TreeNode_previousRequest;

    //............................Menu bar...................................

    //Menus of top menu bar
    private JMenu application_topMenuBar;
    private JMenu view_topMenuBar;
    private JMenu help_topMenuBar;

    //Items of menu bar
    private JMenuItem option_topMenuBar;
    private JMenuItem exit_topMenuBar;
    private JMenuItem toggleFullScreen_topMenuBar;
    private JMenuItem toggleSlider_topMenuBar;
    private JMenuItem about_topMenuBar;
    private JMenuItem insomniaHelp_topMenuBar;

    //panel for option chose in menu bar
    private JPanel optionPanel = new JPanel();
    //option parts
    private JLabel followRedirect;
    private JCheckBox follow_check;
    private JLabel systemTray;
    private JCheckBox tray_check;


    //................................response panel...........................

    //Top label of response panel
    private JPanel statusPanel_response;
    private JLabel statusMessage_response;
    private JLabel statusTime_response;
    private JLabel statusDataVolume_response;

    //TabbedPane for response panel
    private JTabbedPane tab_response;
    private JPanel body_response;
    private JPanel header_response;

    //Header Name and value fields
    private JTable nameAndValueTable_response;
    private String[] tableColumns_response = new String[] { "NAME", "VALUE"};

    //actual data for the table in a 2d array
    private Object[][] headerData_response = new Object[][] {
            {"Server", "hello"},
            {"bye", "Rambo"},
            {"good", "Zorro"},
    };
    //Button for coping text key and values
    private JButton copyToClipboard_response;
    //Response body comboBox
    private JComboBox bodyType_response;
    //panels for body of response
    private JPanel Raw_response;
    private JPanel Preview_response;
    private JPanel Json_response;

    //..............................Objects for inner classes......................

    private TopMenuBar topMenuBar_Class;
    private PreviousRequests previousRequests_Class;
    private NewRequests newRequests_Class;
    private Response response_Class;


    /**
     * this is the constructor of gui part of project
     * in this class we make a frame and add some other
     * this to it with calling other inner classes
     */
    public View(){
        //basic panels and frame
        frame = new JFrame();
        previousRequests = new JPanel();
        newRequests = new JPanel();
        response = new JPanel();
        topMenuBar = new JMenuBar();

        //setting frame
        frame.setTitle("Insomnia");
        frame.setBounds(130,100,1100,500);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        frame.add(previousRequests, BorderLayout.WEST);
        frame.add(newRequests, BorderLayout.CENTER);
        frame.add(response, BorderLayout.EAST);
        frame.add(topMenuBar, BorderLayout.NORTH);

        //initializing class objects
        topMenuBar_Class = new TopMenuBar();
        previousRequests_Class = new PreviousRequests();
        newRequests_Class = new NewRequests();
        response_Class = new Response();

        //adding listeners for components
        addActionListeners_comboBoxes();
        addActionListeners_items();
        addActionListeners_buttons();
        addActionListeners_checkBoxes();
        setTreeSelectionHandler();


        //mnemonic and accelerator for components
        addingMnemonic_Accelerators();


        //showing frame
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //......................classes for basic parts of app....................

    /**
     * this is an inner classes for top menu bar
     * we make the items and add their properties
     * at some other methods of this class
     */
    private class TopMenuBar {

        /**
         * in the constructor we make the basic of menu bar
         * and items and by calling other methods we complete
         * this menu part
         */
        public TopMenuBar(){
            //the menus of menu bar
            application_topMenuBar = new JMenu("Application");
            view_topMenuBar = new JMenu("View");
            help_topMenuBar = new JMenu("Help");

            //adding menus to menu bar
            topMenuBar.add(application_topMenuBar);
            topMenuBar.add(view_topMenuBar);
            topMenuBar.add(help_topMenuBar);

            //creating menu items
            option_topMenuBar = new JMenuItem("Option");
            exit_topMenuBar = new JMenuItem("Exit");
            toggleFullScreen_topMenuBar = new JMenuItem("Toggle Full Screen");
            toggleSlider_topMenuBar = new JMenuItem("Toggle Slider");
            about_topMenuBar = new JMenuItem("About");
            insomniaHelp_topMenuBar = new JMenuItem("Insomnia Help");

            //adding menu items to menus
            application_topMenuBar.add(option_topMenuBar);
            application_topMenuBar.add(exit_topMenuBar);
            view_topMenuBar.add(toggleFullScreen_topMenuBar);
            view_topMenuBar.add(toggleSlider_topMenuBar);
            help_topMenuBar.add(about_topMenuBar);
            help_topMenuBar.add(insomniaHelp_topMenuBar);

            //set the list that will be shown after clicking option item
            setListForOption();
        }

        /**
         * this class implements the list for option item of menu bar
         */
        private void setListForOption() {
            //first line of list
            followRedirect = new JLabel("Follow Redirect");
            follow_check = new JCheckBox();
            follow_check.setToolTipText("follow redirect check");

            //second line of list
            systemTray = new JLabel("System Tray");
            tray_check = new JCheckBox();
            tray_check.setToolTipText("system tray check");

            //setting the lay out of panel
            optionPanel.setLayout(new GridLayout(2, 2));

            //adding lines to panel
            optionPanel.add(followRedirect);
            optionPanel.add(follow_check);
            optionPanel.add(systemTray);
            optionPanel.add(tray_check);

        }
    }

    /**
     * this is a inner class for left part of frame
     * in this class we have a tree and some tree nodes
     * that whenever we add a request it will shown in its part
     */
    private class PreviousRequests {

        /**
         *constructor of class
         * here we make the tree head and tree nodes
         */
        public PreviousRequests() {
            //making tree head and some tree nodes
            Method_TreeHead_previousRequest = new DefaultMutableTreeNode("Method");
            GET_TreeNode_previousRequest = new DefaultMutableTreeNode("GET");
            DELETE_TreeNode_previousRequest = new DefaultMutableTreeNode("DELETE");
            POST_TreeNode_previousRequest = new DefaultMutableTreeNode("POST");
            PATCH_TreeNode_previousRequest = new DefaultMutableTreeNode("PATCH");
            PUT_TreeNode_previousRequest = new DefaultMutableTreeNode("PUT");

            //adding tree nodes to tree head
            Method_TreeHead_previousRequest.add(GET_TreeNode_previousRequest);
            Method_TreeHead_previousRequest.add(DELETE_TreeNode_previousRequest);
            Method_TreeHead_previousRequest.add(POST_TreeNode_previousRequest);
            Method_TreeHead_previousRequest.add(PATCH_TreeNode_previousRequest);
            Method_TreeHead_previousRequest.add(PUT_TreeNode_previousRequest);

            //adding tree head to a Tree
            tree_previousRequest = new JTree(Method_TreeHead_previousRequest);
            tree_previousRequest.setRootVisible(false);

            //making a scroll for tree part
            treeScroll_previousRequest = new JScrollPane(tree_previousRequest);
            treeScroll_previousRequest.setPreferredSize(new Dimension(180, 430));

            //adding the scroll to left panel
            previousRequests.add(treeScroll_previousRequest);
        }
    }

    /**
     * this is a class for central part of app ui
     * in this part we handle the new requests
     */
    private class NewRequests {

        /**
         * constructor of class
         * form here we call other methods for completing part
         */
        public NewRequests() {
            newRequests.setLayout(new BorderLayout());
            setTop();
            setCenter();
        }

        /**
         * by this method we complete the top part this panel
         * mean: url comboBox and url text and save and send buttons
         */
        private void setTop() {

            //creating and adding methods to url type combo box
            urlType_newRequest = new JComboBox();

            urlType_newRequest.addItem("GET");
            urlType_newRequest.addItem("DELETE");
            urlType_newRequest.addItem("POST");
            urlType_newRequest.addItem("PATCH");
            urlType_newRequest.addItem("PUT");


            //create and set size of url address part
            url_newRequest = new JTextField();
            url_newRequest.setPreferredSize(new Dimension(270, 27));

            //send button
            send_newRequest = new JButton("Send");

            //save button
            save_newRequest = new JButton("Save");
//            try {
//                file = new FileWriter("Saving File.txt", true);
//            }catch (IOException ex){
//                JOptionPane.showMessageDialog(null, ex, "File problem", JOptionPane.ERROR_MESSAGE);
//            }

            //making a panel and adding components to it
            JPanel topPanel = new JPanel();
            topPanel.setPreferredSize(new Dimension(100, 35));
            topPanel.add(urlType_newRequest);
            topPanel.add(url_newRequest);
            topPanel.add(send_newRequest);
            topPanel.add(save_newRequest);

            //add the panel to the top of new request panel
            newRequests.add(topPanel, BorderLayout.NORTH);
        }

//        private void writingToFile(String message) {
//            try{
//                file.write((message + "\r\n"));
//            }catch (IOException ex){
//                JOptionPane.showMessageDialog(null, ex, "File problem", JOptionPane.ERROR_MESSAGE);
//            }
//        }

        /**
         * this method is for center of new request panel
         */
        private void setCenter() {
            //creating a tabbedPane
            tab_newRequest = new JTabbedPane();
            newRequests.add(tab_newRequest, BorderLayout.CENTER);

            body_newRequest = new JPanel();
            header_newRequest = new JPanel();
            query_newRequest = new JPanel();
            auth_newRequest = new JPanel();

            //adding panels to tabbedPane
            tab_newRequest.add("Body", body_newRequest);
            tab_newRequest.add("Header", header_newRequest);
            tab_newRequest.add("Query", query_newRequest);
            tab_newRequest.add("Auth", auth_newRequest);

            //set parts of this panels
            setBody();
            setHeader();
            setQuery();
        }

        /**
         * this method set the body of new requests
         */
        private void setBody(){
            //set the lay out of new request
            body_newRequest.setLayout(new BorderLayout());

            //adding a items and combo box to body part
            messageType_newRequest = new JComboBox();
            messageType_newRequest.addItem("Json");
            messageType_newRequest.addItem("Form Data");
            messageType_newRequest.addItem("Binary Data");
            body_newRequest.add(messageType_newRequest, BorderLayout.NORTH);

            //set parts of body
            setBinaryData();
            setFormData();
            setJson();

        }

        /**
         * binary part of body
         * in this class we chose binary data
         */
        private void setBinaryData(){

            binaryData_newRequest = new JPanel();
            binaryData_newRequest.setLayout(new BorderLayout());


            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 1));

            //label for top of file choosing and text for showing file path
            JLabel label = new JLabel("SELECTED FILE");
            filePath_newRequest = new JTextField();

            panel.add(label);
            panel.add(filePath_newRequest);

            binaryData_newRequest.add(panel, BorderLayout.NORTH);

            JPanel fileWorks = new JPanel();
            //making buttons for choosing and reset files
            chooseFile_newRequest = new JButton("Choose File");
            resetFile_newRequest = new JButton("Reset File");
            fileWorks.add(resetFile_newRequest);
            fileWorks.add(chooseFile_newRequest);

            binaryData_newRequest.add(fileWorks, BorderLayout.CENTER);
        }

        /**
         * this method is for form data part of body
         */
        private void setFormData() {
            formData_newRequest = new JPanel();
        }

        /**
         * this method is for set the json part of body
         */
        private void setJson() {
            json_newRequest = new JTextArea();
            json_newRequest.setBackground(new Color(1,1,1,100));
        }

        /**
         * @return panel for adding on header or query part
         **/
        private JPanel addingNewQueryOrHeader() {
            //Text fields for header
            JTextField keyText = new JTextField("");
            JTextField valueText = new JTextField("");
            //CheckBox for key and value of header visibility
            JCheckBox checkHeader = new JCheckBox("check", true);
            checkHeader.addActionListener(actionHandler_checkBox);
            //delete header
            JButton deleteHeader = new JButton("Delete");
            deleteHeader.setToolTipText("Delete");
            try {
                Image img = ImageIO.read(getClass().getResource("./trash.png"));
                deleteHeader.setIcon(new ImageIcon(img));
            }catch (Exception e){
                    System.out.println(e);
            }
            deleteHeader.addActionListener(actionHandler_buttons);

            keyText.setPreferredSize(new Dimension(180, 30));
            valueText.setPreferredSize(new Dimension(180, 30));
            checkHeader.setPreferredSize(new Dimension(20, 20));
            deleteHeader.setPreferredSize(new Dimension(35, 30));

            JPanel panel = new JPanel();

            panel.add(keyText);
            panel.add(valueText);
            panel.add(checkHeader);
            panel.add(deleteHeader);

            panel.setFocusable(true);
            headers_panels_newRequest.add(panel);
            return panel;
        }

        /**
         * header part of new request panel
         */
        private void setHeader() {
            newHeader_newRequest = new JButton("New Header");
            header_newRequest.add(newHeader_newRequest, BorderLayout.NORTH);
            headers_panels_newRequest = new ArrayList<JPanel>();

            JPanel panel = addingNewQueryOrHeader();
            header_newRequest.add(panel);
            header_newRequest.updateUI();
        }

        /**
         * query part of new request panel
         */
        private void setQuery() {
            newQuery_newRequest = new JButton("New Query");
            query_newRequest.add(newQuery_newRequest, BorderLayout.NORTH);
            query_newRequest.add(addingNewQueryOrHeader());
        }

    }

    /**
     * this class is for response part of app
     */
    private class Response {

        /**
         * constructor for response part
         */
        public Response() {
            setStatus();
            setTab();
        }

        /**
         * this method is for showing the status of time and volume and code
         * in this method we make tree labels and add them to a new panel
         * and add that panel to the north of response part
         */
        private void setStatus(){
            statusPanel_response = new JPanel();
            statusPanel_response.setPreferredSize(new Dimension(400, 35));
            statusPanel_response.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

            statusMessage_response = new JLabel("200 OK");
            statusTime_response = new JLabel("1.45 s");
            statusDataVolume_response = new JLabel("1.32 KB");

            statusMessage_response.setOpaque(true);
            statusMessage_response.setBackground(new Color(106, 204, 75));

            statusTime_response.setOpaque(true);
            statusTime_response.setBackground(new Color(1, 1, 1, 25));

            statusDataVolume_response.setOpaque(true);
            statusDataVolume_response.setBackground(new Color(1, 1, 1, 25));

            statusPanel_response.add(statusMessage_response);
            statusPanel_response.add(statusTime_response);
            statusPanel_response.add(statusDataVolume_response);

            response.setPreferredSize(new Dimension(400, 500));
            response.setLayout(new BorderLayout());
            response.add(statusPanel_response, BorderLayout.NORTH);
        }

        /**
         * setting the tabs of response part
         * we have two tabs named body and header
         */
        private void setTab(){
            tab_response = new JTabbedPane();
            body_response = new JPanel();
            header_response = new JPanel();

            //add the tabs two the tabbedPane
            tab_response.add("Body", body_response);
            tab_response.add("Header", header_response);

            response.add(tab_response, BorderLayout.CENTER);

            setHeader();
            setBody();
        }

        /**
         * setting the header part of response tab
         */
        private void setHeader(){
            //this is list for header part witch the first column of it is name and
            //second column of it is value
            final Class[] columnClass = new Class[]{ String.class, String.class};
            DefaultTableModel model = new DefaultTableModel(headerData_response, tableColumns_response) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnClass[columnIndex];
                }
            };

            nameAndValueTable_response = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(nameAndValueTable_response);
            scrollPane.setPreferredSize(new Dimension(400, 350));

            //this is a button for adding the the list text to clipboard
            copyToClipboard_response = new JButton("Copy to Clipboard");

            header_response.setLayout(new BorderLayout());

            header_response.add(scrollPane, BorderLayout.NORTH);
            header_response.add(copyToClipboard_response, BorderLayout.SOUTH);
        }

        /**
         * set the body of response part
         */
        private void setBody(){
            bodyType_response = new JComboBox();

            bodyType_response.addItem("Raw");
            bodyType_response.addItem("Preview");
            bodyType_response.addItem("Json");

            body_response.setLayout(new BorderLayout());
            body_response.add(bodyType_response, BorderLayout.NORTH);
        }
    }

    //......................accelerator and mnemonic for components...............

    /**
     * this method add some accelerators and mnemonics to buttons
     * add items and etc
     */
    private void addingMnemonic_Accelerators(){

        //top menu bar
        application_topMenuBar.setMnemonic('A');
        option_topMenuBar.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_O, KeyEvent.VK_CONTROL));
        exit_topMenuBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.VK_CONTROL));

        view_topMenuBar.setMnemonic('V');
        toggleSlider_topMenuBar.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_T, KeyEvent.VK_CONTROL));
        toggleFullScreen_topMenuBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.VK_CONTROL));

        help_topMenuBar.setMnemonic('H');
        about_topMenuBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.VK_CONTROL));
        insomniaHelp_topMenuBar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.VK_CONTROL));

        //new requests
        send_newRequest.setMnemonic('P');
        save_newRequest.setMnemonic('S');

        //response
        copyToClipboard_response.setMnemonic('C');
    }

    //.............................adding action listeners.......................

    /**
     * adding action handler for items
     */
    private void addActionListeners_items(){
        option_topMenuBar.addActionListener(actionHandler_items);
        exit_topMenuBar.addActionListener(actionHandler_items);
        toggleFullScreen_topMenuBar.addActionListener(actionHandler_items);
        toggleSlider_topMenuBar.addActionListener(actionHandler_items);
        about_topMenuBar.addActionListener(actionHandler_items);
        insomniaHelp_topMenuBar.addActionListener(actionHandler_items);
    }

    /**
     * adding action handler for comboBoxes
     */
    private void addActionListeners_comboBoxes(){
        urlType_newRequest.addActionListener(actionHandler_comboBox);
        messageType_newRequest.addActionListener(actionHandler_comboBox);
    }

    /**
     * adding action handler for buttons
     */
    private void addActionListeners_buttons(){
        send_newRequest.addActionListener(actionHandler_buttons);
        save_newRequest.addActionListener(actionHandler_buttons);
        newHeader_newRequest.addActionListener(actionHandler_buttons);
        newQuery_newRequest.addActionListener(actionHandler_buttons);
        copyToClipboard_response.addActionListener(actionHandler_buttons);
        chooseFile_newRequest.addActionListener(actionHandler_buttons);
        resetFile_newRequest.addActionListener(actionHandler_buttons);
        save_newRequest.addActionListener(actionHandler_buttons);
    }

    /**
     * adding action handler for checkBoxes
     */
    private void addActionListeners_checkBoxes(){}

    /**
     * adding selection handler for trees
     */
    private void setTreeSelectionHandler(){
        tree_previousRequest.getSelectionModel().addTreeSelectionListener(selectionHandler_tree);
    }


    //...........................handling actions..............................

    /**
     * this class is for handling combBoxes
     */
    private class ActionHandler_ComboBox implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox) e.getSource();
//            newRequests_Class.writingToFile(comboBox.getSelectedItem().toString());

            if (comboBox.getSelectedItem().equals("Form Data")) {
                body_newRequest.add(formData_newRequest, BorderLayout.CENTER);
                formData_newRequest.setVisible(true);
                json_newRequest.setVisible(false);
                binaryData_newRequest.setVisible(false);
            }
            if (comboBox.getSelectedItem().equals("Json")) {
                body_newRequest.add(json_newRequest, BorderLayout.CENTER);
                formData_newRequest.setVisible(false);
                json_newRequest.setVisible(true);
                binaryData_newRequest.setVisible(false);
            }
            if (comboBox.getSelectedItem().equals("Binary Data")) {
                body_newRequest.add(binaryData_newRequest, BorderLayout.CENTER);
                formData_newRequest.setVisible(false);
                json_newRequest.setVisible(false);
                binaryData_newRequest.setVisible(true);
            }
            body_newRequest.updateUI();
        }
    }

    /**
     * this class is for handling the actions for items of menu
     */
    private class ActionHandler_Items implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem menuItem = (JMenuItem)e.getSource();
            String content = menuItem.getText();
//            newRequests_Class.writingToFile(menuItem.getText());

            if(content.equals("Option")){
                JOptionPane optionPane = new JOptionPane(optionPanel,
                        JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
                        new Object[] {}, null);
                JDialog dialog = optionPane.createDialog("Options");
                dialog.setVisible(true);
            }

            if(content.equals("Exit")) {
                if(tray_check.isSelected())
                    frame.setExtendedState(JFrame.ICONIFIED);
                else {
                    try {
                        file.close();
                        System.exit(0);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
            if(content.equals("Toggle Slider")) {
                if(previousRequests.isVisible())
                    previousRequests.setVisible(false);
                else
                    previousRequests.setVisible(true);
            }

            if(content.equals("Toggle Full Screen")){

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

                if(frame.getSize().equals(screenSize)) {
                    frame.setLocation(130, 100);
                    frame.setSize(1100,500); // To minimize a frame
                } else {
                    frame.setLocation(0 , 0);
                    frame.setSize(screenSize);
                }
            }

            if(content.equals("About")){
                String message = "Name : Mohammad Javad Zandiyeh\nId : 9831032\nEmail : mjzandiyeh1379@aut.ac.ir\n";
                JOptionPane.showMessageDialog(null, message, "About", JOptionPane.INFORMATION_MESSAGE);
            }

            if(content.equals("Insomnia Help")){
                JOptionPane.showMessageDialog(null, "will completed nest faze");
            }
        }
    }

    /**
     * this class is for handling action events of buttons
     */
    private class ActionHandler_Buttons implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
//            newRequests_Class.writingToFile(button.getText());
            if(button.getText().equals("New Header"))
                header_newRequest.add(newRequests_Class.addingNewQueryOrHeader());
            if(button.getText().equals("New Query"))
                query_newRequest.add(newRequests_Class.addingNewQueryOrHeader());
            if(button.getText().equals("Copy to Clipboard")){
                String a = "";
                for(int i=0; i<headerData_response.length; i++)
                    a += (headerData_response[i][0] + " " + headerData_response[i][1] + "\n");

                StringSelection stringSelection = new StringSelection (a);
                Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
                clpbrd.setContents (stringSelection, null);
            }
            if(button.getText().equals("Choose File")){
                JFrame choseFileFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(choseFileFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath_newRequest.setText(selectedFile.getAbsolutePath());
                }
            }
            if(button.getText().equals("Reset File"))
                filePath_newRequest.setText("");
            if(button.getText().equals("Save")){
                try {
                    file.write(("content of json:\r\n" + json_newRequest.getText()));
                    file.write(("url: " + url_newRequest.getText()));
                }catch (IOException ex){
                    JOptionPane.showMessageDialog(null, ex
                            , "File problem", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(button.getText().equals("Delete")){
                Enumeration enumeration = new Vector(headers_panels_newRequest).elements();
                while (enumeration.hasMoreElements()) {
                    JPanel panel = (JPanel) enumeration.nextElement();
                    if(panel.getComponent(3).equals(button)){
                        headers_panels_newRequest.remove(panel);
                        panel.setVisible(false);
                    }
                }
            }
        }
    }

    /**
     * this class is for handling actions for check boxes
     */
    private class ActionHandler_CheckBox implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox)e.getSource();

            if(checkBox.getText().equals("check")) {
                Enumeration enumeration = new Vector(headers_panels_newRequest).elements();
                while (enumeration.hasMoreElements()) {
                    JPanel panel = (JPanel) enumeration.nextElement();
                    if (panel.getComponent(2).equals(checkBox)) {
                        if(checkBox.isSelected()){
                            panel.getComponent(0).setEnabled(true);
                            panel.getComponent(1).setEnabled(true);
                        }else {
                            panel.getComponent(0).setEnabled(false);
                            panel.getComponent(1).setEnabled(false);
                        }
                    }
                }
            }

        }
    }

    /**
     * tihs class is for handing tree node part
     */
    private class TreeSelectionHandler implements TreeSelectionListener{

        @Override
        public void valueChanged(TreeSelectionEvent e) {
//            newRequests_Class.writingToFile(e.getPath().toString());

//            System.out.println(e.getPath().toString());
        }
    }

    //notice that some parts of this class are not completed because we need some
    //more details that will gain in second and third faze of project

}


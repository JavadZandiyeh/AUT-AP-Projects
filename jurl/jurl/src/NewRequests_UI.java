import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

public class NewRequests_UI extends JPanel {
    private UI ui;

    /*url and method*/
    //ComboBox beside url
    private JComboBox urlType;
    //Text fields for url
    private JTextField url;
    //Button of send request
    private JButton send;
    //Button for save in a file
    private JButton save;


    /**tabs*/
    //TabbedPane for new request panel
    private JTabbedPane tab;
    //Panels for new request tab
    private JPanel body;
    private JPanel header;
    private JPanel query;


    /*message body*/
    //ComboBox for request body type
    private JComboBox messageType;
    //body panel contents
    private JPanel formData;
    private JTextArea json;
    private JPanel binaryData;


    /*binary message body*/
    //Text for binary file
    private JTextField filePath;
    //Button for file choose
    private JButton chooseFile;
    //Button for reset file
    private JButton resetFile;


    /*form data message body*/
    //keys and values of form data
    private ArrayList<JPanel> formData_panels;
    //button for new map making
    private JButton newForm;
    //map of form data
    private JPanel form_map;


    /*headers*/
    //array list for headers
    private ArrayList<JPanel> headers_panels;
    //Button for make a new header
    private JButton newHeader;
    //panel for headers
    private JPanel header_map;


    /*query*/
    //Button for make a new query
    private JButton newQuery;
    //array list for queries
    private ArrayList<JPanel> queries_panels;
    //panel for queries
    private JPanel query_map;


    GridBagConstraints gbc = new GridBagConstraints();

    //action listeners
    private actionHandler_comboBox actionHandler_comboBox;
    private actionHandler_buttons actionHandler_buttons;
    private actionHandler_checkBox actionHandler_checkBox;

    //----------------------------view part methods-------------------------------
    /**
     * constructor of class
     * form here we call other methods for completing part
     */
    public NewRequests_UI(UI ui) {
        this.ui = ui;
        setLayout(new BorderLayout());

        actionHandler_checkBox = new actionHandler_checkBox();
        actionHandler_buttons = new actionHandler_buttons();
        actionHandler_comboBox = new actionHandler_comboBox();

        setTop();
        setCenter();
    }

    /**
     * by this method we complete the top part this panel
     * mean: url comboBox and url text and save and send buttons
     */
    private void setTop() {

        //creating and adding methods to url type combo box
        urlType = new JComboBox();
        urlType.addItem("GET");
        urlType.addItem("DELETE");
        urlType.addItem("POST");
        urlType.addItem("PATCH");
        urlType.addItem("PUT");


        //create and set size of url address part
        url = new JTextField("");
        url.setPreferredSize(new Dimension(220, 27));

        //send button
        send = new JButton("Send");
        send.addActionListener(actionHandler_buttons);

        //save button
        save = new JButton("Save");
        save.addActionListener(actionHandler_buttons);

        //making a panel and adding components to it
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(500, 35));
        topPanel.add(urlType);
        topPanel.add(url);
        topPanel.add(send);
        topPanel.add(save);

        //add the panel to the top of new request panel
        add(topPanel, BorderLayout.NORTH);
    }

    /**
     * this method is for center of new request panel
     */
    private void setCenter() {
        //creating a tabbedPane
        tab = new JTabbedPane();
        add(tab, BorderLayout.CENTER);

        body = new JPanel();
        header = new JPanel();
        query = new JPanel();

        //adding panels to tabbedPane
        tab.add("Body", body);
        tab.add("Header", header);
        tab.add("Query", query);

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
        body.setLayout(new BorderLayout());

        //adding a items and combo box to body part
        messageType = new JComboBox();
        messageType.addItem("Json");
        messageType.addItem("Form Data");
        messageType.addItem("Binary Data");
        messageType.addActionListener(actionHandler_comboBox);
        body.add(messageType, BorderLayout.NORTH);


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
        binaryData = new JPanel();
        binaryData.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        //label for top of file choosing and text for showing file path
        JLabel label = new JLabel("SELECTED FILE");
        filePath = new JTextField();

        panel.add(label);
        panel.add(filePath);

        binaryData.add(panel, BorderLayout.NORTH);

        JPanel fileWorks = new JPanel();
        //making buttons for choosing and reset files
        chooseFile = new JButton("Choose File");
        chooseFile.addActionListener(actionHandler_buttons);
        resetFile = new JButton("Reset File");
        resetFile.addActionListener(actionHandler_buttons);
        fileWorks.add(resetFile);
        fileWorks.add(chooseFile);

        binaryData.add(fileWorks, BorderLayout.CENTER);
    }

    /**
     * this method is for form data part of body
     */
    private void setFormData() {
        formData = new JPanel();
        formData.setLayout(new BorderLayout());

        newForm = new JButton("New Form");

        formData.add(newForm, BorderLayout.NORTH);
        newForm.addActionListener(actionHandler_buttons);
        formData_panels = new ArrayList<JPanel>();

        form_map = new JPanel();
        form_map.setLayout(new GridBagLayout());
        form_map.setName("form_map");
        JScrollPane scrollPane = new JScrollPane(form_map,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setWheelScrollingEnabled(true);
        formData.add(scrollPane);
    }

    /**
     * this method is for set the json part of body
     */
    private void setJson() {
        json = new JTextArea();
        json.setBackground(new Color(1,1,1, 40));
    }

    /**
     * @return panel for adding on header or query part
     **/
    private JPanel getNewQueryOrHeaderOrForm() {
        //panel of this components
        JPanel panel = new JPanel();


        //key and value
        JTextField keyText = new JTextField("");
        JTextField valueText = new JTextField("");
        keyText.setPreferredSize(new Dimension(180, 30));
        valueText.setPreferredSize(new Dimension(180, 30));
        panel.add(keyText);
        panel.add(valueText);


        //CheckBox for key and value visibility
        JCheckBox checkHeader = new JCheckBox("check", true);
        checkHeader.addActionListener(actionHandler_checkBox);
        checkHeader.setPreferredSize(new Dimension(20, 20));
        panel.add(checkHeader);


        //delete panel
        JButton deleteHeader = new JButton("");
        deleteHeader.setToolTipText("Delete");
        try {
            Image img = ImageIO.read(getClass().getResource("./trash.png"));
            deleteHeader.setIcon(new ImageIcon(img));
        }catch (Exception e){
            e.printStackTrace();
        }
        deleteHeader.addActionListener(actionHandler_buttons);
        deleteHeader.setPreferredSize(new Dimension(35, 30));
        panel.add(deleteHeader);


        panel.setFocusable(true);
        return panel;
    }

    /**
     * header part of new request panel
     */
    private void setHeader() {
        header.setLayout(new BorderLayout());

        newHeader = new JButton("New Header");
        newHeader.addActionListener(actionHandler_buttons);
        header.add(newHeader, BorderLayout.NORTH);
        headers_panels = new ArrayList<JPanel>();

        header_map = new JPanel();
        header_map.setLayout(new GridBagLayout());
        header_map.setName("header_map");
        JScrollPane scrollPane = new JScrollPane(header_map,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setWheelScrollingEnabled(true);
        header.add(scrollPane);
    }

    /**
     * query part of new request panel
     */
    private void setQuery() {
        query.setLayout(new BorderLayout());

        newQuery = new JButton("New Query");
        newQuery.addActionListener(actionHandler_buttons);
        query.add(newQuery, BorderLayout.NORTH);
        queries_panels = new ArrayList<JPanel>();

        query_map = new JPanel();
        query_map.setLayout(new GridBagLayout());
        query_map.setName("query_map");
        JScrollPane scrollPane = new JScrollPane(query_map,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setWheelScrollingEnabled(true);
        query.add(scrollPane);
    }
    //----------------------------------------------------------------------------


    //----------------------------------handlers----------------------------------
    /**
     * action handler for comboBoxes
     */
    private class actionHandler_comboBox implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox) e.getSource();

            if (comboBox.getSelectedItem().equals("Form Data")) {
                body.add(formData, BorderLayout.CENTER);
                formData.setVisible(true);
                json.setVisible(false);
                binaryData.setVisible(false);
            }

            if (comboBox.getSelectedItem().equals("Json")) {
                body.add(json, BorderLayout.CENTER);
                formData.setVisible(false);
                json.setVisible(true);
                binaryData.setVisible(false);
            }

            if (comboBox.getSelectedItem().equals("Binary Data")) {
                body.add(binaryData, BorderLayout.CENTER);
                formData.setVisible(false);
                json.setVisible(false);
                binaryData.setVisible(true);
            }

            body.updateUI();
        }
    }

    /**
     * action handler for buttons
     */
    private class actionHandler_buttons implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();

            if(button.getText().equals("Send")){
                ui.sendRequest();
            }

            if(button.getText().equals("Save")){
                ui.saveRequest();
            }

            if(button.getText().equals("New Form")){
                gbc.gridwidth = GridBagConstraints.REMAINDER;

                JPanel panel = getNewQueryOrHeaderOrForm();
                form_map.add(panel, gbc);
                formData.updateUI();
                formData_panels.add(panel);
            }

            if(button.getText().equals("New Header")){
                gbc.gridwidth = GridBagConstraints.REMAINDER;

                JPanel panel = getNewQueryOrHeaderOrForm();
                header_map.add(panel, gbc);
                header.updateUI();
                headers_panels.add(panel);
            }

            if(button.getText().equals("New Query")){
                gbc.gridwidth = GridBagConstraints.REMAINDER;

                JPanel panel = getNewQueryOrHeaderOrForm();
                query_map.add(panel, gbc);
                query.updateUI();
                queries_panels.add(panel);
            }

            if(button.getText().equals("")){
                ArrayList<JPanel> panels;
                String name = ui.getFocusOwner().getParent().getParent().getName();
                if(name.equals("header_map"))
                    panels = headers_panels;
                else if(name.equals("query_map"))
                    panels = queries_panels;
                else if(name.equals("form_map"))
                    panels = formData_panels;
                else return;

                Enumeration enumeration = new Vector(panels).elements();
                while (enumeration.hasMoreElements()) {
                    JPanel panel = (JPanel) enumeration.nextElement();
                    if(panel.getComponent(3).equals(button)){
                        panels.remove(panel);
                        panel.setVisible(false);
                    }
                }
            }

            if(button.getText().equals("Choose File")){
                JFrame choseFileFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(choseFileFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePath.setText(selectedFile.getAbsolutePath());
                }
            }

            if(button.getText().equals("Reset File")){
                filePath.setText("");
            }
        }
    }

    /**
     * action handler for checkBoxes
     */
    private class actionHandler_checkBox implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox)e.getSource();

            if(checkBox.getText().equals("check")) {
                ArrayList<JPanel> panels;

                String name = ui.getFocusOwner().getParent().getParent().getName();
                if(name.equals("header_map"))
                    panels = headers_panels;
                else if(name.equals("query_map"))
                    panels = queries_panels;
                else if(name.equals("form_map"))
                    panels = formData_panels;
                else return;


                Enumeration enumeration = new Vector(panels).elements();
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
    //----------------------------------------------------------------------------

    //-------------------------------getter methods-------------------------------

    /**
     * @return String that is the method of request
     */
    public String getMethod(){
        return urlType.getSelectedItem().toString();
    }

    /**
     * @return String that is url of request
     */
    public String getURL(){
        if(url.getText().length() == 0)
            return null;
        return url.getText();
    }

    /**
     * @return String that contains headers in a correct arrange
     */
    public String getHeaders(){
        int counter = 0;
        for(JPanel panel: headers_panels) {
            if (panel.getComponent(0).isEnabled())
                counter++;
        }

        if(counter == 0)
            return null;

        StringBuilder builder = new StringBuilder();
        builder.append('\"');
        for(JPanel panel: headers_panels){
            if(! panel.getComponent(0).isEnabled())
                continue;

            JTextField keyT = (JTextField)panel.getComponent(0);
            String key = keyT.getText();
            JTextField valueT = (JTextField)panel.getComponent(1);
            String value = valueT.getText();

            builder.append(key + ":" + value);
            builder.append(';');
        }
        builder.deleteCharAt(builder.length()-1);
        builder.append('\"');

        return builder.toString();
    }

    /**
     * @return String that contains queries for url
     */
    public String getQuery(){
        int counter = 0;
        for(JPanel panel: queries_panels) {
            if (panel.getComponent(0).isEnabled())
                counter++;
        }

        if(counter == 0)
            return null;

        StringBuilder builder = new StringBuilder();
        for(JPanel panel: queries_panels){
            if(! panel.getComponent(0).isEnabled())
                continue;

            JTextField keyT = (JTextField)panel.getComponent(0);
            String key = keyT.getText();
            JTextField valueT = (JTextField)panel.getComponent(1);
            String value = valueT.getText();

            builder.append(key + "=" + value);
            builder.append('&');
        }
        builder.deleteCharAt(builder.length() -1);

        return builder.toString();
    }

    /**
     * @return String that is type of message body
     */
    public String getTypeOfMessageBody(){
        return messageType.getSelectedItem().toString();
    }

    /**
     * @return String that contains form data message in a correct form
     */
    public String getFormData(){
        int counter = 0;
        for(JPanel panel: formData_panels) {
            if (panel.getComponent(0).isEnabled())
                counter++;
        }

        if(counter == 0)
            return null;

        StringBuilder builder = new StringBuilder();
        builder.append('\"');
        for(JPanel panel: formData_panels){
            if(! panel.getComponent(0).isEnabled())
                continue;

            JTextField keyT = (JTextField)panel.getComponent(0);
            String key = keyT.getText();
            JTextField valueT = (JTextField)panel.getComponent(1);
            String value = valueT.getText();

            builder.append(key + "=" + value);
            builder.append('&');
        }
        builder.deleteCharAt(builder.length() -1);
        builder.append('\"');

        return builder.toString();
    }

    /**
     * @return String that contains json message in a correct form
     */
    public String getJson(){
        if(json.getText().length() == 0)
            return null;
        return json.getText().trim().replaceAll("\\s+", "");
    }

    /**
     * @return returns binary data path
     */
    public String getBinaryData(){
        if(filePath.getText().length() == 0)
            return null;
        return filePath.getText();
    }
    //----------------------------------------------------------------------------

    //-------------------------------setter methods-------------------------------

    /**
     * @param url the url we want to set it when open saved request
     */
    public void setUrl(URL url) {
        this.url.setText(url.toString());
    }

    /**
     * @param method method of request
     */
    public void setMethod(Request.Method method) {
        switch (method){
            case DELETE: urlType.setSelectedItem("DELETE"); break;
            case POST: urlType.setSelectedItem("POST"); break;
            case PUT: urlType.setSelectedItem("PUT"); break;
            case GET: urlType.setSelectedItem("GET"); break;
            case PATCH: urlType.setSelectedItem("PATCH"); break;
        }
    }

    /**
     * @param map hash map of headers
     */
    public void setHeader(HashMap<String, String> map) {
        Enumeration enumeration = new Vector(headers_panels).elements();
        while (enumeration.hasMoreElements()) {
            JPanel panel = (JPanel) enumeration.nextElement();
            headers_panels.remove(panel);
            panel.setVisible(false);
        }

        if(map == null)
            return;

        for(String key: map.keySet()) {
            gbc.gridwidth = GridBagConstraints.REMAINDER;

            JPanel panel = getNewQueryOrHeaderOrForm();
            header_map.add(panel, gbc);

            JTextField keyText = (JTextField) panel.getComponent(0);
            JTextField valueText = (JTextField) panel.getComponent(1);
            keyText.setText(key);
            valueText.setText(map.get(key));

            header.updateUI();
            headers_panels.add(panel);
        }
    }

    /**
     * @param map hash map of keys and values of form data
     */
    public void setFormData(HashMap<String, String> map){
        Enumeration enumeration = new Vector(formData_panels).elements();
        while (enumeration.hasMoreElements()) {
            JPanel panel = (JPanel) enumeration.nextElement();
            formData_panels.remove(panel);
            panel.setVisible(false);
        }

        if(map == null)
            return;

        for(String key: map.keySet()) {
            gbc.gridwidth = GridBagConstraints.REMAINDER;

            JPanel panel = getNewQueryOrHeaderOrForm();
            form_map.add(panel, gbc);

            JTextField keyText = (JTextField) panel.getComponent(0);
            JTextField valueText = (JTextField) panel.getComponent(1);
            keyText.setText(key);
            valueText.setText(map.get(key));

            formData.updateUI();
            formData_panels.add(panel);
        }

        messageType.setSelectedItem("Form Data");
    }

    /**
     * @param jsonText text of json request
     */
    public void setJson(String jsonText){
        json.setText(jsonText);
        messageType.setSelectedItem("Json");
    }

    /**
     * @param filePath path of file we want to send it
     */
    public void setBinaryData(String filePath){
        this.filePath.setText(filePath);
        messageType.setSelectedItem("Binary Data");
    }
    //----------------------------------------------------------------------------
}

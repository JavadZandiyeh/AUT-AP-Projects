import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response_UI extends JPanel {
    //Top label of response panel
    private JPanel statusPanel;
    private JLabel statusMessage;
    private JLabel statusTime;
    private JLabel statusDataVolume;


    //TabbedPane for response panel
    private JTabbedPane tab;
    private JPanel body;
    private JPanel header;


    //Header Name and value fields
    private JTable nameAndValueTable;
    private String[] tableColumns = new String[] { "NAME", "VALUE"};


    //Button for coping text key and values
    private JButton copyToClipboard;
    private Object[][] objects;

    //Response body comboBox
    private JComboBox bodyType;
    //panels for body of response
    private JTextPane Raw;
    private JScrollPane RawScroll;

    private JScrollPane PreviewScroll;
    private JLabel PreviewLabel;
    enum PreviewType{Label, Scroll}
    private PreviewType typePreview;

    //handler
    actionHandler_comboBox actionHandler_comboBox;

    /**
     * constructor for response part
     */
    public Response_UI() {
        actionHandler_comboBox = new actionHandler_comboBox();

        setPreferredSize(new Dimension(400, 500));
        setLayout(new BorderLayout());

        setStatusPanel();
        setTabPane();
    }

    //---------------------------------------design part---------------------------------------
    /**
     * this method is for showing the status of time and volume and code
     * in this method we make tree labels and add them to a new panel
     * and add that panel to the north of response part
     */
    private void setStatusPanel(){
        statusPanel = new JPanel();
        statusPanel.setPreferredSize(new Dimension(400, 35));
        statusPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));

        statusMessage = new JLabel();
        statusTime = new JLabel();
        statusDataVolume = new JLabel();

        statusPanel.add(statusMessage);
        statusPanel.add(statusTime);
        statusPanel.add(statusDataVolume);

        add(statusPanel, BorderLayout.NORTH);
    }

    /**
     * setting the tabs of response part
     * we have two tabs named body and header
     */
    private void setTabPane(){
        tab = new JTabbedPane();

        body = new JPanel();
        header = new JPanel();

        //add the tabs two the tabbedPane
        tab.add("Body", body);
        tab.add("Header", header);

        add(tab, BorderLayout.CENTER);

        setHeader();
        setBody();
    }

    /**
     * setting the header part of response tab
     */
    private void setHeader(){
        //this is a button for adding the the list text to clipboard
        copyToClipboard = new JButton("Copy to Clipboard");
        copyToClipboard.addActionListener(new actionHandler_button());

        header.setLayout(new BorderLayout());
        header.add(copyToClipboard, BorderLayout.SOUTH);
    }

    /**
     * set the body of response part
     */
    private void setBody(){
        bodyType = new JComboBox();

        Raw = new JTextPane();
        RawScroll = new JScrollPane(Raw,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        RawScroll.setWheelScrollingEnabled(true);
        body.add(RawScroll, BorderLayout.CENTER);

        PreviewScroll = new JScrollPane(new JTextPane(),
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        PreviewLabel = new JLabel();

        bodyType.addItem("Raw");
        bodyType.addItem("Preview");
        bodyType.addActionListener(actionHandler_comboBox);

        body.setLayout(new BorderLayout());
        body.add(bodyType, BorderLayout.NORTH);
    }
    //-----------------------------------------------------------------------------------------

    //----------------------------------------setters------------------------------------------

    /**
     * by this method we can set the Preview part and if it is text show it
     * on a text and if is picture show it in a label
     * @param object can be string as text part or a picture
     */
    public void setPreview(Object object){
        //first of all we have to clear last Previews of last requests

        if(object instanceof Image) {
            PreviewLabel.removeAll();
            PreviewLabel.revalidate();
            PreviewLabel.repaint();
            PreviewLabel.setVisible(false);

            Image image = (Image) object;

            ImageIcon icon = new ImageIcon(image.getScaledInstance
                    (200, 200, BufferedImage.SCALE_SMOOTH));

            PreviewLabel.setIcon(icon);
            PreviewLabel.setOpaque(true);
            body.add(PreviewLabel, BorderLayout.CENTER);
            typePreview = PreviewType.Label;
        }

        if(object instanceof String){
            PreviewScroll.removeAll();
            PreviewScroll.revalidate();
            PreviewScroll.repaint();
            PreviewScroll.setVisible(false);

            String text = (String) object;
            JTextPane textPane = new JTextPane();
            textPane.setText(text);
            textPane.setPreferredSize(new Dimension(380, 400));
            PreviewScroll = new JScrollPane(
                    textPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            body.add(PreviewScroll, BorderLayout.CENTER);
            typePreview = PreviewType.Scroll;
        }

        body.updateUI();
        bodyType.setSelectedItem("Preview");
    }

    /**
     * @param rawText the raw text of message body
     */
    public void setRaw(String rawText){
        Raw.setText(rawText);
    }

    /**
     * this method is used for setting headers in a table and show it
     * on header part of panel
     * @param map keys and values of map we want to show them on headers table
     */
    public void setHeaderFields(Map<String, List<String>> map){
        header.removeAll();
        //this is list for header part witch the first column of it is name and
        //second column of it is value
        objects = new Object[map.size()][2];
        HashMap<String, String> map1 = new HashMap<>();

        for(String key: map.keySet()){
            StringBuilder builder = new StringBuilder();
            for(String value: map.get(key))
                builder.append(value + " ,");;
            map1.put(key, builder.toString());
        }

        for(int i = 0; i < map1.size(); i++){
            objects[i][0] = map1.keySet().toArray()[i];
            objects[i][1] = map1.get(map1.keySet().toArray()[i]);
        }

        final Class[] columnClass = new Class[]{ String.class, String.class};
        DefaultTableModel model = new DefaultTableModel(objects, tableColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };

        nameAndValueTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(nameAndValueTable);
        scrollPane.setPreferredSize(new Dimension(400, 415));
        header.add(scrollPane, BorderLayout.NORTH);
        header.add(copyToClipboard, BorderLayout.SOUTH);
    }

    /**
     * setting status of response
     * @param response for finding status of response we have to have it:)
     */
    public void setStatusPart(Response response){
        statusMessage.setText(response.getMessage() + " " + response.getCode());
        if(response.getCode() == HttpURLConnection.HTTP_OK)
            statusMessage.setBackground(new Color(106, 204, 75));
        else
            statusMessage.setBackground(new Color(204, 77, 39));
        statusMessage.setOpaque(true);

        statusTime.setText("" + response.getTime() + " ms");
        statusTime.setOpaque(true);

        statusDataVolume.setText("" + response.getVolume() + "Byte");
        statusDataVolume.setOpaque(true);
    }

    /**
     * for refreshing and clearing response part when we open a new request
     */
    public void refresh(){
        PreviewScroll.removeAll();
        PreviewScroll.revalidate();
        PreviewScroll.repaint();
        PreviewScroll.setVisible(false);

        PreviewLabel.removeAll();
        PreviewLabel.revalidate();
        PreviewLabel.repaint();
        PreviewLabel.setVisible(false);

        Raw.setText("");

        header.removeAll();
        header.revalidate();
        header.repaint();
        header.setVisible(false);
    }
    //--------------------------------------handlers-------------------------------------------

    /**
     * class for handling combo box actions
     */
    private class actionHandler_comboBox implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox) e.getSource();


            if (comboBox.getSelectedItem().equals("Raw")) {
                body.add(RawScroll, BorderLayout.CENTER);
                RawScroll.setVisible(true);

                PreviewScroll.setVisible(false);
                PreviewLabel.setVisible(false);
            }

            if (comboBox.getSelectedItem().equals("Preview")) {
                if(typePreview.equals(PreviewType.Label)) {
                    body.add(PreviewLabel, BorderLayout.CENTER);
                    PreviewLabel.setVisible(true);
                    PreviewScroll.setVisible(false);
                } else if(typePreview.equals(PreviewType.Scroll)) {
                    body.add(PreviewScroll, BorderLayout.CENTER);
                    PreviewScroll.setVisible(true);
                    PreviewLabel.setVisible(false);
                }
                RawScroll.setVisible(false);
            }

            body.updateUI();
        }
    }

    /**
     * class for handling button actions
     */
    private class actionHandler_button implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            if(button.getText().equals("Copy to Clipboard")){
                String a = "";
                for(int i=0; i<objects.length; i++)
                    a += (objects[i][0] + " " + objects[i][1] + "\n");

                StringSelection stringSelection = new StringSelection (a);
                Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
                clpbrd.setContents (stringSelection, null);
            }
        }
    }
}

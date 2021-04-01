import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * this class is frame of insomnia and also here
 * we have some inner classes to save and open requests
 */
public class UI extends JFrame{
    //current request of ui
    private Request request;
    //manager for managing request
    private Manager manager;

    //main part of Insomnia
    private TopMenuBar_UI topMenuBar_ui;
    private PreviousRequests_UI previousRequests_ui;
    private NewRequests_UI newRequests_ui;
    private Response_UI response_ui;

    //-----------------------------------design part-----------------------------------

    /**
     * constructor of program that make frame and add panels on
     * also we set the lock and fill here
     */
    public UI() {

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

        this.setTitle("Insomnia");
        this.setBounds(130,100,1100,500);
        this.setLayout(new BorderLayout());
        this.setResizable(true);

        try {
            Image img = ImageIO.read(getClass().getResource("./tray.png"));
            this.setIconImage(img);
        }catch (IOException e){
            showError(e);
        }

        topMenuBar_ui = new TopMenuBar_UI(this);
        previousRequests_ui = new PreviousRequests_UI(this);
        newRequests_ui = new NewRequests_UI(this);
        response_ui = new Response_UI();

        this.add(topMenuBar_ui, BorderLayout.NORTH);
        this.add(previousRequests_ui, BorderLayout.WEST);
        this.add(newRequests_ui, BorderLayout.CENTER);
        this.add(response_ui, BorderLayout.EAST);

        addTrayOption();
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * tray option is a option that allow you to hide fram on task bar
     */
    private void addTrayOption(){
        if(! SystemTray.isSupported())
            return;

        TrayIcon trayIcon;
        SystemTray tray = SystemTray.getSystemTray();

        Image image;
        try {
            image = ImageIO.read(getClass().getResource("./tray.png"));
        }catch (IOException e){
            return;
        }


        //i also add exit and open option for wile the frame is in task bar
        PopupMenu popup=new PopupMenu();
        MenuItem defaultItem=new MenuItem("Exit");
        defaultItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        popup.add(defaultItem);

        defaultItem=new MenuItem("Open");
        defaultItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(true);
                setExtendedState(JFrame.NORMAL);
            }
        });
        popup.add(defaultItem);

        trayIcon = new TrayIcon(image, "Insomnia", popup);
        trayIcon.setImageAutoSize(true);


        addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent e) {
                if(e.getNewState() == ICONIFIED){
                    try {
                        tray.add(trayIcon);
                        setVisible(false);
                    } catch (AWTException ex) {}
                }

                if(e.getNewState() == 7){
                    try{
                        tray.add(trayIcon);
                        setVisible(false);
                    }catch(AWTException ex){}
                }

                if(e.getNewState() == MAXIMIZED_BOTH){
                    tray.remove(trayIcon);
                    setVisible(true);
                }

                if(e.getNewState() == NORMAL){
                    tray.remove(trayIcon);
                    setVisible(true);
                }
            }
        });

    }

    /**
     * for toggle slider option, top menu bar needs to work on this object
     * @return object of PreviousRequests_UI class
     */
    public PreviousRequests_UI getPreviousRequests_ui() {
        return previousRequests_ui;
    }
    //---------------------------------------------------------------------------------


    //------------------------------request handling part------------------------------

    /**
     * with this method we make an object of inner class SendRequest and execute it
     */
    public void sendRequest(){
        new SendRequest().execute();
    }

    /**
     * with this method we make an object of inner class openSavedRequest and execute it
     */
    public void openSavedRequest(MouseEvent e){
        new OpeningSavedRequest(e).execute();
    }

    /**
     * in this method we save the current request in a directory
     */
    public void saveRequest(){
        if(request == null)
            sendRequest();

        String directoryName = JOptionPane.showInputDialog("Which directory?");
        if(directoryName != null && (!directoryName.equals(""))) {
            request.save(directoryName);
            previousRequests_ui.tree.updateUI();
        }
    }

    /**
     * this is a inner class witch help us to send requests
     * this class extends SwingWorker abstract class,this class
     * let us to work on ui without locking screen
     * here we use it for sending request and then show its response
     * on response_ui panel
     */
    private class SendRequest extends SwingWorker< ArrayList<Request>, Void >{

        /**
         * in this class we make a text as project text in console
         * and send it for manager and get the filled requests from
         * that and send it for done() method to show it in ui
         * @return array list of requests that manager returned
         */
        @Override
        protected ArrayList<Request> doInBackground() {
            StringBuilder project = new StringBuilder();
            try {
                //finding project
                String url = newRequests_ui.getURL();
                String method = newRequests_ui.getMethod();
                boolean followRedirect = topMenuBar_ui.getFollow_check();
                String query = newRequests_ui.getQuery();
                String headers = newRequests_ui.getHeaders();
                String messageBodyType = newRequests_ui.getTypeOfMessageBody();
                String json = newRequests_ui.getJson();
                String binaryData = newRequests_ui.getBinaryData();
                String formData = newRequests_ui.getFormData();


                //url and query
                if (url == null)
                    throw new FinishWorkingException("FILL THE URL PART AND TRY AGAIN");
                if(query != null)
                    url = (url + "?" + query);
                project.append(" --url " + url);

                //method
                project.append(" --method " + method);

                //follow redirect
                if(followRedirect)
                    project.append(" -f ");

                //headers
                if(headers != null)
                    project.append(" --headers " + headers);

                //message body
                if(method.equals("POST") || method.equals("PUT") || method.equals("PATCH")) {
                    if (messageBodyType.equals("Json")) {
                        if (json != null)
                            project.append(" --json " + json);
                        else
                            throw new FinishWorkingException("Fill json part and try again");
                    }
                    if (messageBodyType.equals("Form Data")) {
                        if (formData != null)
                            project.append(" --data " + formData);
                        else
                            throw new FinishWorkingException("Fill form data part and try again");
                    }
                    if (messageBodyType.equals("Binary Data")) {
                        if(binaryData != null)
                            project.append(" --upload " + binaryData);
                        else
                            throw new FinishWorkingException("Fill binary data part and try again");
                    }
                }

                manager = new Manager();
                String[] projectArray = project.toString().split("\\s+");
                ArrayList<Request> requests = manager.doTask(projectArray);

                return requests;
            }catch(FinishWorkingException e){
                showError(e);
                return null;
            }
        }

        /**
         * after getting request form doInBackground method
         * we go show it on response part
         */
        @Override
        protected void done() {
            try {
                ArrayList<Request> requestArrayList = get();
                if(requestArrayList == null)
                    return;

                request = requestArrayList.get(0);
                Response response = request.getResponse();

                if(response == null)
                    return;

                response_ui.setRaw(response.getResponseBodyAsRawData());

                if(response.getImage() != null)
                    response_ui.setPreview(response.getImage());
                else {
                    switch (response.getBodyType()){
                        case Json: response_ui.setPreview(response.getResponseBodyAsJson()); break;
                        case Html: response_ui.setPreview(response.getResponseBodyAsHtml()); break;
                        default:   response_ui.setPreview(response.getResponseBodyAsRawData()); break;
                    }
                }


                Map<String, List<String>> map = response.getHeaderFields();
                if(map != null)
                    response_ui.setHeaderFields(map);

                response_ui.setStatusPart(response);

            } catch (InterruptedException e) {
                showError(e);
            } catch (ExecutionException e) {
                showError(e);
            } catch (FinishWorkingException e){
                showError(e);
            }
        }
    }

    /**
     * in this class we open saved requests from directory
     */
    private class OpeningSavedRequest extends SwingWorker<Request, Void>{
        private MouseEvent e;

        /**
         * constructor of class
         * @param e mouse event that happened in save directory
         */
        public OpeningSavedRequest(MouseEvent e){
            this.e = e;
        }

        /**
         * in this method we find the request that user clicked on
         * and return it for done() method to show it in ui
         * @return request that user want to open it
         */
        @Override
        protected Request doInBackground() {
            JTree tree = (JTree)e.getSource();

            int selectedRow = tree.getRowForLocation(e.getX(), e.getY());
            TreePath selectedPath = tree.getPathForLocation(e.getX(), e.getY());

            if(selectedRow != -1) {
                if(e.getClickCount() == 2) {
                    StringBuilder builder = new StringBuilder();
                    for (Object s: selectedPath.getPath())
                        builder.append(s + File.separator);
                    File file = new File(builder.toString());

                    try(FileInputStream fis = new FileInputStream(file);
                        ObjectInputStream ois = new ObjectInputStream(fis)) {

                        Object object = ois.readObject();

                        if(object instanceof Request) {
                            Request request = (Request) object;
                            return request;
                        }
                    }catch (IOException | ClassNotFoundException ex){
                        ex.printStackTrace();
                        showError(ex);
                    }
                }
            }

            return null;
        }

        /**
         * here we get the request and show different part of it in ui
         */
        @Override
        protected void done() {
            //before all we have to clear response part of ui
            response_ui.refresh();

            Request request;
            try {
                request = get();
                if(request == null)
                    return;
            } catch (InterruptedException ex) {
                showError(ex);
                return;
            } catch (ExecutionException ex) {
                showError(ex);
                return;
            }

            newRequests_ui.setMethod(request.getMethod());
            newRequests_ui.setUrl(request.getUrl());

            newRequests_ui.setHeader(request.getHeaders());

            if(request.getBodyType() != null) {
                switch (request.getBodyType()) {
                    case BinaryData: {
                        newRequests_ui.setBinaryData(request.getBodyAsBinaryData());
                        break;
                    }
                    case FormData: {
                        newRequests_ui.setFormData(request.getBodyAsFormData_KeysAndValues());
                        break;
                    }
                    case Json: {
                        newRequests_ui.setJson("\"{" + request.getBodyAsBinaryData() + "}\"");
                        break;
                    }
                }
            }

            topMenuBar_ui.setFollow_check(request.getFollowRedirect());
        }
    }
    //---------------------------------------------------------------------------------

    /**
     * this method is used when a problem happen and show
     * the error option pane
     * @param ex the exception we want to show it in error pane
     */
    private void showError(Exception ex){
        JOptionPane optionPane = new JOptionPane(new JTextArea(ex.getMessage()),
                JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION, null,
                new Object[] {}, null);
        JDialog dialog = optionPane.createDialog("Error");
        dialog.setVisible(true);
    }
}

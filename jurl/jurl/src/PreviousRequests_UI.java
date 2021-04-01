import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

/**
 * this class is for managing saved requests
 * and show them in a good model in tree nodes
 */
public class PreviousRequests_UI extends JPanel {
    private UI ui;
    public JTree tree;
    //place we save requests on
    private static final String saveRequestPlace = "./SavedRequests";

    /**
     * constructor of class
     * @param ui instance of UI class that is frame for this class
     */
    public PreviousRequests_UI(UI ui){
        this.ui = ui;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        ActionHandler_button handlerButton = new ActionHandler_button();

        JButton newDirectory = new JButton("Create");
        newDirectory.setMnemonic('N');
        newDirectory.addActionListener(handlerButton);
        newDirectory.setPreferredSize(new Dimension(180, 25));
        add(newDirectory, gbc);


        JButton removeDirectory = new JButton("Remove");
        removeDirectory.setMnemonic('R');
        removeDirectory.addActionListener(handlerButton);
        removeDirectory.setPreferredSize(new Dimension(180, 25));
        add(removeDirectory, gbc);

        File saveDefault = new File(saveRequestPlace);
        if(! saveDefault.exists())
            saveDefault.mkdir();


        MyFile file = new MyFile(new File(saveRequestPlace));
        TreeModel model = new FileTreeModel(file);
        tree = new JTree(model);
        tree.setEditable(true);
        tree.setRootVisible(true);
        tree.addMouseListener(new MouseHandler());

        JScrollPane treeScroll = new JScrollPane(tree);
        treeScroll.setWheelScrollingEnabled(true);
        treeScroll.setPreferredSize(new Dimension(180, 480));

        //adding the scroll to left panel
        add(treeScroll, gbc);
        treeScroll.updateUI();
    }

    /**
     * handler for buttons
     */
    private class ActionHandler_button implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            String text = button.getText();

            if(text.equals("Create")){
                String directory = JOptionPane.showInputDialog("Write your directory name:");
                if(directory == null)
                    return;

                File file = new File(saveRequestPlace + File.separator + directory);
                if(! file.exists())
                    file.mkdir();
                tree.updateUI();
            }

            if(text.equals("Remove")){
                String directory = JOptionPane.showInputDialog("Write directory name:");
                if(directory == null)
                    return;

                File file = new File(saveRequestPlace + File.separator + directory);
                if(file.exists())
                    file.delete();
                tree.updateUI();
            }
        }
    }

    /**
     * handler for mouse
     */
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2)
                ui.openSavedRequest(e);
        }
    }
}

/**
 * this two class are for managing the view of saved requests
 */
class FileTreeModel implements TreeModel {
    private ArrayList<TreeModelListener> mListeners  = new ArrayList<>();
    private MyFile myFile;

    public FileTreeModel(final MyFile file) {
        myFile = file;
    }

    @Override
    public Object getRoot() {
        return myFile;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((MyFile) parent).listFiles()[index];
    }

    @Override
    public int getChildCount(Object parent) {
        return ((MyFile) parent).listFiles().length;
    }

    @Override
    public boolean isLeaf(Object node) {
        return !((MyFile) node).isDirectory();
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        MyFile oldTmp = (MyFile) path.getLastPathComponent();
        File oldFile = oldTmp.getFile();
        String newName = (String) newValue;
        File newFile = new File(oldFile.getParentFile(), newName);
        oldFile.renameTo(newFile);
        reload();
    }

    @Override
    public int getIndexOfChild(Object parent, final Object child) {
        final MyFile[] files = ((MyFile) parent).listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i] == child) return i;
        }
        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener pL) {
        mListeners.add(pL);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener pL) {
        mListeners.remove(pL);
    }

    public void reload() {
        // Need to duplicate the code because the root can formally be
        // no an instance of the TreeNode.
        int n = getChildCount(getRoot());
        int[] childIdx = new int[n];
        Object[] children = new Object[n];

        for (int i = 0; i < n; i++) {
            childIdx[i] = i;
            children[i] = getChild(getRoot(), i);
        }

        fireTreeStructureChanged(this, new Object[] { getRoot() }, childIdx, children);
    }

   /**
     * @param source the node where the model has changed
     * @param path the path to the root node
     * @param childIndices the indices of the affected elements
     * @param children the affected elements
     */
    protected void fireTreeStructureChanged(Object source, Object[] path, int[] childIndices, Object[] children) {
        TreeModelEvent event = new TreeModelEvent(source, path, childIndices, children);
        for (TreeModelListener l : mListeners) {
            l.treeStructureChanged(event);
        }
    }
}
class MyFile {
    private File myFile;

    public MyFile(final File file) {
        myFile = file;
    }

    public boolean isDirectory() {
        return myFile.isDirectory();
    }

    public MyFile[] listFiles() {
        File[] files = myFile.listFiles();
        if (files == null)
            return null;
        if (files.length < 1)
            return new MyFile[0];

        MyFile[] ret = new MyFile[files.length];
        for (int i = 0; i < ret.length; i++) {
            File f = files[i];
            ret[i] = new MyFile(f);
        }

        return ret;
    }

    public File getFile() {
        return myFile;
    }

    @Override
    public String toString() {
        return myFile.getName();
    }
}


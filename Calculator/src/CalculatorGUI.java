import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;

/**
 * in this class we make graphical interface for calculator
 * and two kind of calculator is available: basic and advance
 */
public class CalculatorGUI {

    //this is the control part for MVC design
    private Operator[] operator;

    //the basic frame of cal
    private JFrame calFrame;
    //the tabbedPane we add two kinds of calculators on
    private JTabbedPane tabbedPane;

    //the basic panel for basic cal
    private JPanel basicPanel;
    //for advance cal
    private JPanel advancePanel;

    //the basic and advance text fields
    private JTextField basicTxtField;
    private JTextField advanceTxtField;
    //the content of text fields
    private String text[];

    //button handlers for basic and advance panels
    private ButtonHandler[] buttonHandlers;

    /**
     * this is a constructor witch calls other classes two make a good interface
     */
    public CalculatorGUI() {
        //initialize array of operators
        operator = new Operator[2];
        operator[0] = new Operator();
        operator[1] = new Operator();

        //initializing objects
        calFrame = new JFrame();

        tabbedPane = new JTabbedPane();

        basicPanel = new JPanel();
        advancePanel = new JPanel();

        buttonHandlers = new ButtonHandler[2];
        buttonHandlers[0] = new ButtonHandler(basicPanel);
        buttonHandlers[1] = new ButtonHandler(advancePanel);

        basicTxtField = new JTextField();
        advanceTxtField = new JTextField();

        text = new String[2];
        text[0] = "";
        text[1] = "";

        //calling the calculator class
        calculator();
    }

    /**
     * in this class we make the frame and the tabbedPane of
     * our calculator and add two panels on tabbedPane
     * at last we call other classes to add other components
     * to our panels
     */
    private void calculator() {
        //frame making and setting the bound of it
        calFrame.setSize(370, 350);
        calFrame.setLocation(300, 250);
        calFrame.add(tabbedPane);

        //make a new menu bar
        JMenuBar menuBar = new JMenuBar();
        calFrame.setJMenuBar(menuBar);

        //add a new menu to menu bar
        JMenu menu = new JMenu("File");
        menu.setMnemonic('F');
        menuBar.add(menu);

        //add items to meu bars and add action listeners to them
        JMenuItem menuItem1 = new JMenuItem("EXIT");
        menuItem1.addActionListener(e -> System.exit(0));
        menuItem1.setMnemonic('X');
        menu.add(menuItem1);

        JMenuItem menuItem2 = new JMenuItem("COPY");
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK ));
        menuItem2.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection (text[0]);
            Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
            clpbrd.setContents (stringSelection, null);
        });
        menu.add(menuItem2);

        //set bounds of tabbedPane and add other panels on
        tabbedPane.setBounds(50, 50, 100, 100);
        tabbedPane.add("Basic", basicPanel);
        tabbedPane.add("Advance", advancePanel);

        //call classes to fill this tabbedPane
        setAdvancePanel();
        setBasicPanel();

        //show the frame
        calFrame.setVisible(true);
        //finish the program after close it
        calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * in this class we set the components to our calculator
     */
    private void setBasicPanel() {
        //set the layout two null
        basicPanel.setLayout(null);
        //set panels to our basic panel
        setTextField(basicPanel);
        setBasicKeyboard(basicPanel);
        setKeyboardPanelNumbers(basicPanel);
    }

    /**
     * in this class we set the components to our calculator
     */
    private void setAdvancePanel() {
        //set the layout two null
        advancePanel.setLayout(null);
        //set panels to our advance panel
        setTextField(advancePanel);
        setBasicKeyboard(advancePanel);
        setKeyboardPanelNumbers(advancePanel);
        setAdvanceKeyboard(advancePanel);
    }

    /**
     * @param panel this is the panel we want to add our numeric keyboard on
     */
    private void setKeyboardPanelNumbers(JPanel panel) {

        int j;
        if(panel.equals(basicPanel))    j = 0;
        else    j = 1;

        //making a new keyboard and add it to panel
        JPanel keyboard = new JPanel();

        //set size and set layout to grid
        keyboard.setBounds(10, 90, 200, 160);
        keyboard.setLayout(new GridLayout(4, 3));

        JButton[] buttons = new JButton[10];
        //adding buttons to keyboard
        for (int i = 9; i > 0; i--) {
            buttons[i] = new JButton("" + i);
            buttons[i].setToolTipText("number " + i);
        }
        JButton button1 = new JButton("0");
        button1.setToolTipText("number 0");
        JButton button2 = new JButton(".");
        button2.setToolTipText("dot sign");
        JButton button3 = new JButton("=");
        button3.setToolTipText("result sign");

        //add listeners to buttons
        for (int i = 9; i > 0; i--)
            buttons[i].addActionListener(buttonHandlers[j]);
        button1.addActionListener(buttonHandlers[j]);
        button2.addActionListener(buttonHandlers[j]);
        button3.addActionListener(buttonHandlers[j]);

        //add buttons to keyboard
        for (int i = 9; i > 0; i--)
            keyboard.add(buttons[i]);
        keyboard.add(button1);
        keyboard.add(button2);
        keyboard.add(button3);

        panel.add(keyboard);
    }

    /**
     * @param panel the panel we want to add the basic operations on
     */
    private void setBasicKeyboard(JPanel panel) {

        int j;
        if(panel.equals(basicPanel))    j = 0;
        else    j = 1;

        //we make a new panel for basic operations
        JPanel basicKeyboard = new JPanel();

        //set the size and set the layout to frid
        basicKeyboard.setBounds(210, 10, 66, 240);
        basicKeyboard.setLayout(new GridLayout(6, 1));
        //make buttons and add tooltip for them
        JButton button = new JButton("AC");
        button.setToolTipText("deleting button");
        JButton button1 = new JButton("/");
        button1.setToolTipText("division");
        JButton button2 = new JButton("*");
        button2.setToolTipText("dot");
        JButton button3 = new JButton("%");
        button3.setToolTipText("mod");
        JButton button4 = new JButton("-");
        button4.setToolTipText("minus");
        JButton button5 = new JButton("+");
        button5.setToolTipText("sum");

        //add action listeners for buttons
        button.addActionListener(buttonHandlers[j]);
        button1.addActionListener(buttonHandlers[j]);
        button2.addActionListener(buttonHandlers[j]);
        button3.addActionListener(buttonHandlers[j]);
        button4.addActionListener(buttonHandlers[j]);
        button5.addActionListener(buttonHandlers[j]);

        //add buttons to keyboard
        basicKeyboard.add(button);
        basicKeyboard.add(button1);
        basicKeyboard.add(button2);
        basicKeyboard.add(button3);
        basicKeyboard.add(button4);
        basicKeyboard.add(button5);

        //add the panel on keyboard
        panel.add(basicKeyboard);
    }

    /**
     * @param panel the panel we want to add the advance operators on
     */
    private void setAdvanceKeyboard(JPanel panel) {

        int j;
        if(panel.equals(basicPanel))    j = 0;
        else    j = 1;

        //make a new panel for advance operators and set its size and layout
        JPanel advanceKeyboard = new JPanel();
        advanceKeyboard.setLayout(new GridLayout(6, 1));
        advanceKeyboard.setBounds(280, 10, 66, 240);

        //making an array of buttons and set their fonts
        JButton[] buttons = new JButton[6];
        for (int i = 0; i < 6; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", 10, 10));
            advanceKeyboard.add(buttons[i]);
        }
        //add text of buttons add tooltip to buttons
        buttons[0].setText("shift");
        buttons[0].setToolTipText("changing operator");
        buttons[1].setText("sin/cos");
        buttons[1].setToolTipText("mosalasaty operators");
        buttons[2].setText("tan/cot");
        buttons[2].setToolTipText("mosalasaty operators");
        buttons[3].setText("exp/log");
        buttons[3].setToolTipText("nemayi operators");
        buttons[4].setText("PI");
        buttons[4].setToolTipText("pi number");
        buttons[5].setText("E");
        buttons[5].setToolTipText("neper number");

        //add action listener for buttons
        for(int i=0; i<6; i++)
            buttons[i].addActionListener(buttonHandlers[j]);

        //add the advance keyboard on its panel
        advancePanel.add(advanceKeyboard);
    }

    /**
     * @param panel the panel we want to add a test field on
     */
    private void setTextField(JPanel panel) {
        //make a panel and set its bound
        JTextField textField = new JTextField();

        if(panel.equals(basicPanel)) {
            textField = basicTxtField;
            textField.addKeyListener(new KeyHandler(basicPanel));
        }
        if(panel.equals(advancePanel)) {
            textField = advanceTxtField;
            textField.addKeyListener(new KeyHandler(advancePanel));
        }

        textField.setBounds(10, 10, 200, 80);
        //set the font of this text field
        textField.setFont(new Font("Arial", 14, 14));


        //add text field on panel
        panel.add(textField);
    }

    /**
     * this class handles key events and writing on text field
     */
    private class KeyHandler extends KeyAdapter{

        JPanel panel;
        public KeyHandler(JPanel panel){
            this.panel = panel;
        }

        @Override
        public void keyTyped(KeyEvent e) {
            if(panel.equals(basicPanel))
                text[0] += e.getKeyChar();
            else
                text[1] += e.getKeyChar();
        }
    }

    /**
     * this class handles action events
     */
    private class ButtonHandler implements ActionListener {

        JPanel panel;
        public ButtonHandler(JPanel panel){
            this.panel = panel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            int j;
            if(panel.equals(basicPanel))    j = 0;
            else    j = 1;

            JButton btn = (JButton) e.getSource();

            for(int i=0; i<10; i++) {
                if (btn.getText().equals("" + i))
                    text[j] += i;
            }

            if(btn.getText().equals(".")) {
                if(!text[j].contains("."))
                    text[j] += ".";
            }

            if(btn.getText().equals("AC")) {
                text[j] = "";
                operator[j].setContent(0, 0);
                operator[j].setContent(1, 0);
            }

            if(btn.getText().equals("PI")){
                text[j] += "3.14";
            }

            if(btn.getText().equals("+")) {
                operator[j].setContent(0,Float.parseFloat(text[j]));
                operator[j].setOp(Operator.Operators.SUM);
                text[j] = "";
            }

            if(btn.getText().equals("-")){
                operator[j].setContent(0,Float.parseFloat(text[j]));
                operator[j].setOp(Operator.Operators.MINUS);
                text[j] = "";
            }

            if(btn.getText().equals("/")){
                operator[j].setContent(0,Float.parseFloat(text[j]));
                operator[j].setOp(Operator.Operators.DIVISION);
                text[j] = "";
            }

            if(btn.getText().equals("%")){
                operator[j].setContent(0,Float.parseFloat(text[j]));
                operator[j].setOp(Operator.Operators.MOD);
                text[j] = "";
            }

            if(btn.getText().equals("*")){
                operator[j].setContent(0,Float.parseFloat(text[j]));
                operator[j].setOp(Operator.Operators.DOT);
                text[j] = "";
            }

            if(btn.getText().equals("=")){
                operator[j].setContent(1, Float.parseFloat(text[j]));
                text[j] = ("" + operator[j].getResult());
                operator[j].setContent(0, Float.parseFloat(text[j]));
                operator[j].setContent(1, 0);
            }

            if(panel.equals(basicPanel))
                basicTxtField.setText(text[j]);
            else
                advanceTxtField.setText(text[j]);
        }
    }



}
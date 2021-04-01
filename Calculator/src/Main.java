import javax.swing.*;

/**
 * this project is a graphical user interface for calculator
 * in this class we handle the look and feel by a try-catch
 * command to set the look and feel to nimbus
 * in this class we call calculator class too
 */
public class Main {

    public static void main(String[] args) {

        //this try-catch is for setting the lookAndFeel to nimbus
        //and handles the exeption too
        try{
            for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
        }

        //making a new calculator
        new CalculatorGUI();

    }
}

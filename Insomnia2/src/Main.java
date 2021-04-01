import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        try{
            for(UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()){
                if("Nimbus".equals(info.getName())){
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"you have not installed Nimbus look and feel");
        }

        new View();
    }
}

import java.io.IOException;
import javax.swing.*;

public class main {
    public static void main(String[] args) throws IOException {

        SwingUtilities.invokeLater(new Runnable (){
            public void run(){
                //Run start window
                JFrame start = new startWindow("ColorBeats!");
                start.setSize(600,900);
                start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                start.setLocationRelativeTo(null);
                start.setVisible(true);
            }
        });
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class instructionWindow  extends JFrame implements ActionListener {

    JLabel panel;
    JButton start;
    JButton back;

    public instructionWindow(String title) {
        super(title);

        File file = new File("assets/image/instruction.png");
        panel = new JLabel();
        panel.setIcon(new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT)));
        panel.setLayout(null);

        start = new JButton("Start");
        start.addActionListener(this);
        start.setBounds(500, 545, 200, 30);
        panel.add(start);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(700, 545, 200, 30);
        panel.add(back);

        add(panel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //Start button will get the main program to start right away.
        if (e.getSource() == start) {
            dispose();
            JFrame main = new mainWindow("ColorBeats!");
            main.setExtendedState(JFrame.MAXIMIZED_BOTH);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setVisible(true);
        }
        //Back button will set it to go back to the start window.
        else if (e.getSource() == back) {
            dispose();
            JFrame start = new startWindow("ColorBeats!");
            start.setSize(600,900);
            start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            start.setLocationRelativeTo(null);
            start.setVisible(true);
        }
    }
}

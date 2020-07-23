import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class startWindow extends JFrame implements ActionListener {
    JLabel panel;
    JButton start;
    JButton instruction;

    public startWindow(String title) {
        super(title);

        //Set background
        File file = new File("assets/image/background1.jpg");
        panel = new JLabel();
        panel.setIcon(new ImageIcon(new ImageIcon(file.getPath()).getImage().getScaledInstance(600, 900, Image.SCALE_DEFAULT)));
        panel.setLayout(null);

        //Start button
        start = new JButton("Start");
        start.addActionListener(this);
        start.setBounds(185, 550, 220, 30);
        panel.add(start);

        //Instruction button
        instruction = new JButton("Instruction");
        instruction.addActionListener(this);
        instruction.setBounds(185, 575, 220, 30);
        panel.add(instruction);

        add(panel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        //Start Button will run the main program
        if (e.getSource() == start) {
            dispose();
            JFrame main = new mainWindow("ColorBeats!");
            main.setExtendedState(JFrame.MAXIMIZED_BOTH);
            main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            main.setVisible(true);
        }
        //Instruction will pop up the instruction window
        else if (e.getSource() == instruction) {
            dispose();
            JFrame instruct = new instructionWindow("ColorBeats!");
            instruct.setSize(900,600);
            instruct.setLocationRelativeTo(null);
            instruct.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            instruct.setVisible(true);

        }
    }
}

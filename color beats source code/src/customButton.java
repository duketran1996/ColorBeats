import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class customButton extends JButton {
    private Color pressedColor = Color.WHITE;

    public customButton (Color rgb) {

        setMaximumSize(new Dimension(80,400));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        //Set the button color according to rgb values.
        setBackground(rgb);

        //Change the color when button is pressed.
        addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent evt) {
                if (getModel().isPressed()) {
                    setBackground(pressedColor);
                } else {
                    setBackground(rgb);
                }
            }
        });
    }
}

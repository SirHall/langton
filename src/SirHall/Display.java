package SirHall;

import javax.swing.*;
import java.awt.*;

public class Display extends JPanel{
    public Display(){
        setLayout(null);
        repaint();
    }

    /**
     * @overrides paintComponent
     * @param g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.BLACK);
        g.setColor(Color.white);

        g.fillRect(0, 0, 100, 100);
    }
}

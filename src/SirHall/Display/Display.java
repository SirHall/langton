package SirHall.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Display extends JPanel{
    public Display(){
        setLayout(null);
        repaint();
    }

    //{TODO} Cleanup! (Temporary)
    public BufferedImage image;

    /**
     * @overrides paintComponent
     * @param g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.BLUE);
        g.drawImage(image, 100, 100, null);
    }

    public void SetImage(BufferedImage image){
        this.image = image;
    }

}

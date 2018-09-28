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
        setBackground(Color.WHITE);
        int minSize = Math.min(getWidth(), getHeight());
        minSize = 400;
        float xmult = minSize / image.getWidth();
        float ymult = minSize / image.getHeight();
        g.drawImage(
                image,
                (int)((getWidth() - image.getWidth()) / 2),
                (int)((getHeight() - image.getHeight()) / 2),
                minSize, minSize, null);
    }

    public void SetImage(BufferedImage image){
        this.image = image;
    }

}

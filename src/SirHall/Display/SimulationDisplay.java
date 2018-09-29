package SirHall.Display;

import SirHall.Maths.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SimulationDisplay extends JPanel{
    public SimulationDisplay(){
        setLayout(null);
    }

    BufferedImage image;
    JFrame frame;

    /**
     * @overrides paintComponent
     * @param g
     */
    public void paintComponent(Graphics g){
        if(image == null || frame == null)
            return;

        super.paintComponent(g);
        setBackground(Color.BLACK);
        //{TODO} Make sure this only scales up by whole number multiples
        int minSize = Math.min(frame.getWidth(), frame.getHeight());
        g.drawImage(
                image,
                (int)((frame.getWidth() - minSize) / 2),
                (int)((frame.getHeight() - minSize) / 2),
                minSize, minSize,
                null);
    }

    public void SetImage(BufferedImage image){
        this.image = image;
    }

    public void SetFrame(JFrame frame){
        this.frame = frame;
    }

}

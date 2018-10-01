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
        //Reference: https://stackoverflow.com/a/6565988
        //rs > ri ? (wi * hs/hi, hs) : (ws, hi * ws/wi)
        double ratioImage = (double)image.getWidth() / image.getHeight();
        double ratioScreen = (double)frame.getWidth() / frame.getHeight();

        //Find the correct dimensions for the image to be displayed at it's full size and at it's original
        //aspect ratio within the screen
        Vector2D dimensions =
                ratioScreen > ratioImage
                        ?
                new Vector2D(image.getWidth() * frame.getHeight() / image.getHeight(), frame.getHeight())
                :
                new Vector2D(frame.getWidth(), image.getHeight() * frame.getWidth() / image.getWidth());

        g.drawImage(
                image,
                (int)((frame.getWidth() - dimensions.GetX()) / 2),
                (int)((frame.getHeight() - dimensions.GetY()) / 2),
                (int)dimensions.GetX(),
                (int)dimensions.GetY(),
                null);
    }

    public void SetImage(BufferedImage image){
        this.image = image;
    }

    public void SetFrame(JFrame frame){
        this.frame = frame;
    }

}

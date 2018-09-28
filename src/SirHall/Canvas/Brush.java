package SirHall.Canvas;

import SirHall.Maths.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brush {
    public Brush(){

    }

    /**
     * Applies the brush to the image
     * @param image
     * @param color
     */
    public void ApplyBrush(BufferedImage image, Color color, Vector2D position){
        position = position.SnapToGrid(1.0f);

//        position.Print();
//        new Vector2D(image.getWidth(), image.getHeight()).Print();

        image.setRGB(
                (int) position.GetX(),
                (int) position.GetY(),
                CanvasJar.ColorToInt(color)
                );
    }
}

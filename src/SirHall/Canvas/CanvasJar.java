package SirHall.Canvas;

import SirHall.Maths.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasJar {
    public CanvasJar(int width, int height, Brush brush){
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.brush = brush;
    }

    BufferedImage image;
    Brush brush;

    public static int ColorToInt(Color color){
        return (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }

    public static Color IntToColor(int color){
        return new Color(color, false);
    }

    public Color GetColorAtPosition(Vector2D position){
        position = TransformToOrigin(position); //Does not modify input instance
        return IntToColor(image.getRGB((int)position.GetX(), (int)position.GetY()));
    }

    public void ApplyBrushAtPosition(Vector2D position, Color color){
        ApplyBrushAtPosition(position, this.brush, color);
    }

    public void ApplyBrushAtPosition(Vector2D position, Brush brush, Color color){
        brush.ApplyBrush(this.image, color, TransformToOrigin(position));
    }

    public BufferedImage GetImage(){return image;}

    public Vector2D TransformToOrigin(Vector2D centerFramePosition){
        centerFramePosition = centerFramePosition.SnapToGrid(1.0f);
        return
                new Vector2D(
                        (image.getWidth() / 2) + centerFramePosition.GetX(),
                        (image.getHeight() / 2) + centerFramePosition.GetY()
                );
    }

}

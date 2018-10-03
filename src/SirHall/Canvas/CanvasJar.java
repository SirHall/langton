package SirHall.Canvas;

import SirHall.Maths.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasJar {
    public CanvasJar(int width, int height, Brush brush){
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.brush = brush;
    }

    private BufferedImage image;
    private Brush brush;

    /**
     * Packs a color into an integer
     * @param color
     * @return
     */
    public static int ColorToInt(Color color){
        return (color.getRed() << 16) | (color.getGreen() << 8) | color.getBlue();
    }

    /**
     * Unpakcs an integer into a new instance of color
     * @param color
     * @return
     */
    public static Color IntToColor(int color){
        return new Color(color, false);
    }

    /**
     * Returns the volor of a pixel at a given position on the image
     * @param position
     * @return
     */
    public Color GetColorAtPosition(Vector2D position){
        //Clamp wrap position within image to prevent looking for pixels outisde the image
        position = position.ClampWrap(new Vector2D(image.getWidth() - 1, image.getHeight() - 1));
        return IntToColor(image.getRGB((int)position.GetX(), (int)position.GetY()));
    }

    /**
     * Applies the selected brush at the given positoin on the image
     * @param position
     * @param color
     */
    public void ApplyBrushAtPosition(Vector2D position, Color color){
        ApplyBrushAtPosition(position, this.brush, color);
    }

    /**
     * Applies the selected brush at the given positoin on the image
     * @param position
     * @param color
     */
    public void ApplyBrushAtPosition(Vector2D position, Brush brush, Color color){
        position = position.ClampWrap(new Vector2D(image.getWidth() - 1, image.getHeight() - 1));
        brush.ApplyBrush(this.image, color, position);
    }

    /**
     * Returns the image assigned to this canvas
     * @return
     */
    public BufferedImage GetImage(){return image;}
}

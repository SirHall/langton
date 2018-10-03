package SirHall.Color;

import java.awt.*;
import java.util.Random;

public class ColorListGen {

    private Color[] colors = new Color[]{
            Color.WHITE,
            Color.BLUE,
            Color.RED,
            Color.GREEN,
            Color.MAGENTA,
            Color.CYAN,
            Color.ORANGE,
            Color.YELLOW,
            Color.PINK,
            Color.DARK_GRAY
    };

    private int i = 0;

    private Random rand = new Random();

    /**
     * Get's the color until it reaches the end of the 'colors' array from where it will generate a random color
     * @return
     */
    public Color GetNextColor(){


        return
                i < colors.length
                        ?
                        colors[i++]
                        :
                        new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }
}

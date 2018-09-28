package SirHall.Color;

import java.awt.*;
import java.util.Random;

public class ColorListGen {

    Color[] colors = new Color[]{
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

    int i = 0;

    Random rand = new Random();

    public Color GetNextColor(){


        return
                i < colors.length
                        ?
                        colors[i++]
                        :
                        new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }
}

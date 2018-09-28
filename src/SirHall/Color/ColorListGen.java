package SirHall.Color;

import java.awt.*;

public class ColorListGen {

    Color[] colors = new Color[]{
            Color.BLACK,
            Color.WHITE
    };

    int i = 0;

    public Color GetNextColor(){
        return colors[i++];
    }
}

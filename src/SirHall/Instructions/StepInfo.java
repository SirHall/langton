package SirHall.Instructions;

import SirHall.Ant;
import SirHall.Canvas.CanvasJar;

import java.awt.*;

/**
 * Provides info about a step taken
 */
public class StepInfo {
    public StepInfo(Ant ant, Color fromColor, Color toColor, CanvasJar canvasJar){
        this.ant = ant;
        this.fromColor = fromColor;
        this.toColor = toColor;
        this.canvasJar = canvasJar;
    }

    public Ant ant;
    public Color fromColor, toColor;
    public CanvasJar canvasJar;
}

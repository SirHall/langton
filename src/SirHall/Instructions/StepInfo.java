package SirHall.Instructions;

import SirHall.Ant;
import SirHall.Canvas.CanvasJar;

import java.awt.*;

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

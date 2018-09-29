package SirHall.Settings;

import SirHall.Canvas.Brush;

public class SimulationSettings {

    public SimulationSettings(String instructions, int height, int width, float orientation, Brush brush){
        this.instructions = instructions;
        this.height = height;
        this.width = width;
        this.startingOrientation = orientation;
        this.brush = brush;
    }

    public String instructions = "LR";
    public int height = 400;
    public int width = 400;
    public float startingOrientation = -90.0f;
    public Brush brush;
}

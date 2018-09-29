package SirHall.Settings;

import SirHall.Canvas.Brush;

public class SimulationSettings {

    public SimulationSettings(
            String instructions, int height, int width, float orientation, Brush brush,
            long initialTicksPerSecond, boolean saveSnapshots, int saveEveryNthTick,
            String directory){
        this.instructions = instructions;
        this.height = height;
        this.width = width;
        this.startingOrientation = orientation;
        this.brush = brush;
        this.initialTicksPerSecond = initialTicksPerSecond;
        this.saveSnapshots = saveSnapshots;
        this.saveEveryNthTick = saveEveryNthTick;
        this.directory = directory;
    }

    public String instructions = "LR";
    public int height = 400;
    public int width = 400;
    public float startingOrientation = -90.0f;
    public Brush brush;
    public long initialTicksPerSecond = 1;
    public boolean saveSnapshots = false;
    public int saveEveryNthTick = 10;
    public String directory = "";
}

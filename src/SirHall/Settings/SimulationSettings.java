package SirHall.Settings;

import SirHall.Canvas.Brush;

public class SimulationSettings {

    public SimulationSettings(
            String instructions, int height, int width, float orientation,
            long initialTicksPerSecond, boolean saveSnapshots, int saveEveryNthTick,
            String directory, boolean snapAntDirection, boolean snapAntPosition){
        this.instructions = instructions;
        this.height = height;
        this.width = width;
        this.startingOrientation = orientation;
        this.initialTicksPerSecond = initialTicksPerSecond;
        this.saveSnapshots = saveSnapshots;
        this.saveEveryNthTick = saveEveryNthTick;
        this.directory = directory;
        this.snapAntDirection = snapAntDirection;
        this.snapAntPosition = snapAntPosition;
    }

    public String instructions = "LR";
    public int height = 400;
    public int width = 400;
    public float startingOrientation = -90.0f;
    public long initialTicksPerSecond = 1;
    public boolean saveSnapshots = false;
    public int saveEveryNthTick = 10;
    public String directory = "";
    public boolean snapAntDirection = false;
    public boolean snapAntPosition = false;

}

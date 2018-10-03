package SirHall;

import SirHall.Canvas.CanvasJar;
import SirHall.Display.SimulationDisplay;
import SirHall.Instructions.*;
import SirHall.Maths.Oper;
import SirHall.Maths.Rotation;
import SirHall.Maths.Vector2D;
import SirHall.Settings.SimulationSettings;
import SirHall.Time.Timer;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Runs all elements of the Langton's Ant simulation
 */
public class Simulation {

    public Simulation(Ant ant, InstructionSet instructionSet, CanvasJar canvas,
                      SimulationDisplay simDisplay, SimulationSettings settings){
        this.ant = ant;
        this.instructionSet = instructionSet;
        this.canvas = canvas;
        this.settings = settings;
        this.simDisplay = simDisplay;
        SetupSimulation();
    }

    protected Ant ant;
    protected InstructionSet instructionSet;
    protected CanvasJar canvas;
    protected SimulationSettings settings;
    protected SimulationDisplay simDisplay;

    protected long steps = 0;
    protected String dir = "./";

    private Timer timer = new Timer();
    private Timer screenRefreshTimer = new Timer(); //We don't need to refresh the display literally every tick

    public void StartSimulation(){
        screenRefreshTimer.StartSimulation(() -> RefreshDisplay());
        timer.StartSimulation(() -> Tick());
    }

    public void StopSimulation(){
        screenRefreshTimer.StopSimulation();
        timer.StopSimulation();
    }

    public void Freeze(){timer.Freeze();}
    public void Unfreeze(){timer.Unfreeze();}
    public void SetFrozen(boolean freeze){if(freeze) Freeze(); else Unfreeze();}
    public boolean GetFrozen(){return timer.GetFrozen();}


    public void SetTPS(long tps){timer.SetTPS(tps);}
    public long GetTPS(){return timer.GetTPS();}

    /**
     * Returns the currently active canvas
     * @return
     */
    public CanvasJar GetCanvas(){return this.canvas;}

    /**
     * Set's up the simulation
     */
    protected void SetupSimulation(){
        InstructionFactory instructionFactory = new InstructionFactory();
        InstructionParser instructionParser = new InstructionParser(instructionFactory);
        this.instructionSet = instructionParser.ParseToInstructions(settings.instructions.toUpperCase());
//        this.instructionSet.Print();
        ant.SetRotDeg(settings.startingOrientation); //Start looking upwards
        ant.SetPosition(new Vector2D(canvas.GetImage().getWidth() / 2.0f, canvas.GetImage().getHeight() / 2.0f).SnapToGrid(1));
        ant.SetRoomSize(new Vector2D(canvas.GetImage().getWidth() - 1, canvas.GetImage().getHeight() - 1));
        timer.SetTPS((long)settings.initialTicksPerSecond);
        screenRefreshTimer.SetTPS(60);
        try {
            if (settings.saveSnapshots)
                this.dir = settings.directory;
            else
                this.dir = new File(".").getCanonicalPath();
        }catch (IOException e){System.out.println(e.getMessage());}
        instructionSet.Print();
    }

    /**
     * Refreshes the display
     */
    protected void RefreshDisplay(){
        simDisplay.SetImage(this.GetCanvas().GetImage());
        simDisplay.repaint();
    }

    /**
     * Advances one tick in the simulation
     */
    public void Tick(){
        steps++;

        if(settings.saveSnapshots && steps % settings.saveEveryNthTick == 0)
            SaveScreenshot(true);

        InstructionColorConversion instructionColorSet = //Current instruction denoted by our current square color
                instructionSet.GetInstruction(canvas.GetColorAtPosition(ant.position));

        instructionColorSet.GetInstruction()
                .Step(new StepInfo(ant, instructionColorSet.GetFromColor(), instructionColorSet.GetToColor(), canvas));

        if(settings.snapAntDirection)
            ant.SetRotation(new Rotation().SetRotDeg(Oper.RoundToN(ant.GetRotation().GetRotDeg(),  90.0f)));
        if(settings.snapAntPosition)
            ant.SetPosition(ant.GetPosition().SnapToGrid(1.0f));
    }

    /**
     * Saves the current image to a file
     * @param successive [true] : increment file name from 1, [false] : filename = $instructionString_$tick
     */
    public void SaveScreenshot(boolean successive){
        String name = successive
                ?
                Long.toString(steps / settings.saveEveryNthTick)
                :
                settings.instructions + "_"+ Long.toString(steps);
        File file = new File( dir + "/" + name + ".png");
        try{
            if(!file.exists())
                file.mkdir();
            ImageIO.write(canvas.GetImage(), "png", file);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Cleans the simulation and prepares it for the garbage collector
     */
    public void Clear(){
        timer.StopSimulation();
        timer = null;
        screenRefreshTimer.StopSimulation();
        screenRefreshTimer = null;
        ant = null;
        instructionSet = null;
        canvas = null;
        settings = null;
        simDisplay = null;
    }

    /**
     * Returns the simulation timer
     * @return
     */
    public Timer GetSimTimer(){return timer;}

    /**
     * Returns the screen refresh timer
     * @return
     */
    public Timer GetScreenTimer(){return screenRefreshTimer;}

    @Override
    protected void finalize(){
        System.out.println("Disposed simulation");
    }
}

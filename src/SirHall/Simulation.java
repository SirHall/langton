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

    Ant ant;
    InstructionSet instructionSet;
    CanvasJar canvas;
    SimulationSettings settings;
    SimulationDisplay simDisplay;

    long steps = 0;

    Timer timer = new Timer();
    Timer screenRefreshTimer = new Timer(); //We don't need to refresh the display literally every tick

    public void StartSimulation(){
        screenRefreshTimer.StartSimulation(() -> RefreshDisplay());
        timer.StartSimulation(() -> Tick());
    }

    public void StopSimulation(){
        screenRefreshTimer.StopSimulation();
        timer.StopSimulation();
    }

    public void SetTPS(long tps){timer.SetTPS(tps);}
    public long GetTPS(){return timer.GetTPS();}

    public CanvasJar GetCanvas(){return this.canvas;}

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
        instructionSet.Print();
    }

    protected void RefreshDisplay(){
        simDisplay.SetImage(this.GetCanvas().GetImage());
        simDisplay.repaint();
    }

    public void Tick(){
        steps++;

        if(settings.saveSnapshots && steps % settings.saveEveryNthTick == 0)
            SaveScreenshot();

        InstructionColorConversion instructionColorSet = //Current instruction denoted by our current square color
                instructionSet.GetInstruction(canvas.GetColorAtPosition(ant.position));

        instructionColorSet.GetInstruction()
                .Step(new StepInfo(ant, instructionColorSet.GetFromColor(), instructionColorSet.GetToColor(), canvas));

        if(settings.snapAntDirection)
            ant.SetRotation(new Rotation().SetRotDeg(Oper.RoundToN(ant.GetRotation().GetRotDeg(),  90.0f)));
        if(settings.snapAntPosition)
            ant.SetPosition(ant.GetPosition().SnapToGrid(1.0f));
    }

    protected void SaveScreenshot(){
//        System.out.println(settings.directory);
        File file = new File( settings.directory + "/" + (steps / settings.saveEveryNthTick) + ".png");
        try{
            if(!file.exists())
                file.mkdir();
            ImageIO.write(canvas.GetImage(), "png", file);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}

package SirHall;

import SirHall.Canvas.CanvasJar;
import SirHall.Instructions.*;
import SirHall.Maths.Vector2D;

/**
 * Runs all elements of the Langton's Ant simulation
 */
public class Simulation {

    public Simulation(Ant ant, InstructionSet instructionSet, CanvasJar canvas){
        this.ant = ant;
        this.instructionSet = instructionSet;
        this.canvas = canvas;
        SetupSimulation();
    }

    Ant ant;
    InstructionSet instructionSet;
    CanvasJar canvas;

    public CanvasJar GetCanvas(){return this.canvas;}

    /*
    Instructions:
    L - Left
    R - Right

    N - North
    S - South
    E - East
    W - Wet

    J - Half Left
    K - Half Right
     */

    protected void SetupSimulation(){
        InstructionFactory instructionFactory = new InstructionFactory();
        InstructionParser instructionParser = new InstructionParser(instructionFactory);
        this.instructionSet = instructionParser.ParseToInstructions("JEL");
//        this.instructionSet.Print();
        ant.GetRotation().SetRotDeg(90.0f); //Start looking upwards
        ant.SetPosition(new Vector2D(canvas.GetImage().getWidth() / 2.0f, canvas.GetImage().getHeight() / 2.0f).SnapToGrid(1));
        ant.SetRoomSize(new Vector2D(canvas.GetImage().getWidth() - 1, canvas.GetImage().getHeight() - 1));
        instructionSet.Print();
    }

    public void Tick(){

        InstructionColorConversion instructionColorSet = //Current instruction denoted by our current square color
                instructionSet.GetInstruction(canvas.GetColorAtPosition(ant.position));
//        System.out.println(instructionColorSet == null);

        instructionColorSet.GetInstruction()
                .Step(new StepInfo(ant, instructionColorSet.GetFromColor(), instructionColorSet.GetToColor(), canvas));

//        System.out.println(ant.GetRotation().GetRotDeg());
    }

}

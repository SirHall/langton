package SirHall;

import SirHall.Canvas.CanvasJar;
import SirHall.Instructions.*;

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

    protected void SetupSimulation(){
        InstructionFactory instructionFactory = new InstructionFactory();
        InstructionParser instructionParser = new InstructionParser(instructionFactory);
        this.instructionSet = instructionParser.ParseToInstructions("LR");
        this.instructionSet.Print();
    }

    public void Tick(){

        InstructionColorConversion instructionColorSet = //Current instruction denoted by our current square color
                instructionSet.GetInstruction(canvas.GetColorAtPosition(ant.position));
        System.out.println(instructionColorSet == null);

        instructionColorSet.GetInstruction()
                .Step(new StepInfo(ant, instructionColorSet.GetFromColor(), instructionColorSet.GetToColor(), canvas));
    }

}

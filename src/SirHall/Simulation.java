package SirHall;

import SirHall.Canvas.CanvasJar;
import SirHall.Instructions.Instruction;
import SirHall.Instructions.InstructionColorConversion;
import SirHall.Instructions.InstructionSet;
import SirHall.Instructions.StepInfo;

/**
 * Runs all elements of the Langton's Ant simulation
 */
public class Simulation {

    public Simulation(Ant ant, InstructionSet instructionSet, CanvasJar canvas){
        this.ant = ant;
        this.instructionSet = instructionSet;
        this.canvas = canvas;
    }

    Ant ant;
    InstructionSet instructionSet;
    CanvasJar canvas;

    public void Tick(){
        InstructionColorConversion instructionColorSet =
                instructionSet.GetInstruction(canvas.GetColorAtPosition(ant.position));
        instructionColorSet.GetInstruction()
                .Step(new StepInfo(ant, instructionColorSet.GetFromColor(), instructionColorSet.GetToColor(), canvas));
    }

}

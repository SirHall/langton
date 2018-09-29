package SirHall.Instructions;

import SirHall.Instructions.Runnable.Instruction;

import java.awt.*;

public class InstructionColorConversion {

    public InstructionColorConversion(Color from, Color to, Instruction instruction){
        this.fromColor = from;
        this.toColor = to;
        this.instruction = instruction;
    }

    Color
            fromColor,
            toColor;

    Instruction instruction;

    public Color GetFromColor(){return fromColor;}
    public void SetFromColor(Color color){fromColor = color;}
    public Color GetToColor(){return toColor;}
    public void SetToColor(Color color){toColor = color;}
    public Instruction GetInstruction(){return instruction;}
}

package SirHall.Instructions;

import SirHall.Instructions.Runnable.Instruction;

import java.awt.*;

public class InstructionColorConversion {

    public InstructionColorConversion(Color from, Color to, Instruction instruction){
        this.fromColor = from;
        this.toColor = to;
        this.instruction = instruction;
    }

    private Color
            fromColor,
            toColor;

    private Instruction instruction;

    /**
     * Returns which color activates this instruction
     * @return
     */
    public Color GetFromColor(){return fromColor;}

    /**
     * Changes which color activates this instruction
     * @param color
     */
    public void SetFromColor(Color color){fromColor = color;}

    /**
     * Returns which color this instruction will convert the current cell to
     * @return
     */
    public Color GetToColor(){return toColor;}

    /**
     * Changes which color this instruction will convert the current cell to
     * @param color
     */
    public void SetToColor(Color color){toColor = color;}

    /**
     * Returns the instruction assigned to this converter
     * @return
     */
    public Instruction GetInstruction(){return instruction;}
}

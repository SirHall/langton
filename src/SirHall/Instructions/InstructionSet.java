package SirHall.Instructions;

import java.util.ArrayList;
import java.awt.*;
import SirHall.*; //I'm lazy

public class InstructionSet {
    ArrayList<InstructionColorConversion> instructionSet = new ArrayList<InstructionColorConversion>();
    boolean wrapped = false;

    /**
     * Add an instruction to the set
     * @param instruction
     * @param fromColor
     * @param toColor
     */
    public void AddToSet(Instruction instruction, Color toColor){
        if(wrapped)
            return;

        for(int i = 0; i < instructionSet.size(); i++)
            if(instructionSet.get(i).GetToColor() == toColor)
                return; //Only continue if our set does not contain the same 'fromcolor's'

        instructionSet.add(
                new InstructionColorConversion(
                        instructionSet.get(instructionSet.size() - 1).GetToColor(),
                        toColor,
                        instruction
                )
        );
    }

    /**
     * Used when all instructions have been added to the set.
     * At this point no more instructions can be added
     */
    public void WrapUpSet(){
        wrapped = true;
        //Set [last].toColor = [first].fromColor
        instructionSet
                .get(instructionSet.size() - 1)
                .SetToColor(
                        instructionSet
                                .get(0)
                                .GetFromColor()
                );
    }

    /**
     * Get the instruction associated with the current color
     * @param currentColor
     * @return
     */
    public InstructionColorConversion GetInstruction(Color currentColor){
        for(int i = 0; i < instructionSet.size(); i++)
            if(instructionSet.get(i).GetFromColor() == currentColor)
                return instructionSet.get(i);
//         throw new Exception("Could not find instruction-color pair!");
        return null;
    }
}

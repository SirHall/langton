package SirHall.Instructions;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.function.*;

/**
 * Builds an instruction set from a string
 */
public class InstructionFactory {

    public InstructionFactory(){
        SetupBluePrints();
    }

    Hashtable<Character, BiFunction<Color, Color, InstructionColorConversion>> blueprints =
            new Hashtable<Character, BiFunction<Color, Color, InstructionColorConversion>>();

    /**
     * Sets up the blueprint builders for which characters map to which instruction set elements
     */
    protected void SetupBluePrints(){

        blueprints.put(
                'L',
                (cFrom, cTo) -> new InstructionColorConversion(Color.white, Color.BLACK, new Instruction_Forward_Left()));

        blueprints.put(
                'R',
                (cFrom, cTo) -> new InstructionColorConversion(Color.BLACK, Color.WHITE, new Instruction_Forward_Right()));

    }


}

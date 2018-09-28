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

    Hashtable<Character, Instruction> blueprints =
            new Hashtable<Character, Instruction>();

    /**
     * Sets up the blueprint builders for which characters map to which instruction set elements
     */
    protected void SetupBluePrints(){

        blueprints.put('L', new Instruction_Forward_Left());

        blueprints.put('R', new Instruction_Forward_Right());

    }

    public Instruction ConstructInstruction(char character){
        if(blueprints.containsKey(character))
            return (Instruction) blueprints.get(character);
        return  null;
    }

}

package SirHall.Instructions;

import SirHall.Color.ColorListGen;

public class InstructionParser {

    public InstructionParser(InstructionFactory sourceFactory){
        this.factory = sourceFactory;
    }

    InstructionFactory factory;

    //{TODO} Temporary
    ColorListGen colorList = new ColorListGen();

    public InstructionSet ParseToInstructions(String text){
        InstructionSet instructionSet = new InstructionSet();
        for(int i = 0; i < text.length(); i++)
            instructionSet.AddToSet(factory.ConstructInstruction(text.charAt(i)), colorList.GetNextColor());
        instructionSet.WrapUpSet();
        return instructionSet;
    }

}

package SirHall.Instructions;

import SirHall.Color.ColorListGen;

import java.awt.*;

public class InstructionParser {

    public InstructionParser(InstructionFactory sourceFactory){
        this.factory = sourceFactory;
    }

    protected InstructionFactory factory;

    //{TODO} Temporary
    protected ColorListGen colorList = new ColorListGen();

    /**
     * Parses an instruction string into an instruction set
     * @param text
     * @return
     */
    public InstructionSet ParseToInstructions(String text){
        InstructionSet instructionSet = new InstructionSet();
        Color toColor = Color.WHITE;
        boolean unique = false;

        for(int i = 0; i < text.length(); i++)
            instructionSet.AddToSet(factory.ConstructInstruction(text.charAt(i)), UniqueColor(instructionSet));

        instructionSet.WrapUpSet();
        return instructionSet;
    }

    /**
     * Returns a random color not pre-existing in the instruction set
     * @param instructionSet
     * @return
     */
    protected Color UniqueColor(InstructionSet instructionSet){
        Color color = Color.BLACK;

        boolean unique = false;

        while(!unique){
            unique = true;
            //Get new random color
            color = colorList.GetNextColor();
            //Check if it is unique
            for(InstructionColorConversion conversion : instructionSet.instructionSet){
                if(conversion.GetToColor().getRGB() == color.getRGB()){
                    unique = false;
                    break;
                }
            }
        }

        return color;
    }

}

package SirHall.Instructions;

import java.awt.*;

@FunctionalInterface
public interface InstructionSetElementBuilder{
    InstructionColorConversion Build(Color fromColor, Color toColor);
}
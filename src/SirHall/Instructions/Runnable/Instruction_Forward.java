package SirHall.Instructions.Runnable;

import SirHall.Instructions.StepInfo;

public class Instruction_Forward extends Instruction {
    public void Step(StepInfo stepInfo){
        stepInfo.canvasJar.ApplyBrushAtPosition(stepInfo.ant.GetPosition(), stepInfo.toColor);
        stepInfo.ant.MoveForward();
    }
}

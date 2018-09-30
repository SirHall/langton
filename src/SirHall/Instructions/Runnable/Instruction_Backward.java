package SirHall.Instructions.Runnable;

import SirHall.Instructions.StepInfo;

public class Instruction_Backward extends Instruction{
    public void Step(StepInfo stepInfo){
        stepInfo.canvasJar.ApplyBrushAtPosition(stepInfo.ant.GetPosition(), stepInfo.toColor);
        stepInfo.ant.RotateByDeg(180);
        stepInfo.ant.MoveForward();
    }
}

package SirHall.Instructions;

public class Instruction_Forward_Right_Half extends Instruction{
    public void Step(StepInfo stepInfo){
        stepInfo.canvasJar.ApplyBrushAtPosition(stepInfo.ant.GetPosition(), stepInfo.toColor);
        stepInfo.ant.RotateByDeg(-45);
        stepInfo.ant.MoveForward();
    }
}


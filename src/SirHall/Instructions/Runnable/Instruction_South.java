package SirHall.Instructions.Runnable;

import SirHall.Instructions.StepInfo;

public class Instruction_South extends Instruction{
	public void Step(StepInfo stepInfo){
        	stepInfo.canvasJar.ApplyBrushAtPosition(stepInfo.ant.GetPosition(), stepInfo.toColor);
        	stepInfo.ant.SetRotDeg(270); //Set looking downwards
        	stepInfo.ant.MoveForward();
	}
}



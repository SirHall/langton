package SirHall.Instructions.Runnable;

import SirHall.Instructions.StepInfo;

public class Instruction_West extends Instruction{
	public void Step(StepInfo stepInfo){
        	stepInfo.canvasJar.ApplyBrushAtPosition(stepInfo.ant.GetPosition(), stepInfo.toColor);
        	stepInfo.ant.SetRotDeg(180); //Set looking westwards
        	stepInfo.ant.MoveForward();
	}
}



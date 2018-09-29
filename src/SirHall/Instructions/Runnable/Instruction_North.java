package SirHall.Instructions.Runnable;

import SirHall.Instructions.StepInfo;

public class Instruction_North extends Instruction{
	public void Step(StepInfo stepInfo){
        	stepInfo.canvasJar.ApplyBrushAtPosition(stepInfo.ant.GetPosition(), stepInfo.toColor);
        	stepInfo.ant.SetRotDeg(90); //Set looking upwards
        	stepInfo.ant.MoveForward();
	}
}


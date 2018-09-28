package SirHall.Instructions;

public class Instruction_East extends Instruction{
	public void Step(StepInfo stepInfo){
        	stepInfo.canvasJar.ApplyBrushAtPosition(stepInfo.ant.GetPosition(), stepInfo.toColor);
        	stepInfo.ant.SetRotDeg(0); //Set looking eastwards
        	stepInfo.ant.MoveForward();
	}
}


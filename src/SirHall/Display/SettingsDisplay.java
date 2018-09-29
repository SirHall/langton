package SirHall.Display;

import SirHall.Canvas.Brush;
import SirHall.Program;
import SirHall.Settings.SimulationSettings;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;

public class SettingsDisplay extends JPanel {
    public SettingsDisplay(){
        SetupElements();;
        SetupListeners();
        SetupLayout();
        updateUI();
    }

    private JButton JButton_Run= new JButton("Run");
    private JTextField JTextField_Instruction = new JTextField("LR");

    void SetupElements(){
        JButton_Run.setBounds(0, 0, 64, 32);
        JTextField_Instruction.setBounds(0,32, 200,32);
    }

    void SetupLayout(){
        setLayout(null);
        super.add(JButton_Run);
        super.add(JTextField_Instruction);
    }

    void SetupListeners(){
        JButton_Run.addActionListener(e -> Button_Run(e)); //Beautiful lambda
    }


    /**
     * Is called when the button labelled 'Run'(JRun) is pressed
     * @param e
     */
    void Button_Run(ActionEvent e){

        Program.ActivateSimulation(
                new SimulationSettings(
                        JTextField_Instruction.getText(),
                        400, 400, -90.0f,
                        new Brush()
                )
        ); //Spaghettification!
//        System.out.println("Pressed");
    }

}

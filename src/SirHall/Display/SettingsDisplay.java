package SirHall.Display;

import SirHall.Canvas.Brush;
import SirHall.Program;
import SirHall.Settings.SimulationSettings;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

public class SettingsDisplay extends JPanel {
    public SettingsDisplay(){
        SetupElements();;
        SetupListeners();
        SetupLayout();
        updateUI();
    }



    private JButton JButton_Run= new JButton("Run");

    private JLabel JLabel_InstructionLabel = new JLabel("Instructions:");
    private JTextField JTextField_Instruction = new JTextField("LR");

    private String[] orientations = new String[]{"North", "South", "East", "West"};
    private JList<String> JList_Orientation = new JList<String>(orientations);
    private JFormattedTextField JFormattedTextField_Orientation = new JFormattedTextField(GetFloatFormatter());
    private JLabel JLabel_Orientation = new JLabel("Orientation:");

    private JLabel JLabel_Dimensions = new JLabel("Dimensions");
    private JFormattedTextField JFormattedTextField_Width = new JFormattedTextField(GetIntegerFormatter());
    private JFormattedTextField JFormattedTextField_Height = new JFormattedTextField(GetIntegerFormatter());

    void SetupElements(){
        JButton_Run.setBounds(0, 0, 64, 32);

        JLabel_InstructionLabel.setBounds(0, 32, 128, 32);
        JTextField_Instruction.setBounds(128,32, 200,32);

        JLabel_Orientation.setBounds(0, 64, 128, 32);
        JList_Orientation.setBounds(192, 64, 64, 68);
        JList_Orientation.setSelectedValue("South", true);
        JFormattedTextField_Orientation.setBounds(128, 64, 64, 32);
        JFormattedTextField_Orientation.setValue(-90);

        //{TODO} Setup dimension J components

    }

    void SetupLayout(){
        setLayout(null);
        super.add(JButton_Run);
        super.add(JTextField_Instruction);
        super.add(JLabel_InstructionLabel);
        super.add(JList_Orientation);
        super.add(JFormattedTextField_Orientation);
        super.add(JLabel_Orientation);
        super.add(JLabel_Dimensions);
        super.add(JFormattedTextField_Height);
        super.add(JFormattedTextField_Width);
    }

    void SetupListeners(){
        JButton_Run.addActionListener(e -> Button_Run(e)); //Beautiful lambda
        JList_Orientation.addListSelectionListener(e -> SelectOrientation(e));
    }

    /**
     * Is called when the button labelled 'Run'(JRun) is pressed
     * @param e
     */
    void Button_Run(ActionEvent e){

        Program.ActivateSimulation(
                new SimulationSettings(
                        JTextField_Instruction.getText(),
                        400, 400,
                        Float.parseFloat(JFormattedTextField_Orientation.getValue().toString()), //Yes, this is the only way I found to get it working
                        new Brush()
                )
        ); //Spaghettification!
//        System.out.println("Pressed");
    }

    /**
     * Triggers anytime a preset direction is selected
     * @param e
     */
    void SelectOrientation(ListSelectionEvent e){
        if(JList_Orientation.isSelectionEmpty())
            return;
        switch(JList_Orientation.getSelectedValue()){
            case "North":
                JFormattedTextField_Orientation.setValue(90.0);
                break;
            case "South":
                JFormattedTextField_Orientation.setValue(-90.0);
                break;
            case "East":
                JFormattedTextField_Orientation.setValue(0.0);
                break;
            case "West":
                JFormattedTextField_Orientation.setValue(180.0);
                break;
        }
    }

    //Misc below
    NumberFormatter GetFloatFormatter(){
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
        formatter.setValueClass(Float.class);
        formatter.setMinimum(-360.0f);
        formatter.setMaximum(360.0f);
        formatter.setAllowsInvalid(true);
        formatter.setCommitsOnValidEdit(false); //Will only format when edit is finished
        return formatter;
    }

    NumberFormatter GetIntegerFormatter(){
        NumberFormatter formatter = new NumberFormatter(NumberFormat.getInstance());
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(true);
        formatter.setCommitsOnValidEdit(false); //Will only format when edit is finished
        return formatter;
    }



}

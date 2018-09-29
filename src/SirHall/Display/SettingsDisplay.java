package SirHall.Display;

import SirHall.Canvas.Brush;
import SirHall.Program;
import SirHall.Settings.SimulationSettings;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.plaf.FileChooserUI;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.io.File;
import java.text.NumberFormat;

public class SettingsDisplay extends JPanel {
    public SettingsDisplay(){
        SetupElements();;
        SetupListeners();
        SetupLayout();
        updateUI();
    }



    private JButton JButton_Run= new JButton("Run");

    private JLabel JLabel_InstructionLabel = new JLabel("Instructions");
    private JTextField JTextField_Instruction = new JTextField("LR");

    private String[] orientations = new String[]{"North", "South", "East", "West"};
    private JList<String> JList_Orientation = new JList<String>(orientations);
    private JFormattedTextField JFormattedTextField_Orientation = new JFormattedTextField(GetFloatFormatter());
    private JLabel JLabel_Orientation = new JLabel("Orientation");

    private JLabel JLabel_Dimensions = new JLabel("Dimensions (x, y)");
    private JFormattedTextField JFormattedTextField_Width = new JFormattedTextField(GetIntegerFormatter());
    private JFormattedTextField JFormattedTextField_Height = new JFormattedTextField(GetIntegerFormatter());

    private JLabel JLabel_TPS = new JLabel("Ticks/Second");
    private JFormattedTextField JFormattedTextField_TPS = new JFormattedTextField(GetIntegerFormatter());

    private JLabel JLabel_SaveScreenshots = new JLabel("Save screenshots?");
    private JToggleButton JToggleButton_SaveScreenShots = new JToggleButton("", false);

    private JLabel JLabel_SaveEveryNthTick = new JLabel("Save/Nth tick");
    private JFormattedTextField JFormattedTextField_SaveEveryNth = new JFormattedTextField(GetIntegerFormatter());
    private String dir = "";

    void SetupElements(){
        JButton_Run.setBounds(0, 0, 64, 32);

        JLabel_InstructionLabel.setBounds(0, 32, 128, 32);
        JTextField_Instruction.setBounds(128,32, 200,32);

        JLabel_Orientation.setBounds(0, 64, 128, 32);
        JList_Orientation.setBounds(192, 64, 64, 68);
        JList_Orientation.setSelectedValue("South", true);
        JFormattedTextField_Orientation.setBounds(128, 64, 64, 32);
        JFormattedTextField_Orientation.setValue(-90);

        JLabel_Dimensions.setBounds(0, 132, 128, 32);
        JFormattedTextField_Width.setBounds(128, 132, 64, 32);
        JFormattedTextField_Height.setBounds(192, 132, 64, 32);
        JFormattedTextField_Height.setValue(400);
        JFormattedTextField_Width.setValue(400);

        JLabel_TPS.setBounds(0, 164, 128, 32);
        JFormattedTextField_TPS.setValue(1000);
        JFormattedTextField_TPS.setBounds(128, 164, 64, 32);

        JLabel_SaveScreenshots.setBounds(0, 196, 128, 32);
        JToggleButton_SaveScreenShots.setBounds(128, 196 + 8, 16, 16);
        JToggleButton_SaveScreenShots.addActionListener(e -> SelectDirectory(e));

        JLabel_SaveEveryNthTick.setBounds(0, 228, 128, 32);
        JFormattedTextField_SaveEveryNth.setValue(100);
        JFormattedTextField_SaveEveryNth.setBounds(128, 228, 64, 32);

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
        super.add(JLabel_TPS);
        super.add(JFormattedTextField_TPS);
        super.add(JLabel_SaveScreenshots);
        super.add(JToggleButton_SaveScreenShots);
        super.add(JLabel_SaveEveryNthTick);
        super.add(JFormattedTextField_SaveEveryNth);
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
                        (Integer) JFormattedTextField_Height.getValue(),
                        (Integer) JFormattedTextField_Width.getValue(),
                        Float.parseFloat(JFormattedTextField_Orientation.getValue().toString()), //Yes, this is the only way I found to get it working
                        new Brush(),
                        (Integer) JFormattedTextField_TPS.getValue(),
                        JToggleButton_SaveScreenShots.isSelected(),
                        (Integer) JFormattedTextField_SaveEveryNth.getValue(),
                        dir
                )
        ); //Spaghettification!
//        System.out.println("Pressed");
    }

    void SelectDirectory(ActionEvent e){
        if(((JToggleButton)e.getSource()).isSelected()) {
//            FileChooseDir.createAndShowGUI();

            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnVal = fc.showOpenDialog(new FileChooseDir());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                dir = file.getAbsolutePath();
            } else {
                JToggleButton_SaveScreenShots.setSelected(false);
            }
        }
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
        formatter.setMinimum(1); //All settings use a minvalue of 1
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(true);
        formatter.setCommitsOnValidEdit(false); //Will only format when edit is finished
        return formatter;
    }

}

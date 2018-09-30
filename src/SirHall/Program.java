package SirHall;

import SirHall.Canvas.Brush;
import SirHall.Canvas.CanvasJar;
import SirHall.Display.SettingsDisplay;
import SirHall.Display.SimulationDisplay;
import SirHall.Instructions.InstructionSet;
import SirHall.Settings.SimulationSettings;
import javax.swing.*;
import java.awt.*;

public class Program{
    public static void main(String args[]){
        ChangeUITheme();

        frame = SetupFrame("Langton's Ant");

        simulationDisplay = SetupSimulationFrame();
        settingsDisplay = SetupSettingsFrame();

        SetupCard();

        ActivateSettings();
//        ActivateSimulation();
//        frame.repaint();
    }

    static Simulation simulation;

    static JFrame frame;
    static SettingsDisplay settingsDisplay;
    static SimulationDisplay simulationDisplay;
    static CardLayout card;
    static Container container;

//    static JPanel currentPanel;

    static int
            WIDTH = 500,
            HEIGHT = 500;

    static JFrame SetupFrame(String title){
        //Setup frame
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    static void SetupCard(){
//        SetupSimulationFrame();
//        SetupSettingsFrame();

        container = new Container();
        card = new CardLayout();
        container.setLayout(card);
        frame.add(container);
        container.setVisible(true);
        container.add("Settings", settingsDisplay);
        container.add("Simulation", simulationDisplay);

//        frame.add(card);
    }

    static SettingsDisplay SetupSettingsFrame(){
//        EmptyFrame();
        SettingsDisplay settingsDisplay = new SettingsDisplay();
//        frame.add(settingsDisplay);
//        settingsDisplay.setVisible(false);
        return settingsDisplay;
    }

    static SimulationDisplay SetupSimulationFrame(){
//        EmptyFrame();
        SimulationDisplay simulationDisplay = new SimulationDisplay();
//        frame.add(simulationDisplay);
        simulationDisplay.SetFrame(frame);
//        simulationDisplay.setVisible(false);
        return simulationDisplay;
    }

    public static void ActivateSettings(){
        if(simulationDisplay == null || settingsDisplay == null)
            return;
        card.show(container, "Settings");
    }

    public static void ActivateSimulation(SimulationSettings simSettings){
        if(simulationDisplay == null || settingsDisplay == null || simSettings == null)
            return;

        card.show(container, "Simulation");

        simulation = new Simulation(
                new Ant(),
                new InstructionSet(),
                new CanvasJar(simSettings.width , simSettings.height, new Brush()),
                simulationDisplay,
                simSettings
        );
        simulation.StartSimulation();
    }

    static void ChangeUITheme(){
        //'Borrowed' from : https://stackoverflow.com/a/39482204
        UIManager.put( "control", new Color(64, 64, 64) );
        UIManager.put( "info", new Color(128,128,128) );
        UIManager.put( "nimbusBase", new Color(39, 42, 49) );
        UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
        UIManager.put( "nimbusDisabledText", new Color( 128, 128, 128) );
        UIManager.put( "nimbusFocus", new Color(115,164,209) );
        UIManager.put( "nimbusGreen", new Color(176,179,50) );
        UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
        UIManager.put( "nimbusLightBackground", new Color(49, 49, 48) );
        UIManager.put( "nimbusOrange", new Color(191,98,4) );
        UIManager.put( "nimbusRed", new Color(169,46,34) );
        UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
        UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
        UIManager.put( "text", new Color( 230, 230, 230) );
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Show your JFrame
    }
}
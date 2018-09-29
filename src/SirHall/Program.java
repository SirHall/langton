package SirHall;

import SirHall.Canvas.Brush;
import SirHall.Canvas.CanvasJar;
import SirHall.Display.SettingsDisplay;
import SirHall.Display.SimulationDisplay;
import SirHall.Instructions.InstructionSet;
import SirHall.Settings.SimulationSettings;
import SirHall.Simulation;
import SirHall.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Program{
    public static void main(String args[]){
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
                new CanvasJar(simSettings.width , simSettings.height, simSettings.brush),
                simulationDisplay,
                simSettings
        );

//        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
//        executorService.scheduleAtFixedRate(
//                () -> PerformTick(),
//                0, 100, TimeUnit.MICROSECONDS);
//        SaveImage("testImage", simulation.GetCanvas().GetImage());
        simulation.StartSimulation();
    }

    static void SaveImage(String fileName, BufferedImage image){
        File file = new File(fileName + ".png");
        try{
            ImageIO.write(image, "png", file);
        }catch(IOException e){}
    }

//    static void PerformTick(){
////        simulationDisplay.SetImage(simulation.GetCanvas().GetImage());
////        simulation.Tick();
//        simulationDisplay.repaint();
//    }
}
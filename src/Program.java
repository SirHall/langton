import SirHall.Canvas.Brush;
import SirHall.Canvas.CanvasJar;
import SirHall.Display.Display;
import SirHall.Instructions.InstructionSet;
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
        SetupFrame("Langton's Ant");
        simulation = new Simulation(
                new Ant(),
                new InstructionSet(),
                new CanvasJar(400 , 400, new Brush())
        );

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                PerformTick();
            }
        }, 0, 1, TimeUnit.MILLISECONDS);
//        SaveImage("testImage", simulation.GetCanvas().GetImage());

    }

    static Simulation simulation;
    static Display display;

    static int
            WIDTH = 500,
            HEIGHT = 500;

    static JFrame SetupFrame(String title){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        frame.setLocationRelativeTo(null);

        display = new Display();
        frame.add(display);
        display.SetFrame(frame);
//        frame.getContentPane().add(, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    static void SaveImage(String fileName, BufferedImage image){
        File file = new File(fileName + ".png");
        try{
            ImageIO.write(image, "png", file);
        }catch(IOException e){}
    }

    static void PerformTick(){
        display.SetImage(simulation.GetCanvas().GetImage());
        simulation.Tick();
        display.repaint();
    }
}
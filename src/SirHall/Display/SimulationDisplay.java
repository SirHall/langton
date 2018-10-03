package SirHall.Display;

import SirHall.Maths.Vector2D;
import SirHall.Program;
import SirHall.Time.Timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class SimulationDisplay extends JPanel implements KeyListener{
    public SimulationDisplay(){
        setLayout(null);
        SetupKeyListeners();
        DisplayTPS();
    }

    BufferedImage image;
    JFrame frame;

    void SetupKeyListeners(){
        super.updateUI();
        super.setFocusable(true);
        super.addKeyListener(this);
    }

    /**
     * @overrides paintComponent
     * @param g
     */
    public void paintComponent(Graphics g){
        if(image == null || frame == null)
            return;
        this.requestFocusInWindow();

        super.paintComponent(g);
        setBackground(Color.BLACK);
        //Reference: https://stackoverflow.com/a/6565988
        //rs > ri ? (wi * hs/hi, hs) : (ws, hi * ws/wi)
        double ratioImage = (double)image.getWidth() / image.getHeight();
        double ratioScreen = (double)frame.getWidth() / frame.getHeight();

        //Find the correct dimensions for the image to be displayed at it's full size and at it's original
        //aspect ratio within the screen
        Vector2D dimensions =
                ratioScreen > ratioImage
                        ?
                new Vector2D(image.getWidth() * frame.getHeight() / image.getHeight(), frame.getHeight())
                :
                new Vector2D(frame.getWidth(), image.getHeight() * frame.getWidth() / image.getWidth());

        g.drawImage(
                image,
                (int)((frame.getWidth() - dimensions.GetX()) / 2),
                (int)((frame.getHeight() - dimensions.GetY()) / 2),
                (int)dimensions.GetX(),
                (int)dimensions.GetY(),
                null);

        if(displayTPS)
            g.drawString(Long.toString(Program.GetSimulation().GetTPS()) + "t/s", 0, 10);
    }

    public void SetImage(BufferedImage image){
        this.image = image;
    }

    public void SetFrame(JFrame frame){
        this.frame = frame;
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                Program.ClearSimulation();
                Program.ActivateSettings();
                break;
            case KeyEvent.VK_SPACE:
                //Toggle freezing the simulation
                Program.GetSimulation().SetFrozen(!Program.GetSimulation().GetFrozen());
                break;
            case KeyEvent.VK_UP: {//Scope
                Timer timer = Program.GetSimulation().GetSimTimer();
                timer.SetTPS((long) Math.ceil(timer.GetTPS() * 1.5));
                timer.ApplyTPS();
                DisplayTPS();
            }break;
            case KeyEvent.VK_DOWN: {
                Timer timer = Program.GetSimulation().GetSimTimer();
                timer.SetTPS((long) Math.ceil(timer.GetTPS() / 1.5));
                timer.ApplyTPS();
                DisplayTPS();
            }break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

    java.util.Timer tpsDisplayTimer;

    boolean displayTPS = false;

    void DisplayTPS(){
        if(tpsDisplayTimer != null)
            tpsDisplayTimer.cancel(); //Stop the old timer
        displayTPS = true;
        tpsDisplayTimer = new java.util.Timer();
        tpsDisplayTimer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        displayTPS = false;
                    }
                },
                3000
        );
    }

}

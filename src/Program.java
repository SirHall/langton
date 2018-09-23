import javax.swing.*;
import java.awt.*;

public class Program{
    public static void main(String args[]){
        SetupFrame("Test Title");
    }


    static int
            WIDTH = 600,
            HEIGHT = 400;

    static JFrame SetupFrame(String title){
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));

        frame.setLocationRelativeTo(null);

        frame.add(new Display());
//        frame.getContentPane().add(, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
        return frame;
    }

}
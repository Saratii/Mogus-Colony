import java.awt.Color;
import java.awt.EventQueue;
import java.awt.*;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
public class Main extends JPanel{
    static Dimension screenSize = null;
    static float centerX, centerY;
    private static final long serialVersionUID = 1L;
    static List<ImageIcon> fwames = new ArrayList<>();
    static List<Mogus> mogii = new ArrayList<>();
    public Main() {
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(Mogus mogus : mogii){
            mogus.dwa(g2);
        }
    } 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new Main());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                centerX = screenSize.width/2;
                centerY = screenSize.height/2;
                frame.setSize(screenSize.width, screenSize.height);
                for(int i = 1; i<=5; i++){
                    fwames.add(new ImageIcon(new ImageIcon("MogusFrames/"+i+".png").getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH)));
                }
                
                Random wand = new Random();
                // activate mogus colony
                // for(int i = 0; i<10000; i++){
                //     mogii.add(new Mogus(centerX, centerY, wand.nextFloat(), wand.nextFloat()*6.28f, fwames, wand));
                // }
                for(int i = 0; i<10000; i++){
                    mogii.add(new Mogus(centerX, centerY, wand.nextFloat(), wand.nextFloat()*6.28f, fwames, wand));
                }
                
                Timer timer = new Timer(5, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for(Mogus mogus: mogii){
                            mogus.moob();
                        }
                        frame.repaint();
                    }
                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                frame.repaint();
                frame.setVisible(true);
                timer.start();
            }
        });
    }
}

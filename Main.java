import java.awt.Color;
import java.awt.EventQueue;
import java.awt.*;
import java.awt.RenderingHints;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.awt.event.*;

public class Main extends JPanel {
    static Random wand;
    static Dimension screenSize = null;
    static float centerX, centerY;
    private static final long serialVersionUID = 1L;
    static List<ImageIcon> fwames = new ArrayList<>();
    static List<ImageIcon> flippedFwames = new ArrayList<>();
    static List<Mogus> mogii = new ArrayList<>();
    static ImageIcon mogusHill;
    static List<Food> foods = new ArrayList<>();

    public Main() {
        setBackground(Color.BLACK);
        wand = new Random();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.add(new Main());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                centerX = screenSize.width / 2;
                centerY = screenSize.height / 2;
                frame.setSize(screenSize.width, screenSize.height);
                for (int i = 1; i <= 5; i++) {
                    fwames.add(new ImageIcon(new ImageIcon("MogusFrames/" + i + ".png").getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH)));
                }
                for (int i = 1; i <= 5; i++) {
                    flippedFwames.add(new ImageIcon(new ImageIcon("MogusFrames/" + i + "f.png").getImage().getScaledInstance(80, 60, Image.SCALE_SMOOTH)));
                }
                mogusHill = new ImageIcon(new ImageIcon("otherImages/mogusHill.png").getImage());

                
                //mogusColony(wand);
                

                Timer timer = new Timer(5, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Mogus mogus : mogii) {
                            mogus.moob();
                        }
                        mogii.removeIf((Mogus mogus) -> mogus.isHome);
                        frame.repaint();
                    }
                });
                Component mouseClick = new MyComponent(); 
                frame.addMouseListener((MouseListener) mouseClick);
                timer.setRepeats(true);
                timer.setCoalesce(true);
                frame.repaint();
                frame.setVisible(true);
                timer.start();
            }
        });
    } 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        AffineTransform at = AffineTransform.getTranslateInstance(centerX-mogusHill.getIconWidth()/2, centerY-mogusHill.getIconHeight()/2);
        g2.drawImage(mogusHill.getImage(), at, this);
        for (Mogus mogus : mogii) {
            mogus.dwa(g2);
            mogus.food.dwa(g2);
        }
    }
    
    public static class MyComponent extends JComponent implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent arg0) {
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            Food nomnom = new Food(e.getX()-20f, e.getY()-20f, Color.red);
            foods.add(nomnom);
            mogii.add(new Mogus(centerX, centerY, wand.nextFloat()+1, wand.nextFloat() * 6f, fwames, wand, nomnom, flippedFwames));
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }
    }
    public static void mogusColony(Random wand) {
        for (int i = 0; i < 5; i++) {
            mogii.add(new Mogus(centerX, centerY, wand.nextFloat(), wand.nextFloat() * 6f, fwames, wand, null, flippedFwames));
        }
    }
}

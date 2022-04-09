import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.awt.geom.AffineTransform;
public class Mogus extends JPanel{
    float xPos;
    float yPos;
    float V;
    float directionAngle;
    List<ImageIcon> fwames;
    int fwameIndex = 0;
    Random wand;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public Mogus(float xPos, float yPos, float V, float directionAngle, List<ImageIcon> fwames, Random wand){
        this.xPos = xPos;
        this.yPos = yPos;
        this.V = V;
        this.directionAngle = directionAngle;
        this.fwames = fwames;
        this.wand = wand;
    }
    public void moob(){
        xPos += V*Math.cos(directionAngle);
        yPos -= V*Math.sin(directionAngle);
        if(xPos <= 0 || xPos >= screenSize.width-fwames.get(fwameIndex/4).getIconWidth()){
            directionAngle = (float) (Math.PI-directionAngle);
        }
        if(yPos <= 0 || yPos >= screenSize.height-fwames.get(fwameIndex/4).getIconHeight()){
            directionAngle = (float) (2*Math.PI-directionAngle);
        }
        directionAngle += wand.nextDouble()/5.0-0.1;
    }
    public void dwa(Graphics2D g){
        AffineTransform at = AffineTransform.getTranslateInstance(xPos-fwames.get(fwameIndex/4).getIconWidth()/2, yPos-fwames.get(fwameIndex/4).getIconHeight()/2);
        at.rotate(directionAngle, fwames.get(fwameIndex/4).getIconWidth()/2, fwames.get(fwameIndex/4).getIconHeight()/2);
        g.drawImage(fwames.get(fwameIndex/4).getImage(), at, this);
        fwameIndex = (fwameIndex+1)%(4*fwames.size());
    } 
}

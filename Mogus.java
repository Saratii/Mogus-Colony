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
    Food food;
    boolean gwabbed = false;
    boolean isHome = false;
    List<ImageIcon> flippedFwames;
    public Mogus(float xPos, float yPos, float V, float directionAngle, List<ImageIcon> fwames, Random wand, Food food, List<ImageIcon> flippedFwames){
        this.xPos = xPos;
        this.yPos = yPos;
        this.V = V;
        this.directionAngle = directionAngle;
        this.fwames = fwames;
        this.wand = wand;
        this.food = food;
        this.flippedFwames = flippedFwames;
    }
    public void moob(){
        if(gwabbed){
            directionAngle = (float) getDirections(xPos, (float) screenSize.getWidth()/2, yPos, (float) screenSize.getHeight()/2);
        } else {
            directionAngle = (float) getDirections(xPos, food.xPos+20, yPos, food.yPos+60);
            if(Math.pow(xPos-(food.xPos+20), 2)+Math.pow(yPos-(food.yPos+60), 2)<2){
                gwabbed = true;
            }
        }
        xPos += V*Math.cos(directionAngle);
        yPos += V*Math.sin(directionAngle);
        if(gwabbed){
            food.xPos = xPos-20;
            food.yPos = yPos-60;
            if(Math.pow(xPos-(screenSize.getWidth()/2), 2)+Math.pow(yPos-(screenSize.getHeight()/2), 2)<2){
                isHome = true;
            }
        }
        // if(xPos <= 0 || xPos >= screenSize.width-icon.getIconWidth()){
        //     directionAngle = (float) (Math.PI-directionAngle);
        // }
        // if(yPos <= 0 || yPos >= screenSize.height-icon.getIconHeight()){
        //     directionAngle = (float) (2*Math.PI-directionAngle);
        // }
        // directionAngle += wand.nextDouble()/5.0-0.1;
    }
    public void dwa(Graphics2D g){
        ImageIcon icon = fwames.get(fwameIndex/4);
        AffineTransform at = AffineTransform.getTranslateInstance(xPos-icon.getIconWidth()/2, yPos-icon.getIconHeight()/2);
        if(directionAngle%(2*Math.PI) > Math.PI/2 && directionAngle%(2*Math.PI) < 3*Math.PI/2){
            icon = flippedFwames.get(fwameIndex/4);
            at.rotate(directionAngle-Math.PI, icon.getIconWidth()/2, icon.getIconHeight()/2);
        } else {
            at.rotate(directionAngle, icon.getIconWidth()/2, icon.getIconHeight()/2);
        }
        g.drawImage(icon.getImage(), at, this);
        fwameIndex = (fwameIndex+1)%(4*fwames.size());
    } 
    public double getDirections(float x1, float x2, float y1, float y2){
        if(x2-x1==0){
            if(y2>y1){
                return Math.PI*3/2;
            } else {
                return Math.PI/2;
            }
        }
        return Math.atan((y2-y1)/(x2-x1)) + (x2<x1?Math.PI:0);
    }
}

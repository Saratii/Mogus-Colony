import java.awt.Color;
import java.awt.*;

public class Food {
    float xPos;
    float yPos;
    Color color;
    public Food(float xPos, float yPos, Color color){
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }
    public void dwa(Graphics2D g){
        g.setColor(color);
        g.fillRect((int)xPos, (int)yPos, 40, 40);
    }
}

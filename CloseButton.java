import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class CloseButton extends Palette implements Button{
    double x,y,width,height;
    CloseButton(){
        x = 735.4;
        y = 8.2;
        width = 55;
        height = 55;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Rectangle2D.Double buttonBorder = new Rectangle2D.Double(x,y,width,height);
        g2d.setColor(popupBorder);
        g2d.fill(buttonBorder);

        Rectangle2D.Double buttonInner = new Rectangle2D.Double(740.7,13.7,44,44);
        g2d.setColor(popupInner);
        g2d.fill(buttonInner);

        Line2D.Double x1 = new Line2D.Double(750.4,48.2,775.4,23.2);
        Line2D.Double x2 = new Line2D.Double(775.5,47.8,750.2,23.6);
        g2d.setColor(popupBorder);
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(x1);
        g2d.draw(x2);
    }

    @Override
    public void function() {

    }

    @Override
    public String getButton() {
        return "Close";
    }

    @Override
    public int getButtonNum() {
        return 999;
    }

    @Override
    public boolean contains(Point mouseClick) {
        if((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y <= mouseClick.getY() && y + height >= mouseClick.getY())){
            return true;
        }
        return false;
    }
}
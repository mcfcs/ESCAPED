import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Default extends Palette implements Popup{
    String message="";
    int decision;
    ArrayList<Button> exitButton = new ArrayList<>();

    Default(){
        decision = 0;
        exitButton.add(new CloseButton());
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(message != null && decision % 2 == 0) {
            Rectangle2D.Double popUpBorder = new Rectangle2D.Double(101.8, 390.1, 596.4, 169.5);
            g2d.setColor(popupBorder);
            g2d.fill(popUpBorder);

            Rectangle2D.Double popUpInner = new Rectangle2D.Double(117.5, 403, 565, 143.8);
            g2d.setColor(popupInner);
            g2d.fill(popUpInner);

            Font f = new Font("Cascadia Mono", Font.BOLD, 28);
            g2d.setColor(popupBorder);
            g2d.setFont(f);
            g2d.drawString(message, 127, 435);

            for(Button exit : exitButton){
                exit.draw(g2d);
            }
        }
    }

    @Override
    public String getPopupName() {
        return "Default";
    }

    @Override
    public void function(String x){
        message = x;
    }

    @Override
    public ArrayList<Button> getButtons() {
        return exitButton;
    }

    @Override
    public void defaultPopup() {

    }

    @Override
    public boolean hasChanged() {
        return false;
    }

    @Override
    public String whatChanged() {
        return "No Change";
    }
}

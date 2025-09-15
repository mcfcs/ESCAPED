import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Controls implements Popup{

    @Override
    public void draw(Graphics2D g2d) {
        Image controls = new ImageIcon(this.getClass().getResource("/images/Controls.png")).getImage();
        g2d.drawImage(controls, 0, 0, null);
    }

    @Override
    public String getPopupName() {
        return "Controls";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        return null;
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
        return "";
    }
}

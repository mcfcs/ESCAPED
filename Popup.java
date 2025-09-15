import java.awt.*;
import java.util.ArrayList;

public interface Popup {
    void draw(Graphics2D g2d);
    String getPopupName();
    void function(String x);
    ArrayList<Button> getButtons();
    void defaultPopup();
    boolean hasChanged();
    String whatChanged();
}

import java.awt.*;
import java.util.*;

public interface Button {
    void draw(Graphics2D g2d);
    void function();
    String getButton();
    int getButtonNum();

    boolean contains(Point mouseClick);
}

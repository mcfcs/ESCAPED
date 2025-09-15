import java.awt.*;
import java.util.ArrayList;

public interface Floor {
    void draw(Graphics2D g2d);
    String getFloor();
    ArrayList<CollisionArea> getCollisionArea();
    ArrayList<InteractionArea> getInteractionArea();
    ArrayList<ChangeRoomArea> getNextRoom();
    String interact(String x);
    Boolean hasChanged();
    int whatChanged();
    void adjust(int a);
}

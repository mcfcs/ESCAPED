import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Outside extends Palette implements Floor{
    ArrayList<CollisionArea> collisionAreas = new ArrayList<>();
    ArrayList<InteractionArea> interactionAreas = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomAreas = new ArrayList<>();

    Outside(){
        collisionAreas.add(new CollisionArea(0,0,800,161));
        collisionAreas.add(new CollisionArea(0,54.7,26.9,544.2));
        collisionAreas.add(new CollisionArea(772.3,60,26.9,544.2));
        collisionAreas.add(new CollisionArea(0,576.6,801.5,23.4));

        interactionAreas.add(new InteractionArea(0,0,0,0,"Nothing"));
        changeRoomAreas.add(new ChangeRoomArea(0,0,0,0,"Nowhere",0,0));

    }
    @Override
    public void draw(Graphics2D g2d) {
        Image outside = new ImageIcon(this.getClass().getResource("/images/Outside.png")).getImage();
        g2d.drawImage(outside, 0, 0, null);
    }

    @Override
    public String getFloor() {
        return "Outside";
    }

    @Override
    public ArrayList<CollisionArea> getCollisionArea() {
        return collisionAreas;
    }

    @Override
    public ArrayList<InteractionArea> getInteractionArea() {
        return interactionAreas;
    }

    @Override
    public ArrayList<ChangeRoomArea> getNextRoom() {
        return changeRoomAreas;
    }

    @Override
    public String interact(String x) {
        return "Nothing";
    }

    @Override
    public Boolean hasChanged() {
        return false;
    }

    @Override
    public int whatChanged() {
        return 0;
    }

    @Override
    public void adjust(int a) {

    }
}

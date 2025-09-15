import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LeftHallway extends Palette implements Floor{

    Boolean isKeyTaken = false;
    ArrayList<CollisionArea> collisionArea = new ArrayList<>();
    ArrayList<InteractionArea> interactionArea = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomArea = new ArrayList<>();

    LeftHallway(){
        collisionArea.add(new CollisionArea(0,90.3,798.9,111.8));
        collisionArea.add(new CollisionArea(2.1,180.5,17.1,326.8));
        collisionArea.add(new CollisionArea(1.1,492.3,798.9,30));
        collisionArea.add(new CollisionArea(279.8,162.9,153.6,88.3));

        interactionArea.add(new InteractionArea(312.4,251.2,81.2,44.1,"Key"));

        changeRoomArea.add(new ChangeRoomArea(58.5,156.1,80.7,64.5,"Player1Room",377,484.9));
        changeRoomArea.add(new ChangeRoomArea(790.4,184.4,9.6,307.8,"MiddleHallway",35.8,333.9));
    }


    @Override
    public void draw(Graphics2D g2d) {
        Image leftHallway = new ImageIcon(this.getClass().getResource("/images/LeftHallway.png")).getImage();
        g2d.drawImage(leftHallway, 0, 0, null);
        if(!isKeyTaken){
            Image leftHallwayKey = new ImageIcon(this.getClass().getResource("/images/LeftHallwayKey.png")).getImage();
            g2d.drawImage(leftHallwayKey, 0, 0, null);
        }
    }

    @Override
    public String getFloor() {
        return "LeftHallway";
    }

    @Override
    public ArrayList<CollisionArea> getCollisionArea() {
        return collisionArea;
    }

    @Override
    public ArrayList<InteractionArea> getInteractionArea() {
        return interactionArea;
    }

    @Override
    public ArrayList<ChangeRoomArea> getNextRoom() {
        return changeRoomArea;
    }

    @Override
    public String interact(String interaction) {
        switch (interaction) {
            case "Key" -> {
                if(!isKeyTaken) {
                    return "LeftHallwayKey";
                }
                else {
                    return "No Function";
                }
            }
            case "Key Taken" -> {
                isKeyTaken = true;
                return "No Function";
            }
        }
        return "No Change";
    }

    @Override
    public Boolean hasChanged() {
        return isKeyTaken;
    }

    @Override
    public int whatChanged() {
        if(isKeyTaken){
            return 1;
        }
        return 0;
    }

    @Override
    public void adjust(int a) {
        if(a == 1){
            isKeyTaken = true;
        }

    }
}

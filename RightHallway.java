import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RightHallway extends Palette implements Floor{
    ArrayList<CollisionArea> collisionArea = new ArrayList<>();
    ArrayList<InteractionArea> interactionArea = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomArea = new ArrayList<>();
    boolean doorOpened;
    boolean barrierBroken;
    int changes;
    RightHallway(){
        changes = 0;
        doorOpened = false;
        barrierBroken = false;

        collisionArea.add(new CollisionArea(1.1,90.3,798.9,115));
        collisionArea.add(new CollisionArea(282.3,160.7,153.6,88));
        collisionArea.add(new CollisionArea(1.1,486.5,778.1,35.7));
        collisionArea.add(new CollisionArea(776,95.2,22.8,428.3));

        interactionArea.add(new InteractionArea(658.8,184.7,85.7,60.4,"NeedPlayer2RoomKey"));

        changeRoomArea.add(new ChangeRoomArea(0,185.6,9.6,307.9,"MiddleHallway",716.4,333.9));
    }
    @Override
    public void draw(Graphics2D g2d) {
        Image rightHallway = new ImageIcon(this.getClass().getResource("/images/RightHallway.png")).getImage();
        g2d.drawImage(rightHallway, 0, 0, null);
    }

    @Override
    public String getFloor() {
        return "RightHallway";
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
        if(doorOpened && barrierBroken){
            changeRoomArea.add(new ChangeRoomArea(659.8,194.8,85.7,12.5,"Player2Room",377,484.9));
        }
        return changeRoomArea;
    }

    @Override
    public String interact(String interaction) {
        switch (interaction) {
            case "NeedPlayer2RoomKey" -> {
                if(!doorOpened) {
                    return "It seems to be locked.";
                }
                else if(!barrierBroken){
                    return "It's blocked from the inside.";
                }else{
                    return "No Function";
                }
            }
            case "Use Player2Room Key" -> {
                if(!doorOpened) {
                    return "RightHallwayRoom";
                }
                else if(!barrierBroken){
                    return "It's blocked from the inside.";
                }
                return "No Function";
            }
            case "Door Unlocked" -> {
                doorOpened = true;
                return "No Function";
            }
            case "RH Barrier Broken" -> {
                barrierBroken = true;
                return "No Function";
            }
        }
        return "No Function";

    }

    @Override
    public Boolean hasChanged() {
        return doorOpened;
    }

    @Override
    public int whatChanged() {
        if(doorOpened){
            return 1;
        }
        return 0;
    }

    @Override
    public void adjust(int a) {
        if(a == 1){
            doorOpened = true;
            barrierBroken = true;
        }
    }
}

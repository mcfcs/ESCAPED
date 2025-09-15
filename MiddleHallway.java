import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MiddleHallway extends Palette implements Floor{
    ArrayList<CollisionArea> collisionArea = new ArrayList<>();
    ArrayList<InteractionArea> interactionArea = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomArea = new ArrayList<>();
    boolean stairsOpened;
    MiddleHallway(){
        stairsOpened = false;
        collisionArea.add(new CollisionArea(1.1,90.3,798.9,115));
        collisionArea.add(new CollisionArea(1.1,486.5,798.9,35.7));

        interactionArea.add(new InteractionArea(60,186.6,187.2,60.4,"Painting"));
        interactionArea.add(new InteractionArea(351.9,185,92.8,60.4,"LockedDoor"));
        interactionArea.add(new InteractionArea(529.2,189,187.2,60.4,"NeedsCrowbar"));

        changeRoomArea.add(new ChangeRoomArea(0,185.6,9.6,307.9,"LeftHallway",719.1,331.1));
        changeRoomArea.add(new ChangeRoomArea(790.4,184.4,9.6,307.9,"RightHallway",35.8,333.9));
    }

    @Override
    public void draw(Graphics2D g2d) {
        Image middleHallway = new ImageIcon(this.getClass().getResource("/images/MiddleHallway.png")).getImage();
        g2d.drawImage(middleHallway, 0, 0, null);
        if(!stairsOpened){
            Image barrier = new ImageIcon(this.getClass().getResource("/images/MiddleHallwayBarrier.png")).getImage();
            g2d.drawImage(barrier, 0, 0, null);
        }
    }

    @Override
    public String getFloor() {
        return "MiddleHallway";
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
        ArrayList<ChangeRoomArea> middleHallway = new ArrayList<>();
        middleHallway.addAll(changeRoomArea);
        if(stairsOpened){
            middleHallway.add(new ChangeRoomArea(529.2,183.3,187.2,27.1,"LivingRoom",96.8,230.6));
        }
        return middleHallway;
    }
    @Override
    public String interact(String interaction) {
        switch (interaction) {
            case "Painting" -> {
                return "MiddleHallwayPainting";
            }
            case "LockedDoor" -> {
                return "I don't think I want to go in.";
            }
            case "NeedsCrowbar" -> {
                if(!stairsOpened) {
                    return "I need something to break this barrier";
                }
                else{
                    return "No Function";
                }
            }
            case "Use Crowbar" ->{
                return "MiddleHallwayBarrier";
            }
            case "Hallway Barrier Broken" -> {
                stairsOpened = true;
                return "No Function";
            }
            case "isCrowbarNeeded" -> {
                return "True";
            }

        }
        return "No Function";
    }

    @Override
    public Boolean hasChanged() {
        if(stairsOpened){
            return true;
        }
        return false;
    }

    @Override
    public int whatChanged() {
        if(stairsOpened){
            return 1;
        }
        return 0;
    }

    @Override
    public void adjust(int a) {
        if(a == 1){
            stairsOpened = true;
        }
    }
}

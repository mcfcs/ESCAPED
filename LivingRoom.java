import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LivingRoom extends Palette implements Floor{
    ArrayList<CollisionArea> collisionArea = new ArrayList<>();
    ArrayList<InteractionArea> interactionArea = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomArea = new ArrayList<>();
    Boolean doorUnlocked = false;

    LivingRoom(){
        collisionArea.add(new CollisionArea(1.1,55.3,60.6,544.7));
        collisionArea.add(new CollisionArea(36.5,59.9,703.5,118.8));
        collisionArea.add(new CollisionArea(545.8,100,151.6,109.8));
        collisionArea.add(new CollisionArea(718.6,149.9,81.4,116.2));
        collisionArea.add(new CollisionArea(718.6,459.9,81.4,80.1));
        collisionArea.add(new CollisionArea(57.2,391.4,69.9,209.1));
        collisionArea.add(new CollisionArea(264.2,246.2,198.1,67));

        interactionArea.add(new InteractionArea(61.8,342.5,114.5,186.5,"Alcohol"));
        interactionArea.add(new InteractionArea(288.7,188.3,147.1,44.1,"Television"));
        interactionArea.add(new InteractionArea(462.5,194.7,71.3,35,"Barrier"));
        interactionArea.add(new InteractionArea(548.3,215.2,143.7,53.7,"Computer"));
        interactionArea.add(new InteractionArea(314.4,490.3,147.1,44.1,"Front Door"));

        changeRoomArea.add(new ChangeRoomArea(96.8,177.3,89.7,15.3,"MiddleHallway",539.1,262.4));
        changeRoomArea.add(new ChangeRoomArea(790.4,266.1,13.1,193.8,"Kitchen",31.1,345.8));
        changeRoomArea.add(new ChangeRoomArea(324.5,594.6,122.8,5.4,"Outside",368.4,233));
    }
    @Override
    public void draw(Graphics2D g2d) {
        if(!doorUnlocked) {
            Image livingRoom = new ImageIcon(this.getClass().getResource("/images/LivingRoom.png")).getImage();
            g2d.drawImage(livingRoom, 0, 0, null);
        } else{
            Image livingRoom2 = new ImageIcon(this.getClass().getResource("/images/LivingRoomUnlocked.png")).getImage();
            g2d.drawImage(livingRoom2, 0, 0, null);
        }
    }

    @Override
    public String getFloor() {
        return "LivingRoom";
    }

    @Override
    public ArrayList<CollisionArea> getCollisionArea() {
        ArrayList<CollisionArea> collisionAreaUpdated = new ArrayList<>();
        if(!doorUnlocked){
            collisionAreaUpdated.addAll(collisionArea);
            collisionAreaUpdated.add(new CollisionArea(0,533.9,800,66.6));
        } else{
            collisionAreaUpdated.addAll(collisionArea);
            collisionAreaUpdated.add(new CollisionArea(17.8,533.9,307.3,66.6));
            collisionAreaUpdated.add(new CollisionArea(447.6,533.9,307.3,66.6));
        }
        return collisionAreaUpdated;
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
            case "Alcohol" -> {
                return "It reeks.";
            }
            case "Television" -> {
                return "I think its broken.";
            }
            case "Barrier" -> {
                return "It seems very sturdy.";
            }
            case "Computer" ->{
                return "LivingRoomComputer";
            }
            case "Front Door" -> {
                return "LivingRoomDoor";
            }
            case "FrontDoor Unlocked" -> {
                doorUnlocked = true;
                return "No Function";
            }
        }
        return "No Function";
    }

    @Override
    public Boolean hasChanged() {
        return doorUnlocked;
    }

    @Override
    public int whatChanged() {
        if(doorUnlocked){
            return 1;
        }
        return 0;
    }

    @Override
    public void adjust(int a) {
        if(a == 1){
            doorUnlocked = true;
        }
    }
}

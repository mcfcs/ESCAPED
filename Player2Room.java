import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Player2Room extends Palette implements Floor{
    ArrayList<CollisionArea> collisionArea = new ArrayList<>();
    ArrayList<CollisionArea> collisionAreaOpened = new ArrayList<>();
    ArrayList<InteractionArea> interactionArea = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomArea = new ArrayList<>();
    Boolean isDoorUnlocked = false;
    Boolean hasLockKey = false;
    Boolean crowbarTaken = false;
    Boolean lockOpened = false;
    Boolean safeOpen = false;
    Boolean barrierBroken = false;
    Player2Room(){
        collisionArea.add(new CollisionArea(137.2,495.8,513.3,47.2));
        collisionArea.add(new CollisionArea(98.5,82,56.6,450.2));
        collisionArea.add(new CollisionArea(139.3,82,532.1,93));
        collisionArea.add(new CollisionArea(650.5,83.4,68.9,436));
        collisionArea.add(new CollisionArea(147.6,150.3,199,58));
        collisionArea.add(new CollisionArea(538.6,151.5,100.2,43.3));
        collisionArea.add(new CollisionArea(561.4,385.7,47.8,39.9));

        collisionAreaOpened.add(new CollisionArea(137.2,495.8,224.4,47.2));
        collisionAreaOpened.add(new CollisionArea(437.1,495.8,224.4,47.2));
        collisionAreaOpened.add(new CollisionArea(98.5,82,56.6,450.2));
        collisionAreaOpened.add(new CollisionArea(139.3,82,532.1,93));
        collisionAreaOpened.add(new CollisionArea(650.5,83.4,68.9,436));
        collisionAreaOpened.add(new CollisionArea(147.6,150.3,199,58));
        collisionAreaOpened.add(new CollisionArea(538.6,151.5,100.2,43.3));
        collisionAreaOpened.add(new CollisionArea(561.4,385.7,47.8,39.9));


        interactionArea.add(new InteractionArea(346.9,486.8,102.3,44.1,"NeedsCrowbar"));
        interactionArea.add(new InteractionArea(189,198.1,52,44.1,"Safe"));
        interactionArea.add(new InteractionArea(275.6,201.2,52,44.1,"Drawer"));
        interactionArea.add(new InteractionArea(361.6,165.7,65.1,60.5,"Blood"));
        interactionArea.add(new InteractionArea(456.8,155.4,45.8,44.1,"Clock"));
        interactionArea.add(new InteractionArea(578.6,192.8,52,44.1,"Lock"));
        interactionArea.add(new InteractionArea(494.6,368.1,30.1,17.6,"Paper"));

        changeRoomArea.add(new ChangeRoomArea(351.3,589.3,102,10.7,"RightHallway",660.8,248.8));
    }


    @Override
    public void draw(Graphics2D g2d) {
        if(!isDoorUnlocked) {
            Image player2Room1 = new ImageIcon(this.getClass().getResource("/images/Player2RoomClosed.png")).getImage();
            g2d.drawImage(player2Room1, 0, 0, null);
        } else{
            Image player2Room2 = new ImageIcon(this.getClass().getResource("/images/Player2RoomOpened.png")).getImage();
            g2d.drawImage(player2Room2, 0, 0, null);
        }
        if(!lockOpened){
            Image player2RoomLock = new ImageIcon(this.getClass().getResource("/images/Room2Lock.png")).getImage();
            g2d.drawImage(player2RoomLock, 0, 0, null);
        }
        if(!barrierBroken){
            Image player2RoomBarrier = new ImageIcon(this.getClass().getResource("/images/Player2Barrier.png")).getImage();
            g2d.drawImage(player2RoomBarrier, 0, 0, null);
        }
    }

    @Override
    public String getFloor() {
        return "Player2Room";
    }

    @Override
    public ArrayList<CollisionArea> getCollisionArea() {
        if(!isDoorUnlocked) {
            return collisionArea;
        } else if(barrierBroken){
            return collisionAreaOpened;
        }
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
            case "Lock" -> {
                if(barrierBroken && isDoorUnlocked) {
                    return "There's no use in looking inside.";
                }
                if(!hasLockKey){
                    return "I need a key to open this.";
                } else{
                    lockOpened = true;
                    return "Player2Lock";
                }
            }
            case "NeedsCrowbar" -> {
                if(!barrierBroken){
                    return "I need something to break this barrier";
                }
                else if(!isDoorUnlocked){
                    return "The Door seems to be Locked.";
                } else {
                    return "No Function";
                }
            }
            case "Use Crowbar" ->{
                if(!barrierBroken) {
                    return "Player2RoomBarrier";
                } else{
                    if(!isDoorUnlocked){
                        return "The Door seems to be Locked.";
                    }
                    return "No Function";
                }
            }
            case "Barrier Broken" ->{
                barrierBroken = true;
                if(!isDoorUnlocked){
                    return "The Door seems to be Locked.";
                }
                return "No Function";
            }
            case "isBarrierBroken" -> {
                if(barrierBroken){
                    return "True";
                }
                return "False";
            }
            case "Safe" -> {
                if(!safeOpen) {
                    return "Player2Safe";
                } else{
                    return "Player2SafeOpened";
                }
            }
            case "Blood" -> {
                return "Looks like its been here for a while.";
            }
            case "Clock" -> {
                return "It looks like its broken.";
            }
            case "Drawer" -> {
                if(barrierBroken && isDoorUnlocked) {
                    return "Very dirty drawer.";
                } else{
                    return "Player2Drawer";
                }
            }
            case "Paper" -> {
                return "Player2Paper";
            }
            case "Key Taken" ->{
                hasLockKey = true;
                return "No Function";
            }
            case "Crowbar Taken" -> {
                crowbarTaken = true;
                return "No Function";
            }
            case "Unlock Door" -> {
                isDoorUnlocked = true;
                return "No Function";
            }
            case "isCrowbarTaken" -> {
                if(crowbarTaken){
                    return "True";
                }
                return "False";
            }
            case "isCrowbarNeeded" -> {
                return "True";
            }
            case "Safe Open" -> {
                safeOpen = true;
            }
        }
        return "No Function";
    }

    @Override
    public Boolean hasChanged() {
        return barrierBroken;
    }

    @Override
    public int whatChanged() {
        if(barrierBroken && !safeOpen){
            return 1;
        } else if(safeOpen){
            return 2;
        }
        return 0;
    }

    @Override
    public void adjust(int a) {
        if(a == 1){
            barrierBroken = true;
            lockOpened = true;
        } else if(a == 2){
            safeOpen = true;
        }
    }
}


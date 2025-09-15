import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player1Room extends Palette implements Floor{
    ArrayList<CollisionArea> collisionArea = new ArrayList<>();
    ArrayList<CollisionArea> collisionAreaOpened = new ArrayList<>();
    ArrayList<InteractionArea> interactionArea = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomArea = new ArrayList<>();
    Boolean isDoorUnlocked = false;
    Boolean otherPlayer = false;
    Boolean safeKeyTaken = false;
    Boolean doorKeyTaken = false;
    Boolean screwDriverTaken = false;
    Boolean openLock = false;
    Boolean screwDriverTook = false;

    Player1Room(){

        collisionArea.add(new CollisionArea(98.5,82.0,56.6,450.2));
        collisionArea.add(new CollisionArea(138.5,153.5,185.8,45));
        collisionArea.add(new CollisionArea(348.5,131,44.6,45));
        collisionArea.add(new CollisionArea(139.3,82,532.1,72.2));
        collisionArea.add(new CollisionArea(520.9,83.4,149.2,168.5));
        collisionArea.add(new CollisionArea(650.5,83.4,68.9,436));
        collisionArea.add(new CollisionArea(372.1,341.4,203.2,40.4));
        collisionArea.add(new CollisionArea(615.2,444.9,29.5,42.7));

        interactionArea.add(new InteractionArea(180.9,200.1,54.8,44.1,"Lock"));
        interactionArea.add(new InteractionArea(348.5,175.4,38.6,55.7,"Safe"));
        interactionArea.add(new InteractionArea(406.7,153.5,114.2,42.9,"Help"));
        interactionArea.add(new InteractionArea(520.6,251.7,113.7,43.4,"Bookshelf"));
        interactionArea.add(new InteractionArea(337.6,475.3,126.2,21.4,"Door"));
        interactionArea.add(new InteractionArea(605,442.7,43.6,49.3,"Trash"));

        changeRoomArea.add(new ChangeRoomArea(351.3,589.3,102,10.7,"LeftHallway",56.8,253.9));
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(!isDoorUnlocked && !openLock){
            Image player1Room1 = new ImageIcon(this.getClass().getResource("/images/Player1RoomClosed.png")).getImage();
            g2d.drawImage(player1Room1, 0, 0, null);
        } else if(!isDoorUnlocked && openLock){
            Image player1Room2 = new ImageIcon(this.getClass().getResource("/images/Player1RoomClosed2.png")).getImage();
            g2d.drawImage(player1Room2, 0, 0, null);
        } else if(isDoorUnlocked){
            Image player1Room3 = new ImageIcon(this.getClass().getResource("/images/Player1RoomOpened.png")).getImage();
            g2d.drawImage(player1Room3, 0, 0, null);
        }

    }

    @Override
    public String getFloor() {
        return "Player1Room";
    }

    @Override
    public ArrayList<CollisionArea> getCollisionArea() {
        ArrayList<CollisionArea> updatedArea = new ArrayList<>();
        updatedArea.addAll(collisionArea);
        if(!isDoorUnlocked) {
            updatedArea.add(new CollisionArea(137.2,495.8,250.7,47.2));
            updatedArea.add(new CollisionArea(412.1,495.8,247.3,47.2));
        } else{
            updatedArea.add(new CollisionArea(437.1,495.8,222.3,47.2));
        }
        return updatedArea;
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
    public String interact(String interaction){
        switch (interaction) {
            case "Lock" -> {
                if(!safeKeyTaken && !isDoorUnlocked) {
                    return "I need a key to open this.";
                }
                else if(!screwDriverTaken && !safeKeyTaken && otherPlayer){
                    return "I don't really want to get that screwdriver.";
                }
                else if(screwDriverTaken || screwDriverTook){
                    return "It is a very dusty shelf.";
                }
                else {
                    return "Player1Lock";
                }
            }
            case "Safe" -> {
                if(isDoorUnlocked){
                    return "There is a spider inside this safe.";
                }
                return "Player1RoomSafe";
            }
            case "Help" -> {
                return "The wall says Help";
            }
            case "Bookshelf" -> {
                return "Player1RoomBookshelf";
            }
            case "Door" -> {
                if (!isDoorUnlocked && !doorKeyTaken) {
                    return "The Door seems to be Locked.";
                } else if (!isDoorUnlocked){
                    return "Player1RoomDoor";
                } else {
                    return "No Function";
                }
            }
            case "Trash" -> {
                if(!isDoorUnlocked) {
                    return "Player1TrashCan";
                } else{
                    return "Disgusting.";
                }
            }
            case "Key Taken" ->{
                safeKeyTaken = true;
                return "No Function";
            }
            case "Door Key Taken" ->{
                doorKeyTaken = true;
                return "No Function";
            }case "Screwdriver Taken" ->{
                screwDriverTaken = true;
                return "No Function";
            }case "Door Key Used" ->{
                isDoorUnlocked = true;
                return "No Function";
            }case "Screwdriver and Door Key are both Taken" ->{
                screwDriverTaken = true;
                doorKeyTaken = true;
                return "No Function";
            }case "Lock Opened" ->{
                openLock = true;
                return "No Function";
            }
            case "isScrewdriverTaken" -> {
                if(screwDriverTaken){
                    System.out.println("scrwdriver");
                    return "True";
                } else{
                    return "No Function";
                }
            }
        }
        return "No Function";
    }

    @Override
    public Boolean hasChanged() {
        return isDoorUnlocked;
    }
    @Override
    public int whatChanged(){
        if(isDoorUnlocked && !screwDriverTaken){
            return 1;
        } else if(isDoorUnlocked && screwDriverTaken){
            return 2;
        }
        return 0;
    }
    @Override
    public void adjust(int a) {
        if(a == 1){
            isDoorUnlocked = true;
            otherPlayer = true;
        } else if(a == 2){
            isDoorUnlocked = true;
            otherPlayer = true;
            screwDriverTook = true;
        }
    }
}

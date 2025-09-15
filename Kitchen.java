import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Kitchen extends Palette implements Floor{
    ArrayList<CollisionArea> collisionArea = new ArrayList<>();
    ArrayList<InteractionArea> interactionArea = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomArea = new ArrayList<>();
    Boolean isUnlocked;
    Boolean screwdriverUsed = false;
    public Kitchen() {
        isUnlocked = false;
        collisionArea.add(new CollisionArea(-5.8,219,42.4,46.7));
        collisionArea.add(new CollisionArea(28.8,69.2,42.4,196.5));
        collisionArea.add(new CollisionArea(36.5,59.9,504.9,153.8));
        collisionArea.add(new CollisionArea(541.3,59.9,228.8,108.5));
        collisionArea.add(new CollisionArea(740,57.1,42.4,530.8));
        collisionArea.add(new CollisionArea(42.6,540,717.4,40.4));
        collisionArea.add(new CollisionArea(30.5,459.8,42.4,120.6));
        collisionArea.add(new CollisionArea(0,458.8,42.4,46.7));
        collisionArea.add(new CollisionArea(333.3,311.1,207.7,153.8));
        collisionArea.add(new CollisionArea(290.8,336,42.5,53.5));
        collisionArea.add(new CollisionArea(410.3,273.6,53.8,53.5));
        collisionArea.add(new CollisionArea(539.6,338.6,48.2,53.5));

        interactionArea.add(new InteractionArea(71.2,212.6,98.6,62.2,"Sink"));
        interactionArea.add(new InteractionArea(316.5,217,72.7,61.1,"Refrigerator"));

        changeRoomArea.add(new ChangeRoomArea(-4.2,265,13.1,193.8,"LivingRoom",719.1,342.5));
        changeRoomArea.add(new ChangeRoomArea(540.5,160.6,199.5,17.6,"Basement",448.6,245.7));
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(!isUnlocked) {
            Image kitchen = new ImageIcon(this.getClass().getResource("/images/Kitchen.png")).getImage();
            g2d.drawImage(kitchen, 0, 0, null);
        } else{
            Image kitchen2 = new ImageIcon(this.getClass().getResource("/images/KitchenUnlocked.png")).getImage();
            g2d.drawImage(kitchen2, 0, 0, null);
        }
    }

    @Override
    public String getFloor() {
        return "Kitchen";
    }

    @Override
    public ArrayList<CollisionArea> getCollisionArea() {
        return collisionArea;
    }

    @Override
    public ArrayList<InteractionArea> getInteractionArea() {
        ArrayList<InteractionArea> updatedArea = new ArrayList<>();
        updatedArea.addAll(interactionArea);
        if(!isUnlocked){
            updatedArea.add(new InteractionArea(220.4,213.7,71.7,61.1,"NeedsBasementKey"));
        }
        else if(isUnlocked && !screwdriverUsed){
            updatedArea.add(new InteractionArea(220.4,213.7,71.7,61.1,"NeedsScrewdriver"));
        }
        else if(isUnlocked && screwdriverUsed){
            updatedArea.add(new InteractionArea(220.4,213.7,71.7,61.1,"KitchenLock2"));
        }
        return updatedArea;
    }

    @Override
    public ArrayList<ChangeRoomArea> getNextRoom() {
        return changeRoomArea;
    }

    @Override
    public String interact(String interaction) {
        switch (interaction) {
            case "Sink" -> {
                return "The sink has molds.";
            }
            case "NeedsBasementKey" -> {
                if(!isUnlocked) {
                    return "I need a key to open this.";
                }
            }
            case "isBasementKeyNeeded" -> {
                return "True";
            }
            case "Use Basement Key" -> {
                if(!isUnlocked) {
                    return "KitchenLock";
                } else{
                    if(!screwdriverUsed){
                        return "I need a screwdriver to open something inside.";
                    } else{
                        return "KitchenLockCase";
                    }
                }
            }
            case "Refrigerator" -> {
                return "I can smell the rot from here.";
            }
            case "Kitchen Lock Opened" -> {
                isUnlocked = true;
                return "No Function";
            }
            case "isScrewdriverNeeded" -> {
                return "Yes";
            }
            case "NeedsScrewdriver" -> {
                if(!screwdriverUsed){
                    return "I need a screwdriver here.";
                } else{
                    return "No Function";
                }
            }
            case "Use Screwdriver" -> {
                return "KitchenLockCase";
            }
            case "isBasementKeyUsed" -> {
                if(isUnlocked){
                    return "True";
                } else{
                    return "False";
                }
            }
            case "KitchenLock Unlocked" -> {
                screwdriverUsed = true;
                return "KitchenLockCase";
            }
            case "KitchenLock2" -> {
                return "KitchenLockCase2";
            }

        }
        return "No Function";
    }

    @Override
    public Boolean hasChanged() {
        return isUnlocked;
    }

    @Override
    public int whatChanged() {
        if(isUnlocked && !screwdriverUsed){
            return 1;
        } else if(isUnlocked && screwdriverUsed){
            return 2;
        }
        return 0;
    }

    @Override
    public void adjust(int a) {
        if(a == 1){
            isUnlocked = true;
        } else if(a == 2){
            isUnlocked = true;
            screwdriverUsed = true;
        }
    }
}

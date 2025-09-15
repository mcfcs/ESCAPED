import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Basement extends Palette implements Floor{
    ArrayList<CollisionArea> collisionArea = new ArrayList<>();
    ArrayList<InteractionArea> interactionArea = new ArrayList<>();
    ArrayList<ChangeRoomArea> changeRoomArea = new ArrayList<>();
    Boolean ventOpened;
    Boolean isKeyTaken;
    Boolean nothingInside;
    Basement(){
        ventOpened = false;
        nothingInside = false;
        isKeyTaken = false;
        collisionArea.add(new CollisionArea(25.4,45.5,752.4,28.7));
        collisionArea.add(new CollisionArea(46.1,73.9,377.6,94.7));
        collisionArea.add(new CollisionArea(45.9,168.9,291.5,62.7));
        collisionArea.add(new CollisionArea(47.6,232.6,161.6,18.5));
        collisionArea.add(new CollisionArea(46.1,251.7,45.9,102.2));
        collisionArea.add(new CollisionArea(23.3,73.1,22.9,466.9));
        collisionArea.add(new CollisionArea(23.8,514.3,752.4,28.7));
        collisionArea.add(new CollisionArea(752,73.1,22.6,470.6));
        collisionArea.add(new CollisionArea(512.7,74.2,261.9,91.6));
        collisionArea.add(new CollisionArea(49.7,391.8,86,122.5));
        collisionArea.add(new CollisionArea(506,367.5,246.2,150.1));

        interactionArea.add(new InteractionArea(653,165.1,100.2,55.7,"NeedsCrowbar"));
        interactionArea.add(new InteractionArea(156.5,256.7,131,55.7,"Boxes"));
        interactionArea.add(new InteractionArea(43.8,278.2,87.9,92.8,"Bottles"));
        interactionArea.add(new InteractionArea(51.2,374.7,103,126,"Skulls"));
        interactionArea.add(new InteractionArea(496.1,357.5,237.8,136.2,"Spiders"));

        changeRoomArea.add(new ChangeRoomArea(422.5,168.6,90.4,11.1,"Kitchen",555,226));
    }
    @Override
    public void draw(Graphics2D g2d) {
        Image basement = new ImageIcon(this.getClass().getResource("/images/Basement.png")).getImage();
        g2d.drawImage(basement, 0, 0, null);
        if(ventOpened){
            Image basementVent = new ImageIcon(this.getClass().getResource("/images/BasementVentOpened.png")).getImage();
            g2d.drawImage(basementVent, 0, 0, null);
        }
    }

    @Override
    public String getFloor() {
        return "Basement";
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
        switch (interaction){
            case "Bottles" -> {
                return "Smells like alcohol.";
            }
            case "Skulls" -> {
                return "That's creepy";
            }
            case "Spiders" -> {
                return "They seem to be sleeping";
            }
            case "Boxes" -> {
                return "I don't want to check whats inside.";
            }
            case "NeedsCrowbar" -> {
                if(!nothingInside) {
                    if (!ventOpened) {
                        return "I need a crowbar to open this.";
                    } else {
                        return "BasementVentOpened";
                    }
                } else{
                    return "There is nothing inside.";
                }
            }
            case "isCrowbarNeeded" -> {
                return "True";
            }
            case "Use Crowbar" -> {
                if(!nothingInside) {
                    if (!ventOpened) {
                        return "BasementVent";
                    } else {
                        return "BasementVentOpened";
                    }
                } else{
                    return "There is nothing inside";
                }
            }
            case "Vent Opened" -> {
                ventOpened = true;
                return "No Function";
            }
            case "Basement Key Taken" -> {
                isKeyTaken = true;
                nothingInside = true;

                return "No Function";
            }
            case "isBasementKeyTaken" -> {
                if(isKeyTaken){
                    return "True";
                } else{
                    return "False";
                }
            }
        }
        return "No Function";
    }

    @Override
    public Boolean hasChanged() {
        return nothingInside || ventOpened;
    }

    @Override
    public int whatChanged() {
        if(ventOpened && !nothingInside){
            return 1;
        } else if(ventOpened && nothingInside){
            return 2;
        }
        return 0;
    }

    @Override
    public void adjust(int a) {
        if(a == 1){
            ventOpened = true;
        } else if(a == 2){
            ventOpened = true;
            nothingInside = true;
        }
    }
}

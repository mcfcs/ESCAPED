import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class KitchenLockCase extends Palette implements Popup{
    Button closeButton = new CloseButton();
    Boolean Unlocked;
    Boolean isLooking;
    KitchenLockCase(){
        Unlocked = false;
        isLooking = false;
    }

    @Override
    public void draw(Graphics2D g2d) {
        Image kitchenPopup = new ImageIcon(this.getClass().getResource("/images/KitchenCabinet.png")).getImage();
        g2d.drawImage(kitchenPopup, 0, 0, null);

        if(!Unlocked){
            Image kitchenLock = new ImageIcon(this.getClass().getResource("/images/KitchenCabinetLock.png")).getImage();
            g2d.drawImage(kitchenLock, 0, 0, null);
        }
        if(isLooking){
            Image kitchenNote = new ImageIcon(this.getClass().getResource("/images/KitchenNote.png")).getImage();
            g2d.drawImage(kitchenNote, 0, 0, null);
        }


        closeButton.draw(g2d);
    }

    @Override
    public String getPopupName() {
        return "KitchenLockCase";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        if(!Unlocked){
            buttons.add(new OpenLock());
        }
        if(!isLooking){
            buttons.add(new SeeNote());
        }

        buttons.add(closeButton);
        return buttons;
    }

    @Override
    public void defaultPopup() {
        isLooking = false;
    }

    @Override
    public boolean hasChanged() {
        return Unlocked;
    }

    @Override
    public String whatChanged() {
        if(Unlocked){
            return "KitchenLock Unlocked";
        }
        return "No Change";
    }

    public class OpenLock implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            Unlocked = true;
        }

        @Override
        public String getButton() {
            return "";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((388.8 <= mouseClick.getX() && 388.8 + 411.2 >= mouseClick.getX()) && (360.7 <= mouseClick.getY() && 360.7+210.4 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }

    public class SeeNote implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            isLooking = true;
        }

        @Override
        public String getButton() {
            return " ";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((418.4 <= mouseClick.getX() && 418.4 + 159.2 >= mouseClick.getX()) && (373.5 <= mouseClick.getY() && 373.5+177.3 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
}

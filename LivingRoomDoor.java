import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class LivingRoomDoor extends Palette implements Popup{
    ArrayList<Button> frontDoorCode = new ArrayList<>();

    String leftCode = "";
    String rightCode = "";
    Boolean isUnlocked = false;
    Button closeButton = new CloseButton();
    Color identification;
    LivingRoomDoor(){
        identification = (cobweb);
        frontDoorCode.add(new FrontDoorButtons(78.4,127.8,54.6,55.9,"left","1"));
        frontDoorCode.add(new FrontDoorButtons(153.7,127.8,54.6,55.9,"left","2"));
        frontDoorCode.add(new FrontDoorButtons(78.4,199.1,54.6,55.9,"left","3"));
        frontDoorCode.add(new FrontDoorButtons(153.7,199.1,54.6,55.9,"left","4"));
        frontDoorCode.add(new FrontDoorButtons(78.4,271.1,54.6,55.9,"left","5"));
        frontDoorCode.add(new FrontDoorButtons(153.7,271.1,54.6,55.9,"left","6"));
        frontDoorCode.add(new FrontDoorButtons(78.4,343.1,54.6,55.9,"left","7"));
        frontDoorCode.add(new FrontDoorButtons(153.7,343.1,54.6,55.9,"left","8"));
        frontDoorCode.add(new FrontDoorButtons(78.4,412.7,54.6,55.9,"left","9"));
        frontDoorCode.add(new FrontDoorButtons(153.7,412.7,54.6,55.9,"left","0"));

        frontDoorCode.add(new FrontDoorButtons(578.4,130.3,54.6,55.9,"right","1"));
        frontDoorCode.add(new FrontDoorButtons(655.9,130.3,54.6,55.9,"right","2"));
        frontDoorCode.add(new FrontDoorButtons(578.4,201.1,54.6,55.9,"right","3"));
        frontDoorCode.add(new FrontDoorButtons(655.9,201.1,54.6,55.9,"right","4"));
        frontDoorCode.add(new FrontDoorButtons(578.4,272,54.6,55.9,"right","5"));
        frontDoorCode.add(new FrontDoorButtons(655.9,272,54.6,55.9,"right","6"));
        frontDoorCode.add(new FrontDoorButtons(578.4,342.8,54.6,55.9,"right","7"));
        frontDoorCode.add(new FrontDoorButtons(655.9,342.8,54.6,55.9,"right","8"));
        frontDoorCode.add(new FrontDoorButtons(578.4,413.6,54.6,55.9,"right","9"));
        frontDoorCode.add(new FrontDoorButtons(655.9,413.6,54.6,55.9,"right","0"));

        frontDoorCode.add(new FrontDoorButtons(454.1,236.3,81.2,44.3,null,"enter"));
        frontDoorCode.add(new FrontDoorButtons(453.1,329.9,80.2,44.3,null,"reset"));

    }
    @Override
    public void draw(Graphics2D g2d) {
        Image frontDoor = new ImageIcon(this.getClass().getResource("/images/FrontDoor.png")).getImage();
        g2d.drawImage(frontDoor, 0, 0, null);

        Rectangle2D.Double checker = new Rectangle2D.Double(397.4,294.9,105.7,19.1);
        g2d.setColor(identification);
        g2d.fill(checker);
        closeButton.draw(g2d);

        Font f = new Font("Cascadia Mono", Font.BOLD, 30);
        g2d.setColor(black);
        g2d.setFont(f);

        g2d.drawString(leftCode, 356,176);
        g2d.drawString(rightCode, 356,447);
    }

    @Override
    public String getPopupName() {
        return "LivingRoomDoor";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> livingRoomButtons = new ArrayList<>();
        if(!isUnlocked){
            livingRoomButtons.addAll(frontDoorCode);
        }
        return livingRoomButtons;
    }

    @Override
    public void defaultPopup() {

    }

    @Override
    public boolean hasChanged() {
        return isUnlocked;
    }

    @Override
    public String whatChanged() {
        if(isUnlocked){
            return "FrontDoor Unlocked";
        }
        return "No Change";
    }
    public class FrontDoorButtons extends Palette implements Button{
        double x,y,width,height;
        String part,function;
        FrontDoorButtons(double x, double y, double width, double height,String part, String function){
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.part = part;
            this.function=function;
        }

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            if(part!=null){
                if(part.equals("left") && leftCode.length() < 4){
                    leftCode += function;
                    identification = cobweb;
                } else if(part.equals("right") && rightCode.length() < 4 ){
                    rightCode += function;
                    identification = cobweb;
                }
            } else {
                if(function.equals("enter")){
                    if(leftCode.equals("2416") && rightCode.equals("8006")){
                        identification = safeEnter;
                        isUnlocked = true;
                    } else{
                        leftCode = "";
                        rightCode = "";
                        identification = safeDelete;
                    }
                } else if(function.equals("reset")){
                    leftCode = "";
                    rightCode = "";
                }
            }

        }

        @Override
        public String getButton() {
            return "A";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y   <= mouseClick.getY() && y+height  >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
}

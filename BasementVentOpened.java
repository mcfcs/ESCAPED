import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.lang.reflect.Array;
import java.util.*;

public class BasementVentOpened extends Palette implements Popup{
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<Button> safeButtons = new ArrayList<>();
    Boolean isSafeOpen = false;
    Boolean isCaseOpen = false;
    Boolean isKeyTaken = false;
    Boolean isPasswordCorrect = false;
    Button closeButton = new CloseButton();
    String code;
    Color identification;

    BasementVentOpened(){
        identification = (cobweb);
        code = "";
        buttons.add(closeButton);
        buttons.add(new OpenSafe());

        safeButtons.add(new SafeButtons(157.1,196.5,77.7,63.7,"7"));
        safeButtons.add(new SafeButtons(234.4,196.5,77.7,63.7,"8"));
        safeButtons.add(new SafeButtons(322.1,196.5,77.7,63.7,"9"));
        safeButtons.add(new SafeButtons(146.6,275.8,77.7,63.7,"4"));
        safeButtons.add(new SafeButtons(234.5,275.8,77.7,63.7,"5"));
        safeButtons.add(new SafeButtons(322.3,275.8,77.7,63.7,"6"));
        safeButtons.add(new SafeButtons(146.5,353.3,77.7,63.7,"1"));
        safeButtons.add(new SafeButtons(234.5,353.3,77.7,63.7,"2"));
        safeButtons.add(new SafeButtons(322.1,353.3,77.7,63.7,"3"));
        safeButtons.add(new SafeButtons(146.9,431,77.7,63.7,"0"));
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(!isSafeOpen){
            Image safeClosed = new ImageIcon(this.getClass().getResource("/images/BasementVent1.png")).getImage();
            g2d.drawImage(safeClosed, 0, 0, null);
        } else if(isSafeOpen && !isCaseOpen){
            Image caseClosed = new ImageIcon(this.getClass().getResource("/images/BasementVent2.png")).getImage();
            g2d.drawImage(caseClosed, 0, 0, null);

            Rectangle2D.Double id = new Rectangle2D.Double(546,155.9,42,105.3);
            g2d.setColor(identification);
            g2d.fill(id);

            Font f = new Font("Cascadia Mono", Font.BOLD, 30);
            g2d.setColor(cobweb);
            g2d.setFont(f);
            g2d.drawString(code, 220, 154);


        } else if(isSafeOpen && isCaseOpen){
            Image caseOpened = new ImageIcon(this.getClass().getResource("/images/BasementVent3.png")).getImage();
            g2d.drawImage(caseOpened, 0, 0, null);
            if(!isKeyTaken){
                Image key = new ImageIcon(this.getClass().getResource("/images/BasementVentKey.png")).getImage();
                g2d.drawImage(key, 0, 0, null);
            }
        }
        closeButton.draw(g2d);
    }

    @Override
    public String getPopupName() {
        return "BasementVentOpened";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> currentButtons = new ArrayList<>();
        if(!isSafeOpen && !isCaseOpen && !isPasswordCorrect){
            currentButtons.addAll(buttons);
        } else if(isSafeOpen && !isCaseOpen && !isPasswordCorrect){
            currentButtons.addAll(safeButtons);
        } else if(isSafeOpen && !isCaseOpen && isPasswordCorrect){
            currentButtons.add(new OpenCase());
        } else if(isCaseOpen){
            currentButtons.add(new GetKey());
        }
        currentButtons.add(closeButton);
        return currentButtons;
    }

    @Override
    public void defaultPopup() {

    }

    @Override
    public boolean hasChanged() {
        return isKeyTaken;
    }

    @Override
    public String whatChanged() {
        if(isKeyTaken){
            return "Basement Key Taken";
        }
        return "No Change";
    }
    public class OpenSafe implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            isSafeOpen = true;

        }

        @Override
        public String getButton() {
            return "z";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((386.2 <= mouseClick.getX() && 386.2+193.9 >= mouseClick.getX()) && (304.6  <= mouseClick.getY() && 304.6+147.9  >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
    public class SafeButtons extends Palette implements Button{
        double x,y,width,height;
        String function;
        SafeButtons(double x, double y, double width, double height, String function){
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.function=function;
        }

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            if(code.length() <= 4){
                code += function;
                identification = cobweb;
            }
            if(code.length() == 4){
                if(code.equals("9625")){
                    isPasswordCorrect = true;
                    identification = safeEnter;
                } else{
                    isPasswordCorrect = false;
                    identification = safeDelete;
                    code = "";
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
    public class OpenCase implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            isCaseOpen = true;

        }

        @Override
        public String getButton() {
            return "a";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((0 <= mouseClick.getX() && 800 >= mouseClick.getX()) && (0 <= mouseClick.getY() && 600 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
    public class GetKey implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            isKeyTaken = true;

        }

        @Override
        public String getButton() {
            return "he";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((198.7 <= mouseClick.getX() && 198.7+367.2 >= mouseClick.getX()) && (119.8 <= mouseClick.getY() && 119.8+277.3 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player2Safe extends Palette implements Popup {
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<Button> safeButtons = new ArrayList<>();
    Boolean isSafeOpen = false;
    Boolean isPasswordCorrect = false;
    Button closeButton = new CloseButton();
    String code;
    Color identification;

    Boolean note1Opened = false;
    Boolean note2Opened = false;

    Player2Safe(){
        identification = cobweb;
        code = "";
        buttons.add(closeButton);

        safeButtons.add(new SafeButtons(157.1,196.5,77.7,63.7,"7",null));
        safeButtons.add(new SafeButtons(234.4,196.5,77.7,63.7,"8",null));
        safeButtons.add(new SafeButtons(322.1,196.5,77.7,63.7,"9",null));
        safeButtons.add(new SafeButtons(146.6,275.8,77.7,63.7,"4",null));
        safeButtons.add(new SafeButtons(234.5,275.8,77.7,63.7,"5",null));
        safeButtons.add(new SafeButtons(322.3,275.8,77.7,63.7,"6",null));
        safeButtons.add(new SafeButtons(146.5,353.3,77.7,63.7,"1",null));
        safeButtons.add(new SafeButtons(234.5,353.3,77.7,63.7,"2",null));
        safeButtons.add(new SafeButtons(322.1,353.3,77.7,63.7,"3",null));
        safeButtons.add(new SafeButtons(146.9,431,77.7,63.7,"0",null));
        safeButtons.add(new SafeButtons(235.8,431,164.1,65.7,null,"delete"));
        safeButtons.add(new SafeButtons(507.7,232.3,137.8,135.4,null,"enter"));
    }

    @Override
    public void draw(Graphics2D g2d) {
        if(!isSafeOpen){
            Image caseClosed = new ImageIcon(this.getClass().getResource("/images/Player2Safe.png")).getImage();
            g2d.drawImage(caseClosed, 0, 0, null);

            Rectangle2D.Double id = new Rectangle2D.Double(507.7,232.3,137.8,135.4);
            g2d.setColor(identification);
            g2d.fill(id);

            Font f = new Font("Cascadia Mono", Font.BOLD, 30);
            g2d.setColor(black);
            g2d.setFont(f);
            g2d.drawString(code, 220, 154);
        }
        else {
            Image safeInside = new ImageIcon(this.getClass().getResource("/images/Player2SafeInside.png")).getImage();
            g2d.drawImage(safeInside, 0, 0, null);
            if(note1Opened){
                Image note1 = new ImageIcon(this.getClass().getResource("/images/SafeNote1.png")).getImage();
                g2d.drawImage(note1, 0, 0, null);
            } else if(note2Opened){
                Image note2 = new ImageIcon(this.getClass().getResource("/images/SafeNote2.png")).getImage();
                g2d.drawImage(note2, 0, 0, null);
            }
        }
        closeButton.draw(g2d);
    }

    @Override
    public String getPopupName() {
        return "Player2Safe";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {

        ArrayList<Button> currentButtons = new ArrayList<>();

        if(!isSafeOpen && !isPasswordCorrect){
            currentButtons.addAll(safeButtons);
        } else if(!isSafeOpen && isPasswordCorrect){
            currentButtons.add(new OpenSafe());
        } else if(isSafeOpen){
            if(!note1Opened){
                currentButtons.add(new SeeNote(153.3,127.8,165.4,193.2,"openNote1"));
            }
            if(!note2Opened){
                currentButtons.add(new SeeNote(269.4,318.4,204.5,193.2,"openNote2"));
            }
            if(note1Opened){
                currentButtons.add(new SeeNote(0,0,800,600,"closeNote1"));
            }
            if(note2Opened){
                currentButtons.add(new SeeNote(0,0,800,600,"closeNote2"));
            }
        }
        currentButtons.add(closeButton);
        return currentButtons;
    }

    @Override
    public void defaultPopup() {

    }

    @Override
    public boolean hasChanged() {
        return isSafeOpen;
    }

    @Override
    public String whatChanged() {
        if(isSafeOpen){
            return "Safe Open";
        }
        return "No Change";
    }
    public class SafeButtons extends Palette implements Button{
        double x,y,width,height;
        String function, otherFunction;
        SafeButtons(double x, double y, double width, double height, String function, String otherFunction){
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.function=function;
            this.otherFunction = otherFunction;
        }

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            if(function != null){
                if(code.equals("Incorrect!")){
                    code = "";
                }
                if(code.length() < 4){
                    code += function;
                    identification = cobweb;
                }
            } else{
                if(otherFunction.equals("enter")){
                    if(code.equals("1204")){
                        code = "Correct!";
                        isPasswordCorrect = true;
                        identification = safeEnter;
                    } else{
                        code = "Incorrect!";
                        identification = safeDelete;
                    }
                } else if(otherFunction.equals("delete")){
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
            return "null";
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
    public class SeeNote implements Button {
        double x,y,width,height;
        String function;

        SeeNote(double x, double y, double width, double height, String function){
            this.x=x;
            this.y=y;
            this.width = width;
            this.height = height;
            this.function = function;
        }


        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            if(function.equals("openNote1")){
                note1Opened = true;
            } else if(function.equals("closeNote1")){
                note1Opened = false;
            } else if(function.equals("openNote2")){
                note2Opened = true;
            } else if(function.equals("closeNote2")){
                note2Opened = false;
            }
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
            if((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y<= mouseClick.getY() && y+height  >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
}

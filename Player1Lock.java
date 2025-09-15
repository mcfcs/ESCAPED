import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
public class Player1Lock extends Palette implements Popup{

    ArrayList<Button> buttons = new ArrayList<>();

    ArrayList<Button> buttonsOpened = new ArrayList<>();
    int num1,num2,num3,num4;
    Boolean isOpened = false;
    Boolean screwDriverTaken = false;
    Boolean caseOpened = false;
    Boolean isKeyTaken = false;
    Boolean passwordIsCorrect = false;
    Button closeButton = new CloseButton();
    int decision = 0;

    Color darkerButton = safeButtonShade;
    Color normalButton = safeButton;
    Player1Lock(){
        buttons.add(closeButton);
        buttons.add(new ButtonOption("Use key"));

        buttonsOpened.add(new Player1LockNumbers(508.8,295.4,36.1,22.7,1,"add"));
        buttonsOpened.add(new Player1LockNumbers(554.5,295.4,36.1,22.7,2,"add"));
        buttonsOpened.add(new Player1LockNumbers(602.5,295.4,36.1,22.7,3,"add"));
        buttonsOpened.add(new Player1LockNumbers(648.8,295.4,36.1,22.7,4,"add"));
        buttonsOpened.add(new Player1LockNumbers(508.8,352.6,36.1,22.7,1,"subtract"));
        buttonsOpened.add(new Player1LockNumbers(554.5,352.6,36.1,22.7,2,"subtract"));
        buttonsOpened.add(new Player1LockNumbers(602.5,352.6,36.1,22.7,3,"subtract"));
        buttonsOpened.add(new Player1LockNumbers(648.8,352.6,36.1,22.7,4,"subtract"));
        buttonsOpened.add(new Player1LockNumbers(550.3,384.6,104.5,22.7,0,"check"));

        num1 = 0;
        num2 = 0;
        num3 = 0;
        num4 = 0;

    }
    @Override
    public void draw(Graphics2D g2d) {
        if(!isOpened){
            Rectangle2D.Double popUpBorder = new Rectangle2D.Double(101.8, 390.1, 596.4, 169.5);
            g2d.setColor(popupBorder);
            g2d.fill(popUpBorder);

            Rectangle2D.Double popUpInner = new Rectangle2D.Double(117.5, 403, 565, 143.8);
            g2d.setColor(popupInner);
            g2d.fill(popUpInner);

            Font f = new Font("Cascadia Mono", Font.BOLD, 28);
            g2d.setColor(popupBorder);
            g2d.setFont(f);
            g2d.drawString("The key seems to fit in the lock.", 128, 435);

            for(Button button : buttons){
                button.draw(g2d);
            }
        } else{
            if(!caseOpened) {
                Image openedDrawer = new ImageIcon(this.getClass().getResource("/images/OpenedDrawer1.png")).getImage();
                g2d.drawImage(openedDrawer, 0, 0, null);

                Font f = new Font("Cascadia Mono", Font.BOLD, 30);
                g2d.setColor(numbers);
                g2d.setFont(f);
                g2d.drawString(String.valueOf(num1), 519, 345);
                g2d.drawString(String.valueOf(num2), 566, 345);
                g2d.drawString(String.valueOf(num3), 613, 345);
                g2d.drawString(String.valueOf(num4), 660, 345);

                Rectangle2D.Double buttonShade = new Rectangle2D.Double(550.3, 384.6, 104.5, 22.7);
                g2d.setColor(darkerButton);
                g2d.fill(buttonShade);

                Rectangle2D.Double button = new Rectangle2D.Double(556.3, 384.6, 93, 22.7);
                g2d.setColor(normalButton);
                g2d.fill(button);
            } else{
                if(!isKeyTaken) {
                    Image openedCase = new ImageIcon(this.getClass().getResource("/images/OpenedDrawer2.png")).getImage();
                    g2d.drawImage(openedCase, 0, 0, null);
                }else{
                    Image openedCase2 = new ImageIcon(this.getClass().getResource("/images/OpenedDrawer3.png")).getImage();
                    g2d.drawImage(openedCase2, 0, 0, null);
                }

            }
            if(!screwDriverTaken){
                Image screwDriver = new ImageIcon(this.getClass().getResource("/images/Screwdriver.png")).getImage();
                g2d.drawImage(screwDriver,0,0,null);
            }
        }
        closeButton.draw(g2d);
    }

    @Override
    public String getPopupName() {
        return "Player1Lock";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> newButtons = new ArrayList<>();

        if(isOpened){
            newButtons.addAll(buttons);
            if(!screwDriverTaken){
                newButtons.add(new ScrewDriver());
            }
            if(!passwordIsCorrect){
                newButtons.addAll(buttonsOpened);
            }
            if(passwordIsCorrect && !caseOpened){
                newButtons.add(new OpenCase());
            }
            if(caseOpened && !isKeyTaken){
                newButtons.add(new GetKey());
            }
        }
        else{
            newButtons.addAll(buttons);
        }
        return newButtons;
    }

    @Override
    public void defaultPopup() {
        if(decision == 1){
            isOpened = true;
        } else{
            isOpened = false;
        }
    }

    @Override
    public boolean hasChanged() {
        if(screwDriverTaken || isKeyTaken || isOpened){
            return true;
        }
        return false;
    }

    @Override
    public String whatChanged() {
        if(isOpened && !screwDriverTaken && !isKeyTaken){
            return "Lock Opened";
        }
        else if(isKeyTaken && !screwDriverTaken){
            return "Door Key Taken";
        }
        else if(screwDriverTaken && isKeyTaken){
            return "Screwdriver and Door Key are both Taken";
        }
        return "No Change";
    }
    public class ScrewDriver implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            screwDriverTaken = true;
        }

        @Override
        public String getButton() {
            return "not close!";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((62.5 <= mouseClick.getX() && 62.5+147.7 >= mouseClick.getX()) && (185  <= mouseClick.getY() && 185+300.2  >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
    public class ButtonOption extends Palette implements Button{
        String text;
        ButtonOption(String text){
            this.text = text;
        }
        @Override
        public void draw(Graphics2D g2d) {
            Rectangle2D.Double buttonBorder = new Rectangle2D.Double(484.8,488.9,188.6,51.1);
            g2d.setColor(popupBorder);
            g2d.draw(buttonBorder);

            Rectangle2D.Double buttonInner = new Rectangle2D.Double(490.4,494.4,176.6,40.2);
            g2d.setColor(popupInner);
            g2d.draw(buttonInner);

            Font f = new Font("Cascadia Mono", Font.BOLD, 28);
            g2d.setColor(popupBorder);
            g2d.setFont(f);
            g2d.drawString(text, 495, 525);
        }

        @Override
        public void function() {
            isOpened = true;
            decision = 1;
        }

        @Override
        public String getButton() {
            return "I love bini";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((484.8 <= mouseClick.getX() && 484.8 + 188.6 >= mouseClick.getX()) && (488.9 <= mouseClick.getY() && 488.9 + 51.1 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
    public class Player1LockNumbers implements Button{
        double x,y,width,height;
        int buttonNum;
        String function;

        Player1LockNumbers(double x, double y, double width, double height,int buttonNum, String function){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.buttonNum = buttonNum;
            this.function = function;
        }

        @Override
        public void draw(Graphics2D g2d) {
        }

        @Override
        public void function() {
            if(buttonNum !=0) {
                if (function.equals("add")) {
                    if (buttonNum == 1) {
                        num1++;
                        if (num1 == 10) {
                            num1 = 0;
                        }
                    } else if (buttonNum == 2) {
                        num2++;
                        if (num2 == 10) {
                            num2 = 0;

                        }
                    } else if (buttonNum == 3) {
                        num3++;
                        if (num3 == 10) {
                            num3 = 0;
                        }
                    } else if (buttonNum == 4) {
                        num4++;
                        if (num4 == 10) {
                            num4 = 0;
                        }
                    }
                } else if (function.equals("subtract")) {
                    if (buttonNum == 1) {
                        num1--;
                        if (num1 == -1) {
                            num1 = 10;
                        }
                    } else if (buttonNum == 2) {
                        num2--;
                        if (num2 == -1) {
                            num2 = 10;

                        }
                    } else if (buttonNum == 3) {
                        num3--;
                        if (num3 == -1) {
                            num3 = 10;
                        }
                    } else if (buttonNum == 4) {
                        num4--;
                        if (num4 == -1) {
                            num4 = 10;
                        }
                    }
                }
                normalButton = safeButton;
                darkerButton = safeButtonShade;
            } else{
                if(num1 == 2 && num2 == 4 && num3 == 4 && num4 == 4){
                    normalButton = safeEnter;
                    darkerButton = safeEnterShade;
                    passwordIsCorrect = true;
                } else{
                    normalButton = safeDelete;
                    darkerButton = safeDeleteShade;
                }
            }
        }

        @Override
        public String getButton() {
            return "akjshdksah";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y + height  <= mouseClick.getY() && y+height*2  >= mouseClick.getY())){
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
            caseOpened = true;

        }

        @Override
        public String getButton() {
            return "asdhsa";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((433.8 <= mouseClick.getX() && 433.8+335.5 >= mouseClick.getX()) && (224.4 <= mouseClick.getY() && 224.4+262.3 >= mouseClick.getY())){
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
            return "kakampi ang mga tala";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((533.1 <= mouseClick.getX() && 533.1+166.8 >= mouseClick.getX()) && (323.5 <= mouseClick.getY() && 323.5+93.8 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }

}
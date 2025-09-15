import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LivingRoomComputer extends Palette implements Popup{
    String password;
    Boolean isOpened = false;
    Boolean isUnlocked = false;
    Boolean incorrectPassword = false;
    Boolean textOpened = false;
    Boolean folderOpened = false;
    ArrayList<Button> keyboardButtons = new ArrayList<>();
    Button closeButton = new CloseButton();
    Button powerButton = new KeyboardButtons(685.8,345.1,35,35,"power");
    int a = 0;

    LivingRoomComputer(){
        password = "";

        keyboardButtons.add(new KeyboardButtons(113.7,345.1,35,35,"!"));
        keyboardButtons.add(new KeyboardButtons(160.7,345.1,35,35,"@"));
        keyboardButtons.add(new KeyboardButtons(207.7,345.1,35,35,"#"));
        keyboardButtons.add(new KeyboardButtons(254.7,345.1,35,35,"$"));
        keyboardButtons.add(new KeyboardButtons(301.7,345.1,35,35,"%"));
        keyboardButtons.add(new KeyboardButtons(348.7,345.1,35,35,"^"));
        keyboardButtons.add(new KeyboardButtons(391.7,345.1,35,35,"&"));
        keyboardButtons.add(new KeyboardButtons(434.7,345.1,35,35,"*"));
        keyboardButtons.add(new KeyboardButtons(479.1,345.1,35,35,"("));
        keyboardButtons.add(new KeyboardButtons(522.1,345.1,35,35,")"));
        keyboardButtons.add(new KeyboardButtons(571.1,345.5,105.7,35,"backspace"));
        keyboardButtons.add(new KeyboardButtons(131.2,389.3,35,35,"Q"));
        keyboardButtons.add(new KeyboardButtons(175.2,389.3,35,35,"W"));
        keyboardButtons.add(new KeyboardButtons(219.2,389.3,35,35,"E"));
        keyboardButtons.add(new KeyboardButtons(263.2,389.3,35,35,"R"));
        keyboardButtons.add(new KeyboardButtons(307.2,389.3,35,35,"T"));
        keyboardButtons.add(new KeyboardButtons(351.2,389.3,35,35,"Y"));
        keyboardButtons.add(new KeyboardButtons(395.2,389.3,35,35,"U"));
        keyboardButtons.add(new KeyboardButtons(439.2,389.3,35,35,"I"));
        keyboardButtons.add(new KeyboardButtons(483.2,389.3,35,35,"O"));
        keyboardButtons.add(new KeyboardButtons(527.2,389.3,60.5,35,"P"));
        keyboardButtons.add(new KeyboardButtons(597.8,389.3,35,35,"7"));
        keyboardButtons.add(new KeyboardButtons(641.8,389.3,35,35,"8"));
        keyboardButtons.add(new KeyboardButtons(686,389.3,35,35,"9"));
        keyboardButtons.add(new KeyboardButtons(141.7,433.3,35,35,"A"));
        keyboardButtons.add(new KeyboardButtons(185.2,433.3,35,35,"S"));
        keyboardButtons.add(new KeyboardButtons(228.2,433.3,35,35,"D"));
        keyboardButtons.add(new KeyboardButtons(272.2,433.3,35,35,"F"));
        keyboardButtons.add(new KeyboardButtons(315.2,433.3,35,35,"G"));
        keyboardButtons.add(new KeyboardButtons(359.2,433.3,35,35,"H"));
        keyboardButtons.add(new KeyboardButtons(402.2,433.3,35,35,"J"));
        keyboardButtons.add(new KeyboardButtons(445.2,433.3,35,35,"K"));
        keyboardButtons.add(new KeyboardButtons(489.2,433.3,35,35,"L"));
        keyboardButtons.add(new KeyboardButtons(531.6,434.3,56.2,35,"enter"));
        keyboardButtons.add(new KeyboardButtons(597.8,433.3,35,35,"4"));
        keyboardButtons.add(new KeyboardButtons(641.8,433.3,35,35,"5"));
        keyboardButtons.add(new KeyboardButtons(685.8,433.3,35,35,"6"));
        keyboardButtons.add(new KeyboardButtons(160.7,477.9,35,35,"Z"));
        keyboardButtons.add(new KeyboardButtons(202.7,477.9,35,35,"X"));
        keyboardButtons.add(new KeyboardButtons(245.7,477.9,35,35,"C"));
        keyboardButtons.add(new KeyboardButtons(289.7,477.9,35,35,"V"));
        keyboardButtons.add(new KeyboardButtons(333.7,477.9,35,35,"B"));
        keyboardButtons.add(new KeyboardButtons(376.7,477.9,35,35,"N"));
        keyboardButtons.add(new KeyboardButtons(419.7,477.9,35,35,"M"));
        keyboardButtons.add(new KeyboardButtons(463.7,477.9,35,35,"<"));
        keyboardButtons.add(new KeyboardButtons(507.1,477.9,35,35,">"));
        keyboardButtons.add(new KeyboardButtons(597.8,477.9,35,35,"1"));
        keyboardButtons.add(new KeyboardButtons(641.8,477.9,35,35,"2"));
        keyboardButtons.add(new KeyboardButtons(686,477.9,35,35,"3"));
        keyboardButtons.add(new KeyboardButtons(298.7,522.5,289,35," "));
        keyboardButtons.add(new KeyboardButtons(597.8,521.3,79,35,"0"));
        keyboardButtons.add(new KeyboardButtons(686.5,521.3,35,35,"."));
    }




    @Override
    public void draw(Graphics2D g2d) {
        if(!isOpened) {
            Image computerClosed = new ImageIcon(this.getClass().getResource("/images/ComputerClosed.png")).getImage();
            g2d.drawImage(computerClosed, 0, 0, null);
        } else{
            if(!isUnlocked){
                Image computerLocked = new ImageIcon(this.getClass().getResource("/images/ComputerLocked.png")).getImage();
                g2d.drawImage(computerLocked, 0, 0, null);


                Font f = new Font("Arial", Font.BOLD, 28);
                g2d.setColor(black);
                g2d.setFont(f);
                g2d.drawString(password, 236,225);
                if(incorrectPassword){
                    Font fd = new Font("Arial", Font.PLAIN, 15);
                    g2d.setColor(Color.white);
                    g2d.setFont(fd);
                    g2d.drawString("Incorrect Password!", 565,220);
                }
            } else{
                Image computerOpened = new ImageIcon(this.getClass().getResource("/images/ComputerOpened.png")).getImage();
                g2d.drawImage(computerOpened, 0, 0, null);

                if(a == 1){
                    if(textOpened){
                        Image computerText = new ImageIcon(this.getClass().getResource("/images/ComputerText.png")).getImage();
                        g2d.drawImage(computerText, 0, 0, null);
                    }
                    if(folderOpened){
                        Image computerFolder = new ImageIcon(this.getClass().getResource("/images/ComputerFolder.png")).getImage();
                        g2d.drawImage(computerFolder, 0, 0, null);
                    }
                } else if(a == 2){
                    if(folderOpened){
                        Image computerFolder = new ImageIcon(this.getClass().getResource("/images/ComputerFolder.png")).getImage();
                        g2d.drawImage(computerFolder, 0, 0, null);
                    }
                    if(textOpened){
                        Image computerText = new ImageIcon(this.getClass().getResource("/images/ComputerText.png")).getImage();
                        g2d.drawImage(computerText, 0, 0, null);
                    }
                }
            }
        }
        Image computerBlood = new ImageIcon(this.getClass().getResource("/images/ComputerBloodDetail.png")).getImage();
        g2d.drawImage(computerBlood, 0, 0, null);

    }

    @Override
    public String getPopupName() {
        return "LivingRoomComputer";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        if(isOpened && !isUnlocked){
            buttons.addAll(keyboardButtons);
        } else if(isUnlocked){
            if(!folderOpened){
                buttons.add(new Computer(84.8,107.8,50,43.9,"OpenFolder"));
            }
            if(!textOpened){
                buttons.add(new Computer(84.8,44.2,50,43.9,"OpenText"));
            }
            if(folderOpened){
                buttons.add(new Computer(671.3,60.1,24.4,21.8,"CloseFolder"));
            }
            if(textOpened){
                buttons.add(new Computer(515.1,83.3,24.4,21.8,"CloseText"));
            }
        }

        buttons.add(closeButton);
        buttons.add(powerButton);

        return buttons;
    }

    @Override
    public void defaultPopup() {
        isOpened = false;
        isUnlocked = false;
        password = "";

    }

    @Override
    public boolean hasChanged() {
        return false;
    }

    @Override
    public String whatChanged() {
        return null;
    }

    public class KeyboardButtons implements Button{
        double x,y,width,height;
        String function;
        KeyboardButtons(double x, double y, double width, double height, String function){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.function = function;
        }

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            if(isOpened) {
                if (function.equals("enter")) {
                    if (password.equals("MORISSEY5")) {
                        isUnlocked = true;
                    } else {
                        password = "";
                        incorrectPassword = true;
                    }
                } else if (function.equals("backspace")) {
                    if(!password.isEmpty()) {
                        password = password.substring(0, password.length() - 1);
                        incorrectPassword = false;
                    }
                } else if (function.equals("power")){
                    isOpened = false;
                    password = "";
                    incorrectPassword = false;
                } else{
                    password+=function;
                    incorrectPassword = false;
                }
            } else{
                if (function.equals("power")){
                    isOpened = true;
                    incorrectPassword = false;
                }
            }
        }

        @Override
        public String getButton() {
            return "Charice";
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
    public class Computer implements Button {

        double x, y, width, height;
        String function;

        Computer(double x, double y, double width, double height, String function) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.function = function;
        }

        @Override
        public void draw(Graphics2D g2d) {
        }

        @Override
        public void function() {
            if (function.equals("OpenText")) {
                a = 2;
                textOpened = true;
            } else if (function.equals("CloseText")) {
                textOpened = false;
            } else if (function.equals("OpenFolder")) {
                a = 1;
                folderOpened = true;
            } else if (function.equals("CloseFolder")) {
                folderOpened = false;
            }

        }

        @Override
        public String getButton() {
            return "AS";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if ((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y + height <= mouseClick.getY() && y + height * 2 >= mouseClick.getY())) {
                return true;
            }
            return false;
        }
    }
}

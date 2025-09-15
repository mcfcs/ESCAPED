import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player2Drawer extends Palette implements Popup{
    Boolean isLooking = false;
    Color checker;
    Boolean passwordCorrect = false;
    Boolean isOpened = false;
    Boolean keyTaken = false;
    Button closeButton = new CloseButton();
    Button openCase = new Player2Open();
    Button key = new GetKey();
    ArrayList<Button> buttons = new ArrayList<>();
    ArrayList<Button> passwordButtons = new ArrayList<>();

    Player2Drawer(){
        checker = defaultButton;
        buttons.add(closeButton);
        buttons.add(new ButtonOption("Take a look"));

        passwordButtons.add(new Player2Password(27.9,94.5,129,106.9,defaultButton,0));
        passwordButtons.add(new Player2Password(224.9,94.5,129,106.9,defaultButton,0));
        passwordButtons.add(new Player2Password(435.4,94.5,129,106.9,defaultButton,0));
        passwordButtons.add(new Player2Password(646,94.5,129,106.9,defaultButton,0));
        passwordButtons.add(new Player2Password(70.7,457.3,89.9,64.9,checker,999));
    }


    @Override
    public void draw(Graphics2D g2d) {
        if (!isLooking) {
            Rectangle2D.Double popUpBorder = new Rectangle2D.Double(101.8, 390.1, 596.4, 169.5);
            g2d.setColor(popupBorder);
            g2d.fill(popUpBorder);

            Rectangle2D.Double popUpInner = new Rectangle2D.Double(117.5, 403, 565, 143.8);
            g2d.setColor(popupInner);
            g2d.fill(popUpInner);

            Font f = new Font("Cascadia Mono", Font.BOLD, 28);
            g2d.setColor(popupBorder);
            g2d.setFont(f);
            g2d.drawString("Very dusty drawer.", 128, 435);

            for (Button button : buttons) {
                button.draw(g2d);
            }
        } else {
            if (!isOpened) {
                Image drawer1 = new ImageIcon(this.getClass().getResource("/images/Player2Drawer1.png")).getImage();
                g2d.drawImage(drawer1, 0, 0, null);

            }else{
                Image drawer2 = new ImageIcon(this.getClass().getResource("/images/Player2Drawer2.png")).getImage();
                g2d.drawImage(drawer2, 0, 0, null);
        }
            if(isOpened && !keyTaken){
                Image key = new ImageIcon(this.getClass().getResource("/images/Player2Key1.png")).getImage();
                g2d.drawImage(key, 0, 0, null);
            }

        for (Button button : passwordButtons) {
            button.draw(g2d);
        }
    }

        closeButton.draw(g2d);
    }

    @Override
    public String getPopupName() {
        return "Player2Drawer";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> currentButtons = new ArrayList<>();
        if(!isLooking){
            currentButtons.addAll(buttons);
        }else if(!passwordCorrect){
            currentButtons.addAll(passwordButtons);
        }else if(!isOpened){
            currentButtons.add(openCase);
        }else if(!keyTaken){
            currentButtons.add(key);
        }
        currentButtons.add(closeButton);
        return currentButtons;
    }

    @Override
    public void defaultPopup() {
        isLooking = false;
    }


    @Override
    public boolean hasChanged() {
        if(keyTaken){
            return true;
        }
        return false;
    }

    @Override
    public String whatChanged() {
        if(keyTaken){
            return "Key Taken";
        }
        return "No Change";
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
            isLooking = true;
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
    public class Player2Password implements Button{
        double x,y,width,height;
        int code;
        Color color;
        Color checkerColor;
        ArrayList<Color> colors = new ArrayList<>();
        Player2Password(double x, double y, double width, double height,Color color, int code){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
            this.code = code;

            checkerColor = defaultButton;



            colors.add(new Color(203,203,203,255));
            colors.add(new Color(140,182,223,255));
            colors.add(new Color(247,222,99,255));
            colors.add(new Color(225,168,240,255));
            colors.add(new Color(161,230,99,255));
            colors.add(new Color(224,85,68,255));
            colors.add(new Color(225,90,227,255));
            colors.add(new Color(236,151,82,255));
            colors.add(new Color(72,68,251,255));
            colors.add(new Color(131,157,129,255));

        }

        @Override
        public void draw(Graphics2D g2d) {
            Rectangle2D.Double button = new Rectangle2D.Double(x,y,width,height);
            if(code == 999){
                color = checkerColor;
            }

            g2d.setColor(color);
            g2d.fill(button);
        }

        @Override
        public void function() {
            if(!passwordCorrect && code != 999) {
                code++;
                if (code == 10) {
                    code = 0;
                }
                color = colors.get(code);
            } else if(code == 999 && !passwordCorrect){
                if(passwordButtons.get(0).getButtonNum() == 0
                        && passwordButtons.get(1).getButtonNum() == 5
                        && passwordButtons.get(2).getButtonNum() == 0
                        && passwordButtons.get(3).getButtonNum() == 4){
                    checkerColor = safeEnter;
                    passwordCorrect = true;
                } else{
                    checkerColor = safeDelete;
                }
            }
        }

        @Override
        public String getButton() {
            return " a";
        }

        @Override
        public int getButtonNum() {
            return code;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y <= mouseClick.getY() && y + height >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
    public class Player2Open implements Button{

        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            isOpened = true;

        }

        @Override
        public String getButton() {
            return " awds";
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
            keyTaken = true;

        }

        @Override
        public String getButton() {
            return " awds";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((571.8 <= mouseClick.getX() && 571.8+120.7 >= mouseClick.getX()) && (498.4 <= mouseClick.getY() && 498.4+51.3 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
}

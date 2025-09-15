import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Player2Lock extends Palette implements Popup{
    ArrayList<Button> buttons = new ArrayList<>();
    Boolean isOpened = false;
    Boolean crowbarTaken = false;
    Button closeButton = new CloseButton();
    Player2Lock(){
        buttons.add(closeButton);
        buttons.add(new ButtonOption("Use key"));
    }
    @Override
    public void draw(Graphics2D g2d) {
        if(!isOpened) {
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

            for (Button button : buttons) {
                button.draw(g2d);
            }
        } else{
            Image player2Lock = new ImageIcon(this.getClass().getResource("/images/Player2Lock.png")).getImage();
            g2d.drawImage(player2Lock, 0, 0, null);
            if(!crowbarTaken){
                Image crowbar = new ImageIcon(this.getClass().getResource("/images/Crowbar.png")).getImage();
                g2d.drawImage(crowbar, 0, 0, null);
            }

        }
        closeButton.draw(g2d);

    }

    @Override
    public String getPopupName() {
        return "Player2Lock";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        ArrayList<Button> newButtons = new ArrayList<>();
        if(isOpened && !crowbarTaken){
            newButtons.add(new Crowbar());
        }else if(!isOpened){
            newButtons.addAll(buttons);
        }
        return newButtons;
    }

    @Override
    public void defaultPopup() {

    }

    @Override
    public boolean hasChanged() {
        if(crowbarTaken){
            return true;
        }
        return false;
    }

    @Override
    public String whatChanged() {
        if(crowbarTaken){
            return "Crowbar Taken";
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
            isOpened = true;
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
    public class Crowbar implements Button {
        @Override
        public void draw(Graphics2D g2d) {

        }

        @Override
        public void function() {
            crowbarTaken = true;
        }

        @Override
        public String getButton() {
            return "Asda";
        }

        @Override
        public int getButtonNum() {
            return 0;
        }

        @Override
        public boolean contains(Point mouseClick) {
            if((65.7 <= mouseClick.getX() && 65.7+630.4 >= mouseClick.getX()) && (191.6 <= mouseClick.getY() && 191.6+335.4 >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
}

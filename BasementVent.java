import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BasementVent extends Palette implements Popup{
    ArrayList<Button> buttons = new ArrayList<>();
    Boolean isOpened = false;
    Button closeButton = new CloseButton();

    BasementVent(){
        buttons.add(closeButton);
        buttons.add(new ButtonOption("Open"));
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
            g2d.drawString("I can open this with my Crowbar", 128, 435);

            for(Button button : buttons){
                button.draw(g2d);
            }
        }

    }

    @Override
    public String getPopupName() {
        return "BasementVent";
    }

    @Override
    public void function(String x) {

    }

    @Override
    public ArrayList<Button> getButtons() {
        return buttons;
    }

    @Override
    public void defaultPopup() {

    }

    @Override
    public boolean hasChanged() {
        if(isOpened){
            return true;
        }
        return false;
    }

    @Override
    public String whatChanged() {
        if(isOpened){
            return "Vent Opened";
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
}

/**
This class handles the popup to open the Door in the Right hallway.
 This class extends palette and implements Popup.

 @author Gregorio Delfin P. Pascua (234835)
 @author Antonth Chrisdale C. Lopez (233714)
 @version 14 May 2024

 We have not discussed the Java language code in our program
 with anyone other than our instructor or the teaching assistants
 assigned to this course.

 We have not used Java language code obtained from another student,
 or any other unauthorized source, either modified or unmodified.

 If any Java language code or documentation used in our program
 was obtained from another source, such as a textbook or website,
 that has been clearly noted with a proper citation in the comments
 of our program.
 **/

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class RightHallwayRoom extends Palette implements Popup{
    /**
     * Instantiates all the instance fields requires in this class
     */
    Boolean isOpened;
    ArrayList<Button> buttons;
    Button closeButton;
    RightHallwayRoom(){
        buttons = new ArrayList<>();
        closeButton = new CloseButton();

        buttons.add(closeButton);
        buttons.add(new ButtonOption("Use key"));
        isOpened = false;
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
        }
    }

    @Override
    public String getPopupName() {
        return "RightHallwayRoom";
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
            return "Door Unlocked";
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

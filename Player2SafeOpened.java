/**
 This class handles the popup when the Safe in PLayer 2's room is opened
 It includes the notes to decipher the code to the front door.

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

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player2SafeOpened extends Palette implements Popup{
    /**
     * Instantiates all the instance fields of the Popup Player2SafeOpened.
     */
    Button closeButton;
    Boolean note1Opened;
    Boolean note2Opened;

    /**
     * Constructor that initializes the fields of the Popup Player2SafeOpened.
     */
    Player2SafeOpened(){
        closeButton = new CloseButton();
        note1Opened = false;
        note2Opened = false;
    }

    /**
     *  Draws the images of the popup as well as the notes.
     * @param g2d Graphics2D Object
     */
    @Override
    public void draw(Graphics2D g2d) {
        Image safeInside = new ImageIcon(this.getClass().getResource("/images/Player2SafeInside.png")).getImage();
        g2d.drawImage(safeInside, 0, 0, null);
        if(note1Opened){
            Image note1 = new ImageIcon(this.getClass().getResource("/images/SafeNote1.png")).getImage();
            g2d.drawImage(note1, 0, 0, null);
        } else if(note2Opened) {
            Image note2 = new ImageIcon(this.getClass().getResource("/images/SafeNote2.png")).getImage();
            g2d.drawImage(note2, 0, 0, null);
            closeButton.draw(g2d);
        }
    }

    /**
     * Gets the name of the popup
     * @return the name of the popup
     */
    @Override
    public String getPopupName() {
        return "Player2SafeOpened";
    }

    /**
     * Gets the function of the popup (Only used in the Default popup)
     * @param x message to be displayed (Only used in the Default popup)
     */
    @Override
    public void function(String x) {

    }

    /**
     * Gets the buttons used by the popup
     * @return Arraylist of buttons used by the popup
     */
    @Override
    public ArrayList<Button> getButtons() {

        ArrayList<Button> currentButtons = new ArrayList<>();
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
        currentButtons.add(closeButton);
        return currentButtons;
    }

    /**
     * Adjusts what the popup should be once closed (Not used)
     */
    @Override
    public void defaultPopup() {

    }

    /**
     * Checks whether the popup affected the game or not (Not used)
     * @return whether the popup affected the game or not (Not used)
     */
    @Override
    public boolean hasChanged() {
        return false;
    }

    /**
     * Returns what the popup changed for the game or not (Not used)
     * @return what the popup changed for the game or not (Not used)
     */
    @Override
    public String whatChanged() {
        return "No Change";
    }

    /**
     * Class that implements Button that enables the player to see the notes
     * in the safe. Also includes the button to close the note opened.
     */

    public class SeeNote implements Button {
        double x,y,width,height;
        String function;

        /**
         * Constructor that initializes the fields of the Button.
         * @param x x coordinate of the button
         * @param y y coordinate of the button
         * @param width width of the button
         * @param height height of the button
         * @param function function of the button
         */
        SeeNote(double x, double y, double width, double height, String function){
            this.x=x;
            this.y=y;
            this.width = width;
            this.height = height;
            this.function = function;
        }

        /**
         * Draws the button (Not Used)
         * @param g2d Graphics2D object (Not Used)
         */
        @Override
        public void draw(Graphics2D g2d) {

        }

        /**
         * Void method that does the functions when the button is clicked.
         */
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

        /**
         * Gets the name of the button (Only used for CloseButton())
         * @return name of the Button (Only used for CloseButton())
         */
        @Override
        public String getButton() {
            return " ";
        }

        /**
         * Returns the button number (Only used for Player2Drawer button)
         * @return button number (Only used for Player2Drawer button)
         */

        @Override
        public int getButtonNum() {
            return 0;
        }

        /**
         * Checks whether the player's click of a mouse is within the button area.
         * This is so that the function of the button clicked gets called.
         * @param mouseClick The point where the mouse clicked in the frame.
         * @return whether the player's click of a mouse is within the button area.
         */

        @Override
        public boolean contains(Point mouseClick) {
            if((x <= mouseClick.getX() && x + width >= mouseClick.getX()) && (y<= mouseClick.getY() && y+height  >= mouseClick.getY())){
                return true;
            }
            return false;
        }
    }
}

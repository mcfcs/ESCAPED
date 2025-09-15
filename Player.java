/**
 This class handles all the movement, collisions of the Player. It also includes the player sprite
 and animation when the player is moving.

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.ArrayList;

public class Player extends Palette{
    /**
     * Instantiates all the instance fields of the player.
     */
    private double x,y,moveLEye, moveREye, moveLHand, moveRHand, moveLLeg, moveRLeg,moveEyeDown;
    private Color playerColor;
    private Floor currentRoom;
    private InteractionArea currentInteractionArea;
    private ChangeRoomArea currentChangingRoomArea;
    boolean up,down,left,right,collided,interacting,changing,isGoingUp;
    String direction;
    int playerSprite, animation;

    /**
     * Constructor that initializes the fields of the player.
     * @param a x coordinate of the player
     * @param b y coordinate of the player.
     * @param c Color of the player.
     */
    public Player(double a, double b, Color c){
        this.x = a;
        this.y = b;
        this.playerColor = c;
        up = true;
        down = true;
        left = true;
        right = true;
        collided = false;
        interacting = false;
        changing = false;
        isGoingUp = false;

        moveLEye = 0;
        moveREye = 0;
        moveLHand = 0;
        moveRHand = 0;
        moveLLeg = 0;
        moveRLeg = 0;
        playerSprite = 1;
        animation = 0;
        moveEyeDown = 0;
        setUpAnimationTimer();
    }

    /**
     * Draws the sprite of the player.
     * @param g2d Graphics g2d object
     */
    public void drawSprite(Graphics2D g2d){
        Rectangle2D.Double head = new Rectangle2D.Double(x+12.8,y,20.9,19.6);
        Rectangle2D.Double cheeks = new Rectangle2D.Double(x+11.6,y+9.8,23.5,10.3);
        Rectangle2D.Double neck = new Rectangle2D.Double(x+14.6,y+18.6,17,5.6);
        Rectangle2D.Double leftHand = new Rectangle2D.Double(x+1,y+35.3,6.10,10.3-moveLHand);
        Rectangle2D.Double rightHand = new Rectangle2D.Double(x+40.7,y+35.3,6.1,10.3-moveRHand);
        g2d.setColor(skinTone);
        g2d.fill(head);
        g2d.fill(cheeks);
        g2d.fill(neck);
        g2d.fill(leftHand);
        g2d.fill(rightHand);

        Rectangle2D.Double torso = new Rectangle2D.Double(x+9.9,y+24,27.2,31.9);
        Rectangle2D.Double leftArm1 = new Rectangle2D.Double(x+3.1,y+26.8,7.1,5);
        Rectangle2D.Double leftArm2 = new Rectangle2D.Double(x+1,y+30.3,6.1,5);
        Rectangle2D.Double rightArm1 = new Rectangle2D.Double(x+37.1,y+26.8,7.1,5);
        Rectangle2D.Double rightArm2 = new Rectangle2D.Double(x+40.7,y+30.3,6.1,5);
        g2d.setColor(playerColor);
        g2d.fill(torso);
        g2d.fill(leftArm1);
        g2d.fill(leftArm2);
        g2d.fill(rightArm1);
        g2d.fill(rightArm2);

        Rectangle2D.Double leftPants = new Rectangle2D.Double(x+12.9,y+55.8,8.6,13.9-moveLLeg);
        Rectangle2D.Double rightPants = new Rectangle2D.Double(x+25.7,y+55.8,8.6,13.9-moveRLeg);
        g2d.setColor(pantsColor);
        g2d.fill(leftPants);
        g2d.fill(rightPants);


        Rectangle2D.Double leftEye = new Rectangle2D.Double(x+16.9+moveLEye,y+3.1+moveEyeDown,2.9,6.1);
        Rectangle2D.Double rightEye = new Rectangle2D.Double(x+27+moveREye,y+3.1+moveEyeDown,2.9,6.1);
        Rectangle2D.Double leftShoe = new Rectangle2D.Double(x+12.9,y+69.7-moveLLeg,8.5,5.2);
        Rectangle2D.Double rightShoe = new Rectangle2D.Double(x+25.7,y+69.7-moveRLeg,8.5,5.2);
        g2d.setColor(black);
        g2d.fill(leftEye);
        g2d.fill(rightEye);
        g2d.fill(leftShoe);
        g2d.fill(rightShoe);
    }

    /**
     * Makes the player move horizontally.
     * @param n value added / subtracted to the x coordinate of the player.
     */
    public void moveH(double n){
        x+=n;
    }

    /**
     * Makes the player move vertically.
     * @param n value added / subtracted to the y coordinate of the player.
     */
    public void moveV(double n){
        y += n;
    }

    /**
     * Sets the player's x coordinate.
     * @param n the new value for x
     */
    public void setX(double n){
        x = n;
    }

    /**
     * Sets the player's y coordinate.
     * @param n the new value for y
     */
    public void setY(double n){
        y = n;
    }

    /**
     * Sets the player's floor
     * @param current the new floor for the player.
     */
    public void setFloor(Floor current){
        currentRoom = current;
    }

    /**
     * Gets the x coordinate of the player
     * @return the current x coordinate of the player
     */
    public double getX(){
        return x;
    }

    /**
     * Gets the y coordinate of the player
     * @return the current y coordinate of the player
     */
    public double getY(){
        return y;
    }

    /**
     * Gets the y coordinate of the hit box of the player
     * @return the current y coordinate of the hit box of the player
     */
    public double getCollisionX(){
        return (x+1);
    }
    /**
     * Gets the y coordinate of the hitbox of the player
     * @return the current y coordinate of the hitbox of the player
     */
    public double getCollisionY(){
        return (y+30.3);

    }

    /**
     * Gets the current floor of the player
     * @return the current floor the player is in
     */
    public Floor getFloor(){
        return currentRoom;
    }

    /**
     * Checks if the player can move up
     * @return the boolean if the player can move up
     */
    public boolean canUp(){
        return up;
    }
    /**
     * Checks if the player can move down
     * @return the boolean if the player can move down
     */
    public boolean canDown(){
        return down;
    }
    /**
     * Checks if the player can move left
     * @return the boolean if the player can move left
     */
    public boolean canLeft(){
        return left;
    }
    /**
     * Checks if the player can move right
     * @return the boolean if the player can move right
     */
    public boolean canRight(){
        return right;
    }

    /**
     * Gets the interaction area the player is in
     * @return the interaction area the player is in
     */
    public InteractionArea getInteractionArea(){
        return currentInteractionArea;
    }

    /**
     * Gets the change room area the player is in
     * @return the change room area the player is in
     */
    public ChangeRoomArea getChangeRoomArea(){
        return currentChangingRoomArea;
    }

    /**
     * Checks if the player is colliding to a collision area.
     * @return whether the player is colliding or not
     */

    public boolean isColliding(){
        ArrayList<CollisionArea> collisionChecker = currentRoom.getCollisionArea();
        for(CollisionArea collisionArea : collisionChecker){
            if((this.getCollisionX() <= collisionArea.getX() + collisionArea.getWidth() && 46.1 + this.getCollisionX() >= collisionArea.getX()) && (this.getCollisionY() <= collisionArea.getY()+collisionArea.getHeight() && 47.2 + this.getCollisionY() >= collisionArea.getY())){
                collided = true;
                if(!((this.getCollisionX() + 6<= collisionArea.getX() + collisionArea.getWidth() && 46.1 + this.getCollisionX() + 6 >= collisionArea.getX()) && (this.getCollisionY() <= collisionArea.getY()+collisionArea.getHeight() && 47.2 + this.getCollisionY() >= collisionArea.getY()))){
                    left = false;
                }
                if(!((this.getCollisionX() - 6 <= collisionArea.getX() + collisionArea.getWidth() && 46.1 + this.getCollisionX() - 6>= collisionArea.getX()) && (this.getCollisionY() <= collisionArea.getY()+collisionArea.getHeight() && 47.2 + this.getCollisionY() >= collisionArea.getY()))){
                    right = false;
                }
                if(!((this.getCollisionX() <= collisionArea.getX() + collisionArea.getWidth() && 46.1 + this.getCollisionX() >= collisionArea.getX()) && (this.getCollisionY()+6 <= collisionArea.getY()+collisionArea.getHeight() && 47.2 + this.getCollisionY()+6 >= collisionArea.getY()))){
                    up = false;
                }
                if(!((this.getCollisionX() <= collisionArea.getX() + collisionArea.getWidth() && 46.1 + this.getCollisionX() >= collisionArea.getX()) && (this.getCollisionY()-6 <= collisionArea.getY()+collisionArea.getHeight() && 47.2 + this.getCollisionY()-6 >= collisionArea.getY()))){
                    down = false;
                }
            }

        }
        return collided;
    }

    /**
     * Checks if the player is colliding to an interacting area.
     * @return whether the player is interacting or not
     */
    public boolean isInteracting(){
        ArrayList<InteractionArea> interactionChecker = currentRoom.getInteractionArea();
        for(InteractionArea interactionArea : interactionChecker) {
            if ((this.getCollisionX() <= interactionArea.getX() + interactionArea.getWidth() && 46.1 + this.getCollisionX() >= interactionArea.getX()) && (this.getCollisionY() <= interactionArea.getY() + interactionArea.getHeight() && 47.2 + this.getCollisionY() >= interactionArea.getY())) {
                interacting = true;
                currentInteractionArea = interactionArea;
            }
        }
        return interacting;
    }

    /**
     * Checks if the player is going to be changing rooms.
     * @return whether the player is going to be changing rooms
     */

    public boolean isChangingRooms(){
        ArrayList<ChangeRoomArea> roomChecker = currentRoom.getNextRoom();
        for(ChangeRoomArea changeRoomArea : roomChecker) {
            if ((this.getCollisionX() <= changeRoomArea.getX() + changeRoomArea.getWidth() && 46.1 + this.getCollisionX() >= changeRoomArea.getX()) && (this.getCollisionY() <= changeRoomArea.getY() + changeRoomArea.getHeight() && 47.2 + this.getCollisionY() >= changeRoomArea.getY())) {
                changing = true;
                currentChangingRoomArea = changeRoomArea;
            }
        }
        return changing;
    }

    /**
     * Resolves the collision of the player
     */
    public void resolveCollision(){
        if(collided){
            up = true;
            down = true;
            left = true;
            right = true;
        }
    }

    /**
     * Resolves interacting of the player
     */
    public void resolveInteracting(){
        if(interacting){
            interacting = false;
        }
    }
    /**
     * Resolves change area of the player
     */
    public void resolveChange(){
        if(changing){
            changing = false;
        }
    }

    /**
     * Gets the direction where the player is moving
     * @param x the direction where the player is moving
     */
    public void getMoving(String x){
        direction = x;
        if(direction.equals("right")){
            moveREye = 2.6;
            moveLEye = 5.3;
            moveEyeDown = 0;
            if(playerSprite == 1){
                moveRLeg = 1.9;
                moveLHand = 5.1;
                moveLLeg = 0;
                moveRHand = 0;
            } else{
                moveRLeg = 0;
                moveLHand = 0;
                moveLLeg = 1.9;
                moveRHand = 5.1;
            }
        } else if(direction.equals("left")){
            moveREye = -5.3;
            moveLEye = -2.6;
            moveEyeDown = 0;
            if(playerSprite == 1){
                moveRLeg = 0;
                moveLHand = 0;
                moveLLeg = 1.9;
                moveRHand = 5.1;
            } else{
                moveRLeg = 1.9;
                moveLHand = 5.1;
                moveLLeg = 0;
                moveRHand = 0;
            }
        } else if(direction.equals("up")){
            moveREye = -999;
            moveLEye = -999;
            moveEyeDown = 0;
            if(playerSprite == 1){
                moveRLeg = 1.9;
                moveLHand = 5.1;
                moveLLeg = 0;
                moveRHand = 0;
            } else{
                moveRLeg = 0;
                moveLHand = 0;
                moveLLeg = 1.9;
                moveRHand = 5.1;
            }
        } else if(direction.equals("down")){
            moveREye = 0;
            moveLEye = 0;
            moveEyeDown = 3;
            if(playerSprite == 1){
                moveRLeg = 1.9;
                moveLHand = 5.1;
                moveLLeg = 0;
                moveRHand = 0;
            } else{
                moveRLeg = 0;
                moveLHand = 0;
                moveLLeg = 1.9;
                moveRHand = 5.1;
            }
        }

        else if(direction.equals("idle")){
            moveREye = 0;
            moveLEye = 0;
            moveRLeg = 0;
            moveLHand = 0;
            moveLLeg = 0;
            moveRHand = 0;
            moveEyeDown = 0;
        }
    }

    /**
     * Sets up the animation timer for the sprites
     */
    private void setUpAnimationTimer() {
        ActionListener listener = new AbstractAction() {
            /**
             * To check which sprite to use to create an animation.
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                animation++;
                if(animation % 3 == 0){
                    if(playerSprite == 1){
                        playerSprite = 2;
                    } else{
                        playerSprite = 1;
                    }
                }
            }
        };
        Timer timer = new Timer(100, listener);
        timer.start();
    }
}
/**
 This class is

 @author Gregorio Delfin P. Pascua (234835)
 @author Antonth Chrisdale C. Lopez (233714)
 @version 00 April 2024

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
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.*;

public class GameCanvas extends JComponent {
    private ArrayList<Floor> floors = new ArrayList<Floor>();
    private ArrayList<Popup> popups = new ArrayList<>();
    Player currentPlayer,otherPlayer;
    int playerID;
    Boolean isTogether,popUpEnabled,interact,isSpecialPopup;
    String room, pop;
    Popup currentPU;
    Floor currentF;
    Boolean lightsOn, controlsOn;
    public GameCanvas(){
        controlsOn = true;
        lightsOn = false;
        floors.add(new Player1Room());
        floors.add(new Player2Room());
        floors.add(new LeftHallway());
        floors.add(new MiddleHallway());
        floors.add(new RightHallway());
        floors.add(new LivingRoom());
        floors.add(new Kitchen());
        floors.add(new Basement());
        floors.add(new Outside());

        popups.add(new Default());

        popups.add(new Player1RoomSafe());
        popups.add(new Player1TrashCan());
        popups.add(new Player1Lock());
        popups.add(new Player1RoomDoor());
        popups.add(new Player1RoomBookshelf());

        popups.add(new Player2Drawer());
        popups.add(new Player2Paper());
        popups.add(new Player2Lock());
        popups.add(new Player2RoomBarrier());
        popups.add(new Player2Safe());
        popups.add(new Player2SafeOpened());

        popups.add(new LeftHallwayKey());

        popups.add(new MiddleHallwayPainting());
        popups.add(new MiddleHallwayBarrier());

        popups.add(new RightHallwayRoom());

        popups.add(new LivingRoomComputer());
        popups.add(new LivingRoomDoor());

        popups.add(new KitchenLock());
        popups.add(new KitchenLockCase());
        popups.add(new KitchenLockCase2());

        popups.add(new BasementVent());
        popups.add(new BasementVentOpened());
        popups.add(new Controls());

        isTogether = false;
        isSpecialPopup = false;
        popUpEnabled = false;
        interact = true;
    }

    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);

        Rectangle2D.Double background = new Rectangle2D.Double(0,0,800,600);
        g2d.setColor(Color.BLACK);
        g2d.fill(background);


        for(Floor currentFloor : floors){
            if(currentFloor.getFloor().equals(room)){
                currentF = currentFloor;
                currentPlayer.setFloor(currentFloor);
                currentFloor.draw(g2d);
            }
        }

        if(isTogether) {
            otherPlayer.drawSprite(g2d);
        }

        currentPlayer.drawSprite(g2d);

        float opacity = 0.8f;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

        if(room.equals("Basement") || room.equals("MiddleHallway") || room.equals("LivingRoom") || room.equals("Outside")){
            Image lightsOff = new ImageIcon(this.getClass().getResource("/images/Blackout.png")).getImage();
            g2d.drawImage(lightsOff, 0, 0, null);
        }

        if(room.equals("Kitchen")){
            if(lightsOn) {
                Image kitchenLights = new ImageIcon(this.getClass().getResource("/images/KitchenLightsShape.png")).getImage();
                g2d.drawImage(kitchenLights, 0, 0, null);
            } else{
                Image lightsOff = new ImageIcon(this.getClass().getResource("/images/Blackout.png")).getImage();
                g2d.drawImage(lightsOff, 0, 0, null);
            }
        }
        if(room.equals("LeftHallway")){
            if(lightsOn) {
                Image kitchenLights = new ImageIcon(this.getClass().getResource("/images/LeftHallwayLights.png")).getImage();
                g2d.drawImage(kitchenLights, 0, 0, null);
            }else{
                Image lightsOff = new ImageIcon(this.getClass().getResource("/images/Blackout.png")).getImage();
                g2d.drawImage(lightsOff, 0, 0, null);
            }
        }
        if(room.equals("RightHallway")){
            if(lightsOn) {
                Image kitchenLights = new ImageIcon(this.getClass().getResource("/images/RightHallwayLights.png")).getImage();
                g2d.drawImage(kitchenLights, 0, 0, null);
            } else{
                Image lightsOff = new ImageIcon(this.getClass().getResource("/images/Blackout.png")).getImage();
                g2d.drawImage(lightsOff, 0, 0, null);
            }
        }

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

        if(popUpEnabled && interact && isSpecialPopup){
            for(Popup currentPopup : popups){
                if(pop.equals(currentPopup.getPopupName())){
                    currentPU = currentPopup;
                    currentPopup.draw(g2d);
                }
            }
        }
        else if(popUpEnabled && interact){
            for(Popup drawDefault : popups){
                if("Default".equals(drawDefault.getPopupName())){
                    currentPU = drawDefault;
                    drawDefault.function(pop);
                    drawDefault.draw(g2d);
                }
            }
        }

        if(controlsOn){
            for(Popup controlsPopup : popups){
                if("Controls".equals(controlsPopup.getPopupName())){
                    currentPU = controlsPopup;
                    controlsPopup.draw(g2d);
                }
            }
        }

        if(room.equals("Outside")){
            if(!isTogether) {
                Font f = new Font("Cascadia Mono", Font.BOLD, 21);
                g2d.setColor(Color.white);
                g2d.setFont(f);
                g2d.drawString("Waiting for other player to leave the house.", 140, 60);
            } else if(isTogether){
                Image escaped = new ImageIcon(this.getClass().getResource("/images/Escaped.png")).getImage();
                g2d.drawImage(escaped, 0, 0, null);
            }
        }

    }
    public void passPlayer(Player x, Player y, int pid) {
        this.currentPlayer = x;
        this.otherPlayer = y;
        this.playerID = pid;
        if(playerID == 1){
            room = "Player1Room";
        }else{
            room = "Player2Room";
        }
    }

    public void isTogether(boolean together){
        this.isTogether = together;
    }
    public boolean isPopUp(){
        return popUpEnabled;
    }
    public void popUpEnabled(){
        popUpEnabled = true;
    }
    public void popUpDisabled(){
        currentPU.defaultPopup();
        popUpEnabled = false;
    }
    public void withinInteractionArea(boolean ia){
        interact = ia;
    }
    public Floor getCurrentFloor(){
        return currentF;
    }
    public void popUpChecker(String msg){
        pop = msg;
        for(Popup checker : popups){
            if(checker.getPopupName().equals(pop)){
                isSpecialPopup = true;
                break;
            } else{
                isSpecialPopup = false;
            }
        }
    }
    public Popup getCurrentPopup(){
        return currentPU;
    }
    public String getCurrentRoom(){
        return room;
    }
    public void changeRoom(String room){
        this.room = room;
    }
    public Floor findRoom(String floor){
        for(Floor foundFloor : floors){
            if(foundFloor.getFloor().equals(floor)){
                return foundFloor;
            }
        }
        return null;
    }
    public void lights(Boolean isLightsOn){
        lightsOn = isLightsOn;
    }
    public void controls(int x){
        if(x % 2 == 0) {
            controlsOn = true;
        } else{
            controlsOn = false;
        }
    }
}

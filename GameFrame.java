import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class GameFrame extends JFrame {
    private JFrame frame;
    private GameCanvas gameCanvas;
    private Player player1, player2, user, otherUser;
    private boolean up, down, left, right;
    private Socket socket;
    private int playerID;
    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    private Boolean isTogether;
    private int decision;
    private String interaction, direction;
    private ArrayList<Button> buttons;
    private int decision1;
    public GameFrame() {
        decision = 1;
        direction = "idle";
        decision1 = 0;
        up = false;
        down = false;
        left = false;
        right = false;
        isTogether = false;
        frame = new JFrame();
        gameCanvas = new GameCanvas();
        player1 = new Player(292.1, 373, new Color(78, 132, 194));
        player2 = new Player(293.8, 278.6, new Color(107, 157, 95));

    }

    public void connectToServer(String ipAddress, int portNumber) {
        try {
            socket = new Socket(ipAddress, portNumber);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are player#" + playerID);
            if (playerID == 1) {
                System.out.println("Waiting for Player #2 to connect...");
            }

            rfsRunnable = new ReadFromServer(in);
            wtsRunnable = new WriteToServer(out);

            rfsRunnable.waitForStartMsg();


        } catch (IOException ex) {
            System.out.println("IOException from connectToServer()");
        }
    }

    public void setUpGUI() {
        gameCanvas.setPreferredSize(new Dimension(800, 600));
        Container contentPane = frame.getContentPane();
        contentPane.add(gameCanvas);
        createSprites();
        frame.setTitle("Player # " + playerID);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        setUpMouseListener();
        setUpAnimationTimer();
        setUpKeyListener();
    }

    public void createSprites() {
        if(playerID == 1){
            user = player1;
            otherUser = player2;
        } else{
            user = player2;
            otherUser = player1;
        }
        gameCanvas.passPlayer(user,otherUser,playerID);
        user.setFloor(gameCanvas.getCurrentFloor());
        gameCanvas.isTogether(false);
    }

    private void setUpAnimationTimer() {
        ActionListener listener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              double speed = 5;
                    user.isColliding();
                    user.isInteracting();
                    user.isChangingRooms();
                    if (up && user.canUp()) {
                        user.moveV(-speed);
                        direction = "up";
                        user.getMoving("up");
                    } else if (down && user.canDown()) {
                        user.moveV(speed);
                        direction = "down";
                        user.getMoving("down");
                    } else if (left && user.canLeft()) {
                        user.moveH(-speed);
                        direction = "left";
                        user.getMoving("left");
                    } else if (right && user.canRight()) {
                        user.moveH(speed);
                        direction = "right";
                        user.getMoving("right");
                    }
                    user.resolveCollision();
                    user.resolveInteracting();
                    user.resolveChange();

                    if(!user.isInteracting() && decision % 2 == 0){
                        gameCanvas.popUpDisabled();
                        decision++;
                    }
                    if(user.isChangingRooms()){
                        gameCanvas.changeRoom(user.getChangeRoomArea().getNewRoom());
                        user.setX(user.getChangeRoomArea().getSpawnX());
                        user.setY(user.getChangeRoomArea().getSpawnY());
                    }
                    if(gameCanvas.getCurrentPopup() != null && gameCanvas.getCurrentPopup().hasChanged()){
                        gameCanvas.getCurrentFloor().interact(gameCanvas.getCurrentPopup().whatChanged());
                    }

                    if(gameCanvas.findRoom("RightHallway").whatChanged() == 1){
                        gameCanvas.findRoom("Player2Room").interact("Unlock Door");
                    }

                    gameCanvas.withinInteractionArea(user.isInteracting());
                    gameCanvas.isTogether(isTogether);
                    gameCanvas.repaint();

            }
        };
        Timer timer = new Timer(25, listener);
        timer.start();
    }

    private void setUpKeyListener() {
        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            up = true;
                            break;
                        case KeyEvent.VK_DOWN:
                            down = true;
                            break;
                        case KeyEvent.VK_LEFT:
                            left = true;
                            break;
                        case KeyEvent.VK_RIGHT:
                            right = true;
                            break;
                    }
                    if(keyCode == KeyEvent.VK_E){

                        if(user.isInteracting() && (!(user.getFloor().interact(user.getInteractionArea().getFunction())).equals("No Function"))) {
                            interaction = user.getInteractionArea().getFunction();

                            if(user.getInteractionArea().getFunction().equals("NeedsCrowbar") && user.getFloor().interact("isCrowbarNeeded").equals("True")){
                                if(gameCanvas.findRoom("Player2Room").interact("isCrowbarTaken").equals("True")){
                                    interaction = "Use Crowbar";
                                }
                            }

                            if(user.getInteractionArea().getFunction().equals("NeedPlayer2RoomKey")){
                                if(gameCanvas.findRoom("LeftHallway").whatChanged() == 1){
                                    interaction = "Use Player2Room Key";
                                }
                                if(gameCanvas.findRoom("Player2Room").interact("isBarrierBroken").equals("True")
                                && gameCanvas.findRoom("RightHallway").whatChanged() == 1 && gameCanvas.getCurrentRoom().equals("RightHallway")){
                                    interaction = "RH Barrier Broken";
                                }
                            }
                            if(gameCanvas.findRoom("Kitchen").interact("isBasementKeyUsed").equals("False")) {
                                if (user.getInteractionArea().getFunction().equals("NeedsBasementKey") && user.getFloor().interact("isBasementKeyNeeded").equals("True")) {
                                    if (gameCanvas.findRoom("Basement").interact("isBasementKeyTaken").equals("True")) {
                                        interaction = "Use Basement Key";
                                    }
                                }
                            }

                            if(user.getInteractionArea().getFunction().equals("NeedsScrewdriver") && user.getFloor().interact("isScrewdriverNeeded").equals("Yes")){
                                if(gameCanvas.findRoom("Player1Room").interact("isScrewdriverTaken").equals("True")){
                                    interaction = "Use Screwdriver";
                                }
                            }

                            System.out.println(interaction);


                            if (decision % 2 == 1 && !(user.getFloor().interact(interaction).equals("No Function"))) {
                                gameCanvas.popUpChecker(user.getFloor().interact(interaction));
                                gameCanvas.popUpEnabled();
                                decision++;

                            } else if (decision % 2 == 0) {
                                gameCanvas.popUpDisabled();
                                decision++;
                            }
                        }
                    }
                    if(keyCode == KeyEvent.VK_ESCAPE){
                        decision1++;
                        gameCanvas.controls(decision1);
                    }
                    gameCanvas.repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        up = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = false;
                        break;
                }
                user.getMoving("idle");
                direction = "idle";
                gameCanvas.repaint();

            }
        };
        frame.addKeyListener(keyListener);
        frame.setFocusable(true);
    }

    private void setUpMouseListener() {
        MouseListener mouseListener = new MouseListener() {
            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Point mouseClick = new Point(e.getPoint());
                if(user.isInteracting() && gameCanvas.isPopUp()){
                    buttons = gameCanvas.currentPU.getButtons();
                    for(Button button : buttons){
                        if(button.contains(mouseClick)){
                            button.function();
                            if(button.getButton().equals("Close")){
                                gameCanvas.popUpDisabled();
                                decision++;
                            }
                        }
                    }
                }
            }
        };
        frame.addMouseListener(mouseListener);
    }
    private class WriteToServer implements Runnable {
        private DataOutputStream dataOut;
        public WriteToServer(DataOutputStream out) {
            dataOut = out;
        }

        @Override
        public void run() {
            try {
                while(true){
                    if(gameCanvas.getCurrentRoom()!=null) {
                        dataOut.writeUTF(gameCanvas.getCurrentRoom());
                        dataOut.flush();
                    }else{
                        dataOut.writeUTF("");
                        dataOut.flush();
                    }


                    if(user.getFloor() != null) {
                        dataOut.writeBoolean(user.getFloor().hasChanged());
                        dataOut.writeInt(user.getFloor().whatChanged());
                        dataOut.flush();
                    }else{
                        dataOut.writeBoolean(false);
                        dataOut.writeInt(0);
                        dataOut.flush();
                    }

                    dataOut.writeDouble(user.getX());
                    dataOut.writeDouble(user.getY());
                    dataOut.writeUTF(direction);
                    dataOut.flush();

                    try{
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }catch(IOException ex){
                System.out.println("IOException from WTS run()");
            }
        }
    }

    private class ReadFromServer implements Runnable{
        private DataInputStream dataIn;
        public ReadFromServer(DataInputStream in){
            dataIn = in;
        }

        @Override
        public void run() {
            try{
                while (true){

                        isTogether = dataIn.readBoolean();

                        boolean roomChange = dataIn.readBoolean();
                        String whatRoomChanged = dataIn.readUTF();
                        int roomAdjust = dataIn.readInt();

                        double otherX = dataIn.readDouble();
                        double otherY = dataIn.readDouble();

                        String otherDirection = dataIn.readUTF();
                        Boolean isLightsOn = dataIn.readBoolean();

                        if(roomChange && roomAdjust!=0){
                            gameCanvas.findRoom(whatRoomChanged).adjust(roomAdjust);
                            if(whatRoomChanged.equals("RightHallway") && roomAdjust == 1){
                                gameCanvas.findRoom("Player2Room").interact("Unlock Door");
                            }
                        }
                        gameCanvas.lights(isLightsOn);

                        if(isTogether) {
                            otherUser.setX(otherX);
                            otherUser.setY(otherY);
                            otherUser.getMoving(otherDirection);
                        }
                }
            }catch (IOException ex){
                System.out.println("IOException from RFS run()");
            }
        }
        public void waitForStartMsg() {
            try {
                String startMsg = dataIn.readUTF();
                System.out.println("Message from server: " + startMsg);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();

            } catch (IOException ex) {
                System.out.println("IOException from waitForStartMsg()");
            }
        }
    }
}

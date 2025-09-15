/**
 This class handles the server code for the game. It receives the inputs of both playes
 and sends out outputs in order for the game's data to be in unison.

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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;

public class GameServer {
    /**
     * Instantiates the server properties and variables for the game
     */
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;

    private Socket p1Socket;
    private Socket p2Socket;
    private ReadFromClient p1ReadRunnable;

    private ReadFromClient p2ReadRunnable;

    private WriteToClient p1WriteRunnable;

    private WriteToClient p2WriteRunnable;

    private double p1x, p1y, p2x, p2y,flicker;
    private boolean roomChange1,roomChange2,lightsOn;
    private String player1Room,player2Room,p1direction,p2direction;
    private int p1change, p2change;
    private Random random;

    /**
     * This is the constructor of the class and it essentially sets the initial
     * number of players to 0. It also sets the values for the gameStarting and
     * isTogether to false which tells the server that the game is not yet starting
     * and that the players are not together. This also lets the server know where
     * the players will spawn, both of their rooms and coordinates.
     */
    public GameServer(){
        System.out.println("Game Server is running.");
        numPlayers = 0;
        maxPlayers = 2;

        p1x = 100.0;
        p1y = 100.0;
        p2x = 400.0;
        p2y = 100.0;

        player1Room = "Player1Room";
        player2Room = "Player2Room";
        p1direction = "idle";
        p2direction = "idle";
        random = new Random();
        lightsFlickering();

        try{
            ss = new ServerSocket(45371);
        }catch(IOException ex){
            System.out.println("IOException from GameServer constructor");
        }
    }

    /**
     * acceptConnections() is a void method that essentially accept connections
     * from the client to the server. This also checks the number of players
     * currently connected in the server and based on that, it accepts or rejects
     * further connections. This also instantiates one object each for inner classes
     * ReadFromClient and WriteToClient.
     */
    public void acceptConnections(){
        try{
            System.out.println("Waiting for connections...");
            while(numPlayers < maxPlayers){
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());

                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Player #"+ numPlayers+" has connected.");
                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if(numPlayers == 1){
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;


                } else if(numPlayers == 2){
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;

                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();


                    Thread readCoordinatesThread1 = new Thread(p1ReadRunnable);
                    Thread readCoordinatesThread2 = new Thread(p2ReadRunnable);
                    readCoordinatesThread1.start();
                    readCoordinatesThread2.start();

                    Thread writeCoordinatesThread1 = new Thread(p1WriteRunnable);
                    Thread writeCoordinatesThread2 = new Thread(p2WriteRunnable);
                    writeCoordinatesThread1.start();
                    writeCoordinatesThread2.start();



                }
            }
            System.out.println("No longer accepting connections");
        }catch(IOException ex){
            System.out.println("IOException from acceptConnections()");
        }
    }
    /**
     * ReadFromClient is an inner class that implements the Runnable interface
     * which essentially means that it uses Threads. This class is mainly responsible
     * for reading the data and changes sent from the client. The data it reads are:
     * coordinates of each player and the room the player is in.
     */
    private class ReadFromClient implements Runnable {
        private int playerID;
        private DataInputStream dataIn;

        /**
         * This is the constructor of the class. This gets the playerID of the players
         * which enables the server to identify the room, coordinates, and direction of the players.
         * @param pid is the player ID of the player.
         * @param in is the data input.
         */
        public ReadFromClient(int pid, DataInputStream in) {
            playerID = pid;
            dataIn = in;
            System.out.println("RFC" + playerID + " Runnable created");
        }
        /**
         * run() is a void method which essentially is the one reading
         * the coordinates and the rooms of each player corresponding to
         * their player id. It also reads whether the player is moving
         * based on their direction.
         */

        @Override
        public void run() {
            try {
                while (true) {
                    if (playerID == 1) {
                        player1Room = dataIn.readUTF();

                        roomChange1 = dataIn.readBoolean();
                        p1change = dataIn.readInt();

                        p1x = dataIn.readDouble();
                        p1y = dataIn.readDouble();
                        p1direction = dataIn.readUTF();
                    } else if (playerID == 2){
                        player2Room = dataIn.readUTF();

                        roomChange2 = dataIn.readBoolean();
                        p2change = dataIn.readInt();

                        p2x = dataIn.readDouble();
                        p2y = dataIn.readDouble();
                        p2direction = dataIn.readUTF();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * WriteToClient is an inner class that implements the Runnable interface
     * which means that it uses Threads. This class is mainly responsible for
     * sending the interaction boolean from the popup to the server and also
     * sending the coordinates and the rooms of the other player to the other client.
     */
    private class WriteToClient implements Runnable{
        private int playerID;
        private DataOutputStream dataOut;

        /**
         * This is the constructor of the class. It's similar to the ReadToClient one
         * except it's function is to sent out the data it received.
         * @param pid is the Player ID.
         * @param out is the data sent out.
         */
        public WriteToClient(int pid, DataOutputStream out){
            playerID = pid;
            dataOut = out;
            System.out.println("WTC" +  playerID+ " WriteCoordinatesFromClient Runnable created");
        }

        /**
         * run() is a void method responsible for sending the coordinates, movement, and
         * the room of the player to the other player.
         */
        @Override
        public void run() {
                try{
                    while(true){
                        if(player1Room.equals(player2Room)){
                            dataOut.writeBoolean(true);
                            dataOut.flush();
                        }
                        else{
                            dataOut.writeBoolean(false);
                            dataOut.flush();
                        }

                        if(playerID == 1){

                            dataOut.writeBoolean(roomChange2);
                            dataOut.writeUTF(player2Room);
                            dataOut.writeInt(p2change);
                            dataOut.flush();

                            dataOut.writeDouble(p2x);
                            dataOut.writeDouble(p2y);
                            dataOut.writeUTF(p2direction);
                            dataOut.writeBoolean(lightsOn);
                            dataOut.flush();
                        }
                        else if(playerID == 2){

                            dataOut.writeBoolean(roomChange1);
                            dataOut.writeUTF(player1Room);
                            dataOut.writeInt(p1change);
                            dataOut.flush();

                            dataOut.writeDouble(p1x);
                            dataOut.writeDouble(p1y);
                            dataOut.writeUTF(p1direction);

                            dataOut.writeBoolean(lightsOn);
                            dataOut.flush();
                        }
                        try{
                            Thread.sleep(25);
                        }catch(InterruptedException ex){
                            System.out.println("InterruptedException from WTC run()");
                        }
                    }

                }catch(IOException ex){
                    System.out.println("IOException from WTC run()");
                }
            }

        /**
         * sendStartMsg() is a void method that allows the user to know when the game is now
         * starting based on the number of players connected to the server.
         */
        public void sendStartMsg(){
            try{
                dataOut.writeUTF("We now have 2 players. The game can begin!");
            }catch(IOException ex){
                System.out.println("IOException from sendStartMsg()");
            }
        }
    }

    /**
     * Generates a random double from 0-1 to decide whether the lights are flickering or not.
     */
    private void lightsFlickering() {
        ActionListener listener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flicker = random.nextDouble();

                if(flicker < 0.3){
                    lightsOn = true;
                } else{
                    lightsOn = false;
                }
            }
        };
        Timer timer = new Timer(70, listener);
        timer.start();
    }

    /**
     * This is the main method of the GameServer which essentially creates a new instance
     * of the server and also allows the accepting of connections to the server.
     * @param args the arguments it accepts.
     */
    public static void main(String[] args){
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}

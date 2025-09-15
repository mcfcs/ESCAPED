/**
 This class is responsible to starting the game on each player's side.
 It includes the input for the IP Address and port to connect to the server.

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
import java.util.Scanner;

public class GameStarter {
    /**
     * Main method for GameStarter, gets the IP Address and Port Number to connect to the server.
     * @param args
     */
    public static void main(String[] args){
        Scanner console = new Scanner(System.in);
        System.out.println("IP Address:");
        String ipAddress = console.nextLine();
        System.out.println("Port Number:");
        int portNumber = Integer.parseInt(console.nextLine());
        GameFrame gf = new GameFrame();
        gf.connectToServer(ipAddress,portNumber);
        gf.setUpGUI();

    }
}

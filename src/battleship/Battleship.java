/**
 * Name:Hamza
 * Program: Battle Ship
 * Date:11/2021
 * Description: Simplified Battle Ship Game
 */
package battleship;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.sound.sampled.*;

public class Battleship {

    public static void main(String[] args) throws InterruptedException {

        /*All the Arrays needed for the grid. 
        Note: there are 4 arrays for the 4 sections*/
        Scanner scanS = new Scanner(System.in);
        ArrayList a = new ArrayList(), b = new ArrayList(), c = new ArrayList(),
                d = new ArrayList();

        /*
        uShips are the 5 user ships. eShips are the 5 enemy ships. 
        Ushoot is the users input for where they would like to shoot.
        eShoot is randomly chosen 
         */
        ArrayList uShips = new ArrayList(), eShips = new ArrayList(), uShoot
                = new ArrayList(), eShoot = new ArrayList();

        int number, index, turn = -1;
        boolean shoot;

        String[] grid = {"a1", "a2", "a3", "a4", "b1", "b2", "b3", "b4", "c1",
            "c2", "c3", "c4", "d1", "d2", "d3", "d4"};//This is used to spawn ships

        playSound(); //Play music as soon as program runs

        Startup(); //Starting menu

        //Makes a grid
        for (int i = 0; i < 4; i++) {
            a.add("a" + (i + 1));
        }
        for (int i = 0; i < 4; i++) {
            b.add("b" + (i + 1));
        }
        for (int i = 0; i < 4; i++) {
            c.add("c" + (i + 1));
        }
        for (int i = 0; i < 4; i++) {
            d.add("d" + (i + 1));
        }

        //Spawns in 5 ships. Used if statement to prevent reppetition 
        while (uShips.size() != 5) {
            int num = 0 + (int) (15 * Math.random());

            if (!uShips.contains(grid[num])) {
                uShips.add(grid[num]);
            }
            //Turns user ships into "O"s on the map to help user.
            switch (((String) uShips.get(uShips.size() - 1)).charAt(0)) {
                case ('a') -> {
                    number = Character.getNumericValue(((String) uShips.get(uShips.size() - 1)).charAt(1));
                    index = number - 1;
                    a.set(index, "O ");
                }
                case ('b') -> {
                    number = Character.getNumericValue(((String) uShips.get(uShips.size() - 1)).charAt(1));
                    index = number - 1;
                    b.set(index, "O ");
                }
                case ('c') -> {
                    number = Character.getNumericValue(((String) uShips.get(uShips.size() - 1)).charAt(1));
                    index = number - 1;
                    c.set(index, "O ");
                }
                case ('d') -> {
                    number = Character.getNumericValue(((String) uShips.get(uShips.size() - 1)).charAt(1));
                    index = number - 1;
                    d.set(index, "O ");
                }
            }
        }
        //Spawns enemy ships (Not shown to user)
        while (eShips.size() != 5) {
            int num = 0 + (int) (15 * Math.random());
            if (!eShips.contains(grid[num]) && !uShips.contains(grid[num])) {
                eShips.add(grid[num]);
            }
        }
        //Tells the user their ships
        System.out.println("\nYour ships are" + uShips);
        Thread.sleep(1000);

        //While the ships arent 0 keep running
        while (eShips.size() >= 0 || uShips.size() >= 0) {
            turn++;

            shoot = false;
            //Random coordinate the enemy shoots at
            while (shoot == false) {
                int num = 0 + (int) (15 * Math.random());
                if (!eShips.contains(grid[num]) && !eShoot.contains(grid[num]) && !uShoot.contains(grid[num])) {
                    eShoot.add(grid[num]);
                    shoot = true;
                }
            }
            //Asks the user where they would like to shoot
            System.out.println("\nWhere would u like to shoot");
            Thread.sleep(500);
            //Makes an organized grid
            for (int i = 0; i < a.size(); i++) {
                System.out.println(a.get(i) + (" ") + b.get(i) + " "
                        + c.get(i) + " " + d.get(i));
            }

            uShoot.add(scanS.nextLine());
         
            //turns user shots into X
            switch (((String) uShoot.get(uShoot.size() - 1)).charAt(0)) {
                case ('a') -> {
                    number = Character.getNumericValue(((String) uShoot.get(uShoot.size() - 1)).charAt(1));
                    index = number - 1;
                    a.set(index, "X ");
                }
                case ('b') -> {
                    number = Character.getNumericValue(((String) uShoot.get(uShoot.size() - 1)).charAt(1));
                    index = number - 1;
                    b.set(index, "X ");
                }
                case ('c') -> {
                    number = Character.getNumericValue(((String) uShoot.get(uShoot.size() - 1)).charAt(1));
                    index = number - 1;
                    c.set(index, "X");
                }
                case ('d') -> {
                    number = Character.getNumericValue(((String) uShoot.get(uShoot.size() - 1)).charAt(1));
                    index = number - 1;
                    d.set(index, "X ");
                }
            }
            //turns enemy shots into X
            switch (((String) eShoot.get(eShoot.size() - 1)).charAt(0)) {
                case ('a') -> {
                    number = Character.getNumericValue(((String) eShoot.get(eShoot.size() - 1)).charAt(1));
                    index = number - 1;
                    a.set(index, "X ");
                }
                case ('b') -> {
                    number = Character.getNumericValue(((String) eShoot.get(eShoot.size() - 1)).charAt(1));
                    index = number - 1;
                    b.set(index, "X ");
                }
                case ('c') -> {
                    number = Character.getNumericValue(((String) eShoot.get(eShoot.size() - 1)).charAt(1));
                    index = number - 1;
                    c.set(index, "X ");
                }
                case ('d') -> {
                    number = Character.getNumericValue(((String) eShoot.get(eShoot.size() - 1)).charAt(1));
                    index = number - 1;
                    d.set(index, "X ");
                }
            }
            //if the user shoots their own ship
            if (uShips.contains(uShoot.get(turn))) {
                System.out.print("You sunk your own ship, ");
                uShips.remove(uShoot.get(turn));
            }
            //if the user shoots a ship
            if (eShips.contains(uShoot.get(turn))) {
                System.out.println("\nYou sunk a ship");
                Thread.sleep(500);
                eShips.remove(uShoot.get(turn));
                System.out.println("You shot at: " + uShoot);
                Thread.sleep(500);
                System.out.println("The enemy has " + eShips.size() + " Ships");

                //if the user misses
            } else {
                System.out.println("You missed");
                Thread.sleep(500);
                System.out.println("You shot at: " + uShoot);
                Thread.sleep(500);
                System.out.println("The enemy has " + eShips.size() + " Ships");
            }
            //if the enemy shoots a ship
            if (uShips.contains(eShoot.get(turn))) {
                Thread.sleep(500);
                System.out.println("\nThe enemy shot at ship " + eShoot.get(turn));
                Thread.sleep(500);
                System.out.println("enemy shots: " + eShoot);
                uShips.remove(eShoot.get(turn));
                Thread.sleep(500);
                System.out.println("You have " + uShips.size() + " Ships");
                Thread.sleep(500);

                /*if the enemy misses  
                Note: The enemy cant shoot their own ship to make it harder
                 */
            } else {
                Thread.sleep(500);
                System.out.println("\nThe enemy missed and shot at "
                        + eShoot.get(turn));
                Thread.sleep(500);
                System.out.println("You have " + uShips.size() + " Ships");
                Thread.sleep(500);
            }
            //If the user looses(has 0 ships)
            if (uShips.isEmpty()) {
                System.out.println("\nYou lost");
                break;
            }
            //If the user wins(Destroys enemy ships)
            if (eShips.isEmpty()) {
                System.out.println("\nYou won");
                break;
            }
        }

    }

    /**
     * Method: PlaySound Description: Plays music
     */
    public static void playSound() {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("music.wav.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.println("Error with playing sound.");
        }
    }

    /**
     * Method: Instructions Description: Tells the user how to play
     *
     * @throws InterruptedException
     */
    public static void instructions() throws InterruptedException {
        Scanner scanS = new Scanner(System.in);
        System.out.println("\nWelcome to Battle Ships \n");
        Thread.sleep(1000);
        System.out.println("You will be randomly given 5 ships marked O on the map \n");
        Thread.sleep(1000);
        System.out.println("The objective of the game is to find and shoot down the opponents ships before they get to yours\n");
        Thread.sleep(1000);
        System.out.println("Destroyed seas are marked with an X\n");
        Thread.sleep(1000);
        System.out.println("Press any key to continue");
        scanS.nextLine();
    }

    /**
     * Method: Startup Description: Prints the startup menu asking the user if
     * they need instructions or want to jump in the game
     *
     * @throws InterruptedException
     */
    public static void Startup() throws InterruptedException {
        String key;
        Scanner scanS = new Scanner(System.in);
        String message = "\tBATTLE SHIP\n";
        int wait = 250;
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            Thread.sleep(wait); //Introduction written letter by letter
        }
        System.out.print("Name: ");
        scanS.nextLine();
        System.out.println("Destroy all the hidden enemy ships before they destroy yours");
        Thread.sleep(1000);
        System.out.println("\nPress any key to start(\"HELP\" for instructions)");

        key = scanS.nextLine();
        if (key.equalsIgnoreCase("help")) { //If the user inputs help else run the game
            instructions();
        }

    }

}

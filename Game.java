package com.excilys.player;


import com.excilys.board.Board;
import com.excilys.ships.AbstractShip;
import com.excilys.ships.BattleShip;
import com.excilys.ships.Carrier;
import com.excilys.ships.Destroyer;
import com.excilys.ships.Submarine;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
public class Game {

    /* ***
     * Constante
     */
    public static final File SAVE_FILE = new File("savegame.dat");

    /* ***
     * Attributs
     */
    private Player player1;
    //private AIPlayer player2;
    private Player player2;
    private Scanner sin;

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        if (!loadSave()) {
            // init attributes
            // TODO init boards
            Board b1= new Board("test",10);
            Board b2= new Board("test2",10);
            //Board b1, b2; 
            
            // TODO init this.player1 & this.player2
            List<Object> list = new ArrayList<Object>();
            List<AbstractShip> ships = Arrays.asList(new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier());
            List<AbstractShip> ships2 = Arrays.asList(new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier());
            player1= new Player(b1,b2,ships);
            player2=new Player(b2,b1,ships2);
            //player2=new AIPlayer(b2,b1,ships);
            
         // TODO use a scanner to read player name
           player1.toName();
           player2.toName();
           
            b1.print();
            // place player ships
            player1.putShips();
            player2.putShips();
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() {
        int coords[] = {15,15};
        Board b1=player1.board;
        Board b2=player2.board;
        Hit hit;

        // main loop
        b1.print();
        boolean done;
        do {
            hit = Hit.MISS; // TODO player1 send a hit
             System.out.println( player1.name);
            hit= player1.sendHit(coords);
            
            
            boolean strike = hit != Hit.MISS;
            // TODO set this hit on his board (b1)
           // b1.setHit(strike, coords[0], coords[1]);

            done = updateScore();
            /*Affichage du tableau adversaire pour debbugage*/
            //b2.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            save();


            if (!done && !strike) {
                do {
                    //hit = Hit.MISS; // TODO player2 send a hit
                    
                    System.out.println( player2.name);
                    
                    hit=player2.sendHit(coords);
                    
                    strike = hit != Hit.MISS;
                   if (strike) {
                        System.out.println( player1.name);
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        
    }


    private void save() {
        try {
            // TODO bonus 2 : uncomment
             if (!SAVE_FILE.exists()) {
                  SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
              }
                

           //     return true;

            // TODO bonus 2 : serialize players
            //FileOutputStream-> permet ecrire sur le disque
            FileOutputStream fout = new FileOutputStream(SAVE_FILE);
            /*Le outputStream est ouverte*/
            
            /* On creer un objet outputstream pour passer les objet 
             player au fout et les sauvagarder
             */
            ObjectOutputStream output = new ObjectOutputStream(fout);
            output.writeObject(player1);
            output.writeObject(player2);
            
            /*Fermeture d'aces au disque*/
            output.close();
            fout.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            try {
                // TODO bonus 2 : deserialize players
                  /* On creer un objet outputstream pour lire les objet player  */
                ObjectInputStream input = new ObjectInputStream(new FileInputStream(SAVE_FILE));
               
                  /* Lectures des objet*/
                  /* Il fault dire que le objet est du type player, pour etre bien interprete
             */
                player1 = (Player) input.readObject();
                player2 = (Player) input.readObject();
                 /*Fermeture d'aces au disque*/
                input.close();
                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            case NOT: 
                msg="Deja frapee";
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()});
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
}

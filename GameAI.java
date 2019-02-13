/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excilys.player;
import com.excilys.board.Board;
import com.excilys.ships.BattleShipsAI;
import com.excilys.ships.AbstractShip;
import com.excilys.ships.BattleShip;
import com.excilys.ships.Carrier;
import com.excilys.ships.Destroyer;
import com.excilys.ships.Submarine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

/**
 *
 * @author julia
 */

 

public class GameAI {
 
    
    
    /*Demande le taille du tableua*/
    public static int chooseSize()
    {
    System.out.println("Choose size of the board");
    Scanner scan = new Scanner(System.in);
    int size = scan.nextInt();
    System.out.println("Size is " + size);
    return size;
    }
    
    /*Vérifie Gameover*/
    /*Compte le número de navires en le tableau- comme le hasship retourne false si 
    le navire est ecoule - on parcours le tableau et verifie si il y en a encore*/
    public static boolean checkendgame(Player player1, Player player2)
    {
    for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed-1;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
                
            }
    }
        return false;
        
    }
    

     public static void main(String[] args) 
    {
        /*Choix de la taille des tableau*/
        int taille=GameAI.chooseSize();
        /*Initialisation des Boards -pour le jouer et pour  l`AI*/
        Board play= new Board("test",taille);
        Board oponentplay= new Board("test",taille);
        /*Initialisation des object List*/
        List<Object> list = new ArrayList<Object>();
        List<AbstractShip> ships = Arrays.asList(new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier());
        List<AbstractShip> ships1 = Arrays.asList(new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier());
        /*Premier print du tableau du jouer non AI*/
        play.print();
        /*Création des joueurs*/
        Player player= new Player(play,oponentplay,ships);
        AIPlayer oponent=new AIPlayer(oponentplay,play,ships1);
        
      
        /*Choix du nom du joueur*/
        player.toName();
        oponent.name="IA ";
         /*Initialisation du coord pour le methode send hit*/
        int[] coord = {1,2};
         /*Appel des méthodes pour mettre les navires au tableau*/
        oponent.PutShip();
        player.putShips();
          
        boolean doing=true;
        /*Création des Hits*/
        Hit hit;
        Hit hit1;
        /*Loop principal*/
         while(doing)
         {
     
             /*Joueur AI*/
             /*Envoi le frappe*/
             hit1=oponent.SendHit(coord);
             System.out.println("\n"+oponent.name+": Hit at "+(char)(coord[0]+65)+"  "+(coord[1]+1)+ "\n Resultat: "+hit1.toString()+"\n");
              
             oponent.sleep(1000);
             /*Affiche le resultat*/
            // System.out.println("\n Resultat:  "+hit1.toString()+"\n");
             play.getnameBoard();
             System.out.println(player.name);
             
             oponent.sleep(1000);
             /*Affiche le tableau*/
             play.print();
             
             /*Joueur Normal*/
             oponent.sleep(1000);
             /*Envoi le frappe*/
             hit=player.sendHit(coord);
             System.out.println(player.name+": Hit at "+(char)(coord[0]+65)+"  "+(coord[1]+1)+ "\n Resultat: "+hit.toString()+"\n");
              /*Affiche le resultat*/
             oponent.sleep(1000);
             
              
              /*Verifie s'il y a une repetion de coordones demande de tape nouvament*/
             if(hit==Hit.NOT)
              {player.sendHit(coord);}
             
              oponent.sleep(1000);
              /*Affiche le resultat Board*/
              oponentplay.getnameBoard();
              System.out.println(oponent.name);
              
             oponent.sleep(1000);
             oponentplay.print();
             
             /*Verifie si est GAME OVER*/
             int check;
             if(checkendgame(player, oponent))
             {System.out.println(String.format("joueur %d gagne", player.lose ? 2 : 1));
             break;
             }  
             
             
                     
         }
    }
}
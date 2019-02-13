package com.excilys.player;


import com.excilys.board.Board;

import com.excilys.ships.AbstractShip;
import com.excilys.ships.AbstractShip.Orientation;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
public class Player implements Serializable{
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;
    String name;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodinates.
     */
    public Orientation verifieOrientation(String nom){
        Orientation o=Orientation.EAST;
            if (nom.equals("n")) {
				o = Orientation.NORTH;
			} else if (nom.equals("s")) {
				o = Orientation.SOUTH;
			} else if (nom.equals("e")) {
				o = Orientation.EAST;
			} else if (nom.equals("o")) {
				o = Orientation.WEST;
			}
        return o;
        }
    
    
    /*Permet de lire le nom d'utilisateur*/
    public void toName()
    {
    System.out.println("Choose a name");
    Scanner scan = new Scanner(System.in);
    this.name = scan.nextLine();
    System.out.println("Your username is " + this.name);
    
    }
    public void putShips() {
        boolean done = false;
        int i = 0;
        
        do {
            AbstractShip s = ships[i];
            
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getNameShip(), s.getLength());
            System.out.println(msg);
             /*Lire le input d'utilisateur*/ 
            InputHelper.ShipInput res = InputHelper.readShipInput();
         
            
            Orientation orientation = null;
             /*Configure la orientation selon les info*/ 
	    orientation=verifieOrientation(res.orientation);	
            s.setOrientation(orientation);
            // TODO put ship at given position
            
            // TODO when ship placement successful
            /*Appel a la fontion put ships*/
            /*Vérifie le sucès avec l'exception*/ 
            try {
		board.putShip(s, res.x, res.y);
        	} catch(IllegalArgumentException e) {
                    System.err.println("Impossible de placer le navire a cette position");
			i--;
		}
            ++i;
            done = i == 5;   /*5 navires*/ 
             
            board.print();    /*A chaque fois afiche le Board*/ 
        } while (!done);
    }
   
    
    public Hit sendHit(int[] coords) {
        boolean done=true;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
             /*Lire les donnes*/ 
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            //System.out.printf("Hit at x %c and  y %d \n",hitInput.x+65, hitInput.y+1);
            // TODO call sendHit on this.opponentBoard
             /*Appel le hit au tableau du adversaire*/ 
            hit=opponentBoard.sendHit(hitInput.x,hitInput.y);
            done=true;//checkendgame();
            coords[0] = hitInput.x;
            coords[1] = hitInput.y;

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);
        
        return hit;
    }
 /*Acès au variables encapsulé*/ 
    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}

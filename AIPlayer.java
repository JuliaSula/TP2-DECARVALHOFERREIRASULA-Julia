package com.excilys.player;

import com.excilys.board.Board;
import com.excilys.ships.AbstractShip;
import com.excilys.ships.BattleShipsAI;
import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player implements Serializable{
    /* **
     * Attribut
     */
    private BattleShipsAI ai;

    /* **
     * Constructeur
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }

   public void PutShip()
   {
   
    /*On appele le methode de AIBattleship pour mettre les navires en le tableau */
   ai.putShips(ships);
   
   }// TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.

    public Hit SendHit(int[] coord)
    {
   /*Avec les methodes de la AIBattleship , on obtient un variable aleatoire e l'applique en sendHit*/     
 
    Hit hit=ai.sendHit(coord);
    
    return hit;
    }
    
     public static void sleep(int ms) {
    try {
        Thread.sleep(ms);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }
}

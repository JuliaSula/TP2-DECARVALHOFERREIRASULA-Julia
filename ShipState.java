/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excilys.ships;
import com.excilys.player.ColorUtil;
import java.io.Serializable;
//import javafx.scene.paint.Color;
/**
 *
 * @author julia
 */
public class ShipState implements Serializable{
    
    /*Atributs*/
    private AbstractShip ship;
    private boolean struck;
    //private boolean hasShip; 
    
    
    /*Constructeur*/
   public ShipState(AbstractShip ship)
    {
     this.ship=ship;
     this.struck=false;
   //  this.hasShip=true;
    }
    
    /*Verification du état du navire*/
   
   /*Appel la fonction pour obtenir le strikeCount du Ship*/
    public int getStrikeCountState() {
		return ship.getStrikeCount();
	}
   
   /*Appel à partir du ShipState, le augment du número de strike*/
    public void addStrikeStatue() {
            this.ship.addStrike();
            this.struck=true;
    }
    /*Vérifie si le struck is true*/
     public boolean isStruck(){
       return struck;
     }
     
     /*Mettre le nom du navire en rouge*/
     public String toString(){
         String nom=ship.getNameShip();
         if(this.isStruck())
         { 
          nom= ColorUtil.colorize(nom, ColorUtil.Color.RED);
         }
         
         return nom;
     }
     
     /*Mettre le label du navire en rouge*/
     public String toChar(){
         if(this.isStruck())
         { 
        String nom=ColorUtil.colorizech(ship.getLabel(), ColorUtil.Color.RED);
         return nom;
         }
         else{
             return ship.getLabel()+"";
         }
     }
     
     /*Vérifie si le navire a coulé*/
     public boolean isSunkState()
     {
       return ship.isSunk();
     }
     
     /*Permet acès au navire*/
    public AbstractShip getShip()
            {
            return this.ship;
            }
    
}

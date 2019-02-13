/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excilys.board;

import com.excilys.ships.*;
import com.excilys.ships.AbstractShip.Orientation;
import com.excilys.player.Player;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import com.excilys.player.Hit;


/**
 *
 * @author julia
 */
public class Test_Board {
    
    public static void main(String[] args) 
    {
    /*Initialisation des Boards*/ 
    Board test= new Board("test",10);
    Board otest= new Board("test",10);
    
    /*Création de la liste d'objets navire*/
    List<Object> list = new ArrayList<Object>();
    List<AbstractShip> ships = Arrays.asList(new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier());
    
    /*Affichache du tableau*/
    test.print();
    
    /*Création du player*/
    Player play= new Player(test, otest,ships);
   
    // play.putShips();
  
    /*Test fonction putStrip*/
    test.putShip(new Carrier(Orientation.SOUTH), 1, 1);
    test.putShip(new Destroyer(Orientation.EAST), 2, 1);
    
    /*Test fonction sendHit*/
    Hit hit=test.sendHit(2, 1);
    System.out.print("Result "+hit.toString()+"\n");
    Hit hit2=test.sendHit(3, 1);
    //test.sendHit(3, 1);
     System.out.print("Result "+hit2.toString()+"\n");
     
     /*Test fonction hasHit*/
     if(!test.hasShip(3,1))
         System.out.print("IXI MALIA");
     /*Affichache du tableau*/
    test.print();
    
    }
    
}

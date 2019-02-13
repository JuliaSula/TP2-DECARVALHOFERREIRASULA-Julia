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

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

/**
 *
 * @author julia
 */
public class TestGame {
    public static void main(String[] args) 
    {
        
       
        /*EXERCICE 7*/
         /*Création de la board*/ 
         Board test= new Board("test",10);
         //int[] coords= {1,2};
         
        
         /*Creation de la liste de ship*/  
        AbstractShip[] ships= new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new BattleShip(), new Carrier()};
         
        /*Affichage du Tableau*/  
        test.print();
          
           /*Création de la IA*/ 
         BattleShipsAI ai =new BattleShipsAI(test, test);
        
         /*Configure les positions des ships*/ 
         ai.putShips(ships);
         
          /*Configure le loop principal*/ 
         int counter=0;
         boolean louca=true;
         while(louca)
         {
              /*Donne un taille a coords*/ 
         int[] coords=ai.pickRandomCoord();
              /*Envoir de hits*/ 
         Hit hit=ai.sendHit(coords);
                /*Affichage*/ 
         System.out.println("Hit  "+hit.toString());
         
          /*Si le navire coulé -> on compte*/ 
         if(!hit.equals(Hit.NOT)&&!hit.equals(Hit.MISS)&&!hit.equals(Hit.STRIKE))
         {
             counter++;
          
         }
         
          /*Affichage du board*/ 
         test.print();
         
         int aux=0;
          /*Vérifie le número de navire atravers du hasShips*/ 
         for(int j=0;j<test.getSize();j++)
         { for(int i=0;i<test.getSize();i++)
         {  if(!test.hasShip(i, j))
         {        aux++; }}}
         
//         System.out.println("AUX"+aux);

            /*S'il n'y a pas navires -> fini*/ 
         if(aux==test.getSize()*test.getSize())
             louca=false;
         }
                 
    
      
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excilys.ships;

/**
 *
 * @author julia
 */
public class Destroyer extends AbstractShip {

     /*Constructeurs base en la classe mere-un prenne orientation ,l'autre non*/
	public Destroyer(Orientation o) {
		super( "Croiseur",'D', 2, o,0);
	}
        
        public Destroyer() {
		super( "Croiseur",'D', 2, Orientation.EAST,0);
	}
}

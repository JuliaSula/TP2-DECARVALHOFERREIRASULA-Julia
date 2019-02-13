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
public class Carrier extends AbstractShip {
   

	public Carrier (Orientation o) {
		super( "Alan",'C', 5, o ,0);
	}
        
        public Carrier () {
		super( "Alan",'C', 5, Orientation.EAST,0);
	}
        

}

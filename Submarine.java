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
public class Submarine extends AbstractShip {

	public Submarine(Orientation o) {
		super( "Sub",'S', 3, o, 0);
	}
        
        public Submarine() {
		super( "Sub",'S', 3, Orientation.EAST, 0);
	}
}
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
public class BattleShip extends AbstractShip {

	public BattleShip(Orientation o) {
		super( "Bob",'B', 4, o,0);
	}
        
        public BattleShip() {
		super( "Bob",'B', 4, Orientation.EAST,0);
	}
}

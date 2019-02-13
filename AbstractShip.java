/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.excilys.ships;
import java.io.Serializable;
/**
 *
 * @author julia
 */
public abstract class AbstractShip implements Serializable {
 
    
    /*L’orientation des navires est un ensemble fini des quatre valeurs NORTH, SOUTH, EAST et  WEST. 
    Comment faire pour représenter au mieux cette information ?*/
    
    /*On utilise le type enum pour definir l'ensemble d`orientation*/
    public enum Orientation {
		NORTH, SOUTH, EAST, WEST
	}
    
    /*Atributs*/
    private Character label;  
    private String nameShip;
    private int taille;    
    private Orientation orientation;
    private int strikeCount;
    
     /*Constructeur complet*/
    public AbstractShip(String nameShip, Character label,int taille, Orientation orientation,int strikeCount )
    {
    this.nameShip=nameShip;
    this.label=label;
    this.taille=taille;
    this.orientation=orientation;
    this.strikeCount=strikeCount;
    }
    
    
    
   /*Verifie le État des navires*/ 
    
    /*Retourne le StrikeCount*/
  public int getStrikeCount() {
		return this.strikeCount;
	}
  
    /*Augmente le strikeCOunt*/
   public void addStrike() 
    {
            
            if(strikeCount>this.taille) /*Verifie si le ship etait deja coulé-double vérification*/
            {   this.isSunk();
                //System.out.println("This ship is already sunk");
            }
            else{                  /*Sinon ++*/
            this.strikeCount++;
           // System.out.printf("Strikes %d / %d",strikeCount,this.taille);
            }
    
    
    }
   
   /*Verifie si le navire a coulé*/
   public boolean isSunk()
    {
        /*Comparaison entre taille et strikeCount*/
        /*Possible car on interdit augmente de strike avec le meme coordonnes*/
            return this.strikeCount == this.taille;
    }
   
   
   //Aces aux donnes privet
    /*accesseurs nécessaires pour faire de l’encapsulation.*/
    public Character getLabel() {
		return this.label;
	}
   
	public String getNameShip() {
		return this.nameShip;
	}

	public int getLength() {
		return this.taille;
	}

	public Orientation getOrientation() {
		return this.orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
    
}

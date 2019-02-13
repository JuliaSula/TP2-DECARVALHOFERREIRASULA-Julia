/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*Exercice 1*/
    /*Avez-vous pensé à l’encapsulation dans votre classe Board ? :-)
    Oui- tous le champs navires et nom sont privates, il y a le methodes pour montres ces donnes
    Neanmoins, le champ frapes a etait laisse public car tous le jouers sauront les champs frappes
    printf(%2d);*/


/*   
Les indices de position des navires commencent-ils à 1 ou à 0 ?
 En un premier moment->    On a ajuste les variables en les fonctions putHit et putShip
 En un deuxieme -> Les functions en Input helper faisent cet traitement

Que se passe-t-il si la valeur (position + longueur) d’un navire mène en dehors de la grille de jeu ? Que faire dans ce cas ?
Que se passe-t-il si deux navires se chevauchent ? Que faire dans ce cas ?

Pour les autres deux question, on a utilise les exceptions pour les traiter. Par consequence si un navir
est dehors de la grille ou se chevauchent on mis ce erreur en l`ecran
    */


/*Exercice 6*/
/*Que se passe-t-il si on appelle sendHit() deux fois sur la même position d’un navire ?
Le sendHit envoye le hit NOT SHIP a dire que cette position etait deja frappe
Que renvoie la méthode hasShip(x,y) lorsque le navire en (x,y) a été coulé ? 
Il reenvoie false,ainsi si le jeu etait dynamique ca ne possere pas de problemes*/
package com.excilys.board;

/**
 *
 * @author julia
 */


import java.io.Serializable;
import com.excilys.player.ColorUtil;
import com.excilys.player.Hit;
import com.excilys.ships.AbstractShip;
import com.excilys.ships.AbstractShip.Orientation;
import com.excilys.ships.ShipState;

public class Board implements IBoard, Serializable{
     //private static final long serialVersionUID = 12L;
    /*Atributs de la classe Board*/
    private  String nameBoard;          /*Nom*/
    private ShipState[][] navires;      /*Tableau oú on affichera les navires*/
    public Boolean[][] fraps;           /*Tableau que supervisera les frappés*/
    private int taille;                 /*Taille du tableau, faut etre plus que 5 dû 
                                        a AI(elle arrive pas a posicione les bateaux)*/
    
   
    
    /*Constructeur*/
    public Board(String nameBoard, int taille )
    {
    this.nameBoard= nameBoard;                       /*Créer le nom du Board*/
    this.fraps= new Boolean [taille][taille];        /*Créer le tableau des hits*/
    
     /*Initialisation du tableau frappés*/
     /*Comme on utilise ce tableau pour les verification c'est plus sûr de l'initialise*/
    for(int i=0;i<taille;i++)
    {    for(int j=0;j<taille;j++)
        {    this.fraps[i][j]=null;}
    }
    
    this.navires= new ShipState[taille][taille];     /*Créer les états des navires*/   
    this.taille=taille;                              /*Défini du Board*/
    }
    
    /*Constructeur par défault*/
    public Board(String nameBoard)                 
    {
    this.nameBoard= nameBoard;                       /*Créer le nom du Board*/
    this.fraps= new Boolean [10][10];                /*Créer le tableau des hits au taille défault*/
    this.navires= new ShipState[10][10];             /*Créer les états des navires au taille défault*/   
    this.taille=10;                                  /*taille défault*/   
       int i = 0, j = 0;
       /*Initialisation du tableau fraps pour garantir ses valeurs*/
        for(i=0;i<this.taille;i++){
        for(j=0;j<this.taille;j++){
            this.fraps[i][j]=null;
        }
        }
       
    }
    
    /*Acès au encapsulation*/
    /*Retourne de variables privets que on aura besoin*/   
    
    /*Acès au nom du Board*/   
    public String getnameBoard(){
        
        return this.nameBoard;
            }
    
    /*Afficher les tableaux*/
    
     /*Afficher les points sans navire en bleu*/
    public String water(){
        
        String nom=ColorUtil.colorize(" o ", ColorUtil.Color.BLUE);
         return nom;
        
     }
    
    /*Afficher les points avec navire en rouge*/
    public String hit(){
        
        String nom=ColorUtil.colorize(" x ", ColorUtil.Color.RED);
         return nom;
        
     }
    /*Pour créer la ligne de numeros*/   
    public void num()
    {
    for(int i=0;i<this.taille;i++)  
        System.out.printf("%2d ",(i+1)); 
                                            /*Ecrit les numeros */
      System.out.print("\n");                          
    }
    
    public void letter(int i)
    {
      System.out.print(" "+(char)(i+65)+" ");     /*Ecrit les lettres */
                                                /*En utilisant la conversion int->char*/   
    }                                           /*a est representé par 65 en séquence l'alfabet*/  
    
    /*Pour afficher le nom du Tableau*/  
     public void nameTableau(String nome)
    {
    System.out.println(nome);                 /*Montrer le nom*/
    System.out.print("   ");
    }
     
    /*Fonction principal de affichage*/  
    public void print()
    {
        
    int i = 0, j = 0;
    nameTableau("Navires:");                        /*Montrer le nom*/  
    num();                                          /*Affiche la ligne de numeros*/
    
    /*Affichage du tableau navires*/
    for(i=0;i<this.taille;i++){                     /*Parcour le tableau*/
       letter(i);                                   /*La grid de lettre*/
        for(j=0;j<this.taille;j++){                 /*Parcour le tableau*/
            if( this.navires[i][j]!=null) {         /*Verifie s'il a navire*/
                if(this.navires[i][j].isStruck())   /*Verifie si le navire etait touche*/
                System.out.print(" "+this.navires[i][j].toChar()+" ");
                else /*Si le navire etait touche on appel toChar qu'ecrit le label en rouge*/                            
                System.out.print(" "+this.navires[i][j].getShip().getLabel()+" ");
                } else { /*Sinon on ecrit seul le label avec get Label*/
               System.out.print(" "+"."+" ");/*S'il n'a pas navire on met le point*/
            }        
        }
        System.out.print("\n");                     /*Saute de ligne*/
    }
    
    System.out.print("\n");                         /*Saute de ligne*/
    
    /*Montrer le tableu Frappes*/
    nameTableau("Frappes:");                        /*Montre le nom*/
   
    num();                                          /*Numerotation*/
    
   
    /*Affichage du tableau*/
    for(i=0;i<this.taille;i++){                
        letter(i);//                                /*Grid de lettres*/        
        for(j=0;j<this.taille;j++){
            if(this.fraps[i][j] == null)            /*Verifie s'il y a deja ou non un frappe*/
                System.out.print(" . ");            /*S'il n'a frappé affiche le point*/  
            else if(this.fraps[i][j] == false)      /*Si la frappé est false , affiche eau*/
                System.out.print(water());          
            else System.out.print(hit());           /*Sinon affiche le Hit*/
        }
        System.out.print("\n");                     /*Saute de lignes*/
    }  
  }
    
    
    

    

 /*Manager les exceptions de debordement de board du type EAST WEAST*/
   /*On verifie 3 conditions 
      1) Si le navire en tel direction deborde ou non du Board
      2) Si x est deja dehors du Board  
      3) Si y  est deja dehors du Board 
    Les deux dernier peux causé un probleme si en un tableau taille 10 on met un
    navire en 13 de taille 4 en direction north 13-4=9 dans le tableau mais non!
    */
  public int handleExceptionsOrientationEW(AbstractShip ship, int x, int y)
  {
      int dx=0;
  Orientation o = ship.getOrientation();
		if (o == Orientation.EAST) {
			if (x + ship.getLength() >= this.taille||y>taille-1||x>taille-1) {
				throw new IllegalArgumentException("ship is out of the board.");	
			}
			dx = 1;
		
		}
		if (o == Orientation.WEST) {
			if (x + 1 - ship.getLength() < 0||y>taille-1||x>taille-1) { /*Evaluer le limite y+1*/
				throw new IllegalArgumentException("ship is out of the board.");	
			}
			dx = -1;
		}
                return dx;
  }
  
  /*Manager les exceptions de debordement de board du type SOUTH NORH*/
  /*Meme 3 conditions - orientation différents*/
  public int handleExceptionsOrientationSN(AbstractShip ship, int x, int y)
  {
            int dy=0;
            Orientation o = ship.getOrientation();
             if (o == Orientation.SOUTH) {
			if (y + ship.getLength() >= this.taille||y>taille-1||x>taille-1) {
				throw new IllegalArgumentException("ship is out of the board.");	
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (y + 1 - ship.getLength() < 0||y>taille-1||x>taille-1) {
				throw new IllegalArgumentException("ship is out of the board.");	
			}
			dy = -1;
                }
             return dy;
  }
  
   /*Manager les exceptions de mettre un navire au dessous d'autre*/
   /*LA fonction haShip verifie s'il y a un navire ou on essai de mettre autre*/
   public void handleExceptionsShipOverlay(AbstractShip ship, int x, int y)
   {
       int ix = x;
       int iy = y;

	for (int i = 0; i < ship.getLength(); ++i) {
		if (hasShip(ix, iy)) {                            
			throw new IllegalArgumentException("Ship overlays.");
		}
	}
   
   }
   /*On utilise le override pour garantir qu'on sera en train d'appeler la bonne fonction*/
   /*Override verifie si un fonction etait deja defini en implement */
   /*Verifie le taille du navire*/
 @Override
 public int getSize(){
        return this.taille;
 }

@Override /*Pour garantir qu'il y a la fonction qu'on veut ecrire*/
    public boolean hasShip(int x, int y) {
                /*Verifie les exceptions de sortir du Board*/
              
		if (x > this.taille || y > this.taille) {
			throw new IllegalArgumentException("out of the board.");
		}
                
                /*Vérifie le tableau navire s'il y a navires*/
                
                boolean hited;
               
                if(this.navires[x][y]!=null)                    /*Verifie s'il y a un navire*/
                {
                        hited=true;
              
                       if(this.navires[x][y].isSunkState())            /*Verifie si le navire etait coulé*/
                        {
                            hited=false;                        /*Si le navire était coule retourne false*/
                    
                            //System.out.println(hited);           /*Pour verifier si on arrive ici*/
                        }
                }
                else
                {       hited=false;}
                return hited;
                
               
	}
    
@Override
    /*Retourne un lieu de la tableau fraps*/
      public Boolean getHit(int x, int y){
          /*Vérifie l`exception de debordement du Board*/
       if (x > this.taille || y > this.taille) {
			throw new IllegalArgumentException("out of the board.");
		}
		return this.fraps[x][y];
       }
      
@Override
 /*Defini où mettre le navire*/
public void putShip(AbstractShip ship, int x, int y){
   int dx=0,dy=0;
   int xaux=x/*-1*/;                /*Pour résoudre le probleme de comencer a zero*/
   int yaux=y/*-1*/;
   /*Appels les exceptions et avec l'orientation nous donnes les dx et dy pour bien ecrire les labels*/
   dx=handleExceptionsOrientationEW(ship, xaux, yaux);
   dy=handleExceptionsOrientationSN(ship, xaux, yaux);
    
   handleExceptionsShipOverlay(ship, xaux, yaux);
   
   /*Ecrire le navire dans le Board*/
            for (int i = 0; i < ship.getLength(); ++i) {
			this.navires[xaux][yaux] =new ShipState(ship);
			xaux += dx;                         /*Utiliser pour oriente les navires*/
			yaux += dy;                        /*Chaque orientation a un dx ou dy*/
		}
    }

    /*Definir où mettre le hit*/
  @Override
      public void setHit(boolean hit, int x, int y){
          int xi=x;                       
          int yi=y;
           /*Vérifie si le hit est dans le board*/
              if (xi > this.taille || yi > this.taille) {
	throw new IllegalArgumentException("out of the board.");
	}
         //     System.out.printf("%b",this.hasShip(xi,yi));
         
              /*Vérifie si le navire etait frappé*/
              if(this.fraps[xi][yi]==null)              /*Verifie si la condition initial                                         a deja change*/
              {      if(hit==true)          
              {   
               if(this.hasShip(xi,yi))
               {this.fraps[xi][yi] = true;  }            /*Si oui -> frap true*/
             else 
               { this.fraps[xi][yi] = false;}}            /*Sinon false*/
              }
       } 
     
   /*Envoie le hit*/
      @Override
      public Hit sendHit(int x, int y){
          /*Verifie si cette partie etait deja touche*/
          int xi=x/*-1*/;                       /*Resoudre le probleme du indice 0 ou 1*/
          int yi=y/*-1*/;
          //System.out.println(yi);
          if(this.fraps[xi][yi]==null)          /*Si cet endroit etait pas touche on configure le hit*/
          {   
              this.setHit(true, xi, yi);
          }else                                 /*Sinon il avait deja un hit*/
              { 
                  System.out.printf("Hit Already send %b \n",this.fraps[xi][yi]);
                    return Hit.fromInt(0);}
          
          /*A travers du setHit on a déjà vérifie le hasShips en le tableau fraps -donc on le utilise*/
          
          /*Création du objet Hit*/
  
          if(!this.fraps[xi][yi])
          {     return Hit.fromInt(-1);       /*Si fraps false= HIT MISS*/
            
          }
                else {                         /*Sinon*/
              
              this.navires[xi][yi].addStrikeStatue();/*il y a eu un hit -> augmente le strike counter*/
            //  System.out.println("\n STRIKE");
              if(this.navires[xi][yi].isSunkState()) /*Si le state du navire est sunk=*/
            {
                System.out.println("Afundou\n");
                return Hit.fromInt(this.navires[xi][yi].getShip().getLength());/*Créer le hit a partir du taille*/
                
            }else{
                /*Sinon il y a un strike , retourne strike*/
               // System.out.printf("%d \n",this.navires[xi][yi].getShip().getStrikeCount());
                this.navires[xi][yi].toChar();
                return Hit.fromInt(-2);
            }
          }    
          }
       
      
}



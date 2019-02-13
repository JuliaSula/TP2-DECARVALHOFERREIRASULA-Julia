package com.excilys.player;

import java.util.NoSuchElementException;

public enum Hit {
    MISS(-1, "manqué"),
    STRIKE(-2, "touché"),
    NOT(0,"no ship"),
    DESTROYER(2, "Frégate"),
    SUBMARINE(3, "Sous-marin"),
    BATTLESHIP(4, "Croiseur"),
    CARRIER(5, "Porte-avion");

    /* ***
     * Attributs
     */
    private int value;
    private String label;

    /* ***
     * Constructeur
     */
    Hit(int value, String label) {
        this.value = value;
        this.label = label;
    }

    /* ***
     * Méthodes
     */
    public static Hit fromInt(int value) {
        for (Hit hit : Hit.values()) {
            if (hit.value == value) {
                return hit;
            }
        }
        throw new NoSuchElementException("no enum for value " + value);
    }
    
/*Acès aux variables encapsulé*/
    public String toString() {
        return this.label;
    }
    
    public int value()
    {
        return this.value;
    }
};

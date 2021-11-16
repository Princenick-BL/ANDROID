package com.example.td3;

public class Dice {

    private int nbSides;
    // Constructeur pour un dé simple
    public Dice(){
        this.nbSides = 6;
    }
    //Constructeur pour un dé dont les faces > 6
    public  Dice(int nbSides){
        this.nbSides = nbSides;
    }
    //Fonction qui permet de générer le nombre aléatoire
    public int roll(){
        return (int)Math.floor(Math.random()*(nbSides-1+1)+1);
    }
}

package com.example.fer.tateti;

import java.util.Random;

public class Partida {



    public Partida(int dificultad){
        this.Dific = dificultad;
        jugador=1;
    }

    public int ia(){
        jugador = 2;
        int casilla;
        Random casilla_azar = new Random();
        casilla = casilla_azar.nextInt(9);
        return casilla;



    }
    public final int Dific;
    public int jugador;
}

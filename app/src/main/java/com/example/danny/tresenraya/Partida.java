package com.example.danny.tresenraya;

import java.util.Random;

/**
 * Created by danny on 07/04/17.
 */

public class Partida {
    private int dificultad;
    public int jugador;
    private int []casillas;
    private final int [][] COMBINACIONES ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public Partida(int dificultad){
        this.dificultad=dificultad;

        jugador=1;
        casillas=new int[9];

        for(int i=0; i<9;i++){
            casillas[i]=0;
        }

    }

    public boolean compruebacasilla(int casilla){

        if(casillas[casilla]!=0){
            return false;

        }else{
            casillas[casilla]=jugador;
        }
     return true;
    }

    public int ia(){
      int casilla;
        Random casilla_azar=new Random();
        casilla=casilla_azar.nextInt(9);
        return casilla;

    }

    public int turno(){
        boolean emmpate=true;
        boolean ult_movimiento=true;
      for(int i=0;i<COMBINACIONES.length;i++){
          for(int pos:COMBINACIONES[i]){
              if(casillas[pos]!=jugador)ult_movimiento=false;
          if(casillas[pos]==0){
              emmpate=false;
          }
          }//for anidado
      }/// cierre for

        if (ult_movimiento)return jugador;
        ult_movimiento=true;

      if (emmpate){
          return 3;
      }

        jugador++;
        if(jugador>2){
            jugador=1;
        }
        return 0;
    }

    public int dosEnraya(int jugador_en_curso){
        int casilla=-1;
        int cuantas_lleva=0;

        for(int i=0;i<COMBINACIONES.length;i++){
            for(int pos:COMBINACIONES[i]){
                if (casillas[pos]==jugador_en_curso) {
                  cuantas_lleva++;
                    if(casillas[pos]==0)casilla=pos;
                }

            }//for anidado
            if (cuantas_lleva==2 && casilla!=-1)return casilla;
            casilla=-1;
            cuantas_lleva=0;
        }/// cierre for
      return -1;

    }

}

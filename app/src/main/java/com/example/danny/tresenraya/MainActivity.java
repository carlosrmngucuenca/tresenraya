package com.example.danny.tresenraya;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        casillas = new int[9];
        casillas[0]=R.id.casilla1;
        casillas[1]=R.id.casilla2;
        casillas[2]=R.id.casilla3;
        casillas[3]=R.id.casilla4;
        casillas[4]=R.id.casilla5;
        casillas[5]=R.id.casilla6;
        casillas[6]=R.id.casilla7;
        casillas[7]=R.id.casilla8;
        casillas[8]=R.id.casilla9;
    }

    public void aJugar(View vista){
        ImageView imagen;
        for(int cadaCasilla:casillas){
           imagen=(ImageView)findViewById(cadaCasilla);
            imagen.setImageResource(R.drawable.casilla);
        }
    jugadores=1;
     if(vista.getId()==R.id.dosjugadores){
            jugadores=2;
        }

        RadioGroup botonesDif=(RadioGroup)findViewById(R.id.configd);
        int id=botonesDif.getCheckedRadioButtonId();

        int dificultad=0;
        if(id==R.id.normal){
            dificultad=1;

        }else if(id==R.id.imposible){
            dificultad=2;

        }

        partida = new Partida(dificultad);

        ((Button)findViewById(R.id.unjugador)).setEnabled(false);
        ((RadioGroup)findViewById(R.id.configd)).setAlpha(0);
        ((Button)findViewById(R.id.dosjugadores)).setEnabled(false);

    }

    public void toque(View mivista){
        if(partida==null){
            return;
        }
        int casilla=0;
        for(int i=0; i <9 ; i++){
            if(casillas[i]==mivista.getId()){
                casilla=i;
                break;

            }

        }
       Toast mitoast= Toast.makeText(this, "has pulsado la casilla "+casilla, Toast.LENGTH_LONG);
       mitoast.setGravity(Gravity.CENTER,0,0);
        mitoast.show();
        if(partida.compruebacasilla(casilla)==false){
            return;
        }
        marca(casilla);
        partida.turno();
        casilla=partida.ia();
        while(partida.compruebacasilla(casilla)!=true){

            casilla=partida.ia();
        }

        marca(casilla);
        partida.turno();

    }

    private void marca(int casilla){
        ImageView imagen;
        imagen=(ImageView)findViewById(casillas[casilla]);
        if(partida.jugador==1){
            imagen.setImageResource(R.drawable.circulo);

        }else{
            imagen.setImageResource(R.drawable.aspa);
        }
    }
    private int jugadores;
    private int[] casillas;
    private Partida partida;
}

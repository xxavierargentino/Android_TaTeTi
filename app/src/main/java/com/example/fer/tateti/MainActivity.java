package com.example.fer.tateti;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    private int jugadores;
    private int[] CASILLAS;
    private Partida partida;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //asignar casillas al array
        CASILLAS = new int[9];
        CASILLAS[0]= R.id.a1;
        CASILLAS[1]= R.id.a2;
        CASILLAS[2]= R.id.a3;
        CASILLAS[3]= R.id.b1;
        CASILLAS[4]= R.id.b2;
        CASILLAS[5]= R.id.b3;
        CASILLAS[6]= R.id.c1;
        CASILLAS[7]= R.id.c2;
        CASILLAS[8]= R.id.c3;
    }

    public void toque(View miCasilla){
        String etiqueta = miCasilla.getTag().toString();
        Toast toast=Toast.makeText(this,"Etiqueta: " + etiqueta,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
        if(etiqueta.equals("0")) {
            if (partida == null) {
                return;
            }
            int casilla = 0;
            for (int i = 0; i < 9; i++) {
                if (CASILLAS[i] == miCasilla.getId()) {
                    casilla = i;
                    break;
                }
            }
            partida.jugador = 1;
            this.marca(casilla);
            casilla = partida.ia();
            this.marca(casilla);
        }else{
            toast=Toast.makeText(this,"CASILLA MARCADA!!",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }

        /*
        Toast toast = Toast.makeText(this,"Has Presionado la casilla: " + casilla,Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
*/
    }

    private void marca(int num_casilla){
        ImageView imagen = (ImageView)(findViewById(CASILLAS[num_casilla]));
        //QUIZAS MARCA DEBERIA DEVOLVER UN BOOLEAN PARA QUE SI ES
        //LA INTELIG ARTIFICIAL REPITA LA OPERACION Y SI ES UN HUMANO VUELVA A ELEGIR
        //String tag = imagen.getTag().toString();

           if (partida.jugador == 1) {
               imagen.setImageResource(R.drawable.circulo_48x48);
               imagen.setTag(1);
           } else {
               imagen.setImageResource(R.drawable.cruz_48x48);
               imagen.setTag(2);
           }

    }

    public void aJugar(View vista){
        ImageView imagen;
        for (int cadaCasilla:CASILLAS){
            imagen = (ImageView) findViewById(cadaCasilla);
            imagen.setImageResource(R.drawable.casilla_48x48);
            imagen.setBackgroundColor(16777215);
        }

        int jugadores = 1;
        if(vista.getId()==R.id.btnDosJugadores){
            jugadores = 2;
        }

        RadioGroup grupoDificultad = (RadioGroup)findViewById(R.id.ConfigDific);
        int id=grupoDificultad.getCheckedRadioButtonId();

        int dificultad = 0;
        if(id == R.id.radioNormal){
            dificultad = 1;
        }else if(id == R.id.radioImposible){
            dificultad = 2;
        }

        partida = new Partida(dificultad);

        //deshabilitar todos los botones
        ((Button)(findViewById(R.id.btnUnJugador))).setEnabled(false);
        ((Button)(findViewById(R.id.btnDosJugadores))).setEnabled(false);
        ((RadioGroup)(findViewById(R.id.ConfigDific))).setAlpha(0);

    }


}

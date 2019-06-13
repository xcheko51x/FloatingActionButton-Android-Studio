package com.xcheko51x.floatingactionbutton;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);

        fab.setOnTouchListener(new View.OnTouchListener() {

            float inicioX, inicioY;
            float inicioRawX, inicioRawY;
            float distanciaX, distanciaY;
            int ultimaAccion;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                // Leemos los eventos del FAB
                switch (event.getActionMasked()) {

                    case MotionEvent.ACTION_DOWN: // ACCION CUANDO SE PRESIONA EL FAB
                        inicioX = v.getX() - event.getRawX(); // Obtiene la posicion en X
                        inicioY = v.getY() - event.getRawY(); // Obtiene la posicion en Y

                        inicioRawX = event.getRawX();
                        inicioRawY = event.getRawY();

                        ultimaAccion = MotionEvent.ACTION_DOWN;
                        break;

                    case MotionEvent.ACTION_MOVE: // ACCION CUANDO SE MUEVE Y ESTA PRESIONADO EL FAB
                        v.setX(event.getRawX() + inicioX); // Obtiene la posicion con el movimiento en X
                        v.setY(event.getRawY() + inicioY); // Obtiene la posicion con el movimiento en Y

                        ultimaAccion = MotionEvent.ACTION_MOVE;
                        break;

                    case MotionEvent.ACTION_UP: // ACCION CUANDO SE SUELTA EL FAB
                        distanciaX = event.getRawX() - inicioRawX; // Calcula la distancias en X para saber si mostrar el mensaje o no
                        distanciaY = event.getRawY() - inicioRawY; // Calcula la distancias en Y para saber si mostrar el mensaje o no

                        if(Math.abs(distanciaX) < 10 && Math.abs(distanciaY) < 10) {
                            Toast.makeText(MainActivity.this, "ACCIÓN", Toast.LENGTH_SHORT).show();
                        }

                        break;

                    case MotionEvent.ACTION_BUTTON_PRESS:

                        default:
                            return false;
                }
                return true;
            }
        });
    }
}

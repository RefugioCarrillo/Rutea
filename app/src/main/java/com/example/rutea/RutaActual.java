package com.example.rutea;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RutaActual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ruta_actual);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // para abrir cada actividad de la aplicación

        //1_declaracion del menu
        BottomNavigationView menu = findViewById(R.id.menu);

        //2_ seleccion por defecto del item del menu explorar ruta que muestre la pantalla principal
        menu.setSelectedItemId(R.id.rutaActual); // el menu inicia en esta pantalla

        //3_metodo de optencion del id para saber si el usuario cambia de icono
        menu.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            //4_navegacion del menu mediante su id de cada icono para cambio de pantallas
            if (id == R.id.rutaActual) {
                return true; //  esta pantalla sera la inicial
            } else if (id == R.id.login) {
                startActivity(new Intent(RutaActual.this, login.class));
                //animacion para no mostrar un cambio tan brusco
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.explorarRuta) {
                startActivity(new Intent(RutaActual.this, MainActivity.class));
                //animacion para no mostrar un cambio tan brusco
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.opiniones) {
                startActivity(new Intent(RutaActual.this, opiniones.class));
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });


    }
}
package com.example.rutea;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // para abrir cada actividad de la aplicación

        //1_declaracion del menu
        BottomNavigationView menu = findViewById(R.id.menu);

        //2_ seleccion por defecto del item del menu explorar ruta que muestre la pantalla principal
        menu.setSelectedItemId(R.id.login); // el menu inicia en esta pantalla

        //3_metodo de optencion del id para saber si el usuario cambia de icono
        menu.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            //4_navegacion del menu mediante su id de cada icono para cambio de pantallas
            if (id == R.id.login) {
                return true; //  esta pantalla sera la inicial
            } else if (id == R.id.rutaActual) {
                startActivity(new Intent(login.this, RutaActual.class));
                //animacion para no mostrar un cambio tan brusco
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.explorarRuta) {
                startActivity(new Intent(login.this, MainActivity.class));
                //animacion para no mostrar un cambio tan brusco
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.opiniones) {
                startActivity(new Intent(login.this, opiniones.class));
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });



    }
}
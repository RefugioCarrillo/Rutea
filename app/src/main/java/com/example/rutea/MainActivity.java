package com.example.rutea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RutaAdapter adapter;
    List<Ruta> rutaList;
    List<Integer> carruselImages;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.visor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista de rutas
        rutaList = new ArrayList<>();

        // Lista de imágenes para el carrusel (recuerda tener estas imágenes en drawable)
        carruselImages = Arrays.asList(
                R.drawable.c1,
                R.drawable.c2,
                R.drawable.c3,
                R.drawable.c4,
                R.drawable.c5,
                R.drawable.c6,
                R.drawable.c7,
                R.drawable.c8,
                R.drawable.c9,
                R.drawable.c10,
                R.drawable.c11

        );

        // Inicializar el adapter con rutas vacías e imágenes del carrusel
        adapter = new RutaAdapter(rutaList, carruselImages);
        recyclerView.setAdapter(adapter);

        // Firebase Firestore
        db = FirebaseFirestore.getInstance();
        db.collection("rutas")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot doc : queryDocumentSnapshots) {
                        Ruta ruta = doc.toObject(Ruta.class);
                        rutaList.add(ruta);
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );

        // para abrir cada actividad de la aplicación

        //1_declaracion del menu
        BottomNavigationView menu = findViewById(R.id.menu);

        //2_ seleccion por defecto del item del menu explorar ruta que muestre la pantalla principal
        menu.setSelectedItemId(R.id.explorarRuta); // el menu inicia en esta pantalla

        //3_metodo de optencion del id para saber si el usuario cambia de icono
        menu.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            //4_navegacion del menu mediante su id de cada icono para cambio de pantallas
            if (id == R.id.explorarRuta) {
                return true; //  esta pantalla sera la inicial
            } else if (id == R.id.rutaActual) {
                startActivity(new Intent(MainActivity.this, RutaActual.class));
                //animacion para no mostrar un cambio tan brusco
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.login) {
                startActivity(new Intent(MainActivity.this, login.class));
                //animacion para no mostrar un cambio tan brusco
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.opiniones) {
                startActivity(new Intent(MainActivity.this, opiniones.class));
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });


    }
}

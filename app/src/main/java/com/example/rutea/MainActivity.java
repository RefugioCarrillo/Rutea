package com.example.rutea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

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

        BottomNavigationView bottomNav = findViewById(R.id.menu);

        // Ajustar iconos del menú inferior
        for (int i = 0; i < bottomNav.getMenu().size(); i++) {
            final View iconView = bottomNav.findViewById(bottomNav.getMenu().getItem(i).getItemId());

            if (iconView != null) {
                iconView.post(() -> {
                    View icon = iconView.findViewById(com.google.android.material.R.id.icon);
                    if (icon != null) {
                        int sizeInPx = (int) getResources().getDisplayMetrics().density * 32; // 32dp
                        icon.getLayoutParams().height = sizeInPx;
                        icon.getLayoutParams().width = sizeInPx;
                        icon.requestLayout();
                    }
                });
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.visor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Lista de rutas
        rutaList = new ArrayList<>();

        // Imágenes del carrusel
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

        // Inicializar adapter
        adapter = new RutaAdapter(rutaList, carruselImages);
        recyclerView.setAdapter(adapter);

        // Inicializar Firestore
        db = FirebaseFirestore.getInstance();

        // 🔄 Escuchar en tiempo real los cambios en la colección "rutas"
        db.collection("rutas")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot snapshots, FirebaseFirestoreException e) {
                        if (e != null) {
                            Toast.makeText(MainActivity.this, "Error al cargar datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Limpiar lista para evitar duplicados
                        rutaList.clear();

                        for (DocumentSnapshot doc : snapshots.getDocuments()) {
                            Ruta ruta = doc.toObject(Ruta.class);
                            rutaList.add(ruta);
                        }

                        adapter.notifyDataSetChanged();
                    }
                });

        // Navegación del menú inferior
        bottomNav.setSelectedItemId(R.id.explorarRuta); // pantalla inicial

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.explorarRuta) {
                return true;
            } else if (id == R.id.rutaActual) {
                startActivity(new Intent(MainActivity.this, RutaActual.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.login) {
                startActivity(new Intent(MainActivity.this, login.class));
                overridePendingTransition(0, 0);
                return true;
            } else if (id == R.id.opiniones) {
                startActivity(new Intent(MainActivity.this, opiniones.class));
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });

        // Espacio entre tarjetas
        int spaceInDp = 8;
        float scale = getResources().getDisplayMetrics().density;
        int spaceInPx = (int) (spaceInDp * scale + 0.5f);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spaceInPx));
    }
}

package com.example.rutea;

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
                R.drawable.lugar1,
                R.drawable.lugar2,
                R.drawable.lugar3
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
    }
}

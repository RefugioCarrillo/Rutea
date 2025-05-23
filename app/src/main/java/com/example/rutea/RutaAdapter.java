package com.example.rutea;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class RutaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CARRUSEL = 0;
    private static final int TYPE_RUTA = 1;

    private List<Ruta> rutaList;
    private List<Integer> carruselImages; // Ej: Arrays.asList(R.drawable.img1, R.drawable.img2)

    public RutaAdapter(List<Ruta> rutaList, List<Integer> carruselImages) {
        this.rutaList = rutaList;
        this.carruselImages = carruselImages;
    }

    @Override
    public int getItemCount() {
        return rutaList.size() + 1; // +1 para el carrusel
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? TYPE_CARRUSEL : TYPE_RUTA;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == TYPE_CARRUSEL) {
            View view = inflater.inflate(R.layout.item_carrusel, parent, false);
            return new CarruselViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_ruta, parent, false);
            return new RutaViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_CARRUSEL) {
            CarruselViewHolder carruselHolder = (CarruselViewHolder) holder;
            CarruselAdapter carruselAdapter = new CarruselAdapter(carruselImages);
            carruselHolder.viewPager.setAdapter(carruselAdapter);
        } else {
            RutaViewHolder rutaHolder = (RutaViewHolder) holder;
            Ruta ruta = rutaList.get(position - 1); // Desfase por carrusel
            rutaHolder.tvNombreRuta.setText(ruta.getNombre());
            rutaHolder.tvDescripcion.setText(ruta.getDescripcion());
            rutaHolder.tvLugar.setText(ruta.getLugar());
        }
    }

    // ViewHolder para el carrusel
    public static class CarruselViewHolder extends RecyclerView.ViewHolder {
        ViewPager2 viewPager;

        public CarruselViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.viewPager);
        }
    }

    // ViewHolder para rutas
    public static class RutaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreRuta, tvDescripcion, tvLugar;

        public RutaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreRuta = itemView.findViewById(R.id.tvNombreRuta);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvLugar = itemView.findViewById(R.id.tvLugar);
        }
    }
}
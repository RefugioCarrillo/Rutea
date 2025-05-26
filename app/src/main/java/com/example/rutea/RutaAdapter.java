package com.example.rutea;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.GeoPoint;
import android.text.TextUtils;

import java.util.List;

public class RutaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_CARRUSEL = 0;
    private static final int TYPE_RUTA = 1;

    private List<Ruta> rutaList;
    private List<Integer> carruselImages;

    public RutaAdapter(List<Ruta> rutaList, List<Integer> carruselImages) {
        this.rutaList = rutaList;
        this.carruselImages = carruselImages;
    }

    @Override
    public int getItemCount() {
        return rutaList.size() + 1;
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
            rutaHolder.tvEstablecimiento.setText(ruta.getEstablecimiento());

            List<String> categorias = ruta.getCategoria();
            String categoriasTexto;

            if (categorias != null && !categorias.isEmpty()) {
                categoriasTexto = TextUtils.join(", ", categorias);
            } else {
                categoriasTexto = "Sin categorías"; // Texto por defecto si no hay categorías
            }

            rutaHolder.tvCategoria.setText(categoriasTexto);

            // Cargar imagen con Glide
            Glide.with(holder.itemView.getContext())
                    .load(ruta.getImagen())
                    .placeholder(R.drawable.error)
                    .into(rutaHolder.ivImagen);

            // Abrir ubicación en Google Maps
            rutaHolder.btnVerUbicacion.setOnClickListener(v -> {
                GeoPoint geo = ruta.getUbicasion();
                if (geo != null) {
                    String geoUri = "geo:" + geo.getLatitude() + "," + geo.getLongitude() +
                            "?q=" + geo.getLatitude() + "," + geo.getLongitude();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                    intent.setPackage("com.google.android.apps.maps");
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public static class CarruselViewHolder extends RecyclerView.ViewHolder {
        ViewPager2 viewPager;

        public CarruselViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.viewPager);
        }
    }

    public static class RutaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombreRuta, tvDescripcion, tvLugar, tvEstablecimiento, tvCategoria;
        ImageView ivImagen;
        Button btnVerUbicacion;

        public RutaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreRuta = itemView.findViewById(R.id.tvNombreRuta);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvLugar = itemView.findViewById(R.id.tvLugar);
            tvEstablecimiento = itemView.findViewById(R.id.establecimiento);
            tvCategoria = itemView.findViewById(R.id.categoria);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            btnVerUbicacion = itemView.findViewById(R.id.btnVerUbicacion);
        }
    }
}

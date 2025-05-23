package com.example.rutea;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rutea.R;
import com.example.rutea.Ruta;

import java.util.List;

public class RutaAdapter extends RecyclerView.Adapter<RutaAdapter.RutaViewHolder> {

    private List<Ruta> rutaList;

    public RutaAdapter(List<Ruta> rutaList) {
        this.rutaList = rutaList;
    }

    @NonNull
    @Override
    public RutaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ruta, parent, false);
        return new RutaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RutaViewHolder holder, int position) {
        Ruta ruta = rutaList.get(position);
        holder.tvNombreRuta.setText(ruta.getNombre());
        holder.tvDescripcion.setText(ruta.getDescripcion());
        holder.tvLugar.setText(ruta.getLugar());
    }

    @Override
    public int getItemCount() {
        return rutaList.size();
    }

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

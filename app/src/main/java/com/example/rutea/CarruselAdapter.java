package com.example.rutea;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CarruselAdapter extends RecyclerView.Adapter<CarruselAdapter.CarruselViewHolder> {

    private List<Integer> imageList; // Lista de recursos drawable (ej. R.drawable.imagen1)

    public CarruselAdapter(List<Integer> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public CarruselViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrusel_image, parent, false);
        return new CarruselViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarruselViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static class CarruselViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CarruselViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewCarrusel);
        }
    }
}
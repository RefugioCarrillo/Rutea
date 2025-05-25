package com.example.rutea;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        // Aplica espacio solo debajo de cada ítem
        outRect.bottom = space;

        // (Opcional) Si quieres también espacio arriba del primer elemento:
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = space;
        }
    }
}

package com.example.sb_firebase_ecommerce;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class View_Product_Adapter extends RecyclerView.Adapter<View_Product_Adapter.view_holder> {
    @NonNull
    @Override
    public View_Product_Adapter.view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull View_Product_Adapter.view_holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class view_holder extends RecyclerView.ViewHolder {
        public view_holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}


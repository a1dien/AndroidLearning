package com.example.recipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private ArrayList<RecipeItem> recipeItems;
    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public ImageView recipeImageView;
        public TextView recipeTitle;
        public TextView recipeDesc;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeImageView = itemView.findViewById(R.id.foodPicture);
            recipeTitle = itemView.findViewById(R.id.foodTitle);
            recipeDesc = itemView.findViewById(R.id.foodDesc);
        }
    }

    public RecipeAdapter(ArrayList<RecipeItem> recipeItems) {
        this.recipeItems = recipeItems;
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(parent);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        RecipeItem recipeItem = recipeItems.get(position);
        holder.recipeImageView.setImageResource(recipeItem.getRecipeImage());
        holder.recipeTitle.setText(recipeItem.getRecipeTitle());
        holder.recipeDesc.setText(recipeItem.getRecipeDesc());
    }



    @Override
    public int getItemCount() {
        return recipeItems.size();
    }


}

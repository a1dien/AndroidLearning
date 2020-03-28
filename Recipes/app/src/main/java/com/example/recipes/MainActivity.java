package com.example.recipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        ArrayList<RecipeItem> recipeItems = new ArrayList<>();
        recipeItems.add(new RecipeItem(R.drawable.dukan,Recipes.title_dukan,Recipes.desc_dukan,Recipes.fdesc_dukan));
        recipeItems.add(new RecipeItem(R.drawable.borsh, Recipes.title_borsh, Recipes.desc_borsh, Recipes.fdesc_borsh));
*/
        recyclerView.findViewById(R.id.recyclerView);

        /*recyclerView.setHasFixedSize(true);
        adapter = new RecipeAdapter(recipeItems);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);*/
    }
}

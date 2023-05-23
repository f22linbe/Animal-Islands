package com.example.animalislands;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutButton = findViewById(R.id.about_button);

        aboutButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            intent.putExtra("title", "About app");
            startActivity(intent);
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Recycler widget



        String JSON_URL = "https://mobprog.webug.se/json-api?login=f22linbe";
        new JsonTask(this, this).execute(JSON_URL);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Island[] islands = gson.fromJson(json, Island[].class);
        ArrayList<String> detailedItems = new ArrayList<>();
        ArrayList<RecyclerViewItem> items = new ArrayList<>();
        for (Island island : islands) {
            items.add(new RecyclerViewItem(island.toString()));
            detailedItems.add(island.getInfo());
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, detailedItems, item -> {
            Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
            intent.putExtra("itemString", item);
            startActivity(intent);
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    }

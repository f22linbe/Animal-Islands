package com.example.animalislands;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
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

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                intent.putExtra("title", "About app");
                startActivity(intent);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Recycler widget

        ArrayList<RecyclerViewItem> items = new ArrayList<>(Arrays.asList(
                new RecyclerViewItem("Matterhorn"),
                new RecyclerViewItem("Mont Blanc"),
                new RecyclerViewItem("Denali")
        ));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, item -> Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show());

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);


        String JSON_URL = "https://mobprog.webug.se/json-api?login=f22linbe";
        new JsonTask(this, this).execute(JSON_URL);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();
        Island[] islands = gson.fromJson(json, Island[].class);

        ArrayList<RecyclerViewItem> items = new ArrayList<>();

        for (Island island : islands) {
            items.add(new RecyclerViewItem(island.getName()));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, item -> Toast.makeText(MainActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show());

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    }

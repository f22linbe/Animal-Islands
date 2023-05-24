package com.example.animalislands;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {
private Spinner dropdownMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutButton = findViewById(R.id.about_button);
        dropdownMenu = findViewById(R.id.dropdown);

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
        // Arraylists
        ArrayList<String> detailedItems = new ArrayList<>();
        ArrayList<RecyclerViewItem> items = new ArrayList<>();

        List<String> countryItems = new ArrayList<>();

        for (Island island : islands) {
            items.add(new RecyclerViewItem(island.toString()));
            detailedItems.add(island.getInfo());
            countryItems.add(island.getLocation());
        }

        ArrayAdapter<String> dropAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, countryItems);
        dropdownMenu.setAdapter(dropAdapter);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, detailedItems, item -> {
            Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
            intent.putExtra("itemString", item);
            startActivity(intent);
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        dropdownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCountry = countryItems.get(position);

                List<RecyclerViewItem> filterCountry = new ArrayList<>();

                for (RecyclerViewItem item : items) {
                    if(item.getTitle().contains(selectedCountry)) {
                        filterCountry.add(item);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    }

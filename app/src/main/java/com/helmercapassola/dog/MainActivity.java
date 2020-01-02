package com.helmercapassola.dog;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Dialog dialog;
    EditText new_title, new_desc;
    ImageView new_image;
    Button btn_save;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;



   // private  ArrayList<Dog> dogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

  /*
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dogs = new ArrayList<Dog>();

        adapter = new DogAdapter(dogs, this);
        recyclerView.setAdapter(adapter);

        //imageView = findViewById(R.id.imageview);*/


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogShow();

            }
        });
    }


    public void DialogShow(){

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_item);

        new_title = dialog.findViewById(R.id.new_title);
        new_desc = dialog.findViewById(R.id.new_desc);
        new_image = dialog.findViewById(R.id.new_image);
        btn_save = dialog.findViewById(R.id.btn_save);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

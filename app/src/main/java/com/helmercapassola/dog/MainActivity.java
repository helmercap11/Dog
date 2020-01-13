package com.helmercapassola.dog;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Dialog dialog;
    EditText new_title, new_desc;
    ImageView new_image;
    Button btn_save;

    RecyclerView recyclerView;


    DogAdapter dogAdapter;

    String ficheiro;


    ArrayList<Dog> dogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dogAdapter = new DogAdapter(this);
        recyclerView.setAdapter(dogAdapter);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String value = preferences.getString("text", "Não tem dados");

        getSupportActionBar().setTitle(value);


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
        new_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isStoragePermissionGrantend()){
                    Intent gallerIntent = new Intent(Intent.ACTION_PICK);
                    gallerIntent.setType("image/*"); // expecificar o tipo de ficheiro
                    startActivityForResult(gallerIntent, 12);
                }else {
                    Toast.makeText(MainActivity.this, "Voĉe não tem permissão", Toast.LENGTH_SHORT).show();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 12);
                }

            }
        });
        btn_save = dialog.findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               save();
            }
        });


        dialog.setTitle("Novo Item");
        dialog.show();
    }

    public void  save(){


        String title = new_title.getText().toString();
        String desc = new_desc.getText().toString();
        dogAdapter.dogList.add(new Dog(title,desc,ficheiro));
        dogAdapter.notifyDataSetChanged();
        DogSharedPreferences.saveDotList(dogAdapter.dogList, MainActivity.this, "dogList");
        ficheiro = null;
        dialog.dismiss();
    }


    public  boolean isStoragePermissionGrantend(){

        return ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data !=null){
            Uri uri = data.getData();
            Log.i("MainActivity", "Localização do ficheiro: " + uri.getPath());
            ficheiro = getRealPathFormURI(uri);
            File file =  new File(ficheiro);
            Picasso.get().load(file).into(new_image);

        }


    }

    //localizar ficheiro

    public  String getRealPathFormURI(Uri  contentURI){

        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        cursor.moveToFirst();

        int id = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        String result = cursor.getString(id);
        cursor.close();
        return result;
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

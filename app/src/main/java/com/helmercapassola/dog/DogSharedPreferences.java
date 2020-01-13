package com.helmercapassola.dog;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class DogSharedPreferences {


    public static  void saveDotList(List<Dog> dogs, Context context, String key){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson =  new Gson();
        String json = gson.toJson(dogs);
        editor.putString(key, json);
        editor.apply();
    }


    public  static  List<Dog>  loadDog(Context context, String key){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = preferences.getString(key, null);
        Type type =  new  TypeToken<List<Dog>>(){}.getType();

        return  gson.fromJson(json, type);
    }

}

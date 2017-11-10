package com.example.jasna.myapplication_2.planets_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

import static android.content.ContentValues.TAG;

/**
 * Created by jasna on 24.10.17..
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "planets_database.db";
    public static final String TABLE_NAME = "planets_tbl";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESC= "description";
    public static final String COLUMN_TIME = "creation_date";


    public SQLiteHelper(Context context) {
        super(context,DATABASE_NAME, null, 7);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_NAME + " TEXT NOT NULL, " + COLUMN_DESC + " TEXT, " + COLUMN_TIME + " INTEGER);");

        //private static HashMap<String, String> all_planets_oncreate = new HashMap<String, String>();

        ContentValues initialValues1 = new ContentValues();
        initialValues1.put(COLUMN_NAME, "Mercury");
        initialValues1.put(COLUMN_DESC,"Mercury is the smallest and innermost planet in the Solar System. Its orbital period around the Sun of 88 days is the shortest of all the planets in the Solar System. It is named after the Roman deity Mercury, the messenger to the gods.");

        ContentValues initialValues2 = new ContentValues();
        initialValues2.put(COLUMN_NAME, "Venus");
        initialValues2.put(COLUMN_DESC,"Venus is the second planet from the Sun, orbiting it every 224.7 Earth days. It has no natural satellites.");


        ContentValues initialValues3 = new ContentValues();
        initialValues3.put(COLUMN_NAME, "Earth");
        initialValues3.put(COLUMN_DESC,"3rd Rock from the Sun!");

        ContentValues initialValues4 = new ContentValues();
        initialValues4.put(COLUMN_NAME, "Mars");
        initialValues4.put(COLUMN_DESC,"Yummy!");

        ContentValues initialValues5 = new ContentValues();
        initialValues5.put(COLUMN_NAME, "Jupiter");
        initialValues5.put(COLUMN_DESC,"Jupiter i Nevera - svemir pljeskavica!");

        ContentValues initialValues6 = new ContentValues();
        initialValues6.put(COLUMN_NAME, "Saturn");
        initialValues6.put(COLUMN_DESC,"Saturn is the sixth planet from the Sun and the second-largest in the Solar System, after Jupiter. It is a gas giant with an average radius about nine times that of Earth. It has only one-eighth the average density of Earth, but with its larger volume Saturn is over 95 times more massive. Saturn is named after the Roman god of agriculture; its astronomical symbol (♄) represents the god\\'s sickle.");

        ContentValues initialValues7 = new ContentValues();
        initialValues7.put(COLUMN_NAME, "Uranus");
        initialValues7.put(COLUMN_DESC,"LOL");

        ContentValues initialValues8 = new ContentValues();
        initialValues8.put(COLUMN_NAME, "Neptune");
        initialValues8.put(COLUMN_DESC,"Shortly after its discovery, Neptune was referred to simply as the planet exterior to Uranus or as Le Verrier's planet. The first suggestion for a name came from Galle, who proposed the name Janus. In England, Challis put forward the name Oceanus.");

        ContentValues initialValues9 = new ContentValues();
        initialValues9.put(COLUMN_NAME, "Pluto");
        initialValues9.put(COLUMN_DESC,"Pluto Has Been Officially Reclassified As A Planet.");

        db.insert(TABLE_NAME, null, initialValues1);
        db.insert(TABLE_NAME, null, initialValues2);
        db.insert(TABLE_NAME, null, initialValues3);
        db.insert(TABLE_NAME, null, initialValues4);
        db.insert(TABLE_NAME, null, initialValues5);
        db.insert(TABLE_NAME, null, initialValues6);
        db.insert(TABLE_NAME, null, initialValues7);
        db.insert(TABLE_NAME, null, initialValues8);
        db.insert(TABLE_NAME, null, initialValues9);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVal = new ContentValues();
        contentVal.put(COLUMN_NAME, name);

        Log.d(TAG, "addData: Adding " + name + "to" + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentVal);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void updateName(String newItem, String oldItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME + " = '" + newItem + "' WHERE " + COLUMN_NAME + " = '" + oldItem + "'";
        Log.d(TAG, "updateName: query: " + query1);
        Log.d(TAG, "updateName: Setting name to" + newItem);
        db.execSQL(query1);
    }

    public void deletePlanet(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query2 = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = '" + name + "'";
        Log.d(TAG, "deletePlanet: query: " + query2);
        Log.d(TAG, "deletePlanet: Deleting " + name + " from database.");
        db.execSQL(query2);
    }
}

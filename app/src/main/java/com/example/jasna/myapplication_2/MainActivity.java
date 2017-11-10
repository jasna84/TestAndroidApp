package com.example.jasna.myapplication_2;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jasna.myapplication_2.planets_db.SQLiteHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    SQLiteHelper myDb;

    Button add;
    EditText text;
    ListView lista;
    Button btn;
    CheckBox check;


    @Override //changed to protected
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new SQLiteHelper(this);

        add = (Button) findViewById(R.id.dodaj);
        text = (EditText) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.dugme);
        check = (CheckBox) findViewById(R.id.checkBox);

        lista = (ListView) findViewById(R.id.lista);
        lista.setVisibility(View.GONE);
        lista.setClickable(true);


        final String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Pluto"}; // povezali smo sad ovo sa List planet_list


        final List<String> planet_list = new ArrayList<String>(Arrays.asList(planets)); // a ovo povezano sa array adapterom

        final ArrayAdapter<String> mojAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, planet_list);

        lista.setAdapter(mojAdapter); // bindovali smo listView sa podacima iz adaptera

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lista.getVisibility() == View.GONE) {
                    lista.setVisibility(View.VISIBLE);
                } else {
                    lista.setVisibility(View.GONE);
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newItem = text.getText().toString();

                if (!newItem.isEmpty()) {
                    planet_list.add(newItem);
                    mojAdapter.notifyDataSetChanged();
                    AddPlanets(newItem);
                    text.getText().clear();
                } else {
                    Toast.makeText(MainActivity.this, "Make sure that the new planet name is added", Toast.LENGTH_LONG).show();
                }
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final String pozicija = (String) mojAdapter.getItem(position); //novi kod

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert");
                builder.setMessage("Do you want to delete this planet?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        planet_list.remove(position);
                        mojAdapter.notifyDataSetChanged();
                        myDb.deletePlanet(pozicija);
                        Toast.makeText(MainActivity.this, "Removed from the list", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();

                return true;
            }

        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String newItem = text.getText().toString();//new name
                String oldItem = (String) mojAdapter.getItem(position);//old name - novi red


                if(check.isChecked() && !newItem.isEmpty() ){
                    planet_list.set(position, newItem);
                    mojAdapter.notifyDataSetChanged();
                    myDb.updateName(newItem, oldItem);
                    text.getText().clear();
                } else if (check.isChecked() && newItem.isEmpty()){
                    Toast.makeText(MainActivity.this, "Make sure that the new planet name is added", Toast.LENGTH_LONG).show();
                } else {

                    Intent intent = new Intent(MainActivity.this, Pop.class);
                    String pozicija = (String) mojAdapter.getItem(position);
                    intent.putExtra("planet_name_clicked", pozicija);
                    startActivity(intent);
                }
            }
        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(check.isChecked()){
                    add.setVisibility(View.INVISIBLE);
                } else {
                    add.setVisibility(View.VISIBLE);
                }

            }
        });


    }

    private void AddPlanets(String newItem) {
        boolean insertData = myDb.addData(newItem);
        if(insertData) {
            Toast.makeText(this, "New Planet Added!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

}


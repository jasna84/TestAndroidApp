package com.example.jasna.myapplication_2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;


/**
 * Created by jasna on 13.10.17..
 */



public class Pop extends Activity {

    TextView mercuryTV;
    TextView venusTV;
    TextView earthTV;
    TextView marsTV;
    TextView jupiterTV;
    TextView saturnTV;
    TextView uranusTV;
    TextView neptuneTV;
    TextView plutoTV;

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);


        mercuryTV = (TextView) findViewById(R.id.text1);
        venusTV = (TextView) findViewById(R.id.text2);
        earthTV = (TextView) findViewById(R.id.text3);
        marsTV = (TextView) findViewById(R.id.text4);
        jupiterTV = (TextView) findViewById(R.id.text5);
        saturnTV = (TextView) findViewById(R.id.text6);
        uranusTV = (TextView) findViewById(R.id.text7);
        neptuneTV = (TextView) findViewById(R.id.text8);
        plutoTV = (TextView) findViewById(R.id.text9);

    Intent intent2 = getIntent();
        Bundle b = intent2.getExtras();
        String data = b.getString("planet_name_clicked");

        if (data != null) {
            switch (data) {
                case "Mercury":

                    mercuryTV.setVisibility(View.VISIBLE);
                    break;
                case "Venus":
                    venusTV.setVisibility(View.VISIBLE);
                    break;
                case "Earth":
                    earthTV.setVisibility(View.VISIBLE);
                    break;
                case "Mars":
                    marsTV.setVisibility(View.VISIBLE);
                    break;
                case "Jupiter":
                    jupiterTV.setVisibility(View.VISIBLE);
                    break;
                case "Saturn":
                    saturnTV.setVisibility(View.VISIBLE);
                    break;
                case "Uranus":
                    uranusTV.setVisibility(View.VISIBLE);
                    break;
                case "Neptune":
                    neptuneTV.setVisibility(View.VISIBLE);
                    break;
                case "Pluto":
                    plutoTV.setVisibility(View.VISIBLE);
                    break;
                default:
                    Toast.makeText(Pop.this, "The description for this planet is not added", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
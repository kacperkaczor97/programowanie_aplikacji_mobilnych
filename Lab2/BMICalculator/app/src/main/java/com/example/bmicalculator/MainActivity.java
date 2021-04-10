/* Kalkulator BMI Kacper Kaczor
 */
package com.example.bmicalculator;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
// Deklaracja zmiennych
    private EditText height;
    private EditText weight;
    private TextView result;
    private TextView resultPpmMen;
    private TextView resultPpmWoman;

    private EditText age;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        age = (EditText) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);
        resultPpmMen = (TextView) findViewById(R.id.resultPpmMen);
        resultPpmWoman = (TextView) findViewById(R.id.resultPpmWoman);


    }
// Metoda obliczająca

    public void calKcalMen(View v){
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
        String ageStr = age.getText().toString();

        if (heightStr != null && !"".equals(heightStr) && weightStr != null && !"".equals(weightStr) && ageStr != null && !"".equals(ageStr)) {
            float heightValue = Float.parseFloat(heightStr);
            float weightValue = Float.parseFloat(weightStr);
            float ageValue = Float.parseFloat(ageStr);

            float ppmForMen = 66.47f + (13.7f * weightValue) + (5.0f * heightValue) - (6.76f * ageValue);

            displayPpmForMen(ppmForMen);
        }
    }

    public void calKcalWoman(View v){
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();
        String ageStr = age.getText().toString();

        if (heightStr != null && !"".equals(heightStr) && weightStr != null && !"".equals(weightStr) && ageStr != null && !"".equals(ageStr)) {
            float heightValue = Float.parseFloat(heightStr);
            float weightValue = Float.parseFloat(weightStr);
            float ageValue = Float.parseFloat(ageStr);

            float ppmForWoman = 655.1f + (9.567f * weightValue) + (1.85f * heightValue) - (4.68f * ageValue);

            displayPpmForWoman(ppmForWoman);
        }
    }
// Wyswietlenie Wyniku

    private void displayPpmForMen(float ppmForMen) {
        String PpmForMenLabel = "";
        PpmForMenLabel = ppmForMen + "\n\n";
        resultPpmMen.setText(PpmForMenLabel);
    }

    private void displayPpmForWoman(float ppmForWoman) {
        String PpmForWomanLabel = "";
        PpmForWomanLabel = ppmForWoman + "\n\n";
        resultPpmWoman.setText(PpmForWomanLabel);
    }

}
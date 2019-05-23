package pl.saoneth.android_lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DodajWpis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);

        ArrayAdapter gatunki = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Pies", "Kot", "Rybki"});
        Spinner gatunek = findViewById(R.id.gatunek);
        gatunek.setAdapter(gatunki);
    }

    public void wyslij(View view) {
        EditText kolor = findViewById(R.id.kolor);
        EditText wielkosc = findViewById(R.id.wielkosc);
        EditText opis = findViewById(R.id.opis);
        Spinner gatunek = findViewById(R.id.gatunek);
        Animal zwierze = new Animal(gatunek.getSelectedItem().toString(), kolor.getText().toString(), Float.valueOf(wielkosc.getText().toString()), opis.getText().toString());
        Intent intencja = new Intent();
        intencja.putExtra("nowy", zwierze);
        setResult(RESULT_OK, intencja);
        finish();
    }
}

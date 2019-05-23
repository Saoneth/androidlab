package pl.saoneth.android_lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DodajWpis extends AppCompatActivity {
    private int modyfi_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);

        ArrayAdapter gatunki = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Pies", "Kot", "Rybki"});
        Spinner gatunek = findViewById(R.id.gatunek);
        gatunek.setAdapter(gatunki);

        Bundle extras = getIntent().getExtras();
        try {
            if (extras.getSerializable("element") != null) {
                Animal zwierz = (Animal) extras.getSerializable("element");
                EditText kolor = findViewById(R.id.kolor);
                EditText wielkosc = findViewById(R.id.wielkosc);
                EditText opis = findViewById(R.id.opis);
                kolor.setText(zwierz.getKolor());
                wielkosc.setText(Float.toString(zwierz.getWielkosc()));
                int spinnerPosition = gatunki.getPosition(zwierz.getGatunek());
                gatunek.setSelection(spinnerPosition);
                opis.setText(zwierz.getOpis());
                this.modyfi_id = zwierz.getId();
            }
        } catch (Exception ex) {
            this.modyfi_id = 0;
        }
    }

    public void wyslij(View view) {
        EditText kolor = findViewById(R.id.kolor);
        EditText wielkosc = findViewById(R.id.wielkosc);
        EditText opis = findViewById(R.id.opis);
        Spinner gatunek = findViewById(R.id.gatunek);
        Animal zwierze = new Animal(gatunek.getSelectedItem().toString(), kolor.getText().toString(), Float.valueOf(wielkosc.getText().toString()), opis.getText().toString());
        zwierze.setId(this.modyfi_id);
        Intent intencja = new Intent();
        intencja.putExtra("nowy", zwierze);
        setResult(RESULT_OK, intencja);
        finish();
    }
}

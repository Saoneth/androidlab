package pl.saoneth.android_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DodajWpis extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);
    }

    public void wyslij(View view) {
        EditText kontrolka = findViewById(R.id.editText);
        String pole = kontrolka.getText().toString();
        Intent intencja = new Intent();
        intencja.putExtra("wpis", pole);
        setResult(RESULT_OK, intencja);
        finish();
    }
}

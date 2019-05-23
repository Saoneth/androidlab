package pl.saoneth.android_lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MySQLite db;
    private SimpleCursorAdapter adapter;

    // To-do: reszta aplikacji

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MySQLite(this);

        this.adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, db.lista(), new String[]{"_id", "gatunek"}, new int[]{android.R.id.text1, android.R.id.text2}, SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        ListView listview = findViewById(R.id.listView);
        listview.setAdapter(this.adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
                TextView name = view.findViewById(android.R.id.text1);

                Animal zwierz = db.pobierz(Integer.parseInt(name.getText().toString()));

                Intent intencja = new Intent(getApplicationContext(), DodajWpis.class);
                intencja.putExtra("element", zwierz);
                startActivityForResult(intencja, 2);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = view.findViewById(android.R.id.text1);

                db.usun(name.getText().toString());

                adapter.changeCursor(db.lista());
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void nowyWpis(MenuItem mi) {
        Intent intencja = new Intent(this, DodajWpis.class);
        startActivityForResult(intencja, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode != 1 && requestCode != 2) || resultCode != RESULT_OK) {
            return;
        }

        Bundle extras = data.getExtras();
        Animal nowy = (Animal) extras.getSerializable("nowy");

        if (requestCode == 2) {
            this.db.aktualizuj(nowy);
        } else {
            this.db.dodaj(nowy);
        }

        adapter.changeCursor(db.lista());
        adapter.notifyDataSetChanged();
    }


}

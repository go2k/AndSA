package koenig.sabel.com.koenig;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;

public class AlleOrteActivity extends AppCompatActivity {

    private ListView listView;
    private OrtsListe ortsListe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alle_orte);


        listView = findViewById(R.id.lvOrte);

        Serializable serializable = getIntent().getSerializableExtra("ORTE");

        if(serializable instanceof OrtsListe){
            ortsListe = (OrtsListe) serializable;

            ArrayAdapter<Ort> arrayAdapter = new ArrayAdapter<Ort>(this, android.R.layout.simple_list_item_1, ortsListe.getListe());

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                }
            });
        }





    }
}

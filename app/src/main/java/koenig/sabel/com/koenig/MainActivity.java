package koenig.sabel.com.koenig;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private Button btnSpeichern;
    private Button btnAlleOrteAnzeigen;
    private EditText etOrt;
    private TextView tvLux;
    private OrtsListe ortsListe;
    private SensorManager sensorManager;
    private Sensor sensor;
    private float helligkeitInLux;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ortsListe = new OrtsListe();
        helligkeitInLux = 0;
        initComponents();
        initEvents();
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    private void initEvents() {

        btnSpeichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!etOrt.getText().toString().equals("")) {
                    Ort ort = new Ort();
                    ort.setOrt(etOrt.getText().toString());
                    ort.setTimestamp(new Date());
                    ort.setLux(helligkeitInLux);
                    ortsListe.add(ort);
                } else {
                    Toast.makeText(MainActivity.this, "Bitte einen Ort eingeben", Toast.LENGTH_LONG).show();
                }
                //Toast.makeText(MainActivity.this, String.valueOf(ortsListe.size()), Toast.LENGTH_LONG).show();
            }
        });

        btnAlleOrteAnzeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AlleOrteActivity.class);
                intent.putExtra("ORTE", ortsListe);
                startActivity(intent);
            }
        });

    }

    private void initComponents() {
        btnSpeichern = findViewById(R.id.btnSpeichern);
        btnAlleOrteAnzeigen = findViewById(R.id.btnAnzeigen);
        etOrt = findViewById(R.id.etOrt);
        tvLux = findViewById(R.id.tvLux);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == sensor.TYPE_LIGHT) {
            tvLux.setText("" + event.values[0]);
            helligkeitInLux = event.values[0];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

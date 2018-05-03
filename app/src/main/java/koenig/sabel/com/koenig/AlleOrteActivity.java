package koenig.sabel.com.koenig;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.CountDownTimer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AlleOrteActivity extends AppCompatActivity {

    private ListView listView;
    private OrtsListe ortsListe;
    private Camera cam;
    private android.hardware.Camera.Parameters param;
    private CountDownTimer countDownTimer;
    private long timeInMilliSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alle_orte);
        timeInMilliSec = 5000;

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            if (ContextCompat.checkSelfPermission(AlleOrteActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(AlleOrteActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }

        listView = findViewById(R.id.lvOrte);

        Serializable serializable = getIntent().getSerializableExtra("ORTE");

        if (serializable instanceof OrtsListe) {
            ortsListe = (OrtsListe) serializable;

            ArrayAdapter<Ort> arrayAdapter = new ArrayAdapter<Ort>(this, android.R.layout.simple_list_item_1, ortsListe.getListe());

            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

                        if (cam == null) {
                            cam = Camera.open();
                        }
                        param = cam.getParameters();
                        param.setFlashMode(Parameters.FLASH_MODE_TORCH);
                        cam.setParameters(param);
                        cam.startPreview();
                        initTimer(timeInMilliSec);

                    } else {

                        Toast.makeText(getApplicationContext(), "keine Foto-LED", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void initTimer(long timeInMilliSec) {

        countDownTimer = new CountDownTimer(timeInMilliSec + 1000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                param = cam.getParameters();
                param.setFlashMode(Parameters.FLASH_MODE_OFF);
                cam.setParameters(param);
                cam.stopPreview();
            }
        }.start();

    }

}

package br.com.murilo.cameraandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.murilo.cameraandroid.util.Alert;

public class MainActivity extends Activity {
    private static final int API_CAMERA_CAPTURE = 0;

    private Button captureButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initialize();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    private void initialize() {
        this.captureButton = (Button) findViewById(R.id.capture_button);
        this.captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureWithAPI(v);
            }
        });
    }

    private void captureWithAPI(View v) {
        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivityForResult(intent, API_CAMERA_CAPTURE);
    }
}

package br.com.murilo.cameraandroid;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import br.com.murilo.cameraaandroid.camera.CameraPreview;
import br.com.murilo.cameraandroid.util.Alert;

/**
 * Created by murilocosta on 6/21/16.
 */
@SuppressWarnings("deprecation")
public class CameraActivity extends Activity implements Camera.PictureCallback {
    private Camera camera;
    private CameraPreview cameraPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        this.startCamera();
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Alert.showShortToast(this, "Length " + data.length);
        finish();
    }

    public void onClick(View v) {
        this.cameraPreview.stop();
        this.camera.takePicture(null, null, this);
    }

    private void startCamera() {
        this.camera = Camera.open(getBackCamera());
        this.cameraPreview = new CameraPreview(this, this.camera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(this.cameraPreview);
    }

    private int getBackCamera() {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

}

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

    private int backCameraID;
    private int frontCameraID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        this.setCamerasIDs();
        this.startCamera(this.backCameraID);
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        Alert.showShortToast(this, "Length " + data.length);
        finish();
    }

    public void onClick(View v) {
        this.camera.takePicture(null, null, this);
    }

    private void setCamerasIDs() {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);

            switch (info.facing) {
                case Camera.CameraInfo.CAMERA_FACING_FRONT:
                    this.frontCameraID = i;
                    break;
                case Camera.CameraInfo.CAMERA_FACING_BACK:
                    this.backCameraID = i;
                    break;
            }
        }
    }

    private void startCamera(int cameraID) {
        this.camera = Camera.open(cameraID);
        this.cameraPreview = new CameraPreview(this, this.camera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(this.cameraPreview);
    }
}

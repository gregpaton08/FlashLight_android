package com.example.flashlight;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    Camera cam = null;
    Parameters params;
    SurfaceTexture surfText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Hide title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        surfText = new SurfaceTexture(0);
    }

    public void onClickFlash(View view) {
        if (cam == null) {
            cam = Camera.open();
            try {
                cam.setPreviewTexture(surfText);
            } catch (IOException e) {
                e.printStackTrace();
            }
            cam.startPreview();
            params = cam.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            cam.setParameters(params);
        }
        else {
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            cam.setParameters(params);
            cam.release();
            cam = null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
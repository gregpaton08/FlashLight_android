package com.example.flashlight;

import android.graphics.SurfaceTexture;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.SurfaceTexture;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    Camera cam = null;
    Parameters params;

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
    }

    public void onClickFlash(View view) {
        if (cam == null) {
            Log.w("Message", "Flash on");
            cam = Camera.open();
            try {
                SurfaceTexture surfText = new SurfaceTexture(0);
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
            Log.w("Message", "Flash off");
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
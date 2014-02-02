package com.example.flashlight;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.Button;
import android.view.View.OnClickListener;

public class MainActivity extends ActionBarActivity {

    Camera cam = null;
    Parameters params;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        cam = Camera.open();
        params = cam.getParameters();
        params.setFlashMode(Parameters.FLASH_MODE_TORCH);
        cam.setParameters(params);
        cam.startPreview();
        Log.w("Message", "Flash on");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        Log.w("Message", "Options Menu");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        Log.w("Message", "Options Created");
//        if (true) {
//            Log.w("Message", "true");
//            Camera cam = Camera.open();
//            Parameters p = cam.getParameters();
//            if (p.getFlashMode() == Parameters.FLASH_MODE_TORCH) {
//                Log.w("Message", "Flash off");
//                p.setFlashMode(Parameters.FLASH_MODE_OFF);
//                cam.setParameters(p);
//                cam.stopPreview();
//            }
//            else {
//                Log.w("Message", "Flash on");
//                p.setFlashMode(Parameters.FLASH_MODE_TORCH);
//                cam.setParameters(p);
//                cam.startPreview();
//            }
//            //cam.release();
//        }
//        else {
//            Log.w("Message", "Flash not supported");
//        }
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    //@Override
    public void onClickFlash(View view) {
        if (cam == null) {
            Log.w("Message", "Flash on");
            cam = Camera.open();
            params = cam.getParameters();
            params.setFlashMode(Parameters.FLASH_MODE_TORCH);
            cam.setParameters(params);
            //FlashLightControl.setText("Set FLASH_MODE_OFF");
        }
        else {
            Log.w("Message", "Flash off");
            params.setFlashMode(Parameters.FLASH_MODE_OFF);
            cam.setParameters(params);
            cam.release();
            cam = null;
            //FlashLightControl.setText("Set FLASH_MODE_TORCH");
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

package com.jacobjacob.morse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import static com.jacobjacob.morse.Util.CONTEXT;
import static com.jacobjacob.morse.Util.MORSECODE;

public class MainActivity extends AppCompatActivity {

    private static CameraManager mCameraManager;
    private static String mCameraId;
    public static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        CONTEXT = this.getApplicationContext();

        textView = findViewById(R.id.textView);



        final Morse morse = new Morse();
        final StringConverter SC = new StringConverter();

        EditText editText = findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MORSECODE = SC.stringToMorseCode(String.valueOf(charSequence));
                textView.setText(MORSECODE);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        /**/
        startFlash();
        /**/

        ToggleButton toggleButtonFlash = findViewById(R.id.flash);
        toggleButtonFlash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                morse.pulseCode(MORSECODE);
            }
        });



    }


    public void startFlash(){
        boolean isFlashAvailable = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!isFlashAvailable) {
            showNoFlashError();
        }
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }





    /**/
    public void showNoFlashError() {
        AlertDialog alert = new AlertDialog.Builder(CONTEXT)
                .create();
        alert.setTitle("Oh no!");
        alert.setMessage("Flash not available in this device...\nor give Camera permission...");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alert.show();
    }/**/

    public static void switchFlashLight(boolean status) {
        try {
            mCameraManager.setTorchMode(mCameraId, status);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}

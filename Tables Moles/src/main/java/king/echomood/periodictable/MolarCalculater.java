package king.echomood.periodictable;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import king.echomood.periodictable.data.ElementCalculation;

public class MolarCalculater extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_molar_calculater);


    }

    @Override
    protected void onResume() {
        super.onResume();

        final EditText formela = (EditText) findViewById(R.id.Main_furmela_text);
        final TextView results = (TextView) findViewById(R.id.elem_text);

        ImageButton btn = (ImageButton) findViewById(R.id.Cam_Btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                            ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                            ) {

                    } else {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) &&
                                shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                                shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                                shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)
                                ) {

                        }

                        requestPermissions(new String[] {Manifest.permission.CAMERA} , 0);

                    }
                }else {

                }

                */


            }

        });

        Button calcule_Btn = (Button) findViewById(R.id.Calculate_BTN);
        calcule_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElementCalculation elementCalculation = new ElementCalculation();

                elementCalculation.setElement_Formela(formela.getText().toString());
                elementCalculation.accept();
                results.setText(Double.toString(elementCalculation.getFinal_Result()) + " g/m" );

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 0) {
            /*
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivity(new Intent(MolarCalculater.this, CaptureActivity.class));
            } else {
                Log.e("Bad", "Not Granted");
            }
            */
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}


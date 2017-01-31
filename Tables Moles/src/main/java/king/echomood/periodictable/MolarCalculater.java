package king.echomood.periodictable;

import android.Manifest;
import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import king.echomood.periodictable.data.ElementCalculation;

public class MolarCalculater extends Fragment {

    View view;
    ArrayAdapter<String> adapter;
    String form;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_molar_calculater , container , false);



        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        final EditText formela = (EditText) view.findViewById(R.id.Main_furmela_text);
        final TextView results = (TextView) view.findViewById(R.id.elem_text);


        ImageButton btn = (ImageButton) view.findViewById(R.id.share_molar_btn);
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

                takeScreenShot();


            }

        });

        Button calcule_Btn = (Button) view.findViewById(R.id.Calculate_BTN);
        calcule_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ElementCalculation elementCalculation = new ElementCalculation();


                form  =   formela.getText().toString();
                form =  form.replaceAll("\\s+","");

                elementCalculation.setElement_Formela(form);
                elementCalculation.accept();
                results.setText(Double.toString(elementCalculation.getFinal_Result()) + " g/m" );

                InputMethodManager inputManager = (InputMethodManager)  getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager.isAcceptingText()) {
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
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

    public void takeScreenShot(){
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss a" , now);
        Bitmap bm = screenShot(this.getView());
        File file = saveBitmap(bm, now + ".png");
        Log.i("chase", "filepath: "+file.getAbsolutePath());
        Uri uri = Uri.fromFile(new File(file.getAbsolutePath()));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Molar Mass of " + form + " is :");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "share via"));
    }



    private Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private static File saveBitmap(Bitmap bm, String fileName){
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
        File dir = new File(path);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dir, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 90, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}


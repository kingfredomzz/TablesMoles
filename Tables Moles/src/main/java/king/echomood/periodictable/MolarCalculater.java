package king.echomood.periodictable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        ImageButton btn = (ImageButton) findViewById(R.id.camer_Button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MolarCalculater.this,CaptureActivity.class));
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
}


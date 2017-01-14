package king.echomood.periodictable;

import android.app.Dialog;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeContainer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_container);
        BottomBar bar = (BottomBar) findViewById(R.id.bottomBar);
        getSupportActionBar().show();
        bar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    getSupportActionBar().hide();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_cont, new PeriodicTableFrag()).commit();
                }else if (tabId == R.id.tab_calculator) {
                    getSupportActionBar().hide();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_cont, new MolarCalculater()).commit();
                }else if (tabId == R.id.tab_soluble) {
                    getSupportActionBar().show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_cont, new Sulobility()).commit();
                }else if ( tabId == R.id.tab_search ) {
                    getSupportActionBar().hide();
                    getDataInJsonFormat ();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_cont, new SearchFragment()).commit();
                }
            }
        });
    }



    public void getDataInJsonFormat (){
        String[] name ={"yousuf" , "Mohammed" , "Ali" , "Hamood" , "Alex"};
        String[] emails = {"yousuf@dd" , "Mohammed@dd" , "Ali@dd" , "Hamood@dd" , "Alex@dd"};

        JSONArray array = new JSONArray();

        for (int i =0; (i < name.length) && (i < emails.length) ; i++ ) {
            JSONObject object = new JSONObject();
            try {
                object.put("name", name[i]);
                object.put("email" , emails[i]);
                array.put(object);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        Log.d("Json is " , array.toString());
    }

}

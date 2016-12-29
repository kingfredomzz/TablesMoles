package king.echomood.periodictable;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;

public class HomeContainer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_container);
        BottomBar bar = (BottomBar) findViewById(R.id.bottomBar);
        getSupportActionBar().hide();
        bar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_cont, new PeriodicTableFrag()).commit();
                }else if (tabId == R.id.tab_calculator) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_cont, new MolarCalculater()).commit();
                }else if (tabId == R.id.tab_soluble) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_cont, new Sulobility()).commit();
                }else if ( tabId == R.id.tab_search ) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragement_cont, new SearchFragment()).commit();
                }
            }
        });


    }


}

package king.echomood.periodictable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.bytecode.opencsv.CSVReader;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import king.echomood.periodictable.data.ElementCalculation;
import king.echomood.periodictable.data.FormulasElements;

/**
 * Created by yousf on 12/28/16.
 */

public class SearchFragment extends Fragment {

    public int size_form = 13;
    EditText text;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    ListView listView;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.search, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listSuggest);
        list = new ArrayList<>();


       getData();

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1 , list);


        listView.setAdapter(adapter);
        return  view;
    }

    //Comparator for Descending Order
    public static Comparator<String> sort_list = new Comparator<String>() {

        public int compare(String o1, String o2) {

            if (o1.length()!=o2.length()) {
                return o1.length()-o2.length(); //overflow impossible since lengths are non-negative
            }
            return o1.compareTo(o2);
        }
    };


    @Override
    public void onResume() {
        super.onResume();


        text = (EditText) view.findViewById(R.id.searchText);

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }


            @Override
            public void afterTextChanged(Editable s) {
                adapter.getFilter().filter(s);

                try {

                    Collections.sort(list, sort_list);
                    Log.d("The list : " , list.toString());
                    adapter.notifyDataSetChanged();
                }catch (Exception e) {
                    Log.d("Error" , e.toString());
                }

            }
        });


    }

    public void getData (){
        // initial var for data
        String[] next  ;
        List<String[]> main = new ArrayList<String[]>() ;

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getActivity().getBaseContext()).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<FormulasElements> formula = realm.where(FormulasElements.class).findAll();
                List<String> temp_lest = new ArrayList<String>();
                for (int i=0; i < size_form ; i ++) {
                    list.add( formula.get(i).getFormula());
                }


            }
        });
    }


}

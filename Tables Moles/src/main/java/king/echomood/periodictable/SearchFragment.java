package king.echomood.periodictable;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import king.echomood.periodictable.data.ElementCalculation;
import king.echomood.periodictable.data.FormulasElements;
import king.echomood.periodictable.data.FurmolaAdapter;
import king.echomood.periodictable.data.FurmolaProvider;

/**
 * Created by yousf on 12/28/16.
 */

public class SearchFragment extends Fragment {

    public int size_form = 1359;
    EditText text;
    private ArrayAdapter<String> adapter;
    public FurmolaAdapter furmAdp;
    private List<String> list;
    private List<FurmolaProvider> lis_itm;
    ListView listView;
    View view;
    String[] sympol;
    String[] names;
    int[] ids;
    List<String> temps;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search, container, false);
        sympol = new String[size_form];
        names = new String[size_form];
        ids = new int[size_form];
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


        final ListView listView = (ListView) view.findViewById(R.id.listSuggest);
        list = new ArrayList<>();
        lis_itm = new ArrayList<>() ;

        furmAdp = new FurmolaAdapter(getContext(), R.layout.formula_item);
        getData();
        for (int t =0; t < 55;) {
            try {
                FurmolaProvider provider = new FurmolaProvider(ids[t], sympol[t], names[t]);
                furmAdp.add(provider);
                lis_itm.add(provider);
                t++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


       listView.setAdapter(furmAdp);
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

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {

                Dialog d = new Dialog(getContext());
                d.setTitle(null);
                d.setContentView(R.layout.furmula_detail_layout);
                d.show();

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
                for (int i=0; i < formula.size() ; i ++) {
                    if ( formula.get(i).getFormula() != null) {
                        sympol[i] = formula.get(i).getFormula();
                        names[i] = formula.get(i).getName();
                        ids[i] = i;
                        String form = formula.get(i).getFormula() +  " - " + formula.get(i).getName() ;
                        list.add(form);
                    }
                }


            }
        });
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}

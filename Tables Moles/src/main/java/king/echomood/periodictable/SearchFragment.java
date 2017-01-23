package king.echomood.periodictable;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

import com.roughike.bottombar.BottomBar;

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
import io.realm.Sort;
import king.echomood.periodictable.data.ElementCalculation;
import king.echomood.periodictable.data.FormulasElements;
import king.echomood.periodictable.data.FurmolaAdapter;
import king.echomood.periodictable.data.FurmolaProvider;

import static android.content.ContentValues.TAG;

/**
 * Created by yousf on 12/28/16.
 */

public class SearchFragment extends Fragment {

    public int size_form = 8333;
    private ProgressDialog dialog;
    EditText text;
    private ArrayAdapter<String> adapter;
    private List<String> list;
    View view;
    String[] sympol;
    String[] names;
    int[] ids;
    BottomBar bar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.search, container, false);
        sympol = new String[size_form];
        names = new String[size_form];
        ids = new int[size_form];
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Searching .... ");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        dialog.show();

        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bar = (BottomBar) view.findViewById(R.id.bottomBar);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getActivity().getBaseContext()).build();
        Realm.setDefaultConfiguration(realmConfiguration);


        final ListView listView = (ListView) view.findViewById(R.id.listSuggest);
        list = new ArrayList<>();

        getData();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1 , list);
        listView.setAdapter(adapter);
        text = (EditText) view.findViewById(R.id.searchText);
        dialog.dismiss();
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                final String search = s.toString();
                Realm realm = Realm.getDefaultInstance();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmResults<FormulasElements> results = realm.where(FormulasElements.class).findAll();
                        List<String> list1 = new ArrayList<>();
                        for (int i=0;i < results.size(); i++) {
                            String item =results.get(i).getFormula() + " - " + results.get(i).getName();
                            if (item.startsWith(search)) {
                                Log.d(TAG, "execute: " + search);
                                list1.add(item);
                            }else {
                                final String[] words = item.split(" ");
                                for (String word : words) {
                                    if (word.startsWith(search)) {
                                        list1.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        String item = " ";

                        if (list1.size() == 0) {
                            results = realm.where(FormulasElements.class).beginGroup().contains("formula" ,search).or().contains("name" , search).endGroup().findAll();
                            for (int i =0; i < results.size(); i++) {
                                 item =results.get(i).getFormula() + " - " + results.get(i).getName();
                                if (item.contains(search)) {
                                    list1.add(item);
                                }
                            }
                        }

                        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list1);
                        listView.setAdapter(adapter);

                    }
                });
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {

                ClipboardManager cl = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("element" , list.get(position).toString() );
                cl.setPrimaryClip(data);
                Toast.makeText(getContext(), "Done Copying" , Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getData (){


        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<FormulasElements> formula = realm.where(FormulasElements.class).findAll();
                for (int i=0; i < 100 ; i ++) {
                    if ( formula.get(i).getFormula() != null) {
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
        Log.d("ggg" , totalHeight + " ");
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


}

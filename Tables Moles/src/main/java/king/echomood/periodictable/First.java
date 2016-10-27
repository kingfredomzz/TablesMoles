package king.echomood.periodictable;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import au.com.bytecode.opencsv.CSVReader;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import king.echomood.periodictable.data.Element_Class;


/* this activity is get data from (CSV file) and then enter the data to database
 * export the dataBase if we need it ;)
 * Also this activty is like welcome layout
 * */

public class First extends AppCompatActivity {

    // variables for the database column
    private int[] id;
    private String[] sympol;
    private String[] name;
    private Double[] atomic_mass;
    private String[] type;
    private String[] year_discoverd;
    private String[] Standerd_States;
    private String[] bonding_type;
    private String[] Density;
    private String[] atom_radues;
    private String[] ios_radus;
    private String[] Oxidation_states;
    private String[] Melting_point;
    private String[] Boiling_point;
    private String[] electronic_configuration;
    private String[] electronegativity;
    private String[] ionation_energy;
    private String[] Activation_Energy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        // inttial the values
        id = new int[119];
        sympol = new String[119];
        name = new String[119];
        atomic_mass = new Double[119];

        type = new String[119];
        year_discoverd = new String[119];
        Standerd_States = new String[119];
        bonding_type = new String[119];
        Density = new String[119];
        atom_radues = new String[119];
        ios_radus = new String[119];

        Oxidation_states = new String[119];
        Melting_point = new String[119];
        Boiling_point = new String[119];
        electronic_configuration = new String[119];
        electronegativity = new String[119];
        ionation_energy = new String[119];
        Activation_Energy = new String[119];

        // get data from csv and store them in array
        connect();

        // copy data to the database
        to_DataBase();

    }


    @Override
    protected void onResume() {
        super.onResume();

        // thread to go the periodic table activity after 2 s (2000 ms)
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(First.this, TableHome.class));
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);
    }

    // function to get data from the files
    public void connect(){

        // initial var for data
        String[] next  ;
        List<String[]> list = new ArrayList<String[]>() ;

        try {
            // get whole data
            CSVReader reader = new CSVReader(new InputStreamReader(getResources().getAssets().open("per_data.csv")));

            // enter data to string list for each rows
            for (;;) {
                next = reader.readNext();
                if (next != null) {
                    list.add(next);
                }else {
                    break;
                }
            }

            // put data in global arrays
            for (int i =0; i < list.size(); i ++) {
                id[i] = Integer.parseInt( list.get(i)[0]);
                sympol[i] = list.get(i)[1];
                name[i] = list.get(i)[2];
                atomic_mass[i] = Double.parseDouble(list.get(i)[3]);
                electronic_configuration[i] = list.get(i)[5];
                electronegativity[i] = list.get(i)[6];
                atom_radues[i] = list.get(i)[7];
                ios_radus[i] = list.get(i)[8];
                ionation_energy[i] = list.get(i)[10]+  " kJ/mol";
                Activation_Energy[i] = list.get(i)[11] + " kJ/mol";
                Oxidation_states[i] = list.get(i)[12];
                Standerd_States[i] = list.get(i)[13];
                bonding_type[i] = list.get(i)[14];
                Melting_point[i] = list.get(i)[15];
                Boiling_point[i] = list.get(i)[16];
                Density[i] = list.get(i)[17];
                type[i] = list.get(i)[18];
                year_discoverd[i] = list.get(i)[19];
            }
        }catch (Exception e) {
            Log.e("Error" , e.toString());
        }
    }

    // function to copy data from the Global Arrays to Realm Object data/ElementClass.java
    private void to_DataBase() {

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // copy data to the DB
                try {
                    for (int i = 0; i < name.length; i++) {
                        Element_Class elemnt = realm.createObject(Element_Class.class);
                        elemnt.setId(id[i + 1]);

                        elemnt.setName(name[i]);
                        elemnt.setSympol(sympol[i]);
                        elemnt.setAtomic_mass(atomic_mass[i]);
                        elemnt.setActivation_Energy(Activation_Energy[i]);
                        elemnt.setIonation_energy(ionation_energy[i]);
                        elemnt.setAtom_radues(atom_radues[i]);
                        elemnt.setBoiling_point(Boiling_point[i]);
                        elemnt.setMelting_point(Melting_point[i]);
                        elemnt.setBonding_type(bonding_type[i]);
                        elemnt.setIos_radus(ios_radus[i]);
                        elemnt.setOxidation_states(Oxidation_states[i]);
                        elemnt.setElectronegativity(electronegativity[i]);
                        elemnt.setElectronic_configuration(electronic_configuration[i]);
                        elemnt.setYear_discoverd(year_discoverd[i]);
                        elemnt.setType(type[i]);
                        elemnt.setDensity(Density[i]);
                        elemnt.setStanderd_States(Standerd_States[i]);


                    }
                }catch (Exception e) {
                    Log.e("Error : " , e.toString());
                }
            }
        });
    }

    // function to export the database
    public void exportDatabase() {

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        // init realm
        Realm realm = Realm.getDefaultInstance();

        File exportRealmFile = null;
        try {
            // get or create an "export.realm" file
            exportRealmFile = new File(getApplicationContext().getExternalCacheDir(), "export.realm");

            // if "export.realm" already exists, delete
            exportRealmFile.delete();

            // copy current realm to "export.realm"
            realm.writeCopyTo(exportRealmFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        realm.close();

        // init email intent and add export.realm as attachment
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, "YOUR MAIL");
        intent.putExtra(Intent.EXTRA_SUBJECT, "YOUR SUBJECT");
        intent.putExtra(Intent.EXTRA_TEXT, "YOUR TEXT");
        Uri u = Uri.fromFile(exportRealmFile);
        intent.putExtra(Intent.EXTRA_STREAM, u);

        // start email intent
        startActivity(Intent.createChooser(intent, "YOUR CHOOSER TITLE"));
    }
}

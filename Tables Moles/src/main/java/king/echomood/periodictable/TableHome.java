package king.echomood.periodictable;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ShareActionProvider;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import king.echomood.periodictable.data.Element_Class;

/*
* This activity is to show periodic table like the original
* also it has the navigation drawer show where we can navigate
* when we click to any element, this will take us to the detail activity

 */

public class TableHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // declare the variables
    int[] numbers; // for atomic number
    String[] letters, type; // letters flr sypmols
    Double[] mole = new Double[122]; // to get the molar mass
    int z;
    int lanth = 57, actin = 89;  // for two rows in the bottom

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        z = 0;

        /* init the variables
            the way: I make 3 variables global, why?
             I will init them here when i create the activity, then i
             connect to the database and fill the arrays
             after that i can use them to fill the table (Periodic Table)
        */
        numbers = new int[118];
        letters = new String[118];
        type = new String[118];

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null); // this line to show colors for items in Navigation drawer


    }

    @Override
    protected void onResume() {
        super.onResume();
        // connect to the DB and fill the arrays:
        // numers, letters and type
        Connect_To_DB();

        // initial the Grid Layout
        GridLayout gridLayout = (GridLayout) findViewById(R.id.GG);

        gridLayout.setRowCount(10);
        gridLayout.setColumnCount(18);

        int child_in_Grid = gridLayout.getChildCount();
        // Loop to fill the Grid Layout
        int r = 0, atom = 0, i = 0, n1 = 71;
        lanth = 57;
        actin = 89;
        Log.e("Back ", i + " , " + r + " ");
        if (child_in_Grid == 0) {
            for (r = 0; r < 10; r++) {
                for (int c = 0; c < 18; c++) {

                    if (i > 179) {
                        i = 0;
                    } else {

                        // create layout for each elemnts
                        LinearLayout linearLayout = new LinearLayout(getApplicationContext());

                        // layouts will be vertically so we can order widgets and the properties like we wants
                        linearLayout.setOrientation(LinearLayout.VERTICAL);

                        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(200, 250));

                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) linearLayout.getLayoutParams();
                        marginLayoutParams.rightMargin = 20;
                        marginLayoutParams.leftMargin = 20;


                        // textview for the atomic number, and the sympol
                        TextView textView = new TextView(getApplicationContext()), textView1 = new TextView(getApplicationContext());
                        // for mole
                        TextView moleText = new TextView(getApplicationContext());


                        textView.setTextColor(this.getResources().getColor(R.color.sympleColor));
                        textView1.setTextColor(this.getResources().getColor(R.color.sympleColor));
                        moleText.setTextColor(this.getResources().getColor(R.color.sympleColor));
                        textView.setTextSize(8);
                        textView1.setTextSize(16);
                        textView.setGravity(Gravity.LEFT);
                        textView.setMarqueeRepeatLimit(12);
                        moleText.setGravity(Gravity.CENTER);

                        textView1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);


                        linearLayout.addView(textView, 150, 40);

                        linearLayout.addView(textView1, 150, 110);

                        GridLayout.Spec row = GridLayout.spec(r);
                        GridLayout.Spec column = GridLayout.spec(c);


                        if (i >= 1 && i <= 16) {
                            textView.setText("");
                            textView1.setText("");
                        } else if (i >= 20 && i <= 29) {
                            textView.setText("");
                            textView1.setText("");
                        } else if (i >= 38 && i <= 47) {
                            textView.setText("");
                            textView1.setText("");
                        } else if (i == 92) {
                            textView.setText("");
                            textView1.setText("");
                        } else if (i == 110) {
                            textView.setText("");
                            textView1.setText("");
                        } else if (i >= 126 && i <= 145) {
                            textView.setText("");
                            textView1.setText("");
                        } else if (i >= 93 && i <= 110) {
                            try {

                                linearLayout.setBackgroundDrawable(getResources().getDrawable(displayColor(type[atom + 15])));
                                textView.setText((atom + 16) + "");
                                textView1.setText(letters[atom + 15] + "");
                            } catch (Exception e) {
                                Log.e("Errorooor", e.toString());
                            }

                            atom++;
                        } else if (i >= 111 && i <= 125) {
                            try {
                                linearLayout.setBackgroundDrawable(getResources().getDrawable(displayColor(type[atom + 30])));
                                textView.setText((atom + 31) + "");
                                textView1.setText(letters[atom + 30] + "");
                            } catch (Exception e) {
                                Log.e("Errorooor", e.toString());
                            }

                            atom++;
                        } else if (i >= 146 && i <= 160) {
                            try {
                                linearLayout.setBackgroundDrawable(getResources().getDrawable(displayColor(type[lanth - 1])));
                                textView.setText((lanth) + "");
                                textView1.setText(letters[lanth - 1] + "");
                                lanth++;
                            } catch (Exception e) {
                                Log.e("Errorooor", e.toString());
                            }

                        } else if (i >= 160 && i <= 163) {
                            textView.setText("");
                            textView1.setText("");
                        } else if (i >= 164 && i <= 178) {
                            try {
                                linearLayout.setBackgroundDrawable(getResources().getDrawable(displayColor(type[actin - 1])));
                                textView.setText((actin) + "");
                                textView1.setText(letters[actin - 1] + "");


                                actin++;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else if (i == 179) {
                            break;
                        } else {
                            try {
                                linearLayout.setBackgroundDrawable(getResources().getDrawable(displayColor(type[atom])));
                                textView.setText((atom +1) + "");
                                textView1.setText(letters[atom] + "");


                                atom++;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }


                        try {

                            gridLayout.addView(linearLayout, new GridLayout.LayoutParams(row, column));
                        } catch (Exception e) {
                            Log.e("My Bd", e.toString());
                        }
                        i++;
                    }
                }
            }
        }


        // to click a child layout and take you to it's details
        int chiled = gridLayout.getChildCount();
        Log.e("number in grid = ", chiled + "");
        Log.e("i = ", i + " ");
        for (; z < chiled; z++) {

            int ch = 0;
            if (z == 0) ch = 1;
            else if (z >= 17 && z <= 19) ch = z - 15;
            else if (z >= 30 && z <= 37) ch = z - 25;
            else if (z >= 48 && z <= 91) ch = z - 35;
            else if (z >= 93 && z <= 110) ch = z - 21;
            else if (z >= 111 && z <= 125) ch = z - 7;
            else if (z >= 146 && z <= 159) ch = z - 89;
            else if (z >= 164 && z <= 177) ch = z - 75;
            else ch = 243;
            final int chose = ch;

            LinearLayout c = (LinearLayout) gridLayout.getChildAt(z);

            c.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (chose !=243 ) {

                    Log.e("z = ", z + "");
                    Log.e("choose = ", chose + " ");
                    gos(chose);
                    }
                }
            });

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        z = 0;
        Log.e("dd", "In paused");
        Log.e("dd", z + " = z");
    }

    void gos(int chos) {
        z = 0;

        Log.e("when clicking z = ", z + " ");
        Intent intent = new Intent(TableHome.this, DetailActivity.class);
        intent.putExtra("atom", chos);
        startActivity(intent);
    }

    // function to get data from DB and fill the arrays with them
    public void Connect_To_DB() {


        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Realm realm = Realm.getDefaultInstance();


        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Element_Class> results = realm.where(Element_Class.class).findAll();


                    for (int x = 0; x < results.size(); x++) {
                        if (results.get(x) == null) {
                            continue;
                        }
                        type[x] = results.get(x).getType();
                        numbers[x] = results.get(x).getId();
                        Log.e("id = (", numbers[x] + ")");
                        Log.e("types = (", type[x] + ")");
                        letters[x] = results.get(x).getSympol();
                        mole[x] = results.get(x).getAtomic_mass();
                    }

                }
            });
        } catch (Exception e) {

        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    int displayColor(String c) {

        int reso_int = R.drawable.alkali_metals;

        if (c.contains("nonmetal")) {
            reso_int = R.drawable.nonmetal;
        } else if (c.contains("noble gas")) {
            reso_int = R.drawable.gas_borders;
        } else if (c.contains("alkali metal")) {
            reso_int = R.drawable.alkali_metals;
        } else if (c.contains("alkaline earth metal")) {
            reso_int = R.drawable.alkine_earth_borders;
        } else if (c.contains("metalloid")) {
            reso_int = R.drawable.metalloid;
        } else if (c.contains("halogen")) {
            reso_int = R.drawable.halogens;
        } else if (c.contains("metal")) {
            reso_int = R.drawable.metal;
        } else if (c.contains("transition metal")) {
            reso_int = R.drawable.transition_metals;
        } else if (c.contains("lanthanoid")) {
            reso_int = R.drawable.lanthanoid;
        } else if (c.contains("actinoid")) {
            reso_int = R.drawable.actinoid;
        }

        return reso_int;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.table_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);

            drawer.openDrawer(Gravity.RIGHT);

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
 /*
        if (requestCode == 0) {

            if (grantResults[0] == PackageManager.PERMISS/ION_GRANTED) {
                startActivity(new Intent(TableHome.this, CaptureActivity.class));
            } else {
                Log.e("Bad", "Not Granted");
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

        */
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_solubilty) {
            startActivity(new Intent(TableHome.this, Sulobility.class));
        } else if (id == R.id.nav_camera) {
            /*

            if (Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                        ) {
                    startActivity(new Intent(TableHome.this, CaptureActivity.class));
                } else {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) &&
                            shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE) &&
                            shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                            shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE)
                            ) {
                        startActivity(new Intent(TableHome.this, CaptureActivity.class));
                    }

                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 0);

                }
            }
            */
        } else if (id == R.id.molar_mass) {
            startActivity(new Intent(TableHome.this, MolarCalculater.class));
        } else if (id == R.id.nav_share) {

            Uri imageUri;
            Intent intent;

            imageUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + "icon");
            intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "#periodicTable");
            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
            intent.setType("image/*");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}





package king.echomood.periodictable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import king.echomood.periodictable.data.Element_Class;

public class DetailActivity extends AppCompatActivity {

    Realm realm;
    int x = 0;
    private Context mContext;
    int id  = 0;
    String sympol = "";
    String name= "";
    Double atomic_mass= 1.0;

    String type= "";
    String year_discoverd= "";
    String Standerd_States= "";
    String bonding_type= "";
    String Density= "";
    String atom_radues= "";
    String ios_radus= "";

    String Oxidation_states= "";
    String Melting_point= "";
    String Boiling_point= "";

    String electronic_configuration= "";
    String electronegativity= "";
    String ionation_energy= "";
    String Activation_Energy= "";

    TextView text_id ;
    TextView text_sympol ;
    TextView text_name , text_name2;
    TextView text_atomic_mass , getText_atomic_mass2;

    TextView text_type;
    TextView text_year_discoverd;
    TextView text_Standerd_States;
    TextView text_bonding_type;
    TextView text_Density;
    TextView text_atom_radues;
    TextView text_ios_radus;

    TextView text_Oxidation_states;
    TextView text_Melting_point;
    TextView text_Boiling_point;

    TextView text_electronic_configuration;
    TextView text_electronegativity;
    TextView text_ionation_energy;
    TextView text_Activation_Energy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_detail);


        setClickListenerForDefs();


        x = 0;
         int ids = 0;
        int x_sub = 0;
        x_sub = getIntent().getIntExtra("atom" , 0);

        x = x_sub - 1;

        ids = x_sub;
        text_id = (TextView) findViewById(R.id.atom_numers);
        text_id.setText(ids + "");

        text_name = (TextView) findViewById(R.id.name_text) ;
        text_name2  = (TextView) findViewById(R.id.name_text2);

        text_sympol  = (TextView) findViewById(R.id.sympol_text);
        getText_atomic_mass2 = (TextView) findViewById(R.id.mole_text);
        text_atomic_mass  = (TextView) findViewById(R.id.atomic_text2);
        text_Activation_Energy  = (TextView) findViewById(R.id.EA_energy);
        text_ionation_energy  = (TextView) findViewById(R.id.IE);
        text_Boiling_point  = (TextView) findViewById(R.id.boiling_point);
        text_Melting_point  = (TextView) findViewById(R.id.melting_point);
        text_type  = (TextView) findViewById(R.id.type_text);

        text_year_discoverd  = (TextView) findViewById(R.id.year_text);
        text_Standerd_States  = (TextView) findViewById(R.id.standers_state);
        text_bonding_type  = (TextView) findViewById(R.id.bonding_type_text);
        text_Density  = (TextView) findViewById(R.id.density_text);

        text_atom_radues  = (TextView) findViewById(R.id.atom_raduis);
        text_ios_radus  = (TextView) findViewById(R.id.ion_raduis);
        text_Oxidation_states  = (TextView) findViewById(R.id.oxidatiom_text);
        text_electronegativity  = (TextView) findViewById(R.id.electromagnifity);
        text_electronic_configuration  = (TextView) findViewById(R.id.elec_confi);


        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);


        getImage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Element_Class> results = realm.where(Element_Class.class).findAll();

                id = results.get(x).getId();

                name  = results.get(x).getName();
                text_name.setText(name + " ");

                atomic_mass  = results.get(x).getAtomic_mass();
                getText_atomic_mass2.setText(" " + atomic_mass + " (g/mol)");
                text_atomic_mass.setText(" " + atomic_mass + " (g/mol)");

                atom_radues  = results.get(x).getAtom_radues();
                text_atom_radues.setText(atom_radues);

                ios_radus = results.get(x).getIos_radus();
                text_ios_radus.setText(ios_radus );

                sympol  = results.get(x).getSympol();
                text_sympol.setText(sympol);
                text_name2.setText(name + " ( " + sympol + " )" );

                ionation_energy  = results.get(x).getIonation_energy();
                text_ionation_energy.setText(ionation_energy);

                Activation_Energy  = results.get(x).getActivation_Energy();
                text_Activation_Energy.setText(Activation_Energy);

                year_discoverd  = results.get(x).getYear_discoverd();
                text_year_discoverd.setText(year_discoverd);

                Standerd_States  = results.get(x).getStanderd_States();
                text_Standerd_States.setText(Standerd_States);

                Density  = results.get(x).getDensity();
                text_Density.setText(Density + " (g/cm3)");

                electronegativity  = results.get(x).getElectronegativity();
                text_electronegativity.setText(electronegativity);

                electronic_configuration  = results.get(x).getElectronic_configuration();
                text_electronic_configuration.setText(electronic_configuration);

                Boiling_point  = results.get(x).getBoiling_point();
                text_Boiling_point.setText(Boiling_point + " K");

                Melting_point  = results.get(x).getMelting_point();
                text_Melting_point.setText(Melting_point + " K");

                Oxidation_states  = results.get(x).getOxidation_states();
                text_Oxidation_states.setText(Oxidation_states);

                bonding_type  = results.get(x).getBonding_type();
                text_bonding_type.setText(bonding_type);

                type  = results.get(x).getType();
                text_type.setText(type);
            }
        });


        setTitle( name + " (" + String.format( "%.2f", atomic_mass ) + ")");

    }

    public void getImage(){
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout3);

        int imag = R.drawable.hydrogen_thumb;
        switch (x + 1) {
            case 1 : imag = R.drawable.hydrogen_thumb; break;
            case 2 : imag = R.drawable.helium_thumb; break;
            case 3 : imag = R.drawable.lithium_thumb; break;
            case 4 : imag = R.drawable.beryllium_thumb; break;
            case 5 : imag = R.drawable.boron_thumb; break;
            case 6 : imag = R.drawable.carbon_thumb; break;
            case 7 : imag = R.drawable.nitrogen_thumb; break;
            case 8 : imag = R.drawable.oxygen_thumb; break;
            case 9 : imag = R.drawable.fluorite_thumb; break;
            case 10 : imag = R.drawable.neon_thumb; break;
            case 11 : imag = R.drawable.sodium_thumb; break;
            case 12 : imag = R.drawable.magnesium_thumb; break;
            case 13 : imag = R.drawable.aluminium_thumb; break;
            case 14 : imag = R.drawable.silicon_thumb; break;
            case 15 : imag = R.drawable.phosphorus_thumb; break;
            case 16 : imag = R.drawable.sulfur_thumb; break;
            case 17 : imag = R.drawable.chlorine_thumb; break;
            case 18 : imag = R.drawable.argon_thumb; break;
            case 19  : imag = R.drawable.potassium_thumb; break;
            case 20  : imag = R.drawable.calcium_thumb; break;
            case 21  : imag = R.drawable.scandium_thumb; break;
            case 22  : imag = R.drawable.titaniumcrystal_thumb; break;
            case 23 : imag = R.drawable.vanadium_thumb; break;
            case 24 : imag = R.drawable.chromium_thumb; break;
            case 25 : imag = R.drawable.manganese2_thumb; break;
            case 26 : imag = R.drawable.iron_thumb; break;
            case 27 : imag = R.drawable.cobalt_thumb; break;
            case 28 : imag = R.drawable.nickel_thumb; break;
            case 29 : imag = R.drawable.copper_thumb; break;
            case 30 : imag = R.drawable.zinc_thumb; break;
            case 31 : imag = R.drawable.gallium_thumb; break;
            case 32 : imag = R.drawable.germanium_thumb; break;
            case 33 : imag = R.drawable.arsenic_thumb; break;
            case 34 : imag = R.drawable.selenium_thumb; break;
            case 35 : imag = R.drawable.bromine_thumb; break;
            case 36 : imag = R.drawable.krypton_thumb; break;
            case 37 : imag = R.drawable.rubidium_thumb; break;
            case 38 : imag = R.drawable.strontium_thumb; break;
            case 39 : imag = R.drawable.yttrium_thumb; break;
            case 40 : imag = R.drawable.zirconium_thumb; break;
            case 41 : imag = R.drawable.niobium_thumb; break;
            case 42  : imag = R.drawable.molybdenum_thumb; break;
            case 43  : imag = R.drawable.technetium_thumb; break;
            case 44  : imag = R.drawable.ruthenium_thumb; break;
            case 45  : imag = R.drawable.rhodium_thumb; break;
            case 46  : imag = R.drawable.palladium_thumb; break;
            case 47  : imag = R.drawable.silver_thumb; break;
            case 48  : imag = R.drawable.cadmium_thumb; break;
            case 49  : imag = R.drawable.indium_thumb; break;
            case 50  : imag = R.drawable.tin_thumb; break;
            case 51  : imag = R.drawable.antimony_thumb; break;
            case 52  : imag = R.drawable.tellurium_thumb; break;
            case 53  : imag = R.drawable.iodine_thumb; break;
            case 54  : imag = R.drawable.xenon_thumb; break;
            case 55  : imag = R.drawable.caesium_3_thumb; break;
            case 56  : imag = R.drawable.barium_thumb; break;
            case 57  : imag = R.drawable.lanthanum_thumb; break;
            case 58  : imag = R.drawable.cerium_thumb; break;
            case 59  : imag = R.drawable.praseodymium_thumb; break;
            case 60  : imag = R.drawable.neodymium_thumb; break;
            case 61  : imag = R.drawable.promethium_thumb; break;
            case 62  : imag = R.drawable.samarium_thumb; break;
            case 63  : imag = R.drawable.europium_thumb; break;
            case 64  : imag = R.drawable.gadolinium_thumb; break;
            case 65  : imag = R.drawable.terbium_thumb; break;
            case 66  : imag = R.drawable.dysprosium_2_thumb; break;
            case 67  : imag = R.drawable.holmium_thumb; break;
            case 68  : imag = R.drawable.erbium_thumb; break;
            case 69  : imag = R.drawable.thulium_thumb; break;
            case 70  : imag = R.drawable.ytterbium_thumb; break;
            case 71  : imag = R.drawable.lutetium_thumb; break;
            case 72  : imag = R.drawable.hafnium_thumb; break;
            case 73  : imag = R.drawable.tantalum_thumb; break;
            case 74  : imag = R.drawable.tungsten_rod_thumb; break;
            case 75  : imag = R.drawable.rhenium_3_thumb; break;
            case 76  : imag = R.drawable.osmium_thumb; break;
            case 77  : imag = R.drawable.iridium_thumb; break;
            case 78  : imag = R.drawable.platinum_thumb; break;
            case 79  : imag = R.drawable.gold_thumb; break;
            case 80  : imag = R.drawable.hydrargyrum_thumb; break;
            case 81  : imag = R.drawable.thallium_thumb; break;
            case 82  : imag = R.drawable.lead_thumb; break;
            case 83  : imag = R.drawable.bismuth_thumb; break;
            case 84  : imag = R.drawable.polonium_thumb; break;
            case 85  : imag = R.drawable.astatine_thumb ; break;
            case 86 : imag = R.drawable.radon_thumb; break;
            case 87 : imag = R.drawable.francium_thumb; break;
            case 88 : imag = R.drawable.radium_paint_thumb; break;
            case 89 : imag = R.drawable.actinium_225_thumb; break;
            case 90  : imag = R.drawable.thorium_thumb; break;
            case 91 : imag = R.drawable.protactinium_231_thumb; break;
            case 92 : imag = R.drawable.uranium_thumb; break;
            case 93 : imag = R.drawable.neptunium_thumb; break;
            case 94 : imag = R.drawable.plutonium_thumb; break;
            case 95 : imag = R.drawable.americium_thumb; break;
            case 96 : imag = R.drawable.curium_glow_thumb; break;
            case 97 : imag = R.drawable.berkelium_ornl_thumb; break;
            case 98 : imag = R.drawable.californium_disc_thumb; break;
            case 99 : imag = R.drawable.einsteinium_253_thumb; break;
            case 100 : imag = R.drawable.fermium_thumb; break;
            case 101 : imag = R.drawable.mendelevium_thumb; break;
            case 102 : imag = R.drawable.nobelium_thumb; break;
            case 103 : imag = R.drawable.lawrencium_thumb; break;
            case 104 : imag = R.drawable.rutherfordium_thumb; break;
            default:
                imag = R.drawable.transactinoid_thumb;


        }

        layout.setBackgroundResource(imag);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.wiki_sites) {


            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/" + name));
            startActivity(intent);
        }else if (id == R.id.det_share) {
            takeScreenShot();
        }


        return super.onOptionsItemSelected(item);
    }


    private void takeScreenShot()
    {
        final ViewGroup u = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        ScrollView z = (ScrollView) findViewById(R.id.detail_activity);
        int totalHeight = z.getChildAt(0).getHeight();
        int totalWidth = z.getChildAt(0).getWidth();

        Bitmap b = getBitmapFromView(u,totalHeight,totalWidth);
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss a" , now);
        File file = saveBitmap(b, now + ".png");
        Uri uri = Uri.fromFile(new File(file.getAbsolutePath()));
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Details of " + name + " ( " + sympol + " )");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "share via"));

    }

    public Bitmap getBitmapFromView(View view, int totalHeight, int totalWidth) {

        Bitmap returnedBitmap = Bitmap.createBitmap(totalWidth,totalHeight , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);
        view.draw(canvas);
        return returnedBitmap;
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

    public void setClickListenerForDefs(){
        TextView test = (TextView) findViewById(R.id.test_click);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, "اسم العنصر", Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/List_of_chemical_elements" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });

        TextView molar_mass = (TextView) findViewById(R.id.atomic_mass_def);
        molar_mass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.atomic_mass_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Relative_atomic_mass" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });

        TextView atomic_num = (TextView) findViewById(R.id.atome_numbers_def);
        atomic_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.atomic_number_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Atomic_number" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });

        TextView stander = (TextView) findViewById(R.id.stander_def);
        stander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.Standered_State_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Standard_state" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });

        TextView dens = (TextView) findViewById(R.id.dens_def);
        dens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.density_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Density" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });


        TextView melt_p = (TextView) findViewById(R.id.melt_def);
        melt_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.melting_point_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Melting_point" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });


        TextView boild_p = (TextView) findViewById(R.id.boil_def);
        boild_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.boiling_point_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/boiling_point" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });

        TextView atomi_rad = (TextView) findViewById(R.id.atmoic_rad_def);
        atomi_rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.atomic_raduis_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Atomic_radius" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });


        TextView ion_rad = (TextView) findViewById(R.id.ion_raduis);
        ion_rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.ion_raduis_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/ionic_radius" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });


        TextView oxid = (TextView) findViewById(R.id.oxidation_def);
        oxid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.oxidation_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/oxidation_state" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });

        TextView bonding = (TextView) findViewById(R.id.bonding_type_def);
        bonding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.bonding_type_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Chemical_bond" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });


        TextView elect_conf = (TextView) findViewById(R.id.elect_conf_def);
        elect_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.electronic_conf_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Electron_configuration" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });



        TextView pauling = (TextView) findViewById(R.id.electr_pauling_def);
        pauling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.pauling_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Electronegativity#Pauling_electronegativity" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });




        TextView ions_eng = (TextView) findViewById(R.id.ion_def);
        ions_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.ionizations_eng_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Ionization_energy" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });


        TextView acti_eng = (TextView) findViewById(R.id.activation_def);
        acti_eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.activation_eng_def), Snackbar.LENGTH_LONG);

                snackbar.setAction(getResources().getString(R.string.wiki), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/activation_energy" ));
                        startActivity(intent);
                    }
                });
                snackbar.show();
            }
        });


    }
}

package king.echomood.periodictable.data;

import android.util.Log;

/**
 * Created by Yosuf on 07/01/2017.
 */

public class FurmolaProvider {

    public FurmolaProvider(int id, String sym, String name) {
        this.setId(id);
        this.setName(name);
        this.setSympol(sym);
        this.setMolar(this.getSympol());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSympol() {
        return sympol;
    }

    public void setSympol(String sympol) {
        this.sympol = sympol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMolar() {
        return molar;
    }

    public void setMolar(String molar) {

        try {
            ElementCalculation elementCalculation = new ElementCalculation();

            int count = 0;
            String form = molar;
            form = form.replaceAll("\\s+", "");
            form = form.replace("+", "");
            form = form.replace("-", "");
            form = form.replace(".", "");
            Log.d("cc",form);
            elementCalculation.setElement_Formela(form);
            elementCalculation.accept();
            this.molar = Math.round( elementCalculation.getFinal_Result());


        }catch (Exception e) {
            Log.e("Error" , e.toString());
            this.molar = 0.0;
        }
    }

    private int id;
    private String sympol;
    private String name;
    private double molar;



}

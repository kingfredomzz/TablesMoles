package king.echomood.periodictable.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;

import king.echomood.periodictable.R;

/**
 * Created by Yosuf on 07/01/2017.
 */

public class FurmolaAdapter extends ArrayAdapter implements Filterable {

    List list = new ArrayList<>();
    List disp_ls = new ArrayList<>();
    public FurmolaAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row = convertView;
        DataHandler handler;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.formula_item, parent, false);
            handler = new DataHandler();
            handler.molar = (TextView) row.findViewById(R.id.molar_mass_item);
            handler.name = (TextView) row.findViewById(R.id.name_item);
            handler.sympol = (TextView) row.findViewById(R.id.symp_item);
            row.setTag(handler);
        }else {
            handler = new DataHandler();
            handler.molar = (TextView) row.findViewById(R.id.molar_mass_item);
            handler.name = (TextView) row.findViewById(R.id.name_item);
            handler.sympol = (TextView) row.findViewById(R.id.symp_item);
            row.setTag(handler);
        }

        FurmolaProvider provider ;
        provider = (FurmolaProvider) this.getItem(position);
        handler.molar.setText(provider.getMolar() + " ");
        handler.molar.setBackground(getContext().getResources().getDrawable (R.drawable.molar_back));

        handler.name.setText(provider.getName());
        handler.sympol.setText(provider.getSympol());
        return row;
    }

    private int getColor (double mole){

        int res = 0;
        if (mole > 250 && mole < 500) {

        }
        return android.R.color.holo_green_dark;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
        disp_ls.add(object);
    }

    static class DataHandler {
        TextView molar;
        TextView sympol;
        TextView name;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List filtList = new ArrayList<>();
                if (list == null) {
                    list = new ArrayList(disp_ls);
                }


                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = disp_ls.size();
                    results.values = disp_ls;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for(int i=0; i < disp_ls.size() ; i++) {
                        FurmolaProvider data = (FurmolaProvider) disp_ls.get(i);
                        String da = data.getSympol() ;
                        String d2 = data.getName();
                        if (da.toLowerCase().startsWith(constraint.toString())) {
                            filtList.add(disp_ls.get(i));
                        }
                    }

                    results.count = filtList.size();
                    results.values = filtList;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (List) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}

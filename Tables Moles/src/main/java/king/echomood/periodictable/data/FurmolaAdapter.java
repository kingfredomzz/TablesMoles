package king.echomood.periodictable.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import king.echomood.periodictable.R;

/**
 * Created by Yosuf on 07/01/2017.
 */

public class FurmolaAdapter extends ArrayAdapter {

    List list = new ArrayList();
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
        handler.name.setText(provider.getName());
        handler.sympol.setText(provider.getSympol());
        return row;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
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
}

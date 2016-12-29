package king.echomood.periodictable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by yousf on 12/29/16.
 */

public class Search extends ArrayAdapter<String> {
    private SearchView.SearchAutoComplete mSearchAutoComplete;;

    private List<String> list;
    private Context cc ;
    public Search(Context context, int resource , List<String> rs) {
        super(context, resource, rs);
        this.list = rs;
        cc = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder = null;
        if(view == null) {
            view = view.inflate(cc, R.layout.element_item , null);
            holder.StringNameTextView = (TextView) view.findViewById(R.id.elemetns_item);
            view.setTag(holder);
        }else  {
            holder = (Holder) view.getTag();

        }

        String name = list.get(position);
        holder.StringNameTextView.setText(name);

        return view;
    }



    static class Holder
    {
        private TextView StringNameTextView;
    }

}

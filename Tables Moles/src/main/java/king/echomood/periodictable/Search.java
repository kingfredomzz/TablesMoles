package king.echomood.periodictable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yousf on 12/29/16.
 */

public class Search extends BaseAdapter implements Filterable{
    private SearchView.SearchAutoComplete mSearchAutoComplete;;

    private ArrayList<String> list;
    private ArrayList<String> display_list;
    private Context cc ;
    LayoutInflater inflater;
    public Search(Context context , ArrayList<String> rs) {
        this.list = rs;
        this.display_list = rs;
        cc = context;
        inflater.from(context);
    }

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

    @Override
    public int getCount() {
        return display_list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<String> FilteredArrList = new ArrayList<String>();
                if (list == null) {
                    list = new ArrayList<>(display_list);
                }


                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/

                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = list.size();
                    results.values = list;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < list.size(); i++) {
                        String data = list.get(i);
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new String(list.get(i) ));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                display_list = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };

        return filter;

    }

    static class Holder
    {
        private TextView StringNameTextView;
    }

}

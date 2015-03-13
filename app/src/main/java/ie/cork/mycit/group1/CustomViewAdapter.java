package ie.cork.mycit.group1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomViewAdapter  extends BaseAdapter {

    LayoutInflater inflater;
    List<String> items;

    public CustomViewAdapter(Activity context, List<String> names) {
        super();

        this.items = names;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String appName = items.get(position);

        View vi= convertView;

        if(convertView == null)
            vi = inflater.inflate(R.layout.activity_custom_view_adapter, null);

        TextView txtName = (TextView) vi.findViewById(R.id.listTextViewMed);

        txtName.setText(appName);

        return vi;
    }

}

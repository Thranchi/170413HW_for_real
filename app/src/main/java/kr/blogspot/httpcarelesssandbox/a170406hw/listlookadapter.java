package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by 윤현하 on 2017-04-13.
 */

public class listlookadapter extends BaseAdapter implements Checkable, Filterable{

    ArrayList<Integer> deadlist;
    ArrayList<databox> item=new ArrayList<databox>();
    ArrayList<databox> filteritem=item;
    ArrayList<String> listlist=new ArrayList<String>();
    Context context;
    int index=0;
    boolean checkboxstatus=false;
    String herename;
    MainActivity main=new MainActivity();
    Filter listFilter;

    public listlookadapter(Context context, ArrayList<databox> data, boolean del) {
        this.context = context;
        this.item = data;
        this.checkboxstatus = del;
        deadlist = new ArrayList<Integer>();
    }
    public ArrayList<Integer> getbomb()
    {
        return deadlist;
    }
    public void setdelbutton(boolean del)
    {
        this.checkboxstatus = del;
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final int pos=position;
        final Context context=parent.getContext();

        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listlook, parent, false);
            //convertView = LayoutInflater.from(context).inflate(R.layout.listlook, null);
        }
        TextView listname=(TextView)convertView.findViewById(R.id.listname);
        TextView listtel=(TextView)convertView.findViewById(R.id.listtel);
        ImageView listpicture=(ImageView)convertView.findViewById(R.id.listpicture);
        CheckBox cb=(CheckBox)convertView.findViewById(R.id.cb);

        if(checkboxstatus)
            cb.setVisibility(View.VISIBLE);
        else
            cb.setVisibility(View.GONE);


        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox current = (CheckBox) buttonView;
                if(isChecked)
                {
                    deadlist.add(Integer.parseInt(current.getTag().toString()));
                }
                else
                {
                    deadlist.remove(Integer.parseInt(current.getTag().toString()));
                }
            }
        });

        cb.setTag(position);
        databox databox = item.get(position);

        listname.setText(databox.getName());
        herename=databox.getName();
        listtel.setText(databox.getPhonenumber());
        if (databox.getKind().equals("chicken")) {
            listpicture.setImageResource(R.drawable.chicken);
        } else if (databox.getKind().equals("pizza")) {
            listpicture.setImageResource(R.drawable.pizza);
        } else if (databox.getKind().equals("hamburger")) {
            listpicture.setImageResource(R.drawable.hamburger);
        }

        return convertView;
    }

    Comparator<databox> nameAsc=new Comparator<databox>() {
        @Override
        public int compare(databox o1, databox o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }
    };

    Comparator<databox> kindAsc=new Comparator<databox>() {
        @Override
        public int compare(databox o1, databox o2) {
            return o1.getKind().compareToIgnoreCase(o2.getKind());
        }
    };

    public void setnameSort(){
        Collections.sort(item,nameAsc);
        this.notifyDataSetChanged();
    }

    public void setkindSort(){
        Collections.sort(item,kindAsc);
        this.notifyDataSetChanged();
    }

    public void addItem(databox databox) {
        item.add(databox);
    }

    public String getmenuname() {
        return herename;
    }


    @Override
    public void setChecked(boolean checked) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }

    @Override
    public Filter getFilter() {
        if(listFilter==null)
        {
            listFilter=new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;

            if (constraint == null || constraint.length() == 0)
            {
                results.values = item ;
                results.count = item.size() ;
            }
            else
            {
                    ArrayList<databox> itemList = new ArrayList<databox>() ;
                    for (databox items : item)
                    {
                        if (items.getName().toUpperCase().contains(constraint.toString().toUpperCase()))
                        {
                            itemList.add(items) ;
                        }
                    }
                    results.values = itemList ;
                    results.count = itemList.size() ;
            }

                return results;

        }




        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteritem=(ArrayList<databox>) results.values;

            if(results.count > 0){
                notifyDataSetChanged();
            }
            else {
                notifyDataSetInvalidated();
            }
        }
    }
}


package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by 윤현하 on 2017-04-13.
 */

public class listlookadapter extends BaseAdapter {

    ArrayList<databox> data=new ArrayList<databox>();
    Context context;

    public listlookadapter(Context context, ArrayList<databox> data){
        this.context=context;
        this.data=data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listlook, null);
        }
        TextView listname=(TextView)convertView.findViewById(R.id.listname);
        TextView listtel=(TextView)convertView.findViewById(R.id.listtel);
        ImageView listpicture=(ImageView)convertView.findViewById(R.id.listpicture);

        databox databox = data.get(position);

        listname.setText(databox.getName());
        listtel.setText(databox.getPhonenumber());

        if (databox.getKind().equals("chicken")) {
            listpicture.setImageResource(R.drawable.chicken);
        } else if (databox.getKind().equals("pizza")) {
            listpicture.setImageResource(R.drawable.pizza);
        } else if (databox.getKind().equals("hamburger")) {
            listpicture.setImageResource(R.drawable.hamburger);
        }

        return null;
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
        Collections.sort(data,nameAsc);
        this.notifyDataSetChanged();
    }

    public void setkindSort(){
        Collections.sort(data,kindAsc);
        this.notifyDataSetChanged();
    }

}

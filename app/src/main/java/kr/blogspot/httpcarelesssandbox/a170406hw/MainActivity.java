package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {

    final int MAIN_TO_ADD=12, MAIN_TO_MENU=13, MENU_TO_MAIN=31, ADD_TO_MAIN=21;
    ListView listView;
    ArrayList<String> namebook=new ArrayList<String>();
    int nameindex=0;
    ArrayList<databox> carrier=new ArrayList<databox>();
    //ArrayAdapter<String> adapter;
    listlookadapter adapter;
    Button choice;
    Main2Activity main2=new Main2Activity();
    databox goaway;
    EditText search_bar;
    boolean deleteorder=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("될때가 지났다");
        thisisit();
        search();
        setListView();
    }

    private void thisisit(){
        listView=(ListView)findViewById(R.id.listview);
        //adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        search_bar = (EditText) findViewById(R.id.search_bar);
        adapter=new listlookadapter();
        listView.setAdapter(adapter);
        choice=(Button)findViewById(R.id.b4delete);
    }

    public void onClick(View v){
        if(v.getId()==R.id.b1add) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivityForResult(intent, MAIN_TO_ADD);
        }

        else if(v.getId()==R.id.b2namesort)
        {
            adapter.setnameSort();
            adapter.notifyDataSetChanged();
        }

        else if(v.getId()==R.id.b3kindsort)
        {
            adapter.setkindSort();
            adapter.notifyDataSetChanged();
        }

        else if(v.getId()==R.id.b4delete)
        {
            if(choice.getText().toString().equals("삭제"))
            {
                /*listView.setAdapter(adapter);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("삭제확인");
                dlg.setIcon(R.drawable.potato);
                dlg.setMessage("선택한 맛집정보가 삭제됩니다.");
                dlg.setNegativeButton("취소", null);

                dlg.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                dlg.show();*/
                deleteorder=false;
                adapter.orderreicever(deleteorder);
                adapter.notifyDataSetChanged();
                choice.setText("선택");
            }
            else if(choice.getText().toString().equals("선택"))
            {
                deleteorder=true;
                adapter.orderreicever(deleteorder);
                adapter.notifyDataSetChanged();
                choice.setText("삭제");
                listView.setAdapter(adapter);
            }
        }
    }

//
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==MAIN_TO_ADD){
            if(resultCode==ADD_TO_MAIN){
                goaway=data.getParcelableExtra("result");
                adapter.addItem(goaway);
                namebook.add(nameindex, adapter.getmenuname());
                nameindex++;
                adapter.notifyDataSetChanged();
            }
        }
    }
    public void setListView(){
/*
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {


                return true;
            }
        });
*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                databox item = (databox) parent.getItemAtPosition(position) ;
                Main3Activity menubook=new Main3Activity();
                menubook.setName(item.getName());
                menubook.setKind(item.getKind());
                menubook.setPhonenumber(item.getPhonenumber());
                menubook.setWebsite(item.getWebsite());
                menubook.setMenu1(item.getMenu2());
                menubook.setMenu2(item.getMenu2());
                menubook.setMenu3(item.getMenu3());
                menubook.setDate(item.getDate());
                detailinfo(item.getName());
            }
        });    }

    private void detailinfo(String name){
        Intent intent =new Intent(this, Main3Activity.class);
        intent.putExtra("clue", name);
        startActivityForResult(intent, MAIN_TO_MENU);
    }

    public boolean orderstart() {
        deleteorder=true;
        return deleteorder;
    }

    public boolean orderover() {
        deleteorder=false;
        return deleteorder;
    }

    public void search(){
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String search = s.toString();
                if (search.length() > 0)
                    listView.setFilterText(search);
                    adapter.getFilter().filter();
                else
                    listView.clearTextFilter();

            }
        });

    }
}


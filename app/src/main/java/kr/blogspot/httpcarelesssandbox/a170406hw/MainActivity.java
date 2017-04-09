package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button bt1;
    final int MAIN_TO_ADD=12, MAIN_TO_MENU=13, MENU_TO_MAIN=31, ADD_TO_MAIN=21;
    ListView listView;
    ArrayList<String> data=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisisit();
        setListView();
    }

    private void thisisit(){
        listView=(ListView)findViewById(R.id.listview);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);

        tv=(TextView)findViewById(R.id.tv);
        bt1=(Button)findViewById(R.id.b1add);
    }

    public void onClick(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivityForResult(intent, MAIN_TO_ADD);
        //startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==MAIN_TO_ADD){
            if(resultCode==ADD_TO_MAIN){
                String name=data.getStringExtra("result");
                getstorename(name);
                changenumberofstore();
            }
        }
       /* else if(requestCode==PICK_CONTACT_REQUEST){
            if(resultCode==_RESULT_CODE){
                startActivity(data);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);*/
    }
    public void getstorename(String storename){
        data.add(storename);
        adapter.notifyDataSetChanged();
    }

    public void changenumberofstore(){
        int number=data.size();
        tv.setText("맛집  리스트("+number+"개)");
    }

    public void setListView(){

        //어뎁터 만들기


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                data.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name=data.get(position);
                detailinfo(name);
            }
        });
    }

    private void detailinfo(String name){
        Intent intent =new Intent(this, Main3Activity.class);
        intent.putExtra("clue", name);
        startActivityForResult(intent, MAIN_TO_MENU);
    }
}

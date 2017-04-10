package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import android.widget.Toast;

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
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("삭제확인");
                dlg.setIcon(R.drawable.potato);
                dlg.setMessage("선택한 맛집정보가 삭제됩니다.");
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Main2Activity main2=new Main2Activity();
                        main2.removesomething(data.get(position));
                        data.remove(position);
                        int number=data.size();
                        tv.setText("맛집  리스트("+number+"개)");
                        adapter.notifyDataSetChanged();
                        //Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
                        Snackbar.make(view,"삭제되었습니다",Snackbar.LENGTH_SHORT).show();
                    }
                });
                dlg.show();
                return true;
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

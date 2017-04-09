package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    final int MAIN_TO_ADD=12, MAIN_TO_MENU=13, MENU_TO_MAIN=31, ADD_TO_MAIN=21;
    Button btnback;
    TextView txtname,etmenu1,etmenu2,etmenu3,tvTel,tvURL,tvRegdate;
    ImageView imgno,imageView2,imageView3;
    int key=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        thisisit();
        reaction();
    }

    private void thisisit(){
        btnback=(Button)findViewById(R.id.btnback);

        txtname=(TextView) findViewById(R.id.txtname);
        etmenu1=(TextView)findViewById(R.id.etmenu1);
        etmenu2=(TextView)findViewById(R.id.etmenu2);
        etmenu3=(TextView)findViewById(R.id.etmenu3);
        tvTel=(TextView)findViewById(R.id.tvTel);
        tvURL=(TextView)findViewById(R.id.tvURL);
        tvRegdate=(TextView)findViewById(R.id.tvRegdate);

        imgno=(ImageView)findViewById(R.id.imgno);
        imageView2=(ImageView)findViewById(R.id.imageView2);
        imageView3=(ImageView)findViewById(R.id.imageView3);
    }

    private void reaction() {
        Main2Activity main2=new Main2Activity();

        Intent intent=getIntent();
        String name = intent.getStringExtra("clue");

        key=namesearching(name);

        txtname.setText(main2.carrier.get(key).name);
        etmenu1.setText(main2.carrier.get(key).menu1);
        etmenu2.setText(main2.carrier.get(key).menu2);
        etmenu3.setText(main2.carrier.get(key).menu3);
        tvTel.setText(main2.carrier.get(key).phonenumber);
        tvURL.setText(main2.carrier.get(key).website);
        tvRegdate.setText(main2.carrier.get(key).date);
    }

    private int namesearching(String name) {
        Main2Activity main2=new Main2Activity();
        int clue=main2.lookingforsomethin(name);
        return clue;
    }

    public void onClick(View v){
        Intent intent = new Intent();
        setResult(MENU_TO_MAIN,intent);
        finish();
    }
}

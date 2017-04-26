package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    final int MAIN_TO_ADD = 12, MAIN_TO_MENU = 13, MENU_TO_MAIN = 31, ADD_TO_MAIN = 21;
    Button btnback;
    TextView txtname, etmenu1, etmenu2, etmenu3, tvTel, tvURL, tvRegdate;
    ImageView imgno, imageView2, imageView3;
    int key = 0;
    ArrayList<databox> menubook=new ArrayList<databox>();


    String name="";
    String phonenumber="";
    String kind="";
    String menu1="";
    String menu2="";
    String menu3="";
    String website="";
    String date="";

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getKind() {
        return kind;
    }

    public String getMenu1() {
        return menu1;
    }

    public String getMenu2() {
        return menu2;
    }

    public String getMenu3() {
        return menu3;
    }

    public String getWebsite() {
        return website;
    }

    public String getDate() {
        return date;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setMenu1(String menu1) {
        this.menu1 = menu1;
    }

    public void setMenu2(String menu2) {
        this.menu2 = menu2;
    }

    public void setMenu3(String menu3) {
        this.menu3 = menu3;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Main2Activity main2 = new Main2Activity();
        Intent intent = getIntent();
        thisisit();
        reaction();
    }

    private void thisisit() {
        btnback = (Button) findViewById(R.id.btnback);

        txtname = (TextView) findViewById(R.id.txtname);
        etmenu1 = (TextView) findViewById(R.id.etmenu1);
        etmenu2 = (TextView) findViewById(R.id.etmenu2);
        etmenu3 = (TextView) findViewById(R.id.etmenu3);
        tvTel = (TextView) findViewById(R.id.tvTel);
        tvURL = (TextView) findViewById(R.id.tvURL);
        tvRegdate = (TextView) findViewById(R.id.tvRegdate);

        imgno = (ImageView) findViewById(R.id.imgno);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
    }

    private void reaction() {
        Main2Activity main2 = new Main2Activity();

        Intent intent = getIntent();
        String name = intent.getStringExtra("clue");

        key = namesearching(name);

        txtname.setText(main2.carrier.get(key).name);
        if (main2.carrier.get(key).kind.equals("chicken")) {
            imgno.setImageResource(R.drawable.chicken);
        } else if (main2.carrier.get(key).kind.equals("pizza")) {
            imgno.setImageResource(R.drawable.pizza);
        } else if (main2.carrier.get(key).kind.equals("hamburger")) {
            imgno.setImageResource(R.drawable.hamburger);
        }
        etmenu1.setText(main2.carrier.get(key).menu1);
        etmenu2.setText(main2.carrier.get(key).menu2);
        etmenu3.setText(main2.carrier.get(key).menu3);
        tvTel.setText(main2.carrier.get(key).phonenumber);
        tvURL.setText(main2.carrier.get(key).website);
        tvRegdate.setText(main2.carrier.get(key).date);
    }

    private int namesearching(String name) {
        Main2Activity main2 = new Main2Activity();
        int clue = main2.lookingforsomethin(name);
        return clue;
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnback) {
            Intent intent = new Intent();
            setResult(MENU_TO_MAIN, intent);
            finish();
        } else if (v.getId() == R.id.imageView2) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+tvTel.getText().toString()));
            startActivity(intent);
        }
        else if(v.getId()==R.id.imageView3){
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+tvURL.getText().toString()));
            startActivity(intent);
        }
    }


}

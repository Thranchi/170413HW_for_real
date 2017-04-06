package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {

    final int MAIN_TO_ADD=12, MAIN_TO_MENU=13, MENU_TO_MAIN=31, ADD_TO_MAIN=21;
    databox databox=new databox();
    ArrayList<databox> carrier=new ArrayList<databox>();
    ArrayAdapter<databox> adapter;

    Button btnCancel, btnAdd;
    EditText etname, ettel,etmenu1,etmenu2,etmenu3,etaddr;
    RadioButton radio1,radio2,radio3;
    int categorynumber=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        thisisit();
    }

    private void thisisit() {

        btnCancel=(Button)findViewById(R.id.btnCancel);
        btnAdd=(Button)findViewById(R.id.btnAdd);

        etname=(EditText)findViewById(R.id.etname);
        ettel=(EditText)findViewById(R.id.ettel);
        etmenu1=(EditText)findViewById(R.id.etmenu1);
        etmenu2=(EditText)findViewById(R.id.etmenu2);
        etmenu3=(EditText)findViewById(R.id.etmenu3);
        etaddr=(EditText)findViewById(R.id.etaddr);

        radio1=(RadioButton)findViewById(R.id.radio1);
        radio2=(RadioButton)findViewById(R.id.radio2);
        radio3=(RadioButton)findViewById(R.id.radio3);
    }

    public void onClick(View v){

        Intent intent = new Intent();

        if(v.getId()==R.id.btnAdd)
        {
            String name
                    =etname.getText().toString();
            String phonenumber
                    =ettel.getText().toString();
            String menu1
                    =etmenu1.getText().toString();
            String menu2
                    =etmenu2.getText().toString();
            String menu3
                    =etmenu3.getText().toString();
            String website
                    =etaddr.getText().toString();
            String date
                    =datemaker().toString();
            categorynumber++;
            String stringcategorynumber
                    =categorynumber+"";

            errorblock(name);
            errorblock(phonenumber);
            errorblock(menu1);
            errorblock(menu2);
            errorblock(menu3);
            errorblock(website);
            errorblock(date);
            errorblock(stringcategorynumber);

            databox.inputdata(name,phonenumber,menu1,menu2,menu3,website,date,stringcategorynumber);
            carrier.add(categorynumber, databox);

            intent.putExtra("result",carrier);
        }
        else if(v.getId()==R.id.btnCancel)
        {
            finish();
        }

        setResult(ADD_TO_MAIN,intent);
    }

    private void errorblock(String thing){
        if(thing.getBytes().length==0)
        {
            thing="입력된 값 없음";
        }
    }

    public String datemaker(){

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        return sdfNow.format(date);
    }
}

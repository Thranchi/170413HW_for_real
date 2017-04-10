package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class Main2Activity extends AppCompatActivity {

    final int MAIN_TO_ADD=12, MAIN_TO_MENU=13, MENU_TO_MAIN=31, ADD_TO_MAIN=21;

    ArrayAdapter<databox> adapter;
    databox databox=new databox();
    static ArrayList<databox> carrier=new ArrayList<databox>();
    Button btnCancel, btnAdd;
    EditText etname, ettel,etmenu1,etmenu2,etmenu3,etaddr;
    RadioButton radio1,radio2,radio3;
    int categorynumber=0,index=0;

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

        databox.inputdata("오! 이런 맙소사..","뭔가 잘못되었습니다","죄송합니다","저도 이럴줄은 몰랐는데","그럴줄알고 제가 이렇게","미리 짜놓은거죠","앱이 안멈춘다는게","다행아닙니까","흐하하사사하하하사");
        carrier.add(0, databox);

        if(v.getId()==R.id.btnAdd)
        {
            String name
                    =etname.getText().toString();
            String phonenumber
                    =ettel.getText().toString();
            String kind="";
            if(radio1.isChecked())
            {
                kind="chicken";
            }
            else if(radio2.isChecked())
            {
                kind="pizza";
            }
            else if(radio3.isChecked())
            {
                kind="hamburger";
            }
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

            namemaker(name,0,0);//이름 중복불가처리

            databox.inputdata(name,phonenumber,kind,menu1,menu2,menu3,website,date,stringcategorynumber);
            index++;

            carrier.add(index, databox);

            intent.putExtra("result", name);
            setResult(ADD_TO_MAIN,intent);

            finish();
        }
        else if(v.getId()==R.id.btnCancel)
        {

            finish();
        }

    }

    private String namemaker(String name, int i, int number){
        for(int j=0;j<=carrier.size();j++)
        {
            if(carrier.get(j).name.equals(name))
            {
                for(i=0;i<=carrier.size();i++){
                    if (carrier.get(i).name.equals(name)) {
                        number++;
                        namemaker(name + "(number)",i=0,number);
                    }
                    else if(carrier.get(i).name.equals(name+ "(number)")) {
                        number++;
                        namemaker(name + "(number)",i=0,number);
                    }
                    else
                        return name + "(number)";
                }
            }
            else
                return name;
        }
        return name;
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

    public int lookingforsomethin(String clue){
        for(int i=0;i<=carrier.size();i++)
        {
            if (carrier.get(i).name.equals(clue)) {
                return i;
            }
        }
        return 0;
    }

    public void removesomething(String clue){
        int bomb = lookingforsomethin(clue);
        carrier.remove(bomb);
    }
}

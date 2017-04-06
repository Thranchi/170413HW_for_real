package kr.blogspot.httpcarelesssandbox.a170406hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Button bt1;
    final int MAIN_TO_ADD=12, MAIN_TO_MENU=13, MENU_TO_MAIN=31, ADD_TO_MAIN=21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisisit();
    }

    private void thisisit(){
        bt1=(Button)findViewById(R.id.b1add);
    }

    public void onClick(View v){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==_REQUEST_CODE){
            if(resultCode==_RESULT_CODE){
                String msg=data.getStringExtra("remakemsg");
                tv.setText(msg);
            }
        }
        else if(requestCode==PICK_CONTACT_REQUEST){
            if(resultCode==_RESULT_CODE){
                startActivity(data);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

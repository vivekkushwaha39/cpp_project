package com.example.acer.stt;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button);
        tv1 = (TextView) findViewById(R.id.textView);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i1.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.US);
                startActivityForResult(i1 , 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                ArrayList<String> sList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_LANGUAGE);
                tv1.setText("");
                for(String s1 : sList){
                    tv1.append(s1 + "\n");
                }
                if(sList.contains("open")){
                    Toast.makeText(MainActivity.this, "Open Called !!", Toast.LENGTH_SHORT).show();
                }
                else if(sList.contains("close")){
                    MainActivity.this.finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Kuch Bhi!", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(MainActivity.this, "Dhokaa!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

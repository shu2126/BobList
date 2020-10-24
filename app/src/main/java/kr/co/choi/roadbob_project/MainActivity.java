package kr.co.choi.roadbob_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
    return true;
    }

    public void myListener1(View target){
        Intent intent = new Intent(getApplicationContext(),AddActivity.class);
        startActivity(intent);
    }
    public void myListener2(View target) {
        Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
        startActivity(intent);
    }
    public void myListener3(View target){
        Intent intent = new Intent(getApplicationContext(),ListActivity.class);
        startActivity(intent);
    }
}
package kr.co.choi.roadbob_project;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {
    private static DateBase dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        dbHelper = new DateBase(this);
    }

    public void onClick(View v) {
        SQLiteDatabase db;
        String sql;

        EditText name = (EditText) findViewById(R.id.name);
        EditText tel = (EditText) findViewById(R.id.tel);
        EditText deliv = (EditText) findViewById(R.id.delivery);
        EditText open = (EditText) findViewById(R.id.open);
        EditText close = (EditText) findViewById(R.id.close);
        EditText addr = (EditText) findViewById(R.id.addr);


        String n = name.getText().toString();
        String t = tel.getText().toString();
        String d = deliv.getText().toString();
        String o = open.getText().toString();
        String c = close.getText().toString();
        String a = addr.getText().toString();

        db = dbHelper.getWritableDatabase();
        sql = String.format("INSERT INTO Bob VALUES('" + n + "','"
                + t + "','" + d + "','" + o + "','" + c + "','" + a + "');");

        db.execSQL(sql);
        Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();

        name.setText("");
        tel.setText("");
        deliv.setText("");
        open.setText("");
        close.setText("");
        addr.setText("");

        dbHelper.close();
    }
}

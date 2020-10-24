package kr.co.choi.roadbob_project;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class SearchActivity extends AppCompatActivity {
    private static DateBase dbHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        dbHelper = new DateBase(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    public void searchMap(View target){
        Intent intent = new Intent(getApplicationContext(),SearchMapActivity.class);
        startActivity(intent);
    }

    public void search(View v) {
        SQLiteDatabase db;
        String sql;
        int count = 0;

        EditText name = (EditText) findViewById(R.id.show_name);
        TextView tel = (TextView) findViewById(R.id.show_tel);
        TextView deliv = (TextView) findViewById(R.id.show_delivery);
        TextView open = (TextView) findViewById(R.id.show_open);
        TextView close = (TextView) findViewById(R.id.show_close);
        TextView addr = (TextView) findViewById(R.id.show_addr);

        db = dbHelper.getReadableDatabase();
        sql = "SELECT * FROM Bob;";
        String str = name.getText().toString();
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            if(cursor.getString(0).equals(str)){
                tel.setText(cursor.getString(1));
                deliv.setText(cursor.getString(2));
                open.setText(cursor.getString(3));
                close.setText(cursor.getString(4));
                addr.setText(cursor.getString(5));
                Toast.makeText(getApplicationContext(),"탐색 완료",Toast.LENGTH_SHORT).show();
                count = 100;
            }
        }
        if(count==0) {
            Toast.makeText(getApplicationContext(),
                    "조회결과가 없음",Toast.LENGTH_SHORT).show();
            tel.setText("");
            deliv.setText("");
            open.setText("");
            close.setText("");
            addr.setText("");
        }
        cursor.close();
        dbHelper.close();
    }
}
package kr.co.choi.roadbob_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    private static DateBase dbHelper;
    ArrayList<HashMap<String, String>> personList;
    ListView list;

    private static final String TAG_NAME = "name";
    private static final String TAG_TIME ="open & close";;
    ListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_main);
        dbHelper = new DateBase(this);

        list = (ListView) findViewById(R.id.listView);
        personList = new ArrayList<HashMap<String, String>>();
        showList();
    }
    protected void showList(){

        SQLiteDatabase db;
        db = dbHelper.getReadableDatabase();

        try {
            //SELECT문을 사용하여 테이블에 있는 데이터를 가져옴
            Cursor c = db.rawQuery("SELECT * FROM Bob;", null);
            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                       String Name = c.getString(0);
                        String Open = c.getString(3);
                        String Close =  c.getString(4);
                        //HashMap에 넣기
                        HashMap<String,String> persons = new HashMap<String,String>();

                        persons.put(TAG_NAME,Name);
                        persons.put(TAG_TIME,Open + " ~ " + Close);
                        //ArrayList에 추가
                        personList.add(persons);

                    } while (c.moveToNext());
                }
            }
            c.close();
            dbHelper.close();

            //새로운 apapter를 생성하여 데이터를 넣은 후
            adapter = new SimpleAdapter(
                    this, personList, R.layout.list2,
                    new String[]{TAG_NAME,TAG_TIME},
                    new int[]{ R.id.list_name, R.id.list_time}
            );
            //화면에 보여주기 위해 Listview에 연결
            list.setAdapter(adapter);
        } catch (SQLiteException se) {
            Toast.makeText(getApplicationContext(),  se.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("",  se.getMessage());
        }
    }
}
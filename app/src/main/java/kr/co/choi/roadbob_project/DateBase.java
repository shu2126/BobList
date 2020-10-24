package kr.co.choi.roadbob_project;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DateBase extends SQLiteOpenHelper{

    public DateBase(Context context) {
        super(context, "Bob", null, 2);
    }
    //DB 처음 만들때 호출. - 테이블 생성 등의 초기 처리.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Bob (name TEXT, tel TEXT, " +
                "deliv TEXT, addr TEXT, open TEXT, close TEXT);");
        //result.append("\nt3 테이블 생성 완료.");
    }
    //DB 업그레이드 필요 시 호출. (version값에 따라 반응)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Bob");
        onCreate(db);
    }
}
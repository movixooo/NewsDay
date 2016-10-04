package newsday.zhuoxin.com.newsday.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016.9.20.
 */
public class MySQLite extends SQLiteOpenHelper{
    public MySQLite(Context context) {
        super(context,"News.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id Integer primary key autoincrement,title varchar(20),Url varchar(20))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

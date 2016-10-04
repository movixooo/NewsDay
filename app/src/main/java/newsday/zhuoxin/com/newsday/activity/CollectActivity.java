package newsday.zhuoxin.com.newsday.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import newsday.zhuoxin.com.newsday.Info.NewsInfo;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.SQLite.MySQLite;
import newsday.zhuoxin.com.newsday.adapter.RecyclerAdapter;
import newsday.zhuoxin.com.newsday.base.BaseActivity;
import newsday.zhuoxin.com.newsday.callback.NewsColleccallback;

public class CollectActivity extends BaseActivity {
    List<NewsInfo> data;
    RecyclerAdapter recyclerAdapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    MySQLite mySQLite;
    SQLiteDatabase db;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_collect);
    }

    @Override
    public void initView() {
        toolbar= (Toolbar) findViewById(R.id.tb_collec);
        toolbar.setNavigationIcon(R.drawable.btn_return);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toolbar.setTitle("Newsday");
        recyclerView= (RecyclerView) findViewById(R.id.rv_collec);
        SQLiteGetDate();
        recyclerAdapter=new RecyclerAdapter(this,data);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerAdapter.setNewsColleccallback(new NewsColleccallback() {
            @Override
            public void SkipOnChildClick(String title, String link) {
                Log.i("SkipOnChildClick","点击了跳转");
                Intent intent = new Intent(CollectActivity.this,NewsCoolView.class);
                Bundle bundle=new Bundle();
                bundle.putString("Url",link);
                bundle.putString("title",title);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            @Override
            public void DelOnChildClick(String title, String Url) {
                SQLiteDelDate(title);
                for (int i=0;i<data.size();i++){
                    if (title.equals(data.get(i).titie)){
                        data.remove(i);
                        break;
                    }
                }
                recyclerAdapter.notifyDataSetChanged();
                Toast.makeText(CollectActivity.this,"取消收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void afterViewLogic() {

    }
    private void SQLiteGetDate(){
        mySQLite=new MySQLite(this);
        db=mySQLite.getReadableDatabase();
        Cursor cursor=db.rawQuery("select*from info",null);
        data=new ArrayList<>();
        while (cursor.moveToNext()){
            NewsInfo newsInfo=new NewsInfo();
            newsInfo.titie=cursor.getString(1);
            newsInfo.Url=cursor.getString(2);
            data.add(newsInfo);
        }
        cursor.close();//释放指针
        db.close();//释放流
    }
    private void SQLiteDelDate(String title){
        mySQLite=new MySQLite(this);
        db=mySQLite.getWritableDatabase();
        db.execSQL("delete from info where title='"+title+"'");
        db.close();//释放掉流
    }
}

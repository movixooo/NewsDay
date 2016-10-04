package newsday.zhuoxin.com.newsday.activity;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.SQLite.MySQLite;
import newsday.zhuoxin.com.newsday.base.BaseActivity;
public class NewsCoolView extends BaseActivity {
    CoordinatorLayout cc;
    String Url;
    String title;
    WebView web;
    ProgressBar progressBar;
    Toolbar toolbar;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if (msg.arg1==0) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    progressBar.setProgress(msg.arg1);
                    if (msg.arg1==100) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    };
    @Override
    public void setContentLayout() {
        setContentView(R.layout.news_cool_view);
        Url=getIntent().getExtras().getString("Url");
        title=getIntent().getExtras().getString("title");
    }
    @Override
    public void initView() {
        progressBar= (ProgressBar) findViewById(R.id.pb_news_cool_view);
        toolbar= (Toolbar) findViewById(R.id.tb_news_cool_view);
        Coordinator();//设置收藏栏
        setingToolbar();//设置Toolbar
        web= (WebView) findViewById(R.id.wv_news);
        web.loadUrl(Url);//加载要初始化的网页
        //如果遇到web需要自定义设置
        web.getSettings().setLoadWithOverviewMode(true);//这里面包括所有的web设置
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new myWebviewClient());   //设置本身监听
        web.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Message message=new Message();
                message.arg1=newProgress;
                handler.sendMessage(message);
                Log.i("newProgress",newProgress+"");
            }
        });//2，设置进度条的监听
        Log.i("zxczxczxc","zxczxczxc");
    }

    @Override
    public void afterViewLogic() {

    }
    class myWebviewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                if (web.canGoBack()){
                    web.goBack();
                    return true;
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void Coordinator(){
        cc= (CoordinatorLayout) findViewById(R.id.coor);
        findViewById(R.id.diyi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collect();
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setingToolbar(){
        toolbar.setTitle("NewsDay");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setNavigationIcon(R.drawable.btn_return);//左边那个箭头
        setSupportActionBar(toolbar);//标题栏
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {//返回键的监听
            @Override
            public void onClick(View v) {
                web.goBack();
            }
        });
    }
    public void collect(){
        MySQLite mySQLite=new MySQLite(this);
        SQLiteDatabase db=mySQLite.getWritableDatabase();
//        db.execSQL("insert into info (title,Url)valune('"+title+"','"+Url+"')");
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",title);
        contentValues.put("Url",Url);
        db.insert("info",null,contentValues);
        db.close();
        Snackbar.make(cc,"收藏成功",Snackbar.LENGTH_SHORT).show();
    }
}

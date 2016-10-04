package newsday.zhuoxin.com.newsday.frame;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import newsday.zhuoxin.com.newsday.R;

/**
 * Created by Administrator on 2016.9.1.
 */
public class HotFragment extends Fragment {
    String Url;
    WebView web;
    ProgressBar progressBar;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_hot,container,false);
        return view;
    }
    // View创建完成！
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar =(ProgressBar) view.findViewById(R.id.hot_probar);
        web= (WebView)view.findViewById(R.id.wv_hot);
        web.loadUrl("http://news.qq.com/");//加载要初始化的网页
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
            }
        });//2，设置进度条的监听
    }
    class myWebviewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}

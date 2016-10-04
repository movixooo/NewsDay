package newsday.zhuoxin.com.newsday.base;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;

/**
 * Created by Administrator on 2016.9.14.
 */
public class MyApplication extends Application{
    private static RequestQueue queue;
    private  static Gson gson;
    public static RequestQueue getRequestQueue(){return queue;}
    public static Gson getGson(){return gson;}
    private static MyApplication myApplication;
    public static MyApplication getInstance() {
        return myApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this,"2bf845320bc3e1b4844af875faaa2125");
        myApplication = this;
        // 请求队列的实力化
        queue = Volley.newRequestQueue(getApplicationContext());
        // Gson对象
        gson = new Gson();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480,800)
                .threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY-1)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13)
//                .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(50)
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())// default
//                .imageDownloader(new BaseImageDownloader(this))//default
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())// default
                .writeDebugLogs()
                .build();
        // 2.将配置信息给予我们的ImageLoader对象
        ImageLoader.getInstance().init(configuration);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        queue.cancelAll(this);

    }
}

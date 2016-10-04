package newsday.zhuoxin.com.newsday.Volley;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.base.MyApplication;

/**
 * Created by Administrator on 2016.9.14.
 */
public class VolleyDoHttp {
    private VolleyDoHttp(){}
    private static VolleyDoHttp volleyDoHttp;
    public static VolleyDoHttp getVolleyDoHttp(){
        if (volleyDoHttp==null){
            volleyDoHttp=new VolleyDoHttp();
        }
        return volleyDoHttp;
    }
    private VolleyHttpCallBack volleyHttpCallBack;
    public VolleyHttpCallBack getCallBack(){
        return volleyHttpCallBack;
    }
    public void setCallBack(VolleyHttpCallBack volleyHttpCallBack){
        this.volleyHttpCallBack=volleyHttpCallBack;
    }
    private class MyImageCache implements ImageLoader.ImageCache {
        private LruCache<String,Bitmap> lruCache;
        public MyImageCache(){
            int maxSize=20*1024*1024;
            this.lruCache=new LruCache<String,Bitmap>(maxSize){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getByteCount();
                }
            };
        }
        @Override
        public Bitmap getBitmap(String key) {
            return lruCache.get(key);
        }
        @Override
        public void putBitmap(String key, Bitmap bitmap) {
            lruCache.put(key,bitmap);
        }
    }
    public void imageLoaderByLruCache(String picUrl, ImageView imageView){
        ImageLoader imageLoader=new ImageLoader(MyApplication.getRequestQueue(), new MyImageCache());
        ImageLoader.ImageListener imageListener=ImageLoader.getImageListener(imageView,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        imageLoader.get(picUrl,imageListener);
    }
    public void strongRequestByGETMethod(String url){
        final Gson gson=MyApplication.getGson();
        RequestQueue queue=MyApplication.getRequestQueue();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (volleyHttpCallBack!=null)
                    volleyHttpCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyHttpCallBack.onError(volleyError);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> apikey=new HashMap<String, String>();
                apikey.put("apikey",GetURL.APIKEY);
                return apikey;
            }
        };
        stringRequest.setTag(url);
        queue.add(stringRequest);
    }
}

package newsday.zhuoxin.com.newsday.Volley;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2016.9.14.
 */
public interface VolleyHttpCallBack {
    void onSuccess(String respones);
    void onError(VolleyError verifyError);
}

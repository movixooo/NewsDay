package newsday.zhuoxin.com.newsday.frame;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import newsday.zhuoxin.com.newsday.Info.VolleyInfo;
import newsday.zhuoxin.com.newsday.Info.VolleyInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.activity.NewsCoolView;
import newsday.zhuoxin.com.newsday.adapter.NewsContentAdapter;
import newsday.zhuoxin.com.newsday.adapter.base.BaseLoadingAdapter;
import newsday.zhuoxin.com.newsday.base.MyApplication;
import newsday.zhuoxin.com.newsday.callback.NewsCoolViewcallback;
import newsday.zhuoxin.com.newsday.util.ConnectionURL;
/**
 * Created by Administrator on 2016.9.18.
 */
public class BaiDuNewFragment extends Fragment{
    private View contentView;
    private RecyclerView recyclerView;
    private String newName;
    private RequestQueue requestQueue;
    private Gson gson;
    private List<ContentlistBean> contentlistBeans;
    private BaseLoadingAdapter<ContentlistBean> adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private final int ADD_DATA_FLAG = 0; // 上拉加载
    private final int UPADTE_DATA_FLAG = 1; // 下拉刷新
    private int all_page;// 所有页数
    private int nowPage = 1; //当前页数
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_text, container, false);
        // 获取传入的参数
        newName = getArguments().getString("newName");
        Log.i("qweqweqwe",newName+"    1");
        requestQueue = MyApplication.getInstance().getRequestQueue();
        gson = MyApplication.getInstance().getGson();
        return contentView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.baidu_frag_recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.baidu_frag_refreshLayout);
        // 下拉刷新的色彩配置 最多课可以是四种颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.text_color_blue,
                R.color.text_color_green,
                R.color.text_color_red);
        // 下拉监听
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { // 下拉刷新时的回调
                // TODO 重新获取网络数据
                getBaiduNewInfo(UPADTE_DATA_FLAG, 1);
            }
        });
        contentlistBeans = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new NewsContentAdapter(contentlistBeans, getActivity(), recyclerView);
        adapter.setOnLoadingListener(new BaseLoadingAdapter.OnLoadingListener() {
            @Override
            public void onLoading() {
                getBaiduNewInfo(ADD_DATA_FLAG, nowPage);
            }
        });
        recyclerView.setAdapter(adapter);
        getBaiduNewInfo(ADD_DATA_FLAG, nowPage);// 添加上一页数据
        super.onViewCreated(view, savedInstanceState);
    }
    /**
     * 获取数据
     */
    private void getBaiduNewInfo(final int getDataType, int pager) {
        if (all_page>0&&nowPage>all_page){
            swipeRefreshLayout.setRefreshing(false);
            adapter.setLoadingNoMore();
        }
        StringRequest stringRequest = new StringRequest(ConnectionURL.findNewsByName(newName, pager), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    VolleyInfo volleyInfo = gson.fromJson(response, VolleyInfo.class);
                    contentlistBeans = volleyInfo.getShowapi_res_body().getPagebean().getContentlist();
                    all_page = volleyInfo.getShowapi_res_body().getPagebean().getAllPages();
                    onGetDataSuccess(getDataType);
                } catch (Exception e) {
                    getDefult(getDataType);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                getDefult(getDataType);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("apikey", ConnectionURL.BAUDI_KEY);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    /**
     * 获取网络数据成功的时候
     * @param getDataType
     */

    private void getDefult(int getDataType){
        switch (getDataType){
            case ADD_DATA_FLAG:
                adapter.setLoadingError();
                break;
            case UPADTE_DATA_FLAG:
                swipeRefreshLayout.setRefreshing(false);
                break;
        }
    }
    private void onGetDataSuccess(int getDataType) {
        if (contentlistBeans == null || contentlistBeans.size() == 0)
            return;
        Iterator<ContentlistBean> iterator = contentlistBeans.iterator();
        ContentlistBean temp = null;
        // 遍历数据源 并且把所有的没有图片地址的信息删除掉
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp == null || temp.getImageurls().size() == 0 || temp.getImageurls().get(0).getUrl() == null)
                iterator.remove();
        }
        switch (getDataType){
            case ADD_DATA_FLAG:
                adapter.setLoadingComplete();
                if(contentlistBeans!=null)
                    adapter.addAll(contentlistBeans);
                nowPage++;//当前页数需要做一个增加
                break;
            case UPADTE_DATA_FLAG:
                if(contentlistBeans!=null)
                {
                    adapter.clearAll();
                    adapter.addAll(contentlistBeans);
                }
                swipeRefreshLayout.setRefreshing(false);//将是否刷新更改为false 以消除掉显示的小球
                nowPage = 2;
                break;
        }
        ((NewsContentAdapter)adapter).setNewsCoolViewcallback(new NewsCoolViewcallback(){
            @Override
            public void NewsURLOnChildClick(String title,String Url) {
                Intent intent = new Intent(getActivity(),NewsCoolView.class);
                Bundle bundle=new Bundle();
                bundle.putString("Url",Url);
                bundle.putString("title",title);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

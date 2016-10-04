package newsday.zhuoxin.com.newsday.adapter.base;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import newsday.zhuoxin.com.newsday.R;

/**
 * Created by Administrator on 2016.9.18.
 */
public abstract class BaseLoadingAdapter<T> extends RecyclerView.Adapter{
    private List<T> myDatas;
    private Context context;
    protected LayoutInflater inflater;
    private RecyclerView recyclerView;
    private boolean isLoading = false;
    private boolean firstLoading = true;
    // 标示
    private static final int HOLDER_TYPE_NORMAL = 0;
    private static final int HOLDER_TYPE_LOADING = 1;
    private static final int HOLDER_TYPE_HEADER = 2;
    private LoadingViewHolder loadingViewHolder;

    public interface OnLoadingListener {
        void onLoading();
    }

    private OnLoadingListener onLoadingListener;

    public void setOnLoadingListener(OnLoadingListener onLoadingListener) {
        setOnScrollListener();// 触发
        this.onLoadingListener = onLoadingListener;
    }

    public BaseLoadingAdapter(List<T> myDatas, Context context, RecyclerView recyclerView) {
        super();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.recyclerView = recyclerView;
        this.myDatas = myDatas;
        //显示我们的加载
        notifyLoading();
    }

    /**
     * 因为外部第一次时我们直接给的是一个size为0 所有OnCreatView OnBingView并没有执行到 那么就显示不出来
     * 这里我们手动的去让其实现
     */
    private void notifyLoading() {
        if (myDatas.size() != 0 && myDatas.get(myDatas.size() - 1) != null) {
            myDatas.add(null);
            notifyItemInserted(myDatas.size() - 1);
        }
    }

    /**
     * 数据加载成功
     */
    public void setLoadingComplete() {
        if (myDatas.size() > 0 && myDatas.get(myDatas.size() - 1) == null) {
            isLoading = false;
            myDatas.remove(myDatas.size() - 1);
            notifyItemRemoved(myDatas.size() - 1);
        }
    }

    /**
     * 加载失败
     */
    public void setLoadingError() {
        if (loadingViewHolder != null) {
            // 加载失败所要呈现的内容
            isLoading = false; //
            loadingViewHolder.progressBar.setVisibility(View.GONE);
            loadingViewHolder.textView.setText("加载失败，请点击屏幕进行重新的加载！");
            loadingViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onLoadingListener != null) { // 重写加载的操作
                        isLoading = true;
                        loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                        loadingViewHolder.textView.setText("正在加载 请稍后");
                        onLoadingListener.onLoading();
                    }
                }
            });
        }
    }

    /**
     * 没有更多的数据了
     */
    public void setLoadingNoMore() {
        isLoading = false;
        if (loadingViewHolder != null) {
            loadingViewHolder.progressBar.setVisibility(View.GONE);
            loadingViewHolder.textView.setText("没有更多的数据！");
        }
    }

    /**
     * ScrollListener的实现
     */
    private void setOnScrollListener() {
        if (this.recyclerView == null) {
            Log.e("Exception", "并没有设置上recyclerView");
            return;
        }
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 滑动状态的更改
             * @param recyclerView
             * @param newState
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            /**
             * 滑动的过程当中
             * @param recyclerView
             * @param dx
             * @param dy
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 判定是否是滑到了最后一项 也就是屏幕底端 ViewCompat
                if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
//                    Log.i("onLoading_candown", "ok");
                    if (!isLoading && !firstLoading) {// 不是第一次 也不是加载过程中
//                        Log.i("onLoading", "run");
                        notifyLoading();
                        isLoading = true;
                        if (loadingViewHolder != null) {
                            loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                            loadingViewHolder.textView.setText("正在加载...");
                        }
                        if (onLoadingListener != null)
                            onLoadingListener.onLoading();
                    }
                }
                if (firstLoading)
                    firstLoading = false;
            }
        });
    }

    /**
     * 获取每一项实现的风格
     * 在这里规定为我们定义的flag
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Log.i("getItemViewType", position + "");
        T t = myDatas.get(position);
        if (t == null) {// 并没有数据 还在加载的过程当中
            return HOLDER_TYPE_LOADING;
        } else if (position == 0) { // 头部信息
            return HOLDER_TYPE_HEADER;
        } else {
            return HOLDER_TYPE_NORMAL;
        }
    }

    /**
     * 交给子类去实现的这两个方法
     * 父类只维护到了这边的LoadingViewHolder
     */
    public abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup);

    public abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    public abstract RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup viewGroup);

    public abstract void onBindNormalViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        // 根据标示创建出不同的ViewHolder
        switch (viewType) {
            case HOLDER_TYPE_HEADER:
                viewHolder = onCreateHeaderViewHolder(parent);
                break;
            case HOLDER_TYPE_LOADING:
                viewHolder = onCreateLoadingViewHolder(parent);
                loadingViewHolder = (LoadingViewHolder) viewHolder;
                break;
            case HOLDER_TYPE_NORMAL:
                viewHolder = onCreateNormalViewHolder(parent);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        // 根据标示绑定上不同的数据
        switch (viewType) {
            case HOLDER_TYPE_HEADER:
                onBindHeaderViewHolder(holder, position);
                break;
            case HOLDER_TYPE_LOADING:
                onBindLoadingViewHolder(holder);
                break;
            case HOLDER_TYPE_NORMAL:
                onBindNormalViewHolder(holder, position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return myDatas.size();
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.item_recycle_loading_pg);
            textView = (TextView) itemView.findViewById(R.id.item_recycle_loading_tv);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.item_recycle_loading_layout);
        }
    }

    private RecyclerView.ViewHolder onCreateLoadingViewHolder(ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.item_recycle_foot, parent, false);
        LoadingViewHolder loadingViewHolder = new LoadingViewHolder(itemView);
        return loadingViewHolder;
    }

    private void onBindLoadingViewHolder(RecyclerView.ViewHolder holder) {
        // TODO
    }

    /**
     * 添加数据源
     *
     * @param allDatas
     */
    public void addAll(List<T> allDatas) {
        if (this.myDatas != null)
            this.myDatas.addAll(allDatas);
        notifyDataSetChanged();
    }

    /**
     * 清理数据源
     */
    public void clearAll() {
        if (this.myDatas != null)
            this.myDatas.clear();
        notifyDataSetChanged();
    }

}

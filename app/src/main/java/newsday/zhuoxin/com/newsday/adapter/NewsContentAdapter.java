package newsday.zhuoxin.com.newsday.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.List;

import newsday.zhuoxin.com.newsday.Info.VolleyInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.adapter.base.BaseLoadingAdapter;
import newsday.zhuoxin.com.newsday.callback.NewsCoolViewcallback;

/**
 * Created by Administrator on 2016.9.18.
 */
public class NewsContentAdapter extends BaseLoadingAdapter<ContentlistBean> {
    //    private Context context;
    private List<ContentlistBean> myDatas;
    private DisplayImageOptions options;
    private NewsCoolViewcallback newsCoolViewcallback;

    public void setNewsCoolViewcallback(NewsCoolViewcallback newsCoolViewcallback){
        this.newsCoolViewcallback=newsCoolViewcallback;
    }
    public NewsContentAdapter(List<ContentlistBean> myDatas, Context context, RecyclerView recyclerView) {
        super(myDatas, context, recyclerView);
        this.myDatas = myDatas;
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .showImageForEmptyUri(R.drawable.img_news_lodinglose)
                .showImageOnFail(R.drawable.img_news_lodinglose)
                .showImageOnLoading(R.drawable.img_news_loding)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        View itemView = super.inflater.inflate(R.layout.item_recycle_head, viewGroup, false);
        HeaderViewHolder headerViewHolder = new HeaderViewHolder(itemView);
        return headerViewHolder;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
        headerViewHolder.header_tv.setText(myDatas.get(position).getTitle());
//        headerViewHolder.header_tv.setBackgroundResource(R.drawable.icon_aboutme);
        List<ContentlistBean.ImageEntity> entity = myDatas.get(position).getImageurls();
        if (entity.size() != 0)
            ImageLoader.getInstance().displayImage(entity.get(0).getUrl()
                    , headerViewHolder.header_img, options);
        headerViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("position",position+"");
                newsCoolViewcallback.NewsURLOnChildClick(myDatas.get(position).getTitle(),myDatas.get(position).getLink());


            }
        });
    }
    @Override
    public RecyclerView.ViewHolder onCreateNormalViewHolder(ViewGroup viewGroup) {
        NormalViewHolder normalViewHolder = new NormalViewHolder(inflater.inflate(R.layout.item_recycle_newinfo, viewGroup, false));
//        linearLayout=(LinearLayout)normalViewHolder.find
        return normalViewHolder;
    }
    @Override
    public void onBindNormalViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        NormalViewHolder normalViewHolder = (NormalViewHolder) viewHolder;
        ContentlistBean contentlistBean = (ContentlistBean) myDatas.get(position);
        normalViewHolder.item_news_content.setText(contentlistBean.getDesc());
//        normalViewHolder.item_news_icon.setBackgroundResource(R.drawable.icon_aboutme);
        normalViewHolder.item_news_time.setText(contentlistBean.getPubDate());
        normalViewHolder.item_news_title.setText(contentlistBean.getTitle());
        List<ContentlistBean.ImageEntity> entity = myDatas.get(position).getImageurls();
        if (entity.size() != 0)
            ImageLoader.getInstance().displayImage(entity.get(0).getUrl()
                    , normalViewHolder.item_news_icon, options);

        normalViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("position",position+"");
                newsCoolViewcallback.NewsURLOnChildClick(myDatas.get(position).getTitle(),myDatas.get(position).getLink());
            }
        });
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView header_tv;
        public ImageView header_img;
        public RelativeLayout relativeLayout;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            header_tv = (TextView) itemView.findViewById(R.id.item_head_text);
            header_img = (ImageView) itemView.findViewById(R.id.item_head_img);
            relativeLayout= (RelativeLayout) itemView.findViewById(R.id.relativelayout);
        }
    }

    private class NormalViewHolder extends RecyclerView.ViewHolder {
        public TextView item_news_title, item_news_time, item_news_content;
        public ImageView item_news_icon;
        public LinearLayout linearLayout;
        public NormalViewHolder(View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.carview);
            item_news_title = (TextView) itemView.findViewById(R.id.item_recycle_title);
            item_news_time = (TextView) itemView.findViewById(R.id.item_recycle_time);
            item_news_content = (TextView) itemView.findViewById(R.id.item_recycle_content);
            item_news_icon = (ImageView) itemView.findViewById(R.id.item_recycle_img);
        }
    }
}
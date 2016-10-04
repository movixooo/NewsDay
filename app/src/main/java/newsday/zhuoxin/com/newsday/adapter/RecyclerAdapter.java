package newsday.zhuoxin.com.newsday.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import newsday.zhuoxin.com.newsday.Info.NewsInfo;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.callback.NewsColleccallback;

/**
 * Created by Administrator on 2016.9.8.
 */
public class RecyclerAdapter extends RecyclerView.Adapter{
    Context context;
    List<NewsInfo> data;
    NewsColleccallback newsColleccallback;
    public void setNewsColleccallback(NewsColleccallback newsColleccallback){
        this.newsColleccallback=newsColleccallback;
    }
    public RecyclerAdapter(Context context,List<NewsInfo> data){
        this.context=context;
        this.data=data;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fragment,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).tv_title.setText(data.get(position).titie);
        ((MyViewHolder)holder).imageView.setImageResource(R.drawable.star_selected1);
        ((MyViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsColleccallback.DelOnChildClick(data.get(position).titie,data.get(position).Url);
            }
        });
        ((MyViewHolder) holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsColleccallback.SkipOnChildClick(data.get(position).titie,data.get(position).Url);
            }
        });
    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tv_title;
    ImageView imageView;
    LinearLayout linearLayout;
    public MyViewHolder(View itemView) {
        super(itemView);
        tv_title=(TextView)itemView.findViewById(R.id.tv_collect);
        imageView= (ImageView) itemView.findViewById(R.id.recycler);
        linearLayout= (LinearLayout) itemView.findViewById(R.id.ll_fragment);

    }
}


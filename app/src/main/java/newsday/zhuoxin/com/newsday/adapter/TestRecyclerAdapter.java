package newsday.zhuoxin.com.newsday.adapter;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

/**
 * @author: leejohngoodgame
 * @date: 2016/9/7 09:45
 * @email:18328541378@163.com
 */
public class TestRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
//    private Context context;
//    private LayoutInflater layoutInflater;
//    private List<String> datas;
//    private OnRecyclerViewItemActionListener listener;
//    public OnRecyclerViewItemActionListener getListener() {
//        return listener;
//    }
//
//    public void setListener(OnRecyclerViewItemActionListener listener) {
//        this.listener = listener;
//    }
//    public interface OnRecyclerViewItemActionListener{
//        void onItemClickActionListener(View view, int position);
//        void onItemLongClickActionListener(View view,int position);
//    }
//    public TestRecyclerAdapter(Context context,List<String> datas) {
//        super();
//        this.context = context;
//        this.layoutInflater = LayoutInflater.from(context);
//        this.datas = datas;
//    }
//    /**
//     *  getCount
//     *  返回项的数目
//     * @return
//     */
//    @Override
//    public int getItemCount() {
//        return datas.size();
//    }
//    /**
//     * 创建ViewHolder
//     * @param parent
//     * @param viewType
//     * @return
//     */
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = layoutInflater.inflate(R.layout.item_test_recycler,parent,false);
//        MyViewHolder viewHolder = new MyViewHolder(itemView);
//        return viewHolder;
//    }
//
//    /**
//     * 绑定ViewHolder
//     * 设置数据
//     * @param holder
//     * @param position
//     */
//    @Override
//    public void onBindViewHolder(final ViewHolder holder, final int position) {
//
//        // 设置上数据
//        ((MyViewHolder)holder).tv_show.setText(datas.get(position));
//        if(listener!=null){
//            ((MyViewHolder)holder).tv_show.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = holder.getLayoutPosition();
//                    listener.onItemClickActionListener(holder.itemView,pos);
//                }
//            });
//        }
//    }
//}
//class MyViewHolder extends RecyclerView.ViewHolder {
//    TextView tv_show;
//    /**
//     *
//     * @param itemView 每一项的View
//     */
//    public MyViewHolder(View itemView) {
//        super(itemView);
//        // findViewById
//        tv_show = (TextView)itemView.findViewById(R.id.item_test_recycler_tv);
//    }
}


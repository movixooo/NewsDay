package newsday.zhuoxin.com.newsday.frame;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import newsday.zhuoxin.com.newsday.Prefs.ViewpagerPrefs;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.activity.LabelActivity;
import newsday.zhuoxin.com.newsday.adapter.MessageAdapter;
/**
 * Created by Administrator on 2016.9.1.
 */
public class Messagefragment extends Fragment implements View.OnClickListener{
    TabLayout tabLayout;
    ViewPager viewpager;
    List<Fragment> views;
    List<String> data;
    MessageAdapter messageAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_message,container,false);
        return view;
    }
    // View创建完成！
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.message_iv).setOnClickListener(this);
        tabLayout = (TabLayout) view.findViewById(R.id.message_tab);
        viewpager=(ViewPager) view.findViewById(R.id.message_viewpager);
        initViewpagerFragment();

    }
    private void initViewpagerFragment(){
        views=new ArrayList<>();
        if(data!=null) {
            data.clear();
        }
            views.clear();
            data= new ViewpagerPrefs(getActivity()).getTabInfoFromSharedList();
            for (int i=0;i<data.size();i++){
                BaiDuNewFragment temp= new BaiDuNewFragment();
                Bundle tab=new Bundle();
                tab.putString("newName",data.get(i));
                temp.setArguments(tab);
                views.add(temp);
                Log.i("qweqweqwe",data.toString());
            }
        messageAdapter=new MessageAdapter(getActivity().getSupportFragmentManager(),views,data);//FragmentManager
        viewpager.setAdapter(messageAdapter);
        viewpager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewpager);

    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),LabelActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("requestCode","1000");
        intent.putExtras(bundle);
        startActivityForResult(intent,1000);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent datas) {
        super.onActivityResult(requestCode, resultCode, datas);
        if (requestCode==1000&&resultCode==2000){
            views=new ArrayList<>();
            if(data!=null) {
                data.clear();
            }
            views.clear();
            data= new ViewpagerPrefs(getActivity()).getTabInfoFromSharedList();
            for (int i=0;i<data.size();i++){
                BaiDuNewFragment temp= new BaiDuNewFragment();
                Bundle tab=new Bundle();
                tab.putString("newName",data.get(i));
                temp.setArguments(tab);
                views.add(temp);
                Log.i("qweqweqwe",data.toString());
            }
//            initViewpagerFragment();
            messageAdapter=new MessageAdapter(getActivity().getSupportFragmentManager(),views,data);//FragmentManager
            viewpager.setAdapter(messageAdapter);
            messageAdapter.notifyDataSetChanged();
            tabLayout.setupWithViewPager(viewpager);
        }
    }
}
package newsday.zhuoxin.com.newsday.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import newsday.zhuoxin.com.newsday.ui.FlowLayout;
import newsday.zhuoxin.com.newsday.Prefs.ViewpagerPrefs;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.base.BaseActivity;
public class LabelActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private Toolbar tool_label;
    private Set<String> set;
    private String [] data={"娱乐","科普","电脑","游戏","数码","军事","房产","科技","南海","电视","电影","社会","台湾","科技"};
    private ArrayAdapter arrayAdapter;
    private FlowLayout flowLayout;
    private List<String> datas;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_label);
    }
    @Override
    public void initView() {
        flowLayout=(FlowLayout)findViewById(R.id.fl_label);
        listView=(ListView) findViewById(R.id.lv_label);
        arrayAdapter=new ArrayAdapter(this,R.layout.listview_label,R.id.listview_labels,data);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        tool_label=(Toolbar) findViewById(R.id.tool_label);
        tool_label.setTitle("NewsDay");
        tool_label.setTitleTextColor(Color.parseColor("#ffffff"));
        tool_label.setNavigationIcon(R.drawable.btn_return);//右边那个箭头
        setSupportActionBar(tool_label);//核心点
        tool_label.setNavigationOnClickListener(new View.OnClickListener() {//返回键的监听
            @Override
            public void onClick(View v) {
                delivery();
                finish();
            }
        });
    }
    @Override
    public void afterViewLogic() {
        set=new ViewpagerPrefs(this).getTabInfoFromShared();
        flowLayout.getSetData(set);
        delivery();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (set.add(data[position])){
            flowLayout.getSetData(set);
            delivery();
        }
    }
    public void delivery() {
        datas=new ArrayList<>();
        for (String a:set){
            datas.add(a);
        }
        new ViewpagerPrefs(this).saveTabInfoToShared(set);
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("resultCode","2000");
        bundle.putSerializable("resultData",(Serializable)datas);
        intent.putExtras(bundle);
        setResult(2000,intent); // 设置上返回码 和这个返回数据
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            delivery();
            finish();
        }
        return true;
    }
}

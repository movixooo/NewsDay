package newsday.zhuoxin.com.newsday.activity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;
import newsday.zhuoxin.com.newsday.Prefs.ViewpagerPrefs;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.adapter.ViewpagerAdapter;
import newsday.zhuoxin.com.newsday.base.BaseActivity;
public class Viewpager extends BaseActivity implements View.OnClickListener{
    ViewPager viewPager;
    View read_1,read_2,read_3;
    List<View> data;
    Button button;
    ViewpagerAdapter viewpagerAdapter;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_viewpager);
    }
    public void initView(){
        ViewpagerPrefs.setUp(this);
        data=new ArrayList<>();
        viewPager=(ViewPager)findViewById(R.id.vp_viewpager);
        button=(Button)findViewById(R.id.bt_viewpager);
        button.setOnClickListener(this);
        read_1=LayoutInflater.from(this).inflate(R.layout.lead_1,null);
        read_2=LayoutInflater.from(this).inflate(R.layout.lead_2,null);
        read_3=LayoutInflater.from(this).inflate(R.layout.lead_3,null);
        data.add(read_1);
        data.add(read_2);
        data.add(read_3);
    }
    @Override
    public void afterViewLogic() {
        viewpagerAdapter=new ViewpagerAdapter(data);
        button.setVisibility(View.INVISIBLE);
        viewPager.setAdapter(viewpagerAdapter);
        viewPager.setCurrentItem(0);//初始界面
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//ViewPager的监听
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}//滑动中值的变化
            @Override
            public void onPageSelected(int position) {//position为滑动完后的界面的下标
                if (position==2){
                    button.setVisibility(View.VISIBLE);//显示控件
                }else{
                    button.setVisibility(View.INVISIBLE);//隐藏控件
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
    @Override
    public void onClick(View v) {
        finish();
    }
}

package newsday.zhuoxin.com.newsday.activity;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.base.BaseActivity;
import newsday.zhuoxin.com.newsday.frame.HotFragment;
import newsday.zhuoxin.com.newsday.frame.HuntFragment;
import newsday.zhuoxin.com.newsday.frame.Messagefragment;
public class HomeActivity extends BaseActivity implements View.OnClickListener{
    private RadioButton rb_message,rb_hot,rb_hunt;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Toolbar toolbar;
    private Fragment[] fragments = new Fragment[3];
    private DrawerLayout main_drawer;
    private NavigationView main_naView;
    private View view;
    private TextView me_text;
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_home);
        // 初始化
        fragmentManager = getSupportFragmentManager();
    }
    @Override
    public void initView() {
        rb_message = (RadioButton) findViewById(R.id.home_message);
        rb_hot = (RadioButton) findViewById(R.id.home_hot);
        rb_hunt = (RadioButton) findViewById(R.id.home_hunt);
        rb_message.setOnClickListener(this);
        rb_hot.setOnClickListener(this);
        rb_hunt.setOnClickListener(this);
        choiceFragment(0);
        initToolbar();
        view=main_naView.getHeaderView(0);
        me_text=(TextView)view.findViewById(R.id.me_text);
        me_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(LandingActivity.class);
            }
        });
    }
    private void initShared(){//分享
            ShareSDK.initSDK(this);
            OnekeyShare oks = new OnekeyShare();
            //关闭sso授权
            oks.disableSSOWhenAuthorize();
            // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
            oks.setTitle("标题");
            // titleUrl是标题的网络链接，QQ和QQ空间等使用
            oks.setTitleUrl("http://sharesdk.cn");
            // text是分享文本，所有平台都需要这个字段
            oks.setText("我是分享文本");
            // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
            //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
            // url仅在微信（包括好友和朋友圈）中使用
            oks.setUrl("http://sharesdk.cn");
            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
            oks.setComment("我是测试评论文本");
            // site是分享此内容的网站名称，仅在QQ空间使用
            oks.setSite(getString(R.string.app_name));
            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
            oks.setSiteUrl("http://sharesdk.cn");
            // 启动分享GUI
            oks.show(this);
        }
    public void initToolbar(){
        main_drawer = (DrawerLayout)findViewById(R.id.main_drawer);
        main_naView = (NavigationView)findViewById(R.id.main_naView);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        toolbar.setTitle("NewsDay");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);//核心点
        main_drawer = (DrawerLayout)findViewById(R.id.main_drawer);
        // 对Drawer监听 这个监听别人已经实现了 我们直接先使用 需要注意的是我们需要关联到我们的ToolBar
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,main_drawer,toolbar,R.string.app_name,R.string.app_name);
        // 同步状态
        drawerToggle.syncState();
        // 对我们的DrawerLayout设置上监听 关于滑动的动画 ActionBarDrawerToggle在做实现  我们不需要去管理
        main_drawer.addDrawerListener(drawerToggle);
        // 隐藏的View 呈现出来以后 我们是可能点击的 在这边想有相应的点击处理操作 那么直接想到监听者
        main_naView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    // 选择情况下执行的相关操作
                    case R.id.drawer_me:
                        Toast.makeText(HomeActivity.this,"me click",Toast.LENGTH_SHORT).show();
//                        startActivity(NewsCoolView.class);
                        break;
                    case R.id.drawer_collen:
                        //我的收藏
                        startActivity(CollectActivity.class);
                        break;
                }
                // 注意返回值
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//左边分享按钮
        initShared();
        return true;
    }
    @Override
    public void afterViewLogic() {
    }
    // 隐藏掉所有的Fragment
    private void hideAllFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (fragments[i] != null)
                fragmentTransaction.hide(fragments[i]);
        }
        fragmentTransaction.commit();
    }
    // 切换Fragment
    private void choiceFragment(int index) {
        // 隐藏掉所有的Fragment
        hideAllFragment();
        // 开启事务
        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragments[index] == null) {
            switch (index) {
                case 0:
                    fragments[index] = new Messagefragment();
                    rb_message.setChecked(true);
                    fragmentTransaction.add(R.id.home_frame, fragments[index]);
                    break;
                case 1:
                    fragments[index] = new HotFragment();
                    fragmentTransaction.add(R.id.home_frame, fragments[index]);
                    break;
                case 2:
                    fragments[index] = new HuntFragment();
                    fragmentTransaction.add(R.id.home_frame, fragments[index]);
                    break;
            }
        } else {
            fragmentTransaction.show(fragments[index]);
        }
        // 提交事务
        fragmentTransaction.commit();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_message:
                choiceFragment(0);
                break;
            case R.id.home_hot:
                choiceFragment(1);
                break;
            case R.id.home_hunt:
                choiceFragment(2);
                break;
        }
    }
}

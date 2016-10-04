package newsday.zhuoxin.com.newsday.activity;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import newsday.zhuoxin.com.newsday.Prefs.ViewpagerPrefs;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.base.BaseActivity;
public class WelcomeActivity extends BaseActivity implements Animation.AnimationListener {
    ImageView imageView;
    @Override
    public void setContentLayout() {
        if (ViewpagerPrefs.saveStart(this)){
            Intent intent =new Intent(this,Viewpager.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_welcome);
        imageView=(ImageView)findViewById(R.id.iv_welcome);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.anim_welcome);
        imageView.setAnimation(animation);//设置当前控件动画
        animation.setAnimationListener(this);//开启当前控件动画
    }
    @Override
    public void initView() {}
    @Override
    public void afterViewLogic() {}
    @Override
    public void onAnimationStart(Animation animation) {//动画开始
    }
    @Override
    public void onAnimationEnd(Animation animation) {//动画结束
        Intent intent =new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onAnimationRepeat(Animation animation) {
    }
}
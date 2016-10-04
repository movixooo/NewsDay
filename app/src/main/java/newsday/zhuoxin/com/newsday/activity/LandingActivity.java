package newsday.zhuoxin.com.newsday.activity;

import android.view.View;
import android.widget.Toast;

import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.base.BaseActivity;

public class LandingActivity extends BaseActivity {
    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_landing);
    }
    @Override
    public void initView() {
        findViewById(R.id.bt_landing).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LandingActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void afterViewLogic() {

    }
}

package newsday.zhuoxin.com.newsday.base;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Administrator on 2016.8.31.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 加载布局
        setContentLayout();
        // 初始化View
        initView();
        // 后逻辑
        afterViewLogic();
    }
    public abstract void  setContentLayout();
    public abstract void  initView();
    public abstract void  afterViewLogic();
    public void startActivity(Class<?> targetAct){//跳转
        Intent intent=new Intent(this,targetAct);
        startActivity(intent);
    }
    public String getAppVersionName(){//app名称
        String varName = "";
        try {
            varName = getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return varName;
    }
    public String getAppVersionCode(){//版本号获取
        int varCode = 0;
        try {
            varCode = getPackageManager().getPackageInfo(getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return varCode+"";
    }
    @Override
    public void finish() {
        super.finish();
    }
}
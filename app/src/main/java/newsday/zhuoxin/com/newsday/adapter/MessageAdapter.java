package newsday.zhuoxin.com.newsday.adapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;
/**
 * Created by Administrator on 2016.9.1.
 */
public class MessageAdapter extends FragmentStatePagerAdapter{
    private List<Fragment> views;
    private List<String> data;
    public MessageAdapter(FragmentManager fm,List<Fragment> views, List<String> data) {
        super(fm);
        this.views=views;
        this.data=data;
    }
    @Override
    public int getCount() {
        return views.size();
    }
    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (data.size()>position)
            return data.get(position);
        return "";
    }
    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}

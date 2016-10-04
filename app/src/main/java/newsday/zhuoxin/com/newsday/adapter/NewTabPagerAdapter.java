package newsday.zhuoxin.com.newsday.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2016.9.18.
 */
public class NewTabPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> tabTitleList;
    public NewTabPagerAdapter(FragmentManager fm,List<Fragment> fragmentList,List<String> tabTitleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabTitleList = tabTitleList;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        Log.i("TestGEtCount","p"+fragmentList.size());
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        Log.i("TestinstantiateItem","p"+position);
//        Fragment fragment = (Fragment) super.instantiateItem(container,position);
//        getPageTitle(position);
//        return fragment;
//    }
//
//    @Override
//    public int getItemPosition(Object object) {
//        Log.i("TestgetItemPosition",PagerAdapter.POSITION_NONE+"");
//        return PagerAdapter.POSITION_NONE;
//    }
}

package newsday.zhuoxin.com.newsday.Prefs;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
/**
 * Created by Administrator on 2016.8.31.
 */
public class ViewpagerPrefs {
    private static final String MORNEWS="MORNEWS";
    private static final String KEY="KEY";
    private static final String tab_sharedP="frag";
    private static SharedPreferences sharedPreferences=null;
    private Context context;
    public ViewpagerPrefs(Context context){
        this.context = context ;
    }
    public static boolean saveStart(Context context){//判断是否有创建的文件
        if (sharedPreferences==null){
            sharedPreferences=context.getSharedPreferences(MORNEWS,context.MODE_PRIVATE);//创建文件
        }
        return sharedPreferences.getBoolean(KEY,true);
    }
    public static void setUp(Context context){
        if (sharedPreferences==null){
            sharedPreferences=context.getSharedPreferences(MORNEWS,context.MODE_PRIVATE);//创建文件
        }
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putBoolean(KEY,false);
        editor.commit();
    }
    public List<String> getTabInfoFromSharedList(){
        Set<String> tabs= getTabInfoFromShared();
        List<String> tabList=new ArrayList<>();
        for (String tab:tabs){
            tabList.add(tab);
        }
        return tabList;
    }
    public Set<String>  getTabInfoFromShared(){//取的方法
        SharedPreferences sharedPreferences=context.getSharedPreferences(tab_sharedP,0);
        Set<String> tabs=sharedPreferences.getStringSet("menu",null);
        if (tabs==null){
            tabs=new TreeSet<>();
            tabs.add("安卓");
            tabs.add("互联网");
            tabs.add("四川");
            tabs.add("军事");
            tabs.add("星座");
            tabs.add("游戏");
        }
        return tabs;
    }
    public void saveTabInfoToShared(Set<String> tabs){
        SharedPreferences sharedPreferences=context.getSharedPreferences(tab_sharedP,0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();//清空
        editor.putStringSet("menu",tabs);
        editor.commit();
    }
}
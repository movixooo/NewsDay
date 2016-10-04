package newsday.zhuoxin.com.newsday.frame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import newsday.zhuoxin.com.newsday.Info.NewsInfo;
import newsday.zhuoxin.com.newsday.R;
import newsday.zhuoxin.com.newsday.SQLite.MySQLite;
import newsday.zhuoxin.com.newsday.activity.NewsCoolView;

/**
 * Created by Administrator on 2016.9.1.
 */
public class HuntFragment extends Fragment{
    ImageView imageView;
    ListView listView,listView1;
    EditText editText;
    ArrayAdapter<String> adapter,adapter1;
    List<NewsInfo> list;
    List<String> data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_hunt,container,false);
        return view;
    }
    // View创建完成！
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list=new ArrayList<>();
        data=new ArrayList<>();
        listView1= (ListView) view.findViewById(R.id.tv_hunt1);
        imageView= (ImageView) view.findViewById(R.id.im_hunt);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter1=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,android.R.id.text1,data);
                listView1.setAdapter(adapter1);
                listView1.setVisibility(View.VISIBLE);
                listView.setVisibility(View.INVISIBLE);
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),NewsCoolView.class);
                Bundle bundle=new Bundle();
                bundle.putString("Url",getmas(data.get(position)).get(0).Url);
                bundle.putString("title",data.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listView = (ListView) view.findViewById(R.id.tv_hunt);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setText(data.get(position));
                listView.setVisibility(View.INVISIBLE);
            }
        });
        editText=(EditText)view.findViewById(R.id.et_hunt);
        editText.addTextChangedListener(new TextWatcher() {
            //文本改变前
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            //文本改变中
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()>0){
                    data.clear();
                    listView.setVisibility(View.VISIBLE);
                    listView1.setVisibility(View.INVISIBLE);
                    String msg=s.toString();
                    list=getmas(msg);
                    for (NewsInfo newsInfo:list){
                        data.add(newsInfo.titie);
                    }
                    adapter=new ArrayAdapter<>(getContext(),R.layout.hunt_fragment,R.id.tv_hunt_list,data);
                    listView.setAdapter(adapter);
                }else{
                }
            }
            //文本改变后
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private List<NewsInfo> getmas(String a){
        List<NewsInfo> data=new ArrayList<>();
        NewsInfo newsInfo;
        MySQLite mySQLite=new MySQLite(getActivity());
        SQLiteDatabase db=mySQLite.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from info where title Like '%"+a+"%'",null);
        while(cursor.moveToNext()){
            newsInfo=new NewsInfo();
            newsInfo.titie=cursor.getString(cursor.getColumnIndex("title"));
            newsInfo.Url=cursor.getString(cursor.getColumnIndex("Url"));
            data.add(newsInfo);
        }
        cursor.close();
        db.close();
        return data;
    }
}

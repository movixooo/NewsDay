package newsday.zhuoxin.com.newsday.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016.9.18.
 */
public class ConnectionURL {
    public static final String BAIDU_URL = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
    public static final String BAUDI_KEY = "2bf845320bc3e1b4844af875faaa2125";
    public static String findNewsByName(String newName,int page){
        return BAIDU_URL+"?channelName="+changeToUTF(newName)+"&page="+page;
    }
    private static String changeToUTF(String newName){
        try {
            newName = URLEncoder.encode(newName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newName;
    }
}

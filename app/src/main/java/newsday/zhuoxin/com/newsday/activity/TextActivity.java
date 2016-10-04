package newsday.zhuoxin.com.newsday.activity;

import android.support.v7.app.AppCompatActivity;

public class TextActivity extends AppCompatActivity {
//    private RecyclerView recyclerView;
//    private List<String> list;
//    private Toolbar toolbar;
//    // 线性布局管理者 水平 竖直
//    private LinearLayoutManager linearLayoutManagerH;
//    private LinearLayoutManager linearLayoutManagerV;
//    // 网格布局管理者 竖直 水平
//    private GridLayoutManager gridLayoutManagerV;
//    private StaggeredGridLayoutManager gridLayoutManagerH;
//    private TestRecyclerAdapter adapter;
//    // 间隔类
//    private DividerItemDecoration dividerItemDecoration; // ListView
//    private DividerGridItemDecoration dividerGridItemDecoration;// GridView
//
//    private WebView webView;
//    private ImageView webImg;
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if(msg.what == 1){
//                Log.i("runHand","ok");
//                webView.loadData((String)msg.obj,"text/html;charset=utf-8",null);
//            }
//        }
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
////        MyApplication\
////        String json = "{\"code\":200,\"msg\":\"success\",\"newslist\":\n" +
////                "[{\"ctime\":\"2016-09-08 10:56\",\"title\":\"四川患者抗生素过敏死亡 病历中现伪造签名\",\n" +
////                "\"description\":\"网易社会\",\n" +
////                "\"picUrl\":\"http:\\/\\/s.cimg.163.com\\/catchpic\\/8\\/8D\\/8D8876FA4AF9B1534CE2273BD49296EE.jpg.119x83.jpg\",\n" +
////                "\"url\":\"http:\\/\\/news.163.com\\/16\\/0908\\/10\\/C0EG06CM00011229.html#f=slist\"}\n" +
////                "]}";
////        try {
////            JSONObject jsonAllData = new JSONObject(json);
////            int code = jsonAllData.getInt("code");
////            String msg = jsonAllData.getString("msg");
////            JSONArray jsonNewList = jsonAllData.getJSONArray("newslist");
////            JSONObject indexZeroData = jsonNewList.getJSONObject(0);
////            String citme = indexZeroData.getString("ctime");
////            String picUrl = indexZeroData.getString("picUrl");
////            Log.i("jsonCode",""+code);
////            Log.i("jsonMsg",msg);
////            Log.i("jsoncitme",citme);
////            Log.i("jsonpicUrl",picUrl);
////        }catch (JSONException jsonE){
////        }
//        class NewsInfo{
//
//        }
//        Parameters parameters = new Parameters();
//        parameters.put("num","10");
//        parameters.put("page",1);
//        ApiStoreSDK.execute("http://apis.baidu.com/txapi/social/social",ApiStoreSDK.GET,parameters,new ApiCallBack(){
//            @Override
//            public void onSuccess(int i, String responseString) {
//                super.onSuccess(i, responseString);
//                Log.i("onSuccess",responseString);
//                Gson gson = new Gson();
//
//
//            }
//
//            @Override
//            public void onComplete() {
//                super.onComplete();
//            }
//
//            @Override
//            public void onError(int i, String s, Exception e) {
//                super.onError(i, s, e);
//                Log.i("onError",s);
//                Log.e("onError",e.getMessage());
//            }
//        });
//        webView = (WebView)findViewById(R.id.test_web);
//        webImg = (ImageView) findViewById(R.id.test_web_iv);
//        //      new LoadImageThead().start();
//        // new LoadBaiDuJsonThread().start();
//        // new LoadPagerThread().start();
////        recyclerView = (RecyclerView) findViewById(R.id.test_recycler_view);
////        toolbar = (Toolbar)findViewById(R.id.test_toolbar);
////        setSupportActionBar(toolbar);
////
////        initLayoutManager();
//////         layoutManager GridLayoutManager 确定为线性 并且是V方向
//////         LinearLayoutManager 可以为我们显示竖直或者水平方向上的Listview效果
//////        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//////        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//////        GridLayoutManager layoutManager = new GridLayoutManager(this,5,LinearLayoutManager.VERTICAL,false);
//////        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL);
////
////        recyclerView.setLayoutManager(linearLayoutManagerV);
////        // 做出分割线效果
////        // 1.直接在xml中设置上padding或者margin
////        // 2.给recyclerView 上添加上插件  ItemDecoration
////        // recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL_LIST));
////        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
////        recyclerView.setItemAnimator(new DefaultItemAnimator());
////        initDatas();
////        adapter = new TestRecyclerAdapter(this, list);
////        recyclerView.setAdapter(adapter);
////        adapter.setListener(new TestRecyclerAdapter.OnRecyclerViewItemActionListener() {
////            @Override
////            public void onItemClickActionListener(View view, int position) {
////                Toast.makeText(TestActivity.this,"position-"+position,Toast.LENGTH_SHORT).show();
////                list.remove(position);
////                adapter.notifyItemRemoved(position);
////            }
////
////            @Override
////            public void onItemLongClickActionListener(View view, int position) {
////
////            }
////        });
//
//    }
//
//    private class LoadBaiDuJsonThread extends Thread{
//        @Override
//        public void run() {
//            super.run();
//            try {
//                BufferedReader reader = null;
//                String result = null;
//                StringBuffer sbf = new StringBuffer();
//                String httpUrl = "http://apis.baidu.com/txapi/social/social";
//                String httpArg = "num=10&page=1";
//                httpUrl = httpUrl + "?" + httpArg;
//                URL url = new URL(httpUrl);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                // connection
//                connection.setReadTimeout(5000);// 设置时间
//                connection.setRequestMethod("GET");// 设置请求方法
//                connection.setRequestProperty("apikey","2bf845320bc3e1b4844af875faaa2125");
//                connection.setDoInput(true);//
//
//                InputStream is = connection.getInputStream();
//                reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                String strRead = null;
//                while ((strRead = reader.readLine()) != null) {
//                    sbf.append(strRead);
//                    sbf.append("\r\n");
//                }
//                reader.close();
//                result = sbf.toString();
//                Log.i("json",result);
//            }catch (Exception e){
//
//            }
//        }
//    }
//
//
//    private class LoadImageThead  extends  Thread{
//        @Override
//        public void run() {
//            super.run();
//            try {
//                URL url =  new URL("http://img5.duitang.com/uploads/item/201506/16/20150616221510_YLhda.jpeg");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                // connection
//                connection.setReadTimeout(5000);// 设置时间
//                connection.setRequestMethod("GET");// 设置请求方法
//                connection.setDoInput(true);//
//                // 进行流操作
//                InputStream inputStream = connection.getInputStream();
//                FileOutputStream fileOutputStream =null;
//                BufferedOutputStream bufferedOutputStream =null;
//                BufferedInputStream bufferedInputStream =new BufferedInputStream(inputStream);
//                // 判定外部储存卡是否可用
//                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                    File parentPath = Environment.getExternalStorageDirectory(); // 获取外部SDCARD的路径
//                    File downLoadImag = new File(parentPath,"downLoadImg");//创建出下载的文件
//                    fileOutputStream = new FileOutputStream(downLoadImag);// 创建出节点流
//                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);// 创建缓冲流来提升效率
//                    byte[] buffer = new byte[2*1024];// 创建缓冲区
//                    int len ;
//                    while((len=bufferedInputStream.read(buffer))!=-1){
//                        bufferedOutputStream.write(buffer);// 写入
//                    }
//                    bufferedOutputStream.flush();// 清空
//                    bufferedOutputStream.close();// 不能忘记的关闭流的操作
//                    fileOutputStream.close();
//                    bufferedInputStream.close();
//                    inputStream.close();
//                    // 流操作完成的情况下
//                    // 通过BitmapFactory 生成bitMap对象
//                    final Bitmap bitmap = BitmapFactory.decodeFile(downLoadImag.getAbsolutePath());
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            webImg.setImageBitmap(bitmap);// 给ImageView设置上我们的图片
//                        }
//                    });
//                }
//                connection.disconnect();// 关闭网络连接
//            } catch (MalformedURLException e) {
//                Log.i("Exception","MalformedURLException");
//            }catch (IOException e){
//            }finally {
//
//            }
//        }
//    }
//    private class LoadPagerThread extends Thread{
//
//        // 耗时操作 JSON GSON GSONFormat Volley
//        @Override
//        public void run() {
//            super.run();
//            try {
//                URL url =  new URL("https://www.baidu.com/");
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                // connection
//                connection.setReadTimeout(5000);// 设置时间
//                connection.setRequestMethod("GET");// 设置请求方法
//                connection.setDoInput(true);//
//                // 进行流操作
//                InputStream inputStream = connection.getInputStream();
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                final StringBuffer sb = new StringBuffer();//
//                String doRead = null;
//                while((doRead = bufferedReader.readLine())!=null){
//                    sb.append(doRead);
//                }
////                Message message = new Message();
////                message.what = 1;
////                message.obj = sb.toString();
////                handler.sendMessage(message);
//                // 流的关闭操作
//                bufferedReader.close();
//                inputStreamReader.close();
//                inputStream.close();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        webView.loadData(sb.toString(),"text/html;charset=utf-8",null);
//                    }
//                });
//                connection.disconnect();// 关闭网络连接
//            } catch (MalformedURLException e) {
//                Log.i("Exception","MalformedURLException");
//            }catch (IOException e){
//                Log.i("Exception","IOException");
//            }finally {
//
//            }
//        }
//    }
//
//    private void initLayoutManager(){
//        linearLayoutManagerH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        linearLayoutManagerV = new LinearLayoutManager(this);// 默认是竖直的
//        gridLayoutManagerV = new GridLayoutManager(this,4);// 默认是竖直
//        gridLayoutManagerH = new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL);
//        //dividerItemDecoration = new DividerItemDecoration(this,)
//    }
//
//    private void initDatas() {
//        list = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            list.add("item" + i);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.test_recycler_menu,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id){
//            case R.id.test_action_add:
//                list.add(1,"insertOne");
//                adapter.notifyItemInserted(1);
//                break;
//            case R.id.test_action_delete:
//                list.remove(1);
//                adapter.notifyItemRemoved(1);
//                break;
//            case R.id.test_action_HGridView:
//                recyclerView.setLayoutManager(gridLayoutManagerH);
//                break;
//            case R.id.test_action_VGridView:
//                recyclerView.setLayoutManager(gridLayoutManagerV);
//                break;
//            case R.id.test_action_vListView:
//                recyclerView.setLayoutManager(linearLayoutManagerV);
//                break;
//            case R.id.test_action_HListView:
//                recyclerView.setLayoutManager(linearLayoutManagerH);
//                break;
//
//        }
//        return true;
//    }
//
//    private void initToolBar() {
////        toolbar = (Toolbar)findViewById(R.id.tool_bar);
////        toolbar.setTitle("NewsDay");
////        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
////        //toolbar.setSubtitle("everyDay");
////        setSupportActionBar(toolbar); // 提交
////        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
////        navigationView = (NavigationView)findViewById(R.id.navigation_view);
////        getSupportActionBar().setHomeButtonEnabled(true); // 设置左上角的图标是否可用
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 给左上角图标的左边加上一个返回的图标
////        //创建返回键，并实现打开关/闭监听
////        //对Drawerlayout的监听
////        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
////        mDrawerToggle.syncState();// 同步状态
////        drawerLayout.addDrawerListener(mDrawerToggle);
//    }
//

}

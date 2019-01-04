package com.example.administrator.mhw;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDB();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav);    //侧边栏选中
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.TuJian:
                        Intent tujian = new Intent(MainActivity.this, TuJian_Activity.class);
                        startActivity(tujian);
                        break;

                    case R.id.PeiZhuang:
                        Intent peizhuang = new Intent(Intent.ACTION_VIEW);
                        peizhuang.setData(Uri.parse("http://pc.duowan.com/s/mhw/pz.html"));
                        startActivity(peizhuang);
                        break;

                    case R.id.AboutUs:
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("关于我们");
                        dialog.setMessage("制作人员：吴昊，刘浩天\n版本号：v1.0");
                        dialog.setPositiveButton("感谢使用", null);
                        dialog.show();
                        break;
                }
                return true;
            }
        });

        if(isConnectInternet(MainActivity.this)) {     //有网络连接
            LinearLayout News = (LinearLayout) findViewById(R.id.News);
            News.setVisibility(View.VISIBLE);
            initListView(MainActivity.this);
        }
        else {      //无网络连接
            LinearLayout NoNet = (LinearLayout) findViewById(R.id.No_Net);
            NoNet.setVisibility(View.VISIBLE);
            Button Retry = (Button) findViewById(R.id.Retry);
            Retry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isConnectInternet(MainActivity.this)) {      //判断刷新时是否的状态
                        LinearLayout NoNet = (LinearLayout) findViewById(R.id.No_Net);
                        NoNet.setVisibility(View.GONE);
                        LinearLayout News = (LinearLayout) findViewById(R.id.News);
                        News.setVisibility(View.VISIBLE);
                        initListView(MainActivity.this);
                    }
                }
            });
        }
    }

    public boolean isConnectInternet(Context mContext) {        //判断是否有网络连接
        ConnectivityManager conManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        if(networkInfo != null) return true;
        return false;
    }

    @Override //解决返回键回启动动画的问题，设置为冷启动
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void initDB() {      //初始化数据库
        String DATABASE_PATH = "/data/data/com.example.administrator.mhw/databases/";
        String DATABASE_NAME = "MHW.db";

        File fileDB = new File(DATABASE_PATH + DATABASE_NAME);
        if( !fileDB.exists() ) {    //检测是否已存在数据库
            File file = new File(DATABASE_PATH);
            if(!file.exists())
                file.mkdirs();

            try {       //写入数据
                InputStream is = getBaseContext().getAssets().open(DATABASE_NAME);
                OutputStream os = new FileOutputStream(DATABASE_PATH + DATABASE_NAME);

                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) > 0) {
                    os.write(buffer, 0, len);
                }
                os.flush();
                os.close();
                is.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void initListView(final Context mcontext) {    //首页新闻，从数据库获取数据并添加到listview
        final List<News> newsList = new ArrayList<>();
        NewsAdapter adapter = new NewsAdapter(MainActivity.this, R.layout.news_item, newsList);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "MHW.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("News", null, null, null, null, null, null);
        if( cursor.moveToFirst() ) {
            int id = 0;
            do {
                id++;
                String title = cursor.getString(cursor.getColumnIndex("Title"));
                String time = cursor.getString(cursor.getColumnIndex("Time"));
                String message = cursor.getString(cursor.getColumnIndex("Message"));

                News news = new News(id, title, time, message);
                newsList.add(news);
            } while(cursor.moveToNext());
        }
        cursor.close();

        ListView listView = (ListView) findViewById(R.id.news_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News news = newsList.get(position);
                int resid = news.getResid();
                String title = news.getTitle();
                String message = news.getMessage();
                Intent intent = new Intent(MainActivity.this, NewsInfo.class);
                intent.putExtra("resid", resid+"");
                intent.putExtra("title", title);
                intent.putExtra("message", message);
                startActivity(intent);
            }
        });
    }
}

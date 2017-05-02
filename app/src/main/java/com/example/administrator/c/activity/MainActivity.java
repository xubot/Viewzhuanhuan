package com.example.administrator.c.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.c.Adater.MyRecycleAdapter;
import com.example.administrator.c.MyBean.Bean;
import com.example.administrator.c.MyBean.DataBean;
import com.example.administrator.c.R;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rcv;
    // 当前的条目是recyclerView的头布局
    public static final int HEADER_RECYCLER_VIEW_ITEM = 0;
    // 当前的条目是普通recyclerView的条目
    public static final int NORMAL_RECYCLER_VIEW_ITEM = 1;
    // 一行显示一个
    public static final int RECYCLER_VIEW_ITEM_SINGLE = 3;
    // 一行显示两个
    public static final int RECYCLER_VIEW_ITEM_DOUBLE = 4;
    private TextView iv_switch;// 视图转换
    private MyRecycleAdapter adapter;
    private GridLayoutManager manager;
    private List<DataBean> list=new ArrayList<>();
    private ByteArrayOutputStream bos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv = (RecyclerView) findViewById(R.id.rcv);
        iv_switch = (TextView) findViewById(R.id.iv_switch);
        iv_switch.setOnClickListener(this);
        setData();
        manager = new GridLayoutManager(this, 2);
        // 设置布局管理一条数据占用几行，如果是头布局则头布局自己占用一行
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int postion) {
                if (postion == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        rcv.setLayoutManager(manager);
        adapter = new MyRecycleAdapter(MainActivity.this, list);
        View view = View.inflate(this, R.layout.head, null);
        // 设置当前ViewType
        adapter.setSpanSize(MainActivity.RECYCLER_VIEW_ITEM_DOUBLE);
        adapter.addHeadView(view);
        rcv.setAdapter(adapter);
    }
    //得到数据
    private void setData() {
        String s = setServerData("data.json");
        Gson gson = new Gson();
        Bean erBean = gson.fromJson(s, Bean.class);
        List<Bean.ApkBean> apk = erBean.getApk();
        for(Bean.ApkBean ab:apk) {
            String name = ab.getName();
            String iconUrl = ab.getIconUrl();
            list.add(new DataBean(iconUrl,name));
        }
        Log.i("1111",apk.toString());
    }
    //打开文件
    public String setServerData(String str)  {
        InputStream stream = null;
        try {
            stream = getAssets().open(str);
            byte[]b = new byte[1024];
            int len;
            bos = new ByteArrayOutputStream();
            while ((len = stream.read(b))!=-1){
                bos.write(b,0,len);
            }
            return  bos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  str;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_switch:
                changeRecycleViewList();
                break;
            default:
                break;
        }
    }

    /**
     * 改变RecycleView的显示列数
     */
    private void changeRecycleViewList() {
        if (adapter != null) {
            int spanSize = adapter.getSpanSize();
            // 当前一行显示一列
            if (spanSize == MainActivity.RECYCLER_VIEW_ITEM_SINGLE) {
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (adapter.getItemViewType(position) == MainActivity.HEADER_RECYCLER_VIEW_ITEM) {
                            return 2;
                        } else {
                            return 1;
                        }
                    }
                });
                adapter.setSpanSize(MainActivity.RECYCLER_VIEW_ITEM_DOUBLE);
            }
            // 当前一行显示两列
            else if (spanSize == MainActivity.RECYCLER_VIEW_ITEM_DOUBLE) {
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (adapter.getItemViewType(position) == MainActivity.HEADER_RECYCLER_VIEW_ITEM) {
                            return 2;
                        } else {
                            return 2;
                        }
                    }
                });
                adapter.setSpanSize(MainActivity.RECYCLER_VIEW_ITEM_SINGLE);
            }
            // 第一个参数是动画开始的位置索引
           adapter.notifyItemRangeChanged(2, adapter.getItemCount());
        }
    }
}

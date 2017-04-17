package com.example.administrator.c;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/17 13:16
 */

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapterHolder>{
    private View headView;
    private Context mContext;
    private int count;
    private int spanSize;// 当前每行显示几列

    MyRecycleAdapter(Context mContext, int count) {
        this.count = count;
        this.mContext = mContext;
    }

    /**
     * 设置数据源总的条目
     */
    @Override
    public int getItemCount() {
        //返回条目数加头布局个数
        return count + 1;
    }

    @Override
    public MyRecycleAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = null;
        // 头部
        if (viewType == MainActivity.HEADER_RECYCLER_VIEW_ITEM) {
            root = headView;
        } else {// 普通条目
            /** 一行显示一条 */
            if (viewType == MainActivity.RECYCLER_VIEW_ITEM_SINGLE) {
                root = LayoutInflater.from(mContext).inflate(R.layout.item_single, parent, false);
            }
            /** 一行显示两条 */
            else {
                root = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
            }
        }
        return new MyRecycleAdapterHolder(root, viewType);
    }

    @Override
    public void onBindViewHolder(MyRecycleAdapterHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        // 头部
        if (itemViewType == MainActivity.HEADER_RECYCLER_VIEW_ITEM) {
            return;
        } else {// 普通条目
            if (itemViewType == MainActivity.RECYCLER_VIEW_ITEM_DOUBLE) {// 一行两列视图
                holder.iv_item_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "2列，" + (position - 1) + "", 0)
                                .show();
                    }
                });
            } else if (itemViewType == MainActivity.RECYCLER_VIEW_ITEM_SINGLE) {// 一行一列视图
                holder.iv_item_icon_single.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext,"单列，" + (position - 1) + "", 0).show();
                    }
                });
            }
        }
    }

    /**
     * 添加自定义头部
     */
    public void addHeadView(View view) {
        this.headView = view;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return MainActivity.HEADER_RECYCLER_VIEW_ITEM;
        } else {
            /** 一行显示一条 */
            if (spanSize == MainActivity.RECYCLER_VIEW_ITEM_SINGLE) {
                return MainActivity.RECYCLER_VIEW_ITEM_SINGLE;
                /** 一行显示两条 */
            } else {
                return MainActivity.RECYCLER_VIEW_ITEM_DOUBLE;
            }
        }
    }

    public int getSpanSize() {
        return spanSize;
    }
    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
}
package com.example.administrator.c;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/17 13:18
 */

public class MyRecycleAdapterHolder extends RecyclerView.ViewHolder{
    //一行两列视图
    public ImageView iv_item_icon;
    public TextView tv_item;

    //一行一列视图
    public ImageView iv_item_icon_single;
    public TextView tv_item_single;

    public MyRecycleAdapterHolder(View itemView) {
        super(itemView);
    }
    public MyRecycleAdapterHolder(View itemView,int viewType) {
        super(itemView);
        initView(itemView,viewType);
    }
    private void initView(View itemView, int viewType) {
        if(viewType == MainActivity.RECYCLER_VIEW_ITEM_DOUBLE){
            iv_item_icon = (ImageView) itemView.findViewById(R.id.iv_item_icon);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        }else if(viewType == MainActivity.RECYCLER_VIEW_ITEM_SINGLE){
            iv_item_icon_single = (ImageView) itemView.findViewById(R.id.iv_item_icon_single);
            tv_item_single = (TextView) itemView.findViewById(R.id.tv_item_single);
        }
    }
}

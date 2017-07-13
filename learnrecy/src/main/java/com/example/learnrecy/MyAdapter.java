package com.example.learnrecy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by l-jobs on 2017/7/12.
 */
class MyAdapter extends RecyclerView.Adapter {

    //自定义ViewHolder
    class MyViewHolder extends RecyclerView.ViewHolder {
        private View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public TextView getTitleText(){
            return (TextView) view.findViewById(R.id.title);
        }

        public TextView getContentText() {
            return (TextView) view.findViewById(R.id.content);
        }
    }

    //设置ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //布局解释器，获取创建的new.xml布局，第二个参数是根布局
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news, null));
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        myViewHolder.getTitleText().setText(newsArr[position].title);
        myViewHolder.getContentText().setText(newsArr[position].content);
    }

    //获取数据对象个数
    @Override
    public int getItemCount() {
        return newsArr.length;
    }
    private NewsData[] newsArr = {new NewsData("第一个标题", "第一个内容"), new NewsData("第二个", "第二个"),
            new NewsData("第一个标题", "第一个内容"),new NewsData("第一个标题", "第一个内容"),new NewsData("第一个标题", "第一个内容")};
}

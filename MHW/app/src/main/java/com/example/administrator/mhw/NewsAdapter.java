package com.example.administrator.mhw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    private int resourceId;
    private Context context;

    public NewsAdapter(Context context, int textViewResourceId, List<News> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        View view;
        if(convertView == null) view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        else view = convertView;

        ImageView NewsImage = (ImageView) view.findViewById(R.id.news_image);
        String str = "news"+news.getId();   //拼接字符串
        int resid = context.getResources().getIdentifier(str, "drawable", "com.example.administrator.mhw");     //获取所需图片的id号
        NewsImage.setImageResource(resid);
        news.setResid(resid);

        TextView NewsTitle = (TextView) view.findViewById(R.id.news_title);
        NewsTitle.setText(news.getTitle());

        return view;
    }
}

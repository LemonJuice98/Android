package com.example.administrator.mhw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TuJianAdapter extends ArrayAdapter<TuJian> {
    private int resourceId;
    private Context context;

    public TuJianAdapter(Context context, int textViewResourceId, List<TuJian> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TuJian tujian = getItem(position);
        View view;
        if(convertView == null) view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        else view = convertView;

        ImageView TuJianImage = (ImageView) view.findViewById(R.id.tujian_image);
        String str = tujian.getPinyin();   //拼接字符串
        int resid = context.getResources().getIdentifier(str, "drawable", "com.example.administrator.mhw");     //获取所需图片的id号
        TuJianImage.setImageResource(resid);

        TextView TuJianTitle = (TextView) view.findViewById(R.id.tujian_title);
        TuJianTitle.setText(tujian.getName());

        TextView TujianMessage = (TextView) view.findViewById(R.id.tujian_message);
        TujianMessage.setText("所属种族："+tujian.getClassify()+"  属性弱点："+tujian.getWeak()+"  异常状态："+tujian.getState());

        return view;
    }
}

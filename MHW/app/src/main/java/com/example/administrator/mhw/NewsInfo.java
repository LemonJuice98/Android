package com.example.administrator.mhw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        Intent intent = getIntent();
        String id = intent.getStringExtra("resid");
        int resid = Integer.parseInt(id);

        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");

        TextView NewsTitle = (TextView) findViewById(R.id.newsinfo_title);
        NewsTitle.setText(title);

        ImageView NewsImage = (ImageView) findViewById(R.id.newsinfo_image);
        NewsImage.setImageResource(resid);

        TextView NewsMessage = (TextView) findViewById(R.id.newsinfo_message);
        NewsMessage.setText(message);
        NewsMessage.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}

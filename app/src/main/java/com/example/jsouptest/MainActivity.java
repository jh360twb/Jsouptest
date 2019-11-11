package com.example.jsouptest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    Toolbar toolbar;
    private List<News> news = new ArrayList<>();
    Context context = MainActivity.this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.student_list);
        toolbar = findViewById(R.id.toolbar);
        InitData();
    }

    private void InitData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    NewsJsoup newsJsoup = new NewsJsoup();
                    news = newsJsoup.GetNews();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            InitRecyclerView();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void InitRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(context,news);
        recyclerView.setAdapter(newsAdapter);
    }

}

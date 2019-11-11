package com.example.jsouptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    List<News> newslist = new ArrayList<>();
    LayoutInflater inflater;
    Context context;


    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.newsitem,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = newslist.get(position);
        int pos = position+1;
        holder.title.setText(news.getTitle());
        holder.date.setText(news.getDate());
        holder.head.setText("公告"+pos);
    }

    @Override
    public int getItemCount() {
        return newslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView head;
        TextView title;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head = itemView.findViewById(R.id.head);
            date =itemView.findViewById(R.id.date_text);
            title = itemView.findViewById(R.id.title_text);
        }
    }

    public NewsAdapter(Context context,List<News> newslist){
        this.newslist = newslist;
        this.context = context;
        inflater =LayoutInflater.from(context);
    }
}

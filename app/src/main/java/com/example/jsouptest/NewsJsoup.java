package com.example.jsouptest;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsJsoup {
    private String api = "http://www.lib.wust.edu.cn/bullet/bullet.aspx";
    private static final String TAG = "NewsJsoup";
    List<News> newslist = new ArrayList<>();

    public List<News> GetNews() throws IOException {
        Connection connection = Jsoup.connect(api);
        connection.timeout(5000);
        Document document = connection.get();
        Element element = document.getElementById("content").select("tr").get(0)
                .select("td").get(1).select("div").get(3);
        Elements elements = element.children();
        for (int i=0;i<elements.size();i++){
            Element element1 = elements.get(i);
            String text = element1.select("div").get(1).text().replace("»","");
            String date = element1.select("div").get(2).text();
            News news = new News();
            news.setTitle(text);
            news.setDate(date);
            newslist.add(news);
        }
        return newslist;
    }

}

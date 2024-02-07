package org.example;

import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class HTMLParser {

    private String url;
    private Deque<User> userDeque = new ArrayDeque<>();
    private int userCounter = 1;

    public HTMLParser(String url){
        this.url = url;
    }

    public void parse(){
        try {
            Document doc = Jsoup.connect(url).get();
            Elements users = doc.select("div.response > div > div > p.profile_name");// ник юзера
            Elements userReviews = doc.select("div.response > table > tbody > tr > td > div.brand_words > p > span._reachbanner_");// текст рецензии
            Elements userTopics = doc.select("div.response > div > div > p.sub_title");
            Elements reviewType = doc.select("div.response");

            List<String> reviews = new ArrayList<>();
            for (int i = 0; i < userReviews.size(); i++) {
                String[] mark = userReviews.get(i).html().split("<br>");
                String review = mark[mark.length - 1].split(" из ")[0].trim();
                if (StringUtil.isNumeric(review)){
                    reviews.add(review);
                }else {
                    reviews.add("");
                }
            }

            for (int i = 0; i < users.size(); i++) {
                userDeque.add(new User(userCounter++, users.get(i).text(),userTopics.get(i).text(), reviews.get(i), reviewType.get(i).className().split(" ")[1]));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Deque<User> getUserDeque() {
        return userDeque;
    }

    public void setUserDeque(Deque<User> userDeque) {
        this.userDeque = userDeque;
    }
}

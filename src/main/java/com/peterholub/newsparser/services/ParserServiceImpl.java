package com.peterholub.newsparser.services;

import com.peterholub.newsparser.config.JsoupConfig;
import com.peterholub.newsparser.domains.PageElements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ParserServiceImpl implements ParserService {


    private JsoupConfig jsoupConfig;

    private List<PageElements> pageElementsList = new ArrayList<>();

    public ParserServiceImpl(JsoupConfig jsoupConfig) {
        this.jsoupConfig = jsoupConfig;
    }

    //declaring variables for saving article style
    private Elements articleBold;
    private Elements articleRed;
    private Elements article;

    //Method to separate parsed data by article type
    private Elements[] getArticleTypes() {

        //Start wrapping data by link
        Document document = null;
        try {
            document = Jsoup.connect(jsoupConfig.getUrl()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (document != null) {
            document.data();
        }

        //Select parent class with needed elements
        Elements endlessDiv = null;
        if (document != null) {
            endlessDiv = document.select(jsoupConfig.getEndlessDiv());
        }

        //Creating 3 type of Article
        if (endlessDiv != null) {
            articleBold = endlessDiv.select(jsoupConfig.getArticleBold());

            articleRed = endlessDiv.select(jsoupConfig.getArticleRed());

            //Removing bold & red articles to left default style articles (problem that Jsoup parse all of them by "article" parameter)

            endlessDiv.select(jsoupConfig.getArticleRemove()).remove();

            article = endlessDiv.select(jsoupConfig.getArticle());
        }

        return new Elements[]{article, articleBold, articleRed};

    }

    //Creating list for each article type and add  article style parameter
    private List<PageElements> getListForArticleType(Elements article) {
        LocalDateTime now = LocalDateTime.now();

        String timeParse = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        LocalDateTime parseDateTime = LocalDateTime.parse(timeParse);

        String style;
        //checking what exactly style are articles, and add type to variable "style"
        if (article == articleBold) {
            style = "bold";
        } else if (article == articleRed) {
            style = "red";
        } else {
            style = "default";
        }

        //creating list for each article to add article style

        article.forEach(element -> {

            String time = element.select(jsoupConfig.getTime()).text();


            String headerUrl = String.valueOf(element.getElementsByAttribute(jsoupConfig.getHeaderUrl()));


            String subtitle = element.select(jsoupConfig.getSubtitle()).text();


            String mark = String.valueOf(element.getElementsByTag(jsoupConfig.getMark()));


            pageElementsList.add(new PageElements(style, parseDateTime, time, headerUrl, subtitle, mark));
        });

        return pageElementsList;
    }

    //generate full list of parsed data and sorted by date and time
    public List<PageElements> getPageElements() {

        List<PageElements> pageElements = new ArrayList<>();

        Elements[] articleTypes = getArticleTypes();

        for (int i = 0; i < 3; i++) {

            pageElements = getListForArticleType(articleTypes[i]);
        }

        //Comparator for sorting by time
        Comparator<PageElements> timeCompare = Comparator.comparing(PageElements::getTime);

        //Sorting ArrayList by time field
        pageElements.sort(timeCompare);

        return pageElements;
    }

}
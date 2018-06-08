package com.peterholub.newsparser.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//Class for creating own configuration metadata
@Configuration
@ConfigurationProperties(prefix = "jsop")
public class JsoupConfig {

    private String url;
    private String endlessDiv;
    private String articleBold;
    private String articleRed;
    private String articleRemove;
    private String article;
    private String time;
    private String headerUrl;
    private String href;
    private String subtitle;
    private String mark;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndlessDiv() {
        return endlessDiv;
    }

    public void setEndlessDiv(String endlessDiv) {
        this.endlessDiv = endlessDiv;
    }

    public String getArticleBold() {
        return articleBold;
    }

    public void setArticleBold(String articleBold) {
        this.articleBold = articleBold;
    }

    public String getArticleRed() {
        return articleRed;
    }

    public void setArticleRed(String articleRed) {
        this.articleRed = articleRed;
    }

    public String getArticleRemove() {
        return articleRemove;
    }

    public void setArticleRemove(String articleRemove) {
        this.articleRemove = articleRemove;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}

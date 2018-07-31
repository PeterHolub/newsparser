package com.peterholub.newsparser.domains;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class PageElements {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String style;
    private LocalDateTime parsingDateTime;
    private String time;
    private String headerUrl;
    @Lob
    private String subtitle;
    private String mark;

    public PageElements() {
    }

    public PageElements(String style, LocalDateTime parsingDateTime, String time, String headerUrl, String subtitle, String mark) {
        this.style = style;
        this.parsingDateTime = parsingDateTime;
        this.time = time;
        this.headerUrl = headerUrl;
        this.subtitle = subtitle;
        this.mark = mark;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public LocalDateTime getParsingDateTime() {
        return parsingDateTime;
    }

    public void setParsingDateTime(LocalDateTime parsingDateTime) {
        this.parsingDateTime = parsingDateTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageElements that = (PageElements) o;
        return id == that.id &&
                Objects.equals(style, that.style) &&
                Objects.equals(parsingDateTime, that.parsingDateTime) &&
                Objects.equals(time, that.time) &&
                Objects.equals(headerUrl, that.headerUrl) &&
                Objects.equals(subtitle, that.subtitle) &&
                Objects.equals(mark, that.mark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, style, parsingDateTime, time, headerUrl, subtitle, mark);
    }

    @Override
    public String toString() {
        return "PageElements{" +
                "id=" + id +
                ", style='" + style + '\'' +
                ", parsingDateTime=" + parsingDateTime +
                ", time='" + time + '\'' +
                ", headerUrl='" + headerUrl + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}


package newsparser.model;

//POJO class for parsing HTML elements
public class PageElements {
    private String style;
    private String parseDateTime;
    private String time;
    private String headerUrl;
    private String subtitle;
    private String mark;

    public PageElements(String style, String parseDateTime, String time, String headerUrl, String subtitle, String mark) {

        this.style = style;
        this.parseDateTime = parseDateTime;
        this.time = time;
        this.headerUrl = headerUrl;
        this.subtitle = subtitle;
        this.mark = mark;

    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getParseDateTime() {
        return parseDateTime;
    }

    public void setParseDateTime(String parseDateTime) {
        this.parseDateTime = parseDateTime;
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

    @Override
    public String toString() {
        return "newsparser.model.PageElements{" +
                "style='" + style + '\'' +
                ", parseDateTime='" + parseDateTime + '\'' +
                ", time='" + time + '\'' +
                ", headerUrl='" + headerUrl + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", mark='" + mark + '\'' +
                '}';
    }
}

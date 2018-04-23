package newsparser.parser;

import newsparser.model.PageElements;
import newsparser.properties.PropertiesFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Class for parsing HTML elements
import static jdk.nashorn.internal.objects.NativeString.concat;

public class Parser {

    private ArrayList<PageElements> pageElementsList = new ArrayList<>();
    private PropertiesFile propertiesFile = new PropertiesFile();
    //declaring variables for saving article style
    private Elements articleBold;
    private Elements articleRed;

    public ArrayList<PageElements> getPageElements() throws IOException {
        //Start wrapping data by link
        Document document = Jsoup.connect(propertiesFile.getParserProperties().getProperty("url")).get();
        document.data();
        //Select parent class with needed elements
        Elements endlessDiv = document.select(propertiesFile.getParserProperties().getProperty("endlessDiv"));
        //Creating 3 type of Article
        articleBold = endlessDiv.select(propertiesFile.getParserProperties().getProperty("articleBold"));

        articleRed = endlessDiv.select(propertiesFile.getParserProperties().getProperty("articleRed"));
        //Removing bold & red articles to left default style articles (problem that Jsoup parse all of them by "article" parameter)
        endlessDiv.select(propertiesFile.getParserProperties().getProperty("articleRemove")).remove();

        Elements article = endlessDiv.select(propertiesFile.getParserProperties().getProperty("article"));

        //Creating array an iteration loop for filling pageElementsList
        Elements[] elements = {article, articleBold, articleRed};

        for (int i = 0; i < 3; i++) {
            Style(elements[i]);
        }

        //Sorting ArrayList by time field
        pageElementsList.sort(timeCompare);

        return pageElementsList;

    }

    //Add style to article
    private void Style(Elements article) {
        String style;

        //checking what exactly style are articles, and add type to variable "style"
        if (article == articleBold) {
            style = "bold";
        } else if (article == articleRed) {
            style = "red";
        } else {
            style = "default";
        }

        String timeFormat = "HH:mm";
        //add date and time of parsing
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern(timeFormat)));
        String parseDateTime = String.valueOf(localDate) + concat(" ") + (localTime);

        article.forEach(element -> {

            String time = null;
            try {
                time = element.select(propertiesFile.getParserProperties().getProperty("time")).text();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String headerUrl = null;
            try {
                headerUrl = String.valueOf(element.getElementsByAttribute(propertiesFile.getParserProperties().getProperty("headerUrl")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String subtitle = null;
            try {
                subtitle = element.select(propertiesFile.getParserProperties().getProperty("subtitle")).text();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String mark = null;
            try {
                mark = String.valueOf(element.getElementsByTag(propertiesFile.getParserProperties().getProperty("mark")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            pageElementsList.add(new PageElements(style, parseDateTime, time, headerUrl, subtitle, mark));
        });
    }

    //Comparator for sorting by time
    private Comparator<PageElements> timeCompare = Comparator.comparing(PageElements::getTime);

}

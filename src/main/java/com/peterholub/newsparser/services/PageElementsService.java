package com.peterholub.newsparser.services;

import com.peterholub.newsparser.domains.PageElements;
import java.time.LocalDateTime;
import java.util.List;

public interface PageElementsService {

    void savePageElements(List<PageElements> elements);

    List<PageElements> getPageElementsByDate(LocalDateTime parsingDateTime);

}

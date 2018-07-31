package com.peterholub.newsparser.services;

import com.peterholub.newsparser.domains.PageElements;
import com.peterholub.newsparser.domains.ParsingDates;
import com.peterholub.newsparser.repositories.PageElementsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PageElementsServiceImpl implements PageElementsService {

    private PageElementsRepository pageElementsRepository;

    public PageElementsServiceImpl(PageElementsRepository pageElementsRepository) {
        this.pageElementsRepository = pageElementsRepository;
    }


    public void savePageElements(List<PageElements> elements) {

        pageElementsRepository.saveAll(elements);
    }

    public List<PageElements> getPageElementsByDate(LocalDateTime parsingDateTime) {

        return pageElementsRepository.findAllByParsingDateTime(parsingDateTime);
    }

    //returns hash set of parsing dates from database
    public Set<ParsingDates> getDates() {

        List<PageElements> pageElements = pageElementsRepository.findAll();

        Set<ParsingDates> parsingDates = new HashSet<>();

        for (PageElements element : pageElements) {
            LocalDateTime date = element.getParsingDateTime();
            parsingDates.add(new ParsingDates(date));
        }
        return parsingDates;
    }

}

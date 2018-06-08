package com.peterholub.newsparser.services;

import com.peterholub.newsparser.domains.PageElements;
import java.util.List;

public interface CSVService {
    void createCSVFile(List<PageElements> pageElements);
}

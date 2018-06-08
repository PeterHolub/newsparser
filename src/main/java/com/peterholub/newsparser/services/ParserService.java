package com.peterholub.newsparser.services;

import com.peterholub.newsparser.domains.PageElements;
import java.util.List;

public interface ParserService {

    List<PageElements> getPageElements();
}

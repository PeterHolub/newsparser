package com.peterholub.newsparser.controllers;

import com.peterholub.newsparser.domains.*;
import com.peterholub.newsparser.services.PageElementsServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class ArchiveController {

    private PageElementsServiceImpl pageElementsService;

    public ArchiveController(PageElementsServiceImpl pageElementsService) {
        this.pageElementsService = pageElementsService;
    }

    @PostMapping("/previousResults")
    public String archivePage(Model model) {

        Set<ParsingDates> getDates = pageElementsService.getDates();

        List<ParsingDates> parsingDates = new ArrayList<>(getDates);

        model.addAttribute("parsingDates", parsingDates);

        return "archive";
    }

    @PostMapping("/getDataByDates")
    public String getDataByDates(@RequestParam("parsingdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime parsingDate, Model model) {

        List<PageElements> pageElementsByDate = pageElementsService.getPageElementsByDate(parsingDate);
        model.addAttribute("pageElementsByDate", pageElementsByDate);
        return "archive";
    }
}

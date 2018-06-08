package com.peterholub.newsparser.controllers;

import com.peterholub.newsparser.domains.PageElements;
import com.peterholub.newsparser.repositories.PageElementsRepository;
import com.peterholub.newsparser.services.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller

public class IndexController {
    private EmailServiceImpl emailService;
    private PageElementsRepository pageElementsRepository;
    private ParserServiceImpl parserService;
    private CSVServiceImpl csvService;

    public IndexController(EmailServiceImpl emailService, PageElementsRepository pageElementsRepository, ParserServiceImpl parserService, CSVServiceImpl csvService) {
        this.emailService = emailService;
        this.pageElementsRepository = pageElementsRepository;
        this.parserService = parserService;
        this.csvService = csvService;
    }

    @PostMapping("/index")
    public String indexPage(@RequestParam("button") boolean button, Model model, HttpSession session) {

        if (button) {

            List<PageElements> list = parserService.getPageElements();

            session.setAttribute("pageElements", list);

            return "index";

        } else {
            model.addAttribute("clean", true);
            session.invalidate();
            File file = new File("newsparser.csv");
            file.delete();

            return "index";
        }
    }

    @PostMapping("/saveToCSV")
    public String saveToCSV(@SessionAttribute List<PageElements> pageElements, Model model) {

        csvService.createCSVFile(pageElements);
        model.addAttribute("download", "<a href=\"/downloadCSV\">Download CSV File</a>");
        model.addAttribute("csvSaved", "CSV file generated and ready to download");
        return "index";
    }

    @GetMapping("/downloadCSV")
    public ResponseEntity<InputStreamResource> downloadCSV() throws FileNotFoundException {
        File file = new File("./newsparser.csv");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=" + file.getName()).body(resource);
    }

    @PostMapping("/saveToDatabase")
    public String saveToDataBase(@SessionAttribute List<PageElements> pageElements, Model model) {
        pageElementsRepository.saveAll(pageElements);
        model.addAttribute("databaseSaved", "File uploaded and saved into database");
        return "index";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("email") String to, Model model) {
        emailService.sendEmail(to);
        model.addAttribute("emailSent", "Your email was sent successfully!");
        return "index";
    }

    @GetMapping("/index")
    public String getIndexPage() {

        return "index";
    }

}

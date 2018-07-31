package com.peterholub.newsparser.services;

import com.opencsv.bean.*;
import com.opencsv.exceptions.*;
import com.peterholub.newsparser.domains.PageElements;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.util.List;

@Service
public class CSVServiceImpl implements CSVService {

  public void createCSVFile(List<PageElements> pageElements){

       //Path where file will be generated
       String File_Name = "./newsparser.csv";
       try (
               Writer writer = Files.newBufferedWriter(Paths.get(File_Name))) {

           //writing ArrayList as CSV file using OpenCSV
           StatefulBeanToCsv<PageElements> beanToCsv = new StatefulBeanToCsvBuilder<PageElements>(writer).build();

           beanToCsv.write(pageElements);
       } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
           e.printStackTrace();
       }
   }
}

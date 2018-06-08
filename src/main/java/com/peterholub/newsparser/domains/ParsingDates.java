package com.peterholub.newsparser.domains;

import java.time.LocalDateTime;
import java.util.Objects;

public class ParsingDates {
    private LocalDateTime parsingDates;


    public LocalDateTime getParsingDates() {
        return parsingDates;
    }

    public void setParsingDates(LocalDateTime parsingDates) {
        this.parsingDates = parsingDates;
    }

    public ParsingDates(LocalDateTime parsingDates) {
        this.parsingDates = parsingDates;
    }

    public ParsingDates() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParsingDates that = (ParsingDates) o;
        return Objects.equals(parsingDates, that.parsingDates);
    }

    @Override
    public int hashCode() {

        return Objects.hash(parsingDates);
    }

    @Override
    public String toString() {
        return "ParsingDates{" +
                "parsingDates=" + parsingDates +
                '}';
    }
}

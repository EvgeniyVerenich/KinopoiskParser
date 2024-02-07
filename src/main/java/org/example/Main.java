package org.example;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.*;

public class Main {
    public static void main(String[] args) {

        HTMLParser parser = new HTMLParser("https://www.kinopoisk.ru/film/326/reviews/ord/date/status/all/perpage/200/page/1/");
        parser.parse();
        parser.setUrl("https://www.kinopoisk.ru/film/326/reviews/ord/date/status/all/perpage/200/page/2/");
        parser.parse();
        parser.setUrl("https://www.kinopoisk.ru/film/326/reviews/ord/date/status/all/perpage/200/page/3/");
        parser.parse();
        FileWriter writer = null;
        try {
            writer = new FileWriter("reviews.csv");

            ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
            String[] header = new String[]{"id", "name", "topic", "review", "reviewType"};

            beanWriter.writeHeader(header);
            for (User user : parser.getUserDeque()) {
                 beanWriter.write(user, header, getProcessors());
            }
            writer.flush();
            beanWriter.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private static CellProcessor[] getProcessors() {
        return new CellProcessor[]{
                new UniqueHashCode(),// for id
                new NotNull(),// for name
                new Optional(),// for topic
                new Optional(),
                new NotNull()// for type
        };
    }
}
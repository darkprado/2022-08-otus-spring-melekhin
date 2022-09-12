package ru.otus.example.testingstudentnewsetting.util.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ru.otus.example.utils.impl.CsvReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author s.melekhin
 * @since 12 сент. 2022 г.
 */
@DisplayName("CSV reader class test")
public class CsvReaderTest {

    @DisplayName("check the number of questions")
    @Test
    public void checkNumberQuestions() {
        CsvReader reader = new CsvReader("questions-test.csv");
        assertEquals(2, reader.readFile().size());
    }

}

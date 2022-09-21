package ru.otus.example.utils.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ru.otus.example.entity.Answer;
import ru.otus.example.entity.Question;
import ru.otus.example.enums.CsvPositions;
import ru.otus.example.utils.Reader;

/**
 * @author s.melekhin
 * @since 08 сент. 2022 г.
 */
@Component
public class CsvReader implements Reader {

    private static final char DELIMITER = ';';
    private static final String ANSWER_DELIMITER = ":";

    private final String fileName;

    public CsvReader(@Value("${file.fileName}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Question> readFile() {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)), StandardCharsets.UTF_8)
        )) {
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.newFormat(DELIMITER));
            return csvParser.getRecords()
                    .stream()
                    .map(csvRecord -> {
                        String correctAnswer = csvRecord.get(CsvPositions.CORRECT_ANSWER_POSITION.getVal());
                        return new Question(csvRecord.get(CsvPositions.QUESTION_POSITION.getVal()),
                                Arrays.stream(csvRecord.get(CsvPositions.ANSWER_CHOICE_POSITION.getVal()).split(ANSWER_DELIMITER))
                                        .map(answer -> new Answer(answer, correctAnswer.equals(answer))).collect(Collectors.toList()));
                    }).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

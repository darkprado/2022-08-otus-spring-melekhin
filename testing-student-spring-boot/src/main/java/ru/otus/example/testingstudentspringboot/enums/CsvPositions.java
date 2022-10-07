package ru.otus.example.testingstudentspringboot.enums;

/**
 * @author s.melekhin
 * @since 07 сент. 2022 г.
 */
public enum CsvPositions {

    QUESTION_POSITION(0),
    ANSWER_CHOICE_POSITION(1),
    CORRECT_ANSWER_POSITION(2);
    private final int val;

    private CsvPositions(int v) {
        val = v;
    }

    public int getVal() {
        return val;
    }

}

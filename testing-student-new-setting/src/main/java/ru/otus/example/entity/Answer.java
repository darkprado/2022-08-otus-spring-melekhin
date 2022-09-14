package ru.otus.example.entity;

/**
 * @author s.melekhin
 * @since 14 сент. 2022 г.
 */
public class Answer {

    private final String answerName;
    private final boolean isCorrect;

    public Answer(String answerName, boolean isCorrect) {
        this.answerName = answerName;
        this.isCorrect = isCorrect;
    }

    public String getAnswerName() {
        return answerName;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}

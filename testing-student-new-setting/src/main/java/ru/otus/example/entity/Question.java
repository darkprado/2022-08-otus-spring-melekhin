package ru.otus.example.entity;

import java.util.List;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
public class Question {

    private final String question;
    private final List<String> answerChoice;
    private final String correctAnswer;

    public Question(String question, List<String> answerChoice, String correctAnswer) {
        this.question = question;
        this.answerChoice = answerChoice;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerChoice() {
        return answerChoice;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}

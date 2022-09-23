package ru.otus.example.testingstudentspringboot.entity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
public class Question {

    private final String question;
    private final List<Answer> answers;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerChoice() {
        return answers.stream().map(Answer::getAnswerName).collect(Collectors.toList());
    }

    public Answer getAnswerByAnswerName(String answerName) {
        return answers.stream()
                .filter(answer -> answer.getAnswerName().equals(answerName))
                .findAny().orElse(new Answer(answerName, false));
    }

}

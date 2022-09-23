package ru.otus.example.testingstudentspringboot.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author s.melekhin
 * @since 14 сент. 2022 г.
 */
public class StudentTest {

    private final Student student;
    private Map<String, Answer> answers;
    private final int successPointForTest;

    public StudentTest(Student student, Map<String, Answer> answers, int successPointForTest) {
        this.student = student;
        this.answers = answers;
        this.successPointForTest = successPointForTest;
    }

    public void addStudentAnswer(String questionName, Answer answer) {
        initAnswers();
        answers.put(questionName, answer);
    }

    public boolean testingSuccess() {
        initAnswers();
        return getStudentPoints() >= successPointForTest;
    }

    public int getStudentPoints() {
        return (int) answers.values().stream().filter(Answer::isCorrect).count();
    }

    public Map<String, Answer> getIncorrectQuestionsWithStudentAnswer() {
        Map<String, Answer> incorrectAnswers = new HashMap<>();
        answers.entrySet().stream().filter(answer -> !answer.getValue().isCorrect())
                .forEach(
                        answer -> incorrectAnswers.put(answer.getKey(), answer.getValue()));
        return incorrectAnswers;
    }

    public String getStudent() {
        return student.toString();
    }

    private void initAnswers() {
        if (answers == null) {
            answers = new HashMap<>();
        }
    }
}

package ru.otus.example.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author s.melekhin
 * @since 14 сент. 2022 г.
 */
public class StudentTest {

    private static final String SUCCESS_RESULT = "Student %s, testing success, gain %s point.%s";
    private static final String FAIL_RESULT = "Student %s, testing fail, gain %s point.%s";

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

    public String getTestResult() {
        int correctAnswerCount = getCorrectAnswerCount();
        return correctAnswerCount >= successPointForTest
                ? String.format(SUCCESS_RESULT, student, correctAnswerCount, getIncorrectQuestionsWithStudentAnswer())
                : String.format(FAIL_RESULT, student, correctAnswerCount, getIncorrectQuestionsWithStudentAnswer());
    }

    private int getCorrectAnswerCount() {
        initAnswers();
        return (int) answers.values().stream().filter(Answer::isCorrect).count();
    }

    private String getIncorrectQuestionsWithStudentAnswer() {
        if (getCorrectAnswerCount() != answers.size()) {
            StringBuilder sb = new StringBuilder();
            sb.append(" Incorrect answers:\n");
            answers.entrySet().stream()
                    .filter(answer -> !answer.getValue().isCorrect())
                    .forEach(
                            answer -> sb.append(answer.getKey())
                                    .append("\n")
                                    .append(answer.getValue().getAnswerName())
                                    .append("\n")
                    );
            return sb.toString();
        }
        return "";
    }

    private void initAnswers() {
        if (answers == null) {
            answers = new HashMap<>();
        }
    }
}

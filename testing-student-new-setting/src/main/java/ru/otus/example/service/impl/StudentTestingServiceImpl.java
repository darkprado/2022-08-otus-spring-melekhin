package ru.otus.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.otus.example.entity.Answer;
import ru.otus.example.entity.Question;
import ru.otus.example.entity.Student;
import ru.otus.example.entity.StudentTest;
import ru.otus.example.service.QuestionsService;
import ru.otus.example.service.StudentTestingService;
import ru.otus.example.utils.IOService;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
@Service
public class StudentTestingServiceImpl implements StudentTestingService {

    private static final String SUCCESS_RESULT = "Student %s, testing success, gain %s point.%s";
    private static final String FAIL_RESULT = "Student %s, testing fail, gain %s point.%s";

    private final QuestionsService questionsService;
    private final IOService ioService;
    private final int successTestingPointNumber;

    public StudentTestingServiceImpl(QuestionsService questionsService, IOService ioService, @Value("${point.success}") int successTestingPointNumber) {
        this.questionsService = questionsService;
        this.ioService = ioService;
        this.successTestingPointNumber = successTestingPointNumber;
    }

    @Override
    public void test() {
        do {
            Student student = getStudent();
            StudentTest studentTest = startTesting(student);
            if (studentTest.testingSuccess()) {
                ioService.out(getResult(SUCCESS_RESULT, studentTest));
            } else {
                ioService.out(getResult(FAIL_RESULT, studentTest));
            }
            ioService.out("Continue testing student?(no - exit testing and return result tests)");
        } while (!ioService.in().equals("no"));
    }

    public String getResult(String template, StudentTest studentTest) {
        Map<String, Answer> answers = studentTest.getIncorrectQuestionsWithStudentAnswer();
        if (!answers.isEmpty()) {
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
            return String.format(template, studentTest.getStudent(), studentTest.getStudentPoints(), sb);
        }
        return String.format(template, studentTest.getStudent(), studentTest.getStudentPoints(), "");
    }

    private StudentTest startTesting(Student student) {
        StudentTest studentTest = new StudentTest(student, new HashMap<>(), successTestingPointNumber);
        List<Question> questionList = questionsService.getQuestions();
        questionList.forEach(question -> {
            ioService.out(String.format("Question: %s", question.getQuestion()));
            ioService.out("Choose an answer:");
            question.getAnswerChoice().forEach(ioService::out);
            String studentAnswer = ioService.in();
            studentTest.addStudentAnswer(question.getQuestion(), question.getAnswerByAnswerName(studentAnswer));
            ioService.out("=========================================");
        });
        return studentTest;
    }

    private Student getStudent() {
        Student student = new Student();
        ioService.out("Enter your lastname:");
        student.setLastname(ioService.in());
        ioService.out("Enter your firstname:");
        student.setFirstname(ioService.in());
        ioService.out(String.format("Hello %s", student));
        return student;
    }

}

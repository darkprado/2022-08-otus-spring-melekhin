package ru.otus.example.testingstudentspringboot.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.otus.example.testingstudentspringboot.config.AppProps;
import ru.otus.example.testingstudentspringboot.entity.Answer;
import ru.otus.example.testingstudentspringboot.entity.Question;
import ru.otus.example.testingstudentspringboot.entity.Student;
import ru.otus.example.testingstudentspringboot.entity.StudentTest;
import ru.otus.example.testingstudentspringboot.service.QuestionsService;
import ru.otus.example.testingstudentspringboot.service.StudentTestingService;
import ru.otus.example.testingstudentspringboot.utils.IOService;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
@Service
public class StudentTestingServiceImpl implements StudentTestingService {

    private static final String SUCCESS_KEY = "success";
    private static final String FAIL_KEY = "fail";
    private static final String CONTINUE_KEY = "continue";
    private static final String CONFIRM_KEY = "confirm";
    private static final String INCORRECT_KEY = "incorrect";
    private static final String CHOOSE_KEY = "choose";
    private static final String QUESTION = "question";
    private static final String ENTER_LASTNAME_KEY = "enterLastname";
    private static final String ENTER_FIRSTNAME_KEY = "enterFirstname";
    private static final String BRIEFING_KEY = "briefing";

    private final QuestionsService questionsService;
    private final IOService ioService;
    private final int successTestingPointNumber;
    private final MessageSource messageSource;
    private final Locale locale;

    public StudentTestingServiceImpl(QuestionsService questionsService, IOService ioService, AppProps props, MessageSource messageSource) {
        this.questionsService = questionsService;
        this.ioService = ioService;
        this.successTestingPointNumber = props.getPoint();
        this.messageSource = messageSource;
        this.locale = props.getLocale();
    }

    @Override
    public void test() {
        do {
            Student student = getStudent();
            StudentTest studentTest = startTesting(student);
            if (studentTest.testingSuccess()) {
                ioService.out(getResult(SUCCESS_KEY, studentTest));
            } else {
                ioService.out(getResult(FAIL_KEY, studentTest));
            }
            ioService.out(messageSource.getMessage(CONTINUE_KEY, null, locale));
        } while (!ioService.in().equals(messageSource.getMessage(CONFIRM_KEY, null, locale)));
    }

    public String getResult(String template, StudentTest studentTest) {
        Map<String, Answer> answers = studentTest.getIncorrectQuestionsWithStudentAnswer();
        if (!answers.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append(messageSource.getMessage(INCORRECT_KEY, null, locale));
            answers.entrySet().stream()
                    .filter(answer -> !answer.getValue().isCorrect())
                    .forEach(
                            answer -> sb.append(answer.getKey())
                                    .append("\n")
                                    .append(answer.getValue().getAnswerName())
                                    .append("\n")
                    );
            return messageSource.getMessage(template, new String[]{studentTest.getStudent(), String.valueOf(studentTest.getStudentPoints()), sb.toString()}, locale);
        }
        return messageSource.getMessage(template, new String[]{studentTest.getStudent(), String.valueOf(studentTest.getStudentPoints()), ""}, locale);
    }

    private StudentTest startTesting(Student student) {
        StudentTest studentTest = new StudentTest(student, new HashMap<>(), successTestingPointNumber);
        List<Question> questionList = questionsService.getQuestions();
        questionList.forEach(question -> {
            ioService.out(messageSource.getMessage(QUESTION, new String[]{question.getQuestion()}, locale));
            ioService.out(CHOOSE_KEY);
            question.getAnswerChoice().forEach(ioService::out);
            String studentAnswer = ioService.in();
            studentTest.addStudentAnswer(question.getQuestion(), question.getAnswerByAnswerName(studentAnswer));
            ioService.out("=========================================");
        });
        return studentTest;
    }

    private Student getStudent() {
        Student student = new Student();
        ioService.out(messageSource.getMessage(ENTER_LASTNAME_KEY, null, locale));
        student.setLastname(ioService.in());
        ioService.out(messageSource.getMessage(ENTER_FIRSTNAME_KEY, null, locale));
        student.setFirstname(ioService.in());
        ioService.out(messageSource.getMessage(BRIEFING_KEY, new String[]{student.toString()}, locale));
        return student;
    }

}

package ru.otus.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.otus.example.entity.Question;
import ru.otus.example.entity.Student;
import ru.otus.example.entity.StudentTest;
import ru.otus.example.service.QuestionsService;
import ru.otus.example.service.StudentTestingService;
import ru.otus.example.utils.IOService;
import ru.otus.example.utils.impl.IOServiceImpl;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
@Service
public class StudentTestingServiceImpl implements StudentTestingService {

    private final QuestionsService questionsService;
    private final IOService ioService;
    private final int successTestingPointNumber;
    private final List<StudentTest> studentTestList;
    private boolean continueTesting = true;

    public StudentTestingServiceImpl(QuestionsService questionsService, @Value("${point.success}") int successTestingPointNumber) {
        this.questionsService = questionsService;
        this.successTestingPointNumber = successTestingPointNumber;
        studentTestList = new ArrayList<>();
        this.ioService = new IOServiceImpl(System.out, System.in);
    }

    @Override
    public void test() {
        while (continueTesting) {
            Student student = getStudent();
            startTesting(student);
            ioService.out("Continue testing student?(no - exit testing and return result tests)");
            if (ioService.in().equals("no")) {
                continueTesting = false;
            }
        }
        getResultTesting(studentTestList).forEach(ioService::out);
    }

    public List<String> getResultTesting(List<StudentTest> studentTestList) {
        List<String> resultList = new ArrayList<>();
        for (StudentTest studentTest : studentTestList) {
            resultList.add(studentTest.getTestResult());
        }
        return resultList;
    }

    private void startTesting(Student student) {
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
        studentTestList.add(studentTest);
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

package ru.otus.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ru.otus.example.entity.Question;
import ru.otus.example.entity.Student;
import ru.otus.example.service.QuestionsService;
import ru.otus.example.service.StudentTestingService;
import ru.otus.example.utils.IOService;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
@Service
public class StudentTestingServiceImpl implements StudentTestingService {

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
        Student student = getStudent();
        startTesting(student);
        ioService.out(getResultTesting(student));
    }

    public void startTesting(Student student) {
        List<Question> questionList = questionsService.getQuestions();
        questionList.forEach(question -> {
            ioService.out(String.format("Question: %s", question.getQuestion()));
            ioService.out("Choose an answer:");
            question.getAnswerChoice().forEach(ioService::out);
            if (ioService.in().equals(question.getCorrectAnswer())) {
                student.incrementPoint();
            }
//            ioService.out(String.format("Correct answer: %s", question.getCorrectAnswer()));
            ioService.out("=========================================");
        });
    }

    public String getResultTesting(Student student) {
        if (student.getPoint() >= successTestingPointNumber) {
            return String.format("Congratulation %s, your testing success", student);
        }
        return String.format("%s, try again later", student);
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

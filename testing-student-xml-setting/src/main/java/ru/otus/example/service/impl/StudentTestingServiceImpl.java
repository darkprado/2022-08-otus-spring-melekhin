package ru.otus.example.service.impl;

import java.util.List;
import java.util.Scanner;

import ru.otus.example.dto.StudentDto;
import ru.otus.example.service.QuestionsService;
import ru.otus.example.service.StudentTestingService;
import ru.otus.example.dto.QuestionDto;

/**
 * @author s.melekhin
 * @since 12 май 2022 г.
 */
public class StudentTestingServiceImpl implements StudentTestingService {

    private final QuestionsService qService;
    private final StudentDto student;

    public StudentTestingServiceImpl(QuestionsService qService, StudentDto student) {
        this.qService = qService;
        this.student = student;
    }

    @Override
    public void test() {
        sayWelcome();
        testing();
    }

    private void sayWelcome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your lastname:");
        student.setLastname(scanner.nextLine());
        System.out.println("Enter your firstname:");
        student.setFirstname(scanner.nextLine());
        System.out.printf("Hello %s %n", student);
    }

    public void testing() {
        List<QuestionDto> questionDtoList = qService.getQuestions();
        questionDtoList.forEach(questionDto -> {
            System.out.printf("Question: %s%n", questionDto.getQuestion());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Choose an answer:");
            questionDto.getAnswerChoice().forEach(System.out::println);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("Correct answer: %s%n", questionDto.getCorrectAnswer());
            System.out.println("=========================================");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

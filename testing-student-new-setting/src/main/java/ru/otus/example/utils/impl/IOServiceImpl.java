package ru.otus.example.utils.impl;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import ru.otus.example.utils.IOService;

/**
 * @author s.melekhin
 * @since 10 сент. 2022 г.
 */
@Service
public class IOServiceImpl implements IOService {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String in() {
        return scanner.nextLine();
    }

    @Override
    public void out(String s) {
        System.out.println(s);;
    }
}

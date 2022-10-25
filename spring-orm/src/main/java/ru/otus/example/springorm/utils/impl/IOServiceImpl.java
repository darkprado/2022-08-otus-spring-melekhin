package ru.otus.example.springorm.utils.impl;

import java.io.PrintStream;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import ru.otus.example.springorm.utils.IOService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
public class IOServiceImpl implements IOService {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceImpl() {
        this.output = new PrintStream(System.out);
        this.input = new Scanner(System.in);
    }

    @Override
    public String in() {
        return input.nextLine();
    }

    @Override
    public void out(String s) {
        output.println(s);
    }

}

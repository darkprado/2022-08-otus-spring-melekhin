package ru.otus.example.utils.impl;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import ru.otus.example.utils.IOService;

/**
 * @author s.melekhin
 * @since 10 сент. 2022 г.
 */
public class IOServiceImpl implements IOService {

    private final PrintStream output;
    private final Scanner input;

    public IOServiceImpl(PrintStream output, InputStream input) {
        this.output = output;
        this.input = new Scanner(input);
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

package ru.otus.example.entity;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author s.melekhin
 * @since 08 сент. 2022 г.
 */
public class Student {

    private String firstname;
    private String lastname;

    private final AtomicInteger point = new AtomicInteger(0);

    public int getPoint() {
        return point.get();
    }

    public void incrementPoint() {
        point.incrementAndGet();
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstname, lastname);
    }

}

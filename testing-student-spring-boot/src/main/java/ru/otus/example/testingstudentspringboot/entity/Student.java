package ru.otus.example.testingstudentspringboot.entity;

/**
 * @author s.melekhin
 * @since 08 сент. 2022 г.
 */
public class Student {

    private String firstname;
    private String lastname;

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

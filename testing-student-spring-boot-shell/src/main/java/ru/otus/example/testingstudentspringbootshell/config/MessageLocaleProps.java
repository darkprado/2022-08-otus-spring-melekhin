package ru.otus.example.testingstudentspringbootshell.config;

import java.util.Locale;

/**
 * @author s.melekhin
 * @since 03 окт. 2022 г.
 */
public interface MessageLocaleProps {

    Locale getLocale();

    void setLocale(Locale locale);

}

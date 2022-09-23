package ru.otus.example.testingstudentspringboot.config;

import java.util.Locale;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author s.melekhin
 * @since 17 сент. 2022 г.
 */
@ConfigurationProperties(prefix = "application")
@Component
public class AppProps {

    private Locale locale;
    private int point;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

}

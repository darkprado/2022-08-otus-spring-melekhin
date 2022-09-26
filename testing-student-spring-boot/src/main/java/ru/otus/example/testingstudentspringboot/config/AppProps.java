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
    private int pointNumberForSuccessTesting;

    private String filename;

    private String format;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public int getPointNumberForSuccessTesting() {
        return pointNumberForSuccessTesting;
    }

    public void setPointNumberForSuccessTesting(int pointNumberForSuccessTesting) {
        this.pointNumberForSuccessTesting = pointNumberForSuccessTesting;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

}

package ru.otus.example.testingstudentspringboot.service.impl;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import ru.otus.example.testingstudentspringboot.config.AppProps;
import ru.otus.example.testingstudentspringboot.service.MessageService;

/**
 * @author s.melekhin
 * @since 26 сент. 2022 г.
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;
    private Locale locale;

    public MessageServiceImpl(MessageSource messageSource, AppProps props) {
        this.messageSource = messageSource;
        locale = props.getLocale();
    }

    @Override
    public String getMessage(String key) {
        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, String[] args) {
        return messageSource.getMessage(key, args, locale);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}

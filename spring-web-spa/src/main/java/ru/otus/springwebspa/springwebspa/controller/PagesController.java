package ru.otus.springwebspa.springwebspa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author s.melekhin
 * @since 14 дек. 2022 г.
 */
@Controller
public class PagesController {

    @GetMapping("/book")
    public String allBookPage() {
        return "bookPage";
    }

}

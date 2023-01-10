package ru.otus.springwebspa.springwebspa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author s.melekhin
 * @since 14 дек. 2022 г.
 */
@WebMvcTest(PagesController.class)
public class PagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void allBookPage() throws Exception {
        mvc.perform(get("/book"))
                .andExpect(status().isOk())
                .andExpect(view().name("bookPage"));
    }

}

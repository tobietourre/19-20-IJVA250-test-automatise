package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RechercheController.class)
public class RechercheControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RechercheController rechercheController;

    @MockBean
    private ArticleService mockArticleService;

    @Test
    public void rechercher() throws Exception {
        when(mockArticleService.find("pad")).thenReturn(Collections.emptyList());
        mvc.perform(get("/recherche?query=pad"))
                .andExpect(status().isOk());
        Mockito.verify(mockArticleService).find("pad");
    }

    @Test
    public void rechercher_pad_retourneDeuxResultats() throws Exception {
        Article ipad = new Article();
        ipad.setLibelle("ipad");

        Article paddle = new Article();
        paddle.setLibelle("paddle");


        when(mockArticleService.find("pad")).thenReturn(Arrays.asList(ipad, paddle));
        mvc.perform(get("/recherche?query=pad"))
                .andExpect(status().isOk());
        Mockito.verify(mockArticleService).find("pad");
    }
}
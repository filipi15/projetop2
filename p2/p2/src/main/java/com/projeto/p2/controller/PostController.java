package com.projeto.p2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/catalogo")
    public String catalogo() {
        return "catalogo";
    }
}

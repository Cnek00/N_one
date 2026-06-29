package com.example.N_ONE.notebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Giriş başarılı kanka! Notebook projesinin backend dünyasına hoş geldin. 🚀";
    }
}
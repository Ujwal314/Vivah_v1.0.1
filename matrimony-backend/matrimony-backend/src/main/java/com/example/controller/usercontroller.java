package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/c1")
public class usercontroller {
    @GetMapping("/home")
    public String f1(){
        return "Hello my name is Prateek Kanaujia";
    }
}

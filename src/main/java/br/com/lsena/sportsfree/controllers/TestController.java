package br.com.lsena.sportsfree.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping
    public String testController(){
        return "Teste ok";
    }


    @GetMapping("staging")
    public String testStagingController(){
        return "Teste staging ok";
    }
}

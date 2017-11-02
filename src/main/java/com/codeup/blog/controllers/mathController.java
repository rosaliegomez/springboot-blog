package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mathController {


    @GetMapping("/add/{number}/{word}/{anotherNumber}")
    @ResponseBody
    public int mathController(@PathVariable int number, @PathVariable int anotherNumber, @PathVariable String word) {
            return number + anotherNumber;
        }


    @GetMapping("/subtract/{number}/{word}/{anotherNumber}")
    @ResponseBody
    public int subtract(@PathVariable int number, @PathVariable int anotherNumber, String word){
        return anotherNumber - number;
    }

    @GetMapping("/multiply/{number}/{word}/{anotherNumber}")
    @ResponseBody
    public int multiply(@PathVariable int number, @PathVariable int anotherNumber, String word){
        return number * anotherNumber;
    }

    @GetMapping("/divide/{number}/{word}/{anotherNumber}")
    @ResponseBody
    public double divide(@PathVariable double number, @PathVariable double anotherNumber, String word){
        return number / anotherNumber;
    }
}

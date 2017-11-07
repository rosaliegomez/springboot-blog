package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class RollDice {

@GetMapping("/roll-dice")
    public String home(Model viewModel){
    return "roll-dice";
    }

    @GetMapping ("/roll-dice/{n}")
    public String roll(@PathVariable int n, Model viewModel){
        int dice = (int) Math.floor(Math.random()*6+1);
        System.out.println(dice);
        viewModel.addAttribute("dice",dice);
        viewModel.addAttribute("guessed", n);
        //Or you can make a boolean like the following:
        //boolean win = (dice == guessed) ? true : false;
        return "roll-results";
    }

}

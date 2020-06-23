package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRollGuess(@PathVariable int n, Model model) {
        ArrayList<Integer> diceRoll = new ArrayList<>();

        Random rand = new Random();
        int random = rand.nextInt((6 - 1) + 1) + 1;

        diceRoll.add(random);
        model.addAttribute("diceRoll", diceRoll);
        model.addAttribute("userChoice", n);
        return rollDice();
    }
}

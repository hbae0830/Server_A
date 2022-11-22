package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/list")
    public String list() {
        return "list";
    }

    @GetMapping("/detail/{idx}")
    public String detail() {
        return "detail";
    }

    @GetMapping("/register")
    public String registerGet() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost() {
        return "redirect:/board/list";
    }

    @GetMapping("/update/{idx}")
    public String updateGet() {
        return "update";
    }

    @PostMapping("/update")
    public String updatePost() {
        return "redirect:/board/list";
    }

    @GetMapping("/delete/{idx}")
    public String delete() {
        return "list";
    }
}

package com.codeup.blog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String show() {
        return "Viewing all posts!";
    }

    @GetMapping("/posts/{id}")
    public String showOne(@PathVariable long id, Model model) {
        model.addAttribute("postID", id);
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String viewCreateForm() {
        return "Here is my form!";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPost() {
        return "Creating new post!";
    }


}

package com.codeup.blog.controllers;


import com.codeup.blog.daos.PostsRepository;
import com.codeup.blog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostsRepository postsDao;

    public PostController(PostsRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        List<Post> postList = new ArrayList<>();
        model.addAttribute("noPostsFound", postList.size() == 0);
        model.addAttribute("posts", postList);
        return "/posts/index";
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

    @PostMapping("/posts/create")
    @ResponseBody
    public String savePost() {

        Post newPost = new Post("Post Title", "Here is my post body!");
        postsDao.save(newPost);
        return "Creating new post!";
    }


}

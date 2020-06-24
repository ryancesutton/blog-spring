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

    public PostController(PostsRepository postsRepository) {
        postsDao = postsRepository;
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

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(Model model, @PathVariable long id) {
        Post postToEdit = postsDao.getOne(id);
        model.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    @ResponseBody
    public String update(@PathVariable long id,
                         @RequestParam(name = "title") String title,
                         @RequestParam(name = "body") String body){
        //find a post
        Post foundPost = postsDao.getOne(id);

        //edit the post
        foundPost.setTitle(title);
        foundPost.setBody(body);

        //save the changes
        postsDao.save(foundPost);
        return "Post has been updated";
    }

    @PostMapping("posts/{id}/delete")
    @ResponseBody
    public String destroy(@PathVariable long id) {
        postsDao.deleteById(id);
        return "Post has been deleted";
    }

    @GetMapping("/search")
    public String searchResults(Model model, @RequestParam(name = "term") String term){
        List<Post> posts = postsDao.searchByTitleAndBody(term);
        model.addAttribute("posts", posts);
        return "posts/index";
    }
}

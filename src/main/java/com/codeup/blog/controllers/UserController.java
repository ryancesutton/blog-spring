package com.codeup.blog.controllers;

import com.codeup.blog.daos.UsersRepository;
import com.codeup.blog.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {
    private UsersRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UsersRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/{id}")
    public String viewOtherUserProfile(@PathVariable long id, Model model) {
        User profileUser = users.getOne(id);
        model.addAttribute("user", profileUser);
        List<User> followingList = profileUser.getFollowingList();
        model.addAttribute("followingList", followingList);
        List<User> followerList = profileUser.getFollowerList();
        model.addAttribute("followerList", followerList);
        return "users/profile";
    }

    @PostMapping("/users/follow/{id}") // put this action on the follow button
    public String followUser(@PathVariable long id) {
        //get current user:
        User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = users.getOne(principle.getId());
        User userToFollow = users.getOne(id);


        List<User> following = currentUser.getFollowingList();

        following.add(userToFollow);

        currentUser.setFollowingList(following);

        users.save(currentUser);

        return "redirect:/posts";


    }

    @GetMapping(value = "/gallery.json")
    public @ResponseBody
    List<User> getFollowingListInJson() {
        User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = users.getOne(principle.getId());
        return currentUser.getFollowingList();


    }


}

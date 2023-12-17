package com.example.interestmarket.controller;

import com.example.interestmarket.domain.Post;
import com.example.interestmarket.service.PostService;
import com.example.interestmarket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class InterestMarketController {

    Logger logger = LoggerFactory.getLogger(InterestMarketController.class);
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public InterestMarketController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(String id, String password, Model model) throws ExecutionException, InterruptedException {
        if (userService.login(id, password) != null) {
            List<Post> posts = postService.viewPosts();
            model.addAttribute("posts", posts);
            model.addAttribute("id", id);
            return "postList";
        } else {
            return "loginFail";
        }
    }

    @GetMapping("/posts")
    public String allPost(
            @RequestParam String id,
            Model model) throws ExecutionException, InterruptedException {
        List<Post> posts = postService.viewPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("id", id);
        return "postList";
    }

    @GetMapping("/post")
    public String postView(
            @RequestParam String id,
            @RequestParam String postId,
            Model model) throws ExecutionException, InterruptedException {
        Post post = postService.viewPost(postId);
        model.addAttribute("post", post);
        model.addAttribute("id", id);
        return "postView";
    }

    @GetMapping("/delete")
    public String deletePost(
            @RequestParam String id,
            @RequestParam String writer,
            @RequestParam String postId,
            Model model) throws ExecutionException, InterruptedException {
        if (postService.deletePosts(id, writer, postId)) {
            List<Post> posts = postService.viewPosts();
            model.addAttribute("posts", posts);
            model.addAttribute("id", id);
            return "postList";
        } else {
            return "deleteFail";
        }
    }


    @GetMapping("/writeForm")
    public String writeForm(
            @RequestParam String id,
            Model model) {
        model.addAttribute("id", id);
        return "writeForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/joinForm")
    public String join(
            @RequestParam String name,
            @RequestParam String id,
            @RequestParam String password,
            Model model) throws ExecutionException, InterruptedException {
        userService.join(id, name, password);
        return "redirect:/";
    }

    @PostMapping("/writeForm")
    public String createPost(
            @RequestParam String id,
            @RequestParam String title,
            @RequestParam String content,
            Model model) throws ExecutionException, InterruptedException {
        postService.create(id, title, content);
        List<Post> posts = postService.viewPosts();
        model.addAttribute("posts", posts);
        String a = "redirect:/posts?id=" + id;
        return a;
    }

    @GetMapping("/search")
    public String searchPost(@RequestParam String title, Model model) throws ExecutionException, InterruptedException {
        List<Post> posts = postService.searchByTitle(title);
        model.addAttribute("posts", posts);
        return "postList";
    }
}
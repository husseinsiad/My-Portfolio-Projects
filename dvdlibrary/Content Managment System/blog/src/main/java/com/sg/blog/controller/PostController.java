/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.controller;

import com.sg.blog.data.CategoryDao;
import com.sg.blog.data.PostDao;
import com.sg.blog.data.UserDao;
import com.sg.blog.model.Category;
import com.sg.blog.model.Post;
import com.sg.blog.model.User;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author hause
 */
@Controller
public class PostController {

    @Autowired
    UserDao userdao;
    @Autowired
    PostDao postdao;
    @Autowired
    CategoryDao categorydao;
    

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    
    @GetMapping("index")
    public String index(Model model) {
        List<Post> posts = postdao.findAll();
        List<Post> truePosts = new ArrayList<Post>();
        for(Post post : posts){
            if(post.isStatus()){
                truePosts.add(post);
            }
        }
        List<Category> categories = categorydao.findAll();
        model.addAttribute("post", truePosts);
        model.addAttribute("category", categories);
        return "index";
    }

    @GetMapping("post")
    public String post(Model model ) {
        List<Category> category = categorydao.findAll();
        model.addAttribute("category", category);
        return "post";
    }

    @GetMapping("showPost")
    public String showpost(Integer id, Model model) {
        Post posts = postdao.findById(id).orElse(null);
        List<Category> categories = categorydao.findAll();
        model.addAttribute("post", posts);
        model.addAttribute("category", categories);

        return "show";
    }

    @PostMapping("post")
    public String addPost(Post post, HttpServletRequest request,Principal userInfo) {
        String[] category = request.getParameterValues("categoryid");
        User user = userdao.findByUsername(userInfo.getName());
        //change
        
        LocalDateTime date;
        date = LocalDateTime.now();
        List<Category> categories = new ArrayList<>();
        for (String Categoryid : category) {
            categories.add(categorydao.findById(Integer.parseInt(Categoryid)).orElse(null));
        }
        post.setCategories(categories);
        post.setPostdate(date);
        post.setUser(user);
        postdao.save(post);
        return "redirect:/index";
    }

    @GetMapping("updatePost")
    public String updatePost(Model model, Integer id) {
        Post posts = postdao.findById(id).orElse(null);
        Category category = categorydao.findById(id).orElse(null);
        model.addAttribute("category", category);
        model.addAttribute("post", posts);
        return "UpdatePost";
    }

    @PostMapping("updatePost")
    public String performUpdatePost(Post post, Principal userInfo) {
        User user = userdao.findByUsername(userInfo.getName());
        LocalDateTime date;
        date = LocalDateTime.now();
        post.setPostdate(date);
        post.setUser(user);
        postdao.save(post);
        return "redirect:/index";
    }



    @GetMapping("category")
    public String category() {
        // post
        return "category";
    }

    @PostMapping("Category")
    public String registerCategory(Category category) {
        categorydao.save(category);
        return "redirect:/post";
    }

    @GetMapping("deletePost")
    public String deletePost(int id) {
        postdao.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("postsByCategory")
    public String getPostsByCategory(Integer id, Model model) {
        Category category = categorydao.findById(id).orElse(null);
        List<Post> posts = category.getPost();
        List<Category> allCategories = categorydao.findAll();
        model.addAttribute("post", posts);
        model.addAttribute("categories", allCategories);
        model.addAttribute("category", category);

        return "postsByCategory";
    }

    @GetMapping("postsBySearch")
    public String getPostsBySearch(HttpServletRequest request, Model model) {

        List<Post> posts = postdao.findByPostContaining(request.getParameter("hashtag"));
        List<Category> allCategories = categorydao.findAll();
        model.addAttribute("post", posts);
        model.addAttribute("categories", allCategories);
//        model.addAttribute("search", search);

        return "postBySearch";
    }

}

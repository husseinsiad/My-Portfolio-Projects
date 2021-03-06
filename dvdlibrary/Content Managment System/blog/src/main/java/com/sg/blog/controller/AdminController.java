/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.controller;

import com.sg.blog.data.CategoryDao;
import com.sg.blog.data.PostDao;
import com.sg.blog.data.RoleDao;
import com.sg.blog.data.UserDao;
import com.sg.blog.model.Post;
import com.sg.blog.model.Role;
import com.sg.blog.model.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author siyaa
 */
@Controller
public class AdminController {

    @Autowired
    UserDao users;

    @Autowired
    RoleDao roles;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PostDao postdao;

    @Autowired
    CategoryDao categorydao;

    @GetMapping("/admin")
    public String displayAdminPage(Model model) {
        model.addAttribute("users", users.findAll());
        return "admin";
    }

    @PostMapping("/addUser")
    public String addUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setEnabled(true);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roles.findByRole("ROLE_USER"));
        user.setRoles(userRoles);

        users.save(user);

        return "redirect:/admin";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(Integer id) {
        users.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/editUser")
    public String editUserAction(String[] roleIdList, Boolean enabled, Integer id) {
        User user = users.findById(id).orElse(null);
        if (enabled != null) {
            user.setEnabled(enabled);
        } else {
            user.setEnabled(false);
        }

        Set<Role> roleList = new HashSet<>();
        for (String roleId : roleIdList) {
            Role role = roles.findById(Integer.parseInt(roleId)).orElse(null);
            roleList.add(role);
        }
        user.setRoles(roleList);
        users.save(user);

        return "redirect:/admin";
    }

    @PostMapping("editPassword")
    public String editPassword(Integer id, String password, String confirmPassword) {
        User user = users.findById(id).orElse(null);

        if (password.equals(confirmPassword)) {
            user.setPassword(encoder.encode(password));
            users.save(user);
            return "redirect:/admin";
        } else {
            return "redirect:/editUser?id=" + id + "&error=1";
        }
    }

    @GetMapping("/editUser")
    public String editUserDisplay(Model model, Integer id, Integer error) {
        User user = users.findById(id).orElse(null);
        List roleList = roles.findAll();

        model.addAttribute("user", user);
        model.addAttribute("roles", roleList);

        if (error != null) {
            if (error == 1) {
                model.addAttribute("error", "Passwords did not match, password was not updated.");
            }
        }

        return "editUser";
    }

    @GetMapping("pendingPost")
    public String getPendingPost(Model model) {
        List<Post> post = users.findPendingPost();
        model.addAttribute("post", post);
        return "pendingPost";
    }

    @PostMapping("approvePost")
    public String approvePendingPost(Integer id) {
        Post toUpdate = postdao.findById(id).orElse(null);
        toUpdate.setStatus(true);
        postdao.save(toUpdate);
        return "redirect:/pendingPost";
    }
}

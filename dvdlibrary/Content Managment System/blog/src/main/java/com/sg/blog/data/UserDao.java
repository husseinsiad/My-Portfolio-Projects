/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.data;

import com.sg.blog.model.Post;
import com.sg.blog.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author siyaa
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    @Query("select p from post p where p.status=0")     
    List<Post> findPendingPost();
}

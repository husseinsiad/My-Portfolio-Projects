/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.blog.data;

import com.sg.blog.model.Category;
import com.sg.blog.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hause
 */
@Repository
public interface PostDao extends JpaRepository<Post, Integer>{
    
    @Query("select p from post p where p.content like %?1%")
    List<Post> findByPostContaining(String contains);
    
}

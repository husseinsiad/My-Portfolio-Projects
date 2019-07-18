/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courseManager.CourseManager.Data;

import com.courseManager.CourseManager.Model.Teacher;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface TeacherDao {
    Teacher getTeacherById(int id);
    List<Teacher> getAllTeacher();
    Teacher addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(int id);
}

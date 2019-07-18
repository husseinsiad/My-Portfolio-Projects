/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courseManager.CourseManager.Data;

import com.courseManager.CourseManager.Model.Student;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface StudentDao {
    Student getStudentById(int id);
    List<Student> getAllStudent();
    Student addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(int id);
}

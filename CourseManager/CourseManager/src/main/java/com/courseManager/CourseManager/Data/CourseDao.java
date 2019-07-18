/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courseManager.CourseManager.Data;

import com.courseManager.CourseManager.Model.Course;
import com.courseManager.CourseManager.Model.Student;
import com.courseManager.CourseManager.Model.Teacher;
import java.util.List;

/**
 *
 * @author siyaa
 */
public interface CourseDao {
    Course getCourseById(int id);
    List<Course> getAllCourses();
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(int id);
    
    List<Course> getCoursesForTeacher(Teacher teacher);
    List<Course> getCoursesForStudent(Student student);
    
}

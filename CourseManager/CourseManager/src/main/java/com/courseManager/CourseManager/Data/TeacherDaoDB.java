/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.courseManager.CourseManager.Data;

import com.courseManager.CourseManager.Model.Teacher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author siyaa
 */
@Repository
public class TeacherDaoDB implements TeacherDao {

    @Autowired
    JdbcTemplate Jdbc;

    @Override
    public Teacher getTeacherById(int id) {
        try {
            final String GET_TEACHER_BY_ID = "SELECT * FROM teacher WHERE id=?";
            return Jdbc.queryForObject(GET_TEACHER_BY_ID, new TeacherMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Teacher> getAllTeacher() {
        try {
            final String GET_ALL_TEACHERS = "SELECT * FROM teacher";
            return Jdbc.query(GET_ALL_TEACHERS, new TeacherMapper());
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
         final String INSERT_TEACHER = "INSERT INTO teacher(firstName, lastName, specialty) " +
                "VALUES(?,?,?)";
        Jdbc.update(INSERT_TEACHER,
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getSpecialty());
        int newId = Jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        teacher.setId(newId);

        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) {
       final String UPDATE_TEACHER = "UPDATE teacher SET firstName = ?, lastName = ?, " +
                "specialty = ? WHERE id = ?";
        Jdbc.update(UPDATE_TEACHER,
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getSpecialty(),
                teacher.getId());
    }

    @Override
    @Transactional
    public void deleteTeacher(int id) {
          final String DELETE_COURSE_STUDENT = "DELETE cs.* FROM course_student cs "
                + "JOIN course c ON cs.courseId = c.Id WHERE c.teacherId = ?";
        Jdbc.update(DELETE_COURSE_STUDENT, id);
        
        final String DELETE_COURSE = "DELETE FROM course WHERE teacherId = ?";
        Jdbc.update(DELETE_COURSE, id);
        
        final String DELETE_TEACHER = "DELETE FROM teacher WHERE id = ?";
        Jdbc.update(DELETE_TEACHER, id);
    }

    public static final class TeacherMapper implements RowMapper<Teacher> {

        @Override
        public Teacher mapRow(ResultSet rs, int index) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setFirstName(rs.getString("firstName"));
            teacher.setLastName(rs.getString("lastName"));
            teacher.setSpecialty(rs.getString("specialty"));

            return teacher;
        }
    }

}

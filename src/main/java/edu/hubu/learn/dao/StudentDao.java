package edu.hubu.learn.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.hubu.learn.entity.Student;

public interface StudentDao extends JpaRepository<Student, Long> {

}
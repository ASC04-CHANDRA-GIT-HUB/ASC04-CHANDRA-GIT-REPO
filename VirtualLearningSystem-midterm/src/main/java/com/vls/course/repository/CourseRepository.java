package com.vls.course.repository;

import com.vls.course.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseModel, Integer> {
}

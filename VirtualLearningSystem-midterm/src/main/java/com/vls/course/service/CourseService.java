package com.vls.course.service;

import com.vls.course.model.CourseModel;
import com.vls.course.repository.CourseRepository;
import com.vls.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<CourseModel> getAllCourses() {
        return courseRepository.findAll();
    }

    public CourseModel getCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with ID " + id));
    }

    public CourseModel addCourse(CourseModel course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Integer id) {
        courseRepository.deleteById(id);
    }
}

package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessionByCourseId(courseId);
        return list;
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public void saveSection(CourseSection courseSection) {
        // 补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);

        // 执行插入
        courseContentMapper.saveSection(courseSection);
    }

    @Override
    public void updateSection(CourseSection courseSection) {
        // 1.补全信息
        courseSection.setUpdateTime(new Date());

        // 2.执行courseContentMapper的方法
        courseContentMapper.updateSection(courseSection);
    }

    @Override
    public void updateSectionStatus(int id, int status) {
        // 1.补全信息
        Date date = new Date();
        CourseSection section = new CourseSection();
        section.setUpdateTime(date);
        section.setStatus(status);
        section.setId(id);

        // 2.调用mapper
        courseContentMapper.updateSectionStatus(section);
    }

    @Override
    public void saveLesson(CourseLesson courseLesson) {
        // 1.补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        // 2.调用mapper
        courseContentMapper.saveLesson(courseLesson);
    }

    @Override
    public void updateLesson(CourseLesson courseLesson) {
        // 1.补全信息
        Date date = new Date();
        courseLesson.setUpdateTime(date);

        // 2.调用mapper
        courseContentMapper.updateLesson(courseLesson);
    }

}

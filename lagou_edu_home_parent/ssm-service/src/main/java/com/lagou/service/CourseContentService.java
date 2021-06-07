package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {
    /*
        根据课程id查询章节和课时信息
     */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /*
        根据课程id回显章节对应的课程信息
     */
    public Course findCourseByCourseId(int courseId);

    /*
        新增章节信息
     */
    public void saveSection(CourseSection courseSection);

    /*
        更新章节信息
     */
    public void updateSection(CourseSection courseSection);

    /*
        修改章节状态
     */
    public void updateSectionStatus(int id, int status);

    /*
        新建课时
     */
    public void saveLesson(CourseLesson courseLesson);

    /*
        修改课时
     */
    public void updateLesson(CourseLesson courseLesson);
}

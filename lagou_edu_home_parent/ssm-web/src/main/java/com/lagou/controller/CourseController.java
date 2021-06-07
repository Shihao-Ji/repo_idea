package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 查询课程信息&条件查询 接口
     * */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO){
        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", courseList);
        System.out.println("成功");
        return responseResult;
    }

    /*
        课程图片上传
     */
    @RequestMapping("/courseUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException { // 参数名必须与key值一样

        // 1.判断接收到的上传文件的字符串是否为空
        if (file.isEmpty()) {
            throw new RuntimeException();
        }

        // 2.获取项目部署路径
        // D:\EV\apache-tomcat-8.5.55\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\EV\apache-tomcat-8.5.55\webapps
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        // 3.获取上传时的原文件名
        // lagou.jpg
        String originalFilename = file.getOriginalFilename();

        // 4.避免文件名重复，生成新文件名
        // 12421321.jpg
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.indexOf("."));

        // 5.文件上传
        String uploadPath = substring + "upload\\";
        File uploadFile = new File(uploadPath, newFileName);

        // 如果目录不存在就创建目录
        if (!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
            System.out.println("创建目录: " + uploadFile);
        }

        // 图片就进行了真正的上传
        file.transferTo(uploadFile);

        // 6.将文件名和文件路径返回，进行响应
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        // "filePath" : "http://localhost:8080/upload/1597112871741.jpg"
        map.put("filePath", "http://localhost:8080/upload/" + newFileName);

        ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

        return responseResult;
    }

    /*
        新增课程信息及讲师信息
        新增课程信息和修改课程信息要写在同一个方法中
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO) throws InvocationTargetException, IllegalAccessException {
        if (null == courseVO.getId()) {
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "添加成功", null);
            return responseResult;
        } else {
            //调用service
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true, 200, "修改成功", null);
            return responseResult;
        }
    }

    /*
        根据id查询具体的课程信息及关联的讲师信息
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(int id){
        CourseVO courseVO = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "根据ID查询课程成功", courseVO);
        return responseResult;
    }

    /*
        课程状态管理
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(int id, int status){
        // 调用service,传递参数,完成课程状态变更
        courseService.updateCourseStatus(id,status);
        // 响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "课程状态变更成功", map);
        return responseResult;
    }
}

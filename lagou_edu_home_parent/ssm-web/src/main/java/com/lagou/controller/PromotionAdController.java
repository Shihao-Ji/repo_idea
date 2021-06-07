package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
        广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        return new ResponseResult(true,200,"广告分页查询成功",pageInfo);
    }

    /*
        广告图片上传
     */
    @RequestMapping("/PromotionAdUpload")
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
        新增或更新广告信息
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if (promotionAd.getId() == null) {
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true,200,"新增广告成功",null);
        } else {
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true,200,"更新广告成功",null);
        }
    }

    /*
        根据ID回显广告信息
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true,200,"根据id回显广告信息成功",promotionAd);
    }

    /*
        动态上下线广告
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id, int status){
        promotionAdService.updatePromotionAdStatus(id,status);
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"动态上下线广告成功",map);
    }
}

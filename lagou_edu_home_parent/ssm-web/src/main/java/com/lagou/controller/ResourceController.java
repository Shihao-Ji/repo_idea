package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /*
        分页条件查询资源信息
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO){
        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVO);
        return new ResponseResult(true,200,"分页条件查询资源信息成功",pageInfo);
    }

    /*
        添加或更新资源信息
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if (resource.getId() != null){
            // 修改资源
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改资源信息成功",null);
        } else {
            // 添加资源
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"新增资源信息成功",null);
        }
    }

    /*
        根据id删除资源
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){
        resourceService.deleteResource(id);
        return new ResponseResult(true,200,"删除资源信息成功",null);
    }
}

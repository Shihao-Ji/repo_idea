package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceCategoryVO;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /*
        查询所有资源分类
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> allResourceCategory = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"查询所有资源分类成功",allResourceCategory);
    }

    /*
        新增或更新资源分类信息
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategoryVO resourceCategoryVO){
        if (resourceCategoryVO.getId() != null) {
            resourceCategoryService.updateResourceCategory(resourceCategoryVO);
            return new ResponseResult(true,200,"更新资源分类信息成功",null);
        } else {
            resourceCategoryService.saveResourceCategory(resourceCategoryVO);
            return new ResponseResult(true,200,"添加资源分类信息成功",null);
        }
    }

    /*
        删除资源分类信息
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(int id){
        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(true,200,"删除资源分类信息成功",null);
    }
}

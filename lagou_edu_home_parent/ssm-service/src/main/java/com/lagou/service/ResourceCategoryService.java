package com.lagou.service;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceCategoryVO;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceCategoryService {

    /*
        查询所有资源分类
     */
    public List<ResourceCategory> findAllResourceCategory();

    /*
        添加资源分类
     */
    public void saveResourceCategory(ResourceCategoryVO resourceCategoryVO);

    /*
        更新资源分类
     */
    public void updateResourceCategory(ResourceCategoryVO resourceCategoryVO);

    /*
        删除资源分类
     */
    public void deleteResourceCategory(Integer id);

}

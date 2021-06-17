package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceMapper {

    /*
        分页查询所有资源
     */
    public List<Resource> findAllResource(ResourceVO resourceVO);

    /*
        添加资源信息
     */
    public void saveResource(Resource resource);

    /*
        修改资源信息
     */
    public void updateResource(Resource resource);

    /*
        根据id删除资源
     */
    public void deleteResource(int id);
}

package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO) {
        // 分页查询
        PageHelper.startPage(resourceVO.getCurrentPage(),resourceVO.getPageSize());
        List<Resource> allResource = resourceMapper.findAllResource(resourceVO);

        PageInfo<Resource> pageInfo = new PageInfo<>(allResource);

        return pageInfo;
    }

    @Override
    public void saveResource(Resource resource) {
        // 补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        resourceMapper.saveResource(resource);
    }

    @Override
    public void updateResource(Resource resource) {
        // 补全信息
        Date date = new Date();
        resource.setUpdatedTime(date);
        resource.setUpdatedBy("system");

        resourceMapper.updateResource(resource);
    }

    @Override
    public void deleteResource(int id) {
        resourceMapper.deleteResource(id);
    }
}

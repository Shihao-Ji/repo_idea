package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceService {

    /*
        分页查询所有资源
     */
    public PageInfo<Resource> findAllResource(ResourceVO resourceVO);

}

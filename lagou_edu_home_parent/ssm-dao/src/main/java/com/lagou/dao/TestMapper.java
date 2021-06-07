package com.lagou.dao;

import com.lagou.domain.Test;

import java.util.List;

public interface TestMapper {

    /*
        查询test表的所有数据
     */
    public List<Test> findAllTest();

}

package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

import java.util.List;

public interface PromotionAdService {

    /*
        分页查询，参数传递前端传过来的当前页和每页条数的表现层对象
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

    /*
        新增广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /*
        根据id回显广告信息
     */
    public PromotionAd findPromotionAdById(int id);

    /*
        修改广告信息
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /*
        广告动态上下线
     */
    public void updatePromotionAdStatus(int id, int status);
}

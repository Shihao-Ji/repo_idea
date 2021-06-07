package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /*
        分页查询
     */
    public List<PromotionAd> findAllPromotionAdByPage();

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
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}

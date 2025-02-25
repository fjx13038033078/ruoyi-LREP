package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Purchase;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 8:51
 */
@Mapper
public interface PurchaseMapper {
    /**
     * 获取所有课程购买记录
     *
     * @return 所有课程购买记录的列表
     */
    List<Purchase> getAllPurchases();

    /**
     * 根据购买记录ID获取课程购买记录
     *
     * @param purchaseId 购买记录ID
     * @return 课程购买记录详情
     */
    Purchase getPurchaseById(Long purchaseId);

    /**
     * 添加课程购买记录
     *
     * @param purchase 课程购买记录对象
     * @return 影响的行数
     */
    int addPurchase(Purchase purchase);

    /**
     * 更新课程购买记录
     *
     * @param purchase 课程购买记录对象
     * @return 影响的行数
     */
    int updatePurchase(Purchase purchase);

    /**
     * 删除课程购买记录
     *
     * @param purchaseId 购买记录ID
     * @return 影响的行数
     */
    int deletePurchase(Long purchaseId);
}

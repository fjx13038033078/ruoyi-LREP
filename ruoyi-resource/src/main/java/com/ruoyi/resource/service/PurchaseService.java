package com.ruoyi.resource.service;

import com.ruoyi.resource.domain.Purchase;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:01
 */
public interface PurchaseService {
    /**
     * 获取所有课程购买记录
     *
     * @return 购买记录列表
     */
    List<Purchase> getAllPurchases();

    /**
     * 根据购买记录ID获取课程购买记录
     *
     * @param purchaseId 购买记录ID
     * @return 购买记录详情
     */
    Purchase getPurchaseById(Long purchaseId);

    /**
     * 添加课程购买记录
     *
     * @param purchase 待添加的购买记录信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addPurchase(Purchase purchase);

    /**
     * 更新课程购买记录信息
     *
     * @param purchase 待更新的购买记录信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updatePurchase(Purchase purchase);

    /**
     * 删除课程购买记录
     *
     * @param purchaseId 待删除的购买记录ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deletePurchase(Long purchaseId);
}

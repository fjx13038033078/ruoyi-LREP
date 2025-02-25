package com.ruoyi.resource.service.impl;

import com.ruoyi.resource.domain.Purchase;
import com.ruoyi.resource.mapper.PurchaseMapper;
import com.ruoyi.resource.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseMapper purchaseMapper;

    /**
     * 获取所有课程购买记录
     *
     * @return 购买记录列表
     */
    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseMapper.getAllPurchases();
    }

    /**
     * 根据购买记录ID获取课程购买记录
     *
     * @param purchaseId 购买记录ID
     * @return 购买记录详情
     */
    @Override
    public Purchase getPurchaseById(Long purchaseId) {
        return purchaseMapper.getPurchaseById(purchaseId);
    }

    /**
     * 添加课程购买记录
     *
     * @param purchase 购买记录信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addPurchase(Purchase purchase) {
        int rows = purchaseMapper.addPurchase(purchase);
        return rows > 0;
    }

    /**
     * 更新课程购买记录信息
     *
     * @param purchase 购买记录信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updatePurchase(Purchase purchase) {
        int rows = purchaseMapper.updatePurchase(purchase);
        return rows > 0;
    }

    /**
     * 删除课程购买记录
     *
     * @param purchaseId 待删除的购买记录ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deletePurchase(Long purchaseId) {
        int rows = purchaseMapper.deletePurchase(purchaseId);
        return rows > 0;
    }
}

package com.ruoyi.resource.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.resource.domain.Course;
import com.ruoyi.resource.domain.Favorite;
import com.ruoyi.resource.domain.Purchase;
import com.ruoyi.resource.mapper.PurchaseMapper;
import com.ruoyi.resource.service.PurchaseService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:05
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseMapper purchaseMapper;

    private final CourseServiceImpl courseService;

    private final ISysUserService iSysUserService;

    private final ISysRoleService iSysRoleService;

    /**
     * 获取所有课程购买记录
     *
     * @return 购买记录列表
     */
    @Override
    public List<Purchase> getAllPurchases() {
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equals("admin")) {
            startPage();
            List<Purchase> allPurchases = purchaseMapper.getAllPurchases();
            fillPurchaseInfo(allPurchases);
            return allPurchases;
        } else {
            startPage();
            List<Purchase> purchasesByUserId = purchaseMapper.getPurchasesByUserId(userId);
            fillPurchaseInfo(purchasesByUserId);
            return purchasesByUserId;
        }
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
        Long userId = SecurityUtils.getUserId();
        if (purchaseMapper.findPurchaseByUserIdAndCourseId(userId, purchase.getCourseId()) != null) {
            throw new RuntimeException("您已购买该课程，无需重复购买！");
        } else {
            purchase.setUserId(userId);
            purchase.setPurchaseTime(LocalDateTime.now());
            int rows = purchaseMapper.addPurchase(purchase);
            return rows > 0;
        }
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

    /**
     * 填充购买记录信息
     *
     * @param purchases 购买记录列表
     */
    private void fillPurchaseInfo(List<Purchase> purchases) {
        for (Purchase purchase : purchases) {
            Long courseId = purchase.getCourseId();
            Course course = courseService.getCourseById(courseId);
            purchase.setCourseName(course.getCourseName());
            Long userId = purchase.getUserId();
            String username = iSysUserService.selectUserById(userId).getUserName();
            purchase.setUsername(username);
        }
    }
}

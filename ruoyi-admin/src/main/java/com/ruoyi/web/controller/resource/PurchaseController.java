package com.ruoyi.web.controller.resource;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.resource.domain.Purchase;
import com.ruoyi.resource.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:34
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/lerp/purchase")
public class PurchaseController extends BaseController {

    private final PurchaseService purchaseService;

    /**
     * 获取所有购买记录列表
     *
     * @return 购买记录列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllPurchases() {
        startPage();
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return getDataTable(purchases);
    }

    /**
     * 根据购买记录ID获取购买记录详情
     *
     * @param purchaseId 购买记录ID
     * @return 购买记录详情
     */
    @GetMapping("/get")
    public AjaxResult getPurchaseById(@RequestParam Long purchaseId) {
        return success(purchaseService.getPurchaseById(purchaseId));
    }

    /**
     * 添加购买记录
     *
     * @param purchase 购买记录对象
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult addPurchase(@RequestBody Purchase purchase) {
        return toAjax(purchaseService.addPurchase(purchase));
    }

    /**
     * 更新购买记录信息
     *
     * @param purchase 购买记录对象
     * @return 操作结果
     */
    @PostMapping("/update")
    public AjaxResult updatePurchase(@RequestBody Purchase purchase) {
        return toAjax(purchaseService.updatePurchase(purchase));
    }

    /**
     * 删除购买记录
     *
     * @param purchaseId 购买记录ID
     * @return 操作结果
     */
    @GetMapping("/delete")
    public AjaxResult deletePurchase(@RequestParam Long purchaseId) {
        return toAjax(purchaseService.deletePurchase(purchaseId));
    }
}

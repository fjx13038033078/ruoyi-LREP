package com.ruoyi.web.controller.resource;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.resource.domain.Favorite;
import com.ruoyi.resource.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:33
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/lerp/favorite")
public class FavoriteController extends BaseController {

    private final FavoriteService favoriteService;

    /**
     * 获取所有收藏列表
     *
     * @return 收藏列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllFavorites() {
        startPage();
        List<Favorite> favorites = favoriteService.getAllFavorites();
        return getDataTable(favorites);
    }

    /**
     * 根据收藏ID获取收藏详情
     *
     * @param favoriteId 收藏ID
     * @return 收藏详情
     */
    @GetMapping("/get")
    public AjaxResult getFavoriteById(@RequestParam Long favoriteId) {
        return success(favoriteService.getFavoriteById(favoriteId));
    }

    /**
     * 添加收藏
     *
     * @param favorite 收藏对象
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult addFavorite(@RequestBody Favorite favorite) {
        return toAjax(favoriteService.addFavorite(favorite));
    }

    /**
     * 更新收藏信息
     *
     * @param favorite 收藏对象
     * @return 操作结果
     */
    @PostMapping("/update")
    public AjaxResult updateFavorite(@RequestBody Favorite favorite) {
        return toAjax(favoriteService.updateFavorite(favorite));
    }

    /**
     * 删除收藏
     *
     * @param favoriteId 收藏ID
     * @return 操作结果
     */
    @GetMapping("/delete")
    public AjaxResult deleteFavorite(@RequestParam Long favoriteId) {
        return toAjax(favoriteService.deleteFavorite(favoriteId));
    }
}

package com.ruoyi.resource.service;

import com.ruoyi.resource.domain.Favorite;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:00
 */
public interface FavoriteService {
    /**
     * 获取所有收藏
     *
     * @return 收藏列表
     */
    List<Favorite> getAllFavorites();

    /**
     * 根据收藏ID获取收藏详情
     *
     * @param favoriteId 收藏ID
     * @return 收藏详情
     */
    Favorite getFavoriteById(Long favoriteId);

    /**
     * 添加收藏
     *
     * @param favorite 待添加的收藏信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addFavorite(Favorite favorite);

    /**
     * 更新收藏信息
     *
     * @param favorite 待更新的收藏信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateFavorite(Favorite favorite);

    /**
     * 删除收藏
     *
     * @param favoriteId 待删除的收藏ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteFavorite(Long favoriteId);
}

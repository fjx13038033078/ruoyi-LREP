package com.ruoyi.resource.service.impl;

import com.ruoyi.resource.domain.Favorite;
import com.ruoyi.resource.mapper.FavoriteMapper;
import com.ruoyi.resource.service.FavoriteService;
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
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteMapper favoriteMapper;

    /**
     * 获取所有收藏
     *
     * @return 收藏列表
     */
    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteMapper.getAllFavorites();
    }

    /**
     * 根据收藏ID获取收藏详情
     *
     * @param favoriteId 收藏ID
     * @return 收藏详情
     */
    @Override
    public Favorite getFavoriteById(Long favoriteId) {
        return favoriteMapper.getFavoriteById(favoriteId);
    }

    /**
     * 添加收藏
     *
     * @param favorite 收藏信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addFavorite(Favorite favorite) {
        int rows = favoriteMapper.addFavorite(favorite);
        return rows > 0;
    }

    /**
     * 更新收藏信息
     *
     * @param favorite 收藏信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateFavorite(Favorite favorite) {
        int rows = favoriteMapper.updateFavorite(favorite);
        return rows > 0;
    }

    /**
     * 删除收藏
     *
     * @param favoriteId 待删除的收藏ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteFavorite(Long favoriteId) {
        int rows = favoriteMapper.deleteFavorite(favoriteId);
        return rows > 0;
    }
}

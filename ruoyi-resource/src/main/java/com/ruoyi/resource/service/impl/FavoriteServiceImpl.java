package com.ruoyi.resource.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.resource.domain.Favorite;
import com.ruoyi.resource.mapper.FavoriteMapper;
import com.ruoyi.resource.service.FavoriteService;
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
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteMapper favoriteMapper;

    private final ResourceServiceImpl resourceService;

    private final ISysUserService iSysUserService;

    private final ISysRoleService iSysRoleService;

    /**
     * 获取所有收藏
     *
     * @return 收藏列表
     */
    @Override
    public List<Favorite> getAllFavorites() {
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equals("admin")) {
            startPage();
            List<Favorite> allFavorites = favoriteMapper.getAllFavorites();
            fillFavoriteDetails(allFavorites);
            return allFavorites;
        } else {
            startPage();
            List<Favorite> favorites = favoriteMapper.listFavoritesByUserId(userId);
            fillFavoriteDetails(favorites);
            return favorites;
        }
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
        Long userId = SecurityUtils.getUserId();
        favorite.setUserId(userId);
        Favorite existingFavorite = favoriteMapper.findFavoriteByUserIdAndResourceId(userId, favorite.getResourceId());
        if (existingFavorite != null) {
            throw new RuntimeException("您已收藏该资源，无需重复收藏！");
        } else {
            favorite.setFavoriteTime(LocalDateTime.now());
            int rows = favoriteMapper.addFavorite(favorite);
            return rows > 0;
        }
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

    /**
     * 填充收藏详情，包括资源名称和用户名
     *
     * @param favorites 收藏列表
     */
    private void fillFavoriteDetails(List<Favorite> favorites) {
        for (Favorite favorite : favorites) {
            Long resourceId = favorite.getResourceId();
            favorite.setResourceName(resourceService.getResourceById(resourceId).getResourceName());
            favorite.setUserName(iSysUserService.selectUserById(favorite.getUserId()).getNickName());
        }
    }
}

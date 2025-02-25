package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Favorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 8:51
 */
@Mapper
public interface FavoriteMapper {
    /**
     * 获取所有收藏
     *
     * @return 所有收藏的列表
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
     * @param favorite 收藏对象
     * @return 影响的行数
     */
    int addFavorite(Favorite favorite);

    /**
     * 更新收藏
     *
     * @param favorite 收藏对象
     * @return 影响的行数
     */
    int updateFavorite(Favorite favorite);

    /**
     * 删除收藏
     *
     * @param favoriteId 收藏ID
     * @return 影响的行数
     */
    int deleteFavorite(Long favoriteId);
}

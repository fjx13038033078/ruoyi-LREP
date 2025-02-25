package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * 根据用户ID获取收藏列表
     *
     * @param userId 用户ID
     * @return 收藏列表
     */
    List<Favorite> listFavoritesByUserId(Long userId);

    /**
     * 根据用户ID和资源ID查找收藏
     *
     * @param userId     用户ID
     * @param resourceId 资源ID
     * @return 收藏对象
     */
    Favorite findFavoriteByUserIdAndResourceId(@Param("userId") Long userId, @Param("resourceId") Long resourceId);

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

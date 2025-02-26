package com.ruoyi.resource.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.resource.domain.Remark;
import com.ruoyi.resource.mapper.RemarkMapper;
import com.ruoyi.resource.service.RemarkService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/25 9:03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RemarkServiceImpl implements RemarkService {

    private final RemarkMapper remarkMapper;

    private final ISysUserService sysUserService;

    /**
     * 获取所有评论
     *
     * @return 评论列表
     */
    @Override
    public List<Remark> getAllRemarks() {
        List<Remark> allRemarks = remarkMapper.getAllRemarks();
        return allRemarks;
    }

    /**
     * 根据资源ID获取评论列表
     *
     * @param resourceId 资源ID
     * @return 评论列表
     */
    @Override
    public List<Remark> listRemarksByResourceId(Long resourceId) {
        List<Remark> remarks = remarkMapper.listRemarksByResourceId(resourceId);
        for (Remark remark : remarks) {
            Long userId = remark.getUserId();
            String nickName = sysUserService.selectUserById(userId).getNickName();
            remark.setUsername(nickName);
        }
        return remarks;
    }

    /**
     * 添加评论
     *
     * @param remark 评论信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addRemark(Remark remark) {
        remark.setType(1);
        Long userId = SecurityUtils.getUserId();
        remark.setUserId(userId);
        remark.setRemarkTime(LocalDateTime.now());
        int rows = remarkMapper.addRemark(remark);
        return rows > 0;
    }

    /**
     * 删除评论
     *
     * @param remarkId 待删除的评论ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteRemark(Long remarkId) {
        Long userId = SecurityUtils.getUserId();
        Remark remark = remarkMapper.getRemarkById(remarkId);
        if (remark != null && (userId == remark.getUserId() || userId == 1)) {
            int rows = remarkMapper.deleteRemark(remarkId);
            return rows > 0;
        } else {
            throw new RuntimeException("仅可以删除自己的评论");
        }
    }
}

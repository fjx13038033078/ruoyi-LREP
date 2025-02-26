package com.ruoyi.resource.mapper;

import com.ruoyi.resource.domain.Note;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/26 9:03
 */
@Mapper
public interface NoteMapper {
    /**
     * 获取所有笔记
     *
     * @return 所有笔记的列表
     */
    List<Note> getAllNotes();

    /**
     * 根据笔记ID获取笔记详情
     *
     * @param noteId 笔记ID
     * @return 笔记详情
     */
    Note getNoteById(Long noteId);

    /**
     * 添加笔记
     *
     * @param note 笔记对象
     * @return 影响的行数
     */
    int addNote(Note note);

    /**
     * 更新笔记
     *
     * @param note 笔记对象
     * @return 影响的行数
     */
    int updateNote(Note note);

    /**
     * 删除笔记
     *
     * @param noteId 笔记ID
     * @return 影响的行数
     */
    int deleteNote(Long noteId);
}

package com.ruoyi.resource.service;

import com.ruoyi.resource.domain.Note;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/26 9:04
 */
public interface NoteService {

    /**
     * 获取所有笔记
     *
     * @return 笔记列表
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
     * @param note 待添加的笔记信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addNote(Note note);

    /**
     * 更新笔记信息
     *
     * @param note 待更新的笔记信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateNote(Note note);

    /**
     * 删除笔记
     *
     * @param noteId 待删除的笔记ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteNote(Long noteId);
}

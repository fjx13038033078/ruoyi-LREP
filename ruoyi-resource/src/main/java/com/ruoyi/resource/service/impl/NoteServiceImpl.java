package com.ruoyi.resource.service.impl;

import com.ruoyi.resource.domain.Note;
import com.ruoyi.resource.mapper.NoteMapper;
import com.ruoyi.resource.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/26 9:04
 */
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;

    /**
     * 获取所有笔记
     *
     * @return 笔记列表
     */
    @Override
    public List<Note> getAllNotes() {
        return noteMapper.getAllNotes();
    }

    /**
     * 根据笔记ID获取笔记详情
     *
     * @param noteId 笔记ID
     * @return 笔记详情
     */
    @Override
    public Note getNoteById(Long noteId) {
        return noteMapper.getNoteById(noteId);
    }

    /**
     * 添加笔记
     *
     * @param note 笔记信息
     * @return 添加成功返回 true，否则返回 false
     */
    @Override
    public boolean addNote(Note note) {
        int rows = noteMapper.addNote(note);
        return rows > 0;
    }

    /**
     * 更新笔记信息
     *
     * @param note 笔记信息
     * @return 更新成功返回 true，否则返回 false
     */
    @Override
    public boolean updateNote(Note note) {
        int rows = noteMapper.updateNote(note);
        return rows > 0;
    }

    /**
     * 删除笔记
     *
     * @param noteId 待删除的笔记ID
     * @return 删除成功返回 true，否则返回 false
     */
    @Override
    public boolean deleteNote(Long noteId) {
        int rows = noteMapper.deleteNote(noteId);
        return rows > 0;
    }

}

package com.ruoyi.web.controller.resource;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.resource.domain.Note;
import com.ruoyi.resource.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/2/26 9:06
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/lerp/note")
public class NoteController extends BaseController {

    private final NoteService noteService;

    /**
     * 获取所有笔记列表
     *
     * @return 笔记列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllNotes() {
        startPage();
        List<Note> notes = noteService.getAllNotes();
        return getDataTable(notes);
    }

    /**
     * 根据笔记ID获取笔记详情
     *
     * @param noteId 笔记ID
     * @return 笔记详情
     */
    @GetMapping("/get")
    public AjaxResult getNoteById(@RequestParam Long noteId) {
        return success(noteService.getNoteById(noteId));
    }

    /**
     * 添加笔记
     *
     * @param note 笔记对象
     * @return 操作结果
     */
    @PostMapping("/add")
    public AjaxResult addNote(@RequestBody Note note) {
        return toAjax(noteService.addNote(note));
    }

    /**
     * 更新笔记信息
     *
     * @param note 笔记对象
     * @return 操作结果
     */
    @PostMapping("/update")
    public AjaxResult updateNote(@RequestBody Note note) {
        return toAjax(noteService.updateNote(note));
    }

    /**
     * 删除笔记
     *
     * @param noteId 笔记ID
     * @return 操作结果
     */
    @GetMapping("/delete")
    public AjaxResult deleteNote(@RequestParam Long noteId) {
        return toAjax(noteService.deleteNote(noteId));
    }
}

package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    // 显式构造器注入
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // 查询所有笔记（按创建时间倒序）
    public List<Note> getAllNotes() {
        return noteRepository.findAllByOrderByCreatedAtDesc();
    }

    // 按分类查询
    public List<Note> getByCategory(String category) {
        return noteRepository.findByCategory(category);
    }

    // 搜索
    public List<Note> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllNotes();
        }
        return noteRepository.search("%" + keyword + "%");
    }

    // 根据ID查询
    public Note getById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("笔记不存在，ID: " + id));
    }

    // 保存或更新
    public Note save(Note note) {
        return noteRepository.save(note);
    }

    // 删除
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    // 统计某个分类的笔记数量
    public long countByCategory(String category) {
        return noteRepository.countByCategory(category);
    }

    public Note getByTitle(String title) {
        return noteRepository.findByTitle(title)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("笔记不存在，标题: " + title));
    }


}

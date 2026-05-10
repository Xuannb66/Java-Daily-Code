package com.example.notes.controller;

import com.example.notes.entity.Note;
import com.example.notes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // 获取所有笔记（支持搜索和分类过滤）
    // GET /api/notes
    // GET /api/notes?keyword=xxx
    // GET /api/notes?category=xxx
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category) {
        List<Note> notes;
        if (keyword != null && !keyword.trim().isEmpty()) {
            notes = noteService.search(keyword);
        } else if (category != null && !category.isEmpty()) {
            notes = noteService.getByCategory(category);
        } else {
            notes = noteService.getAllNotes();
        }
        return ResponseEntity.ok(notes);
    }

    // 根据 ID 获取单条笔记
    // GET /api/notes/1
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        try {
            Note note = noteService.getById(id);
            return ResponseEntity.ok(note);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据分类获取笔记
    // GET /api/notes/category/Java
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Note>> getByCategory(@PathVariable String category) {
        List<Note> notes = noteService.getByCategory(category);
        return ResponseEntity.ok(notes);
    }

    // 新建笔记
    // POST /api/notes
    @PostMapping
    public ResponseEntity<Note> createNote(@Valid @RequestBody Note note) {
        Note saved = noteService.save(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 更新笔记
    // PUT /api/notes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @Valid @RequestBody Note note) {
        try {
            note.setId(id);
            Note updated = noteService.save(note);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 删除笔记
    // DELETE /api/notes/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        try {
            noteService.getById(id); // 先检查是否存在
            noteService.delete(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // 获取所有分类（辅助接口）
    // GET /api/notes/categories
    @GetMapping("/categories")
    public ResponseEntity<String[]> getCategories() {
        return ResponseEntity.ok(new String[]{"Java", "Spring", "数据库", "AI", "其他"});
    }
}

package com.example.notes.repository;

import com.example.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // 按创建时间倒序（新笔记排前面）
    List<Note> findAllByOrderByCreatedAtDesc();

    // 按分类查询
    List<Note> findByCategory(String category);

    List<Note> findByTitle(String title);

    long countByCategory(String category);

    // 模糊搜索标题和内容（% 由 Service 层传入）
    @Query("SELECT n FROM Note n WHERE n.title LIKE :keyword OR n.content LIKE :keyword")
    List<Note> search(@Param("keyword") String keyword);
}

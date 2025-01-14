package com.kb.exam.domain.post.repository;

import com.kb.exam.domain.post.entity.Comment;
import com.kb.exam.domain.post.vo.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @NativeQuery("""
            SELECT seq, content
            FROM COMMENT
            WHERE postSeq = :postSeq
            ORDER BY seq
            LIMIT :size OFFSET :offset
            """)
    List<CommentResponse> findAllByPostSeqAndSizeAndOffset(@Param("postSeq") long postSeq,@Param("size") int size, @Param("offset") int offset);
    void deleteAllByPostSeq(Long postSeq);
}

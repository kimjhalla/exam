package com.kb.exam.domain.post.repository;

import com.kb.exam.domain.post.entity.PostAttachFile;
import com.kb.exam.domain.post.vo.PostAttachFileResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostAttachFileRepository extends JpaRepository<PostAttachFile, Long> {

    @NativeQuery("""
            SELECT seq, fileName,  filePath
            FROM POST_ATTACH_FILE
            WHERE postSeq = :postSeq
            """)
    List<PostAttachFileResponse> findAllByPostSeq(@Param("postSeq") long postSeq);
    void deleteAllByPostSeq(long postSeq);
}

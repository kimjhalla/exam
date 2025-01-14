package com.kb.exam.domain.post.repository;

import com.kb.exam.domain.post.entity.PostAttachFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostAttachFileRepository extends JpaRepository<PostAttachFile, Long> {

    void deleteAllByPostSeq(long postSeq);
}

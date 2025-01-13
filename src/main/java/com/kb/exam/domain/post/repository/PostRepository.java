package com.kb.exam.domain.post.repository;

import com.kb.exam.domain.post.entity.Post;
import com.kb.exam.domain.post.vo.PostResponse;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    @NativeQuery(value = """
            SELECT seq, userSeq, title
            FROM POST
            ORDER BY seq
            LIMIT :size OFFSET :page * :size
            """)
    List<PostResponse> findAllByPageAndSize(int page, int size);
}
